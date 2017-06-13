/**
 * This file is part of NoteBLOX.
 *
 * NoteBLOX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NoteBLOX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with NoteBLOX.  If not, see <https://www.gnu.org/licenses/agpl-3.0.en.html>.
 */
package com.noteblox.restdude.service.impl;

import com.noteblox.restdude.model.*;
import com.noteblox.restdude.model.dto.Annotation;
import com.noteblox.restdude.model.mapper.NoteAnnotationMapper;
import com.noteblox.restdude.repository.NoteCommentRepository;
import com.noteblox.restdude.repository.NoteRepository;
import com.noteblox.restdude.repository.CaseTargetRepository;
import com.noteblox.restdude.service.NoteService;
import com.noteblox.restdude.service.WebsiteNotesAppService;
import com.noteblox.restdude.service.BloxService;
import com.restdude.domain.cases.model.CaseStatus;
import com.restdude.domain.cases.model.CaseWorkflow;
import com.restdude.domain.cases.model.dto.CaseCommenttInfo;
import com.restdude.domain.cases.model.enums.ContextVisibilityType;
import com.restdude.domain.cases.service.CaseStatusService;
import com.restdude.domain.cases.service.CaseWorkflowService;
import com.restdude.domain.cases.service.impl.AbstractCaseServiceImpl;
import com.restdude.domain.misc.model.Host;
import com.restdude.domain.users.model.User;
import com.restdude.domain.users.service.UserService;
import com.restdude.mdd.service.PersistableModelService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Named(NoteService.BEAN_ID)
public class NoteServiceImpl
        extends AbstractCaseServiceImpl<Note, NoteComment, NoteRepository>
        implements NoteService {

    public static final char INDEX_CHAR = '-';

    @Value("${restdude.baseurl}")
    private String baseUrl;

    private PersistableModelService<BillableAccount, String> billableAccountService;
    private UserService userService;
    private PersistableModelService<Host, String> hostService;
    private BloxService bloxService;
    private WebsiteNotesAppService websiteNotesAppService;
    private NoteService noteService;
    private CaseStatusService caseStatusService;
    protected NoteCommentRepository noteCommentRepository;
    protected CaseTargetRepository caseTargetRepository;
    private CaseWorkflowService caseWorkflowService;


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Annotation> findAnnotationsForUrl(String httpUrl){
        List<Annotation> annotations = null;
        try {
            URL url = new URL(httpUrl);

            // get the blox
            Optional<Blox> website = this.bloxService.findByUrl(url);
            if(website.isPresent()){
                String path = url.getPath();
                String welcomeFile = "/index.html";

                // index/welcome file?
                if(path.endsWith(welcomeFile)){
                    path = path.substring(0, path.length() - welcomeFile.length());
                }
                log.debug("findAnnotationsForUrl, note target path: {}", path);
                Optional<CaseTarget> target = this.caseTargetRepository.findByPathAndWebsite(path, website.get());

                if(target.isPresent()){
                    List<Note> notes = this.repository.findAnnotationsByTarget(target.get());
                    annotations = new ArrayList<>();
                    NoteAnnotationMapper mapper = NoteAnnotationMapper.INSTANCE;
                    for(Note note : notes){;
                        //(String id, LocalDateTime created, LocalDateTime updated,
                        // String text, String quote, String uri, UserDTO user, String consumer, AnnotationPermissions permissions, List<String> tags, List<SelectionRange> ranges) {
                        //fromAnnotation ann = new fromAnnotation(note.getId(), note.getCreatedDate(), note.getLastModifiedDate(),
                        //        note.getDetail(), note.getQuote(), httpUrl, UserDTO.fromUser(note.getCreatedBy()), null, null, null, note.getRanges());
                        Annotation ann = mapper.toAnnotation(note);
                        annotations.add(ann);
                    }
                }
                else{
                    log.debug("findAnnotationsForUrl, Note target not found for URL: {}", httpUrl);
                }
            }
            else{
                log.debug("findAnnotationsForUrl, Website not found for URL: {}", httpUrl);
            }

        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }

        return annotations != null ? annotations : new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCaseIndex(Note persisted){
        return this.repository.getCaseIndex(persisted);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initDataOverride(User systemUser) {
        log.debug("initData");

        // create "blox" space
        Host moteBloxHost = this.hostService.create(new Host.Builder().name("noteblox.com").description("Noteblox.com main domain").aliase("www.noteblox.com").build());
        Host moteBloxGithubHost = this.hostService.create(new Host.Builder().name("noteblox.github.io").description("Domain for NoteBLOX Server's github pages").aliase("127.0.0.1:4000").aliase("localhost:4000").aliase("noteblox.github.io:80").build());
        BillableAccount billableAccount = this.billableAccountService.create(new BillableAccount(systemUser));
        Blox notebloxCom = this.bloxService.create(new Blox.Builder()
                .billableAccount(billableAccount)
                .name("NTBLX-COM")
                .title("noteblox.com")
                .avatarUrl(baseUrl + "/img/demo/noteblox.com/avatar.png")
                .bannerUrl(baseUrl + "/img/demo/noteblox.com/banner.png")
                .owner(systemUser)
                .description("Noteblox LLC: information about the company, it's offerings, career opportunities, case studies and more.")
                .visibility(ContextVisibilityType.CLOSED)
                .host(moteBloxHost)
                .build());

        Blox notebloxGithubIo = this.bloxService.create(new Blox.Builder()
                .billableAccount(billableAccount)
                .name("NTBLX-GH-PAGES")
                .title("noteblox.github.io")
                .avatarUrl(baseUrl + "/img/demo/noteblox.io/avatar.png")
                .bannerUrl(baseUrl + "/img/demo/noteblox.io/banner.png")
                .owner(systemUser)
                .description("Central Github pages site for Noteblox Open Source projects. Provides access to source code, technical documentation and community support.")
                .visibility(ContextVisibilityType.CLOSED)
                .host(moteBloxGithubHost)
                .build());

        // create notes application and workflow
        // ------------------------------------------
        CaseWorkflow workflow = this.caseWorkflowService.create(
                new CaseWorkflow("SRV-NOTES", "Notes workflow configuration for Noteblox-Server github pages", notebloxGithubIo));

        // add custom statuses
        List<CaseStatus> workflowStatuses = new LinkedList<>();
        //CaseStatus unassigned = caseStatusService.create(new CaseStatus(CaseStatus.UNASSIGNED, "Status for cases that have not yet been assigned", workflow));
        CaseStatus open = caseStatusService.create(new CaseStatus(CaseStatus.OPEN, "Status for open notes", workflow));
        //workflowStatuses.add(unassigned);
        workflowStatuses.add(open);
        workflowStatuses.add(caseStatusService.create(new CaseStatus(CaseStatus.CLOSED, "Status for closed notes", workflow)));
        workflow.setStatuses(workflowStatuses);

        // add notes app
        WebsiteNotesApp notesApp = this.websiteNotesAppService.create(
                new WebsiteNotesApp.Builder()
                        .space(notebloxGithubIo)
                        .parent(notebloxGithubIo)
                        .basePath("/Noteblox-Server")
                        .owner(systemUser)
                        .name(this.getWorkflowName())
                        .title("Noteblox-Server Notes")
                        .description("Notes for Noteblox-Server Github pages")
                        .workflow(workflow)
                        .visibility(ContextVisibilityType.CLOSED)
                        .build());

        // add note target page
        CaseTarget target = this.caseTargetRepository.persist(new CaseTarget("/Noteblox-Server", notebloxGithubIo));

        // add a few notes
        Note note1 = this.create(new Note.Builder()
                .application(notesApp)
                .status(open)
                .title("Sample note #1")
                .quote("on-page support and collaboration tool")
                .detail("This is a comment")
                .assignee(systemUser)
                .target(target)
                .originalUrl("http://noteblox.github.io/Noteblox-Server/index.html")
                .range(new SelectionRange("/div[2]/div[1]/div[1]/section[1]/p[1]", "/div[2]/div[1]/div[1]/section[1]/p[1]", 15, 53))
                .build());

        Note note2 = this.create(new Note.Builder()
                .application(notesApp)
                .status(open)
                .title("Sample note #1")
                .quote("ee the RES")
                .detail("This is another comment")
                .target(target)
                .originalUrl("http://noteblox.github.io/Noteblox-Server/index.html")
                .range(new SelectionRange("/div[2]/div[1]/div[1]/section[2]/p[1]", "/div[2]/div[1]/div[1]/section[2]/p[1]/a[1]", 2, 3))
                .build());


    }



    @Override
    public String getWorkflowDescription() {
        return this.repository.WORKFLOW_DESCRIPTION;
    }

    @Override
    public List<CaseCommenttInfo> getCompactCommentsBySubject(Note note) {
        return null;
    }

    @Override
    public String getWorkflowTitle() {
        return this.repository.WORKFLOW_TITLE;
    }

    @Override
    public CaseWorkflow getWorkflow() {
        return null;
    }

    @Override
    public String getWorkflowName() {
        return this.repository.WORKFLOW_NAME;
    }


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public void setBillableAccountService(PersistableModelService<BillableAccount, String> billableAccountService) {
        this.billableAccountService = billableAccountService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public void setHostService(PersistableModelService<Host, String> hostService) {
        this.hostService = hostService;
    }

    @Autowired
    public void setBloxService(BloxService bloxService) {
        this.bloxService = bloxService;
    }

    @Autowired
    public void setWebsiteNotesAppService(WebsiteNotesAppService websiteNotesAppService) {
        this.websiteNotesAppService = websiteNotesAppService;
    }

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    @Autowired
    public void setCaseStatusService(CaseStatusService caseStatusService) {
        this.caseStatusService = caseStatusService;
    }

    @Autowired
    public void setNoteCommentRepository(NoteCommentRepository noteCommentRepository) {
        this.noteCommentRepository = noteCommentRepository;
    }

    @Autowired
    public void setCaseTargetRepository(CaseTargetRepository caseTargetRepository) {
        this.caseTargetRepository = caseTargetRepository;
    }

    @Autowired
    public void setCaseWorkflowService(CaseWorkflowService caseWorkflowService) {
        this.caseWorkflowService = caseWorkflowService;
    }
}
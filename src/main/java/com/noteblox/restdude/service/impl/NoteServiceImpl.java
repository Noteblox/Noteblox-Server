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
import com.noteblox.restdude.repository.NoteTargetRepository;
import com.noteblox.restdude.service.NoteService;
import com.noteblox.restdude.service.WebsiteNotesAppService;
import com.noteblox.restdude.service.WebsiteService;
import com.restdude.domain.cases.model.CaseStatus;
import com.restdude.domain.cases.model.CaseWorkflow;
import com.restdude.domain.cases.model.Space;
import com.restdude.domain.cases.model.dto.CaseCommenttInfo;
import com.restdude.domain.cases.model.enums.ContextVisibilityType;
import com.restdude.domain.cases.service.CaseStatusService;
import com.restdude.domain.cases.service.CaseWorkflowService;
import com.restdude.domain.cases.service.SpaceService;
import com.restdude.domain.cases.service.impl.AbstractCaseServiceImpl;
import com.restdude.domain.error.model.BaseError;
import com.restdude.domain.error.service.impl.AbstractErrorServiceImpl;
import com.restdude.domain.misc.model.Host;
import com.restdude.domain.users.model.User;
import com.restdude.domain.users.model.UserDTO;
import com.restdude.domain.users.service.UserService;
import com.restdude.mdd.service.PersistableModelService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    private PersistableModelService<BillableAccount, String> billableAccountService;
    private UserService userService;
    private PersistableModelService<Host, String> hostService;
    private SpaceService spaceService;
    private WebsiteService websiteService;
    private WebsiteNotesAppService websiteNotesAppService;
    private NoteService noteService;
    private CaseStatusService caseStatusService;
    protected NoteCommentRepository noteCommentRepository;
    protected NoteTargetRepository noteTargetRepository;
    private CaseWorkflowService caseWorkflowService;


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Annotation> findAnnotationsForUrl(String httpUrl){
        List<Annotation> annotations = null;
        try {
            URL url = new URL(httpUrl);

            // get the website
            Optional<Website> website = this.websiteService.findByUrl(url);
            if(website.isPresent()){
                String path = url.getPath();
                String welcomeFile = "/index.html";

                // index/welcome file?
                if(path.endsWith(welcomeFile)){
                    path = path.substring(0, path.length() - welcomeFile.length());
                }
                log.debug("findAnnotationsForUrl, note target path: {}", path);
                Optional<NoteTarget> target = this.noteTargetRepository.findByPathAndWebsite(path, website.get());

                if(target.isPresent()){
                    List<Note> notes = this.repository.findAnnotationsByTarget(target.get());
                    annotations = new ArrayList<>();
                    NoteAnnotationMapper mapper = NoteAnnotationMapper.INSTANCE;
                    for(Note note : notes){;
                        //(String id, LocalDateTime created, LocalDateTime updated,
                        // String text, String quote, String uri, UserDTO user, String consumer, AnnotationPermissions permissions, List<String> tags, List<SelectionRange> ranges) {
                        //Annotation ann = new Annotation(note.getId(), note.getCreatedDate(), note.getLastModifiedDate(),
                        //        note.getDetail(), note.getQuote(), httpUrl, UserDTO.fromUser(note.getCreatedBy()), null, null, null, note.getRanges());
                        Annotation ann = mapper.noteToAnnotation(note);
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


    @Autowired
    public void setBillableAccountService(PersistableModelService<BillableAccount, String> billableAccountService) {
        this.billableAccountService = billableAccountService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setHostService(PersistableModelService<Host, String> hostService) {
        this.hostService = hostService;
    }

    @Autowired
    public void setSpaceService(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @Autowired
    public void setWebsiteService(WebsiteService websiteService) {
        this.websiteService = websiteService;
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
    public void setNoteTargetRepository(NoteTargetRepository noteTargetRepository) {
        this.noteTargetRepository = noteTargetRepository;
    }

    @Autowired
    public void setCaseWorkflowService(CaseWorkflowService caseWorkflowService) {
        this.caseWorkflowService = caseWorkflowService;
    }
}
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;

import com.noteblox.restdude.model.BillableAccount;
import com.noteblox.restdude.model.CaseTarget;
import com.noteblox.restdude.model.Issue;
import com.noteblox.restdude.model.IssueComment;
import com.noteblox.restdude.model.SelectionRange;
import com.noteblox.restdude.model.Website;
import com.noteblox.restdude.model.WebsiteIssuesApp;
import com.noteblox.restdude.model.dto.Annotation;
import com.noteblox.restdude.model.mapper.IssueAnnotationMapper;
import com.noteblox.restdude.repository.IssueCommentRepository;
import com.noteblox.restdude.repository.IssueRepository;
import com.noteblox.restdude.repository.IssueTargetRepository;
import com.noteblox.restdude.service.IssueService;
import com.noteblox.restdude.service.WebsiteIssuesAppService;
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
import com.restdude.domain.misc.model.Host;
import com.restdude.domain.users.model.User;
import com.restdude.domain.users.service.UserService;
import com.restdude.mdd.service.PersistableModelService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Named(IssueService.BEAN_ID)
public class IssueServiceImpl
        extends AbstractCaseServiceImpl<Issue, IssueComment, IssueRepository>
        implements IssueService {

    public static final char INDEX_CHAR = '-';

    private PersistableModelService<BillableAccount, String> billableAccountService;
    private UserService userService;
    private PersistableModelService<Host, String> hostService;
    private SpaceService spaceService;
    private WebsiteService websiteService;
    private WebsiteIssuesAppService websiteIssuesAppService;
    private IssueService issueService;
    private CaseStatusService caseStatusService;
    protected IssueCommentRepository noteCommentRepository;
    protected IssueTargetRepository noteTargetRepository;
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
                Optional<CaseTarget> target = this.noteTargetRepository.findByPathAndWebsite(path, website.get());

                if(target.isPresent()){
                    List<Issue> items = this.repository.findAnnotationsByTarget(target.get());
                    annotations = new ArrayList<>();
                    IssueAnnotationMapper mapper = IssueAnnotationMapper.INSTANCE;
                    for(Issue item : items){;
                        Annotation ann = mapper.toAnnotation(item);
                        annotations.add(ann);
                    }
                }
                else{
                    log.debug("findAnnotationsForUrl, Issue target not found for URL: {}", httpUrl);
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
    public Integer getCaseIndex(Issue persisted){
        return this.repository.getCaseIndex(persisted);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initDataOverride(User systemUser) {
        log.debug("initData");
        // initialize globals?
        if (this.repository.count() == 0) {

            // create "business" space
            Space notbloxSpace = this.spaceService.findByName("NTBLX");
            Website notebloxCom = this.websiteService.findByUrl("noteblox.com").get();
            Website notebloxGithubIo = this.websiteService.findByUrl("noteblox.github.io").get();

            // create issues application and workflow
            // ------------------------------------------
            CaseWorkflow workflow = this.caseWorkflowService.create(
                    new CaseWorkflow("NTBLX Issues Workflow", "Workflow configuration for " + this.getDomainClass().getSimpleName() + "entries", notbloxSpace));

            // add custom statuses
            List<CaseStatus> workflowStatuses = new LinkedList<>();
            CaseStatus unassigned = caseStatusService.create(new CaseStatus(CaseStatus.UNASSIGNED, "Status for cases that have not yet been assigned", workflow));
            CaseStatus open = caseStatusService.create(new CaseStatus(CaseStatus.OPEN, "Status for pending cases", workflow));
            workflowStatuses.add(unassigned);
            workflowStatuses.add(open);
            workflowStatuses.add(caseStatusService.create(new CaseStatus(CaseStatus.CLOSED, "Status for closed cases", workflow)));
            workflow.setStatuses(workflowStatuses);

            // add notes app
            WebsiteIssuesApp notesApp = this.websiteIssuesAppService.create(
                    new WebsiteIssuesApp.Builder()
                            .space(notbloxSpace)
                            .parent(notbloxSpace)
                            .basePath("/")
                            .owner(systemUser)
                            .name(this.getWorkflowName())
                            .title(this.getWorkflowTitle())
                            .description(this.getWorkflowDescription())
                            .workflow(workflow)
                            .visibility(ContextVisibilityType.CLOSED)
                            .build());

            // add note target page
            CaseTarget target = this.noteTargetRepository.persist(new CaseTarget("/Issueblox-Server", notebloxGithubIo));

            // add a few notes
            Issue note1 = this.create(new Issue.Builder()
                    .application(notesApp)
                    .status(unassigned)
                    .title("Sample issue #1")
                    .quote("on-page support and collaboration tool")
                    .assignee(systemUser)
                    .detail("This is a comment")
                    .target(target)
                    .originalUrl("http://noteblox.github.io/Issueblox-Server/index.html")
                    .range(new SelectionRange("/div[2]/div[1]/div[1]/section[1]/p[1]", "/div[2]/div[1]/div[1]/section[1]/p[1]", 15, 53))
                    .build());
            Issue note2 = this.create(new Issue.Builder()
                    .application(notesApp)
                    .status(unassigned)
                    .title("Sample issue #1")
                    .quote("ee the RES")
                    .detail("This is another comment")
                    .target(target)
                    .originalUrl("http://noteblox.github.io/Issueblox-Server/index.html")
                    .range(new SelectionRange("/div[2]/div[1]/div[1]/section[2]/p[1]", "/div[2]/div[1]/div[1]/section[2]/p[1]/a[1]", 2, 3))
                    .build());


        }

    }



    @Override
    public String getWorkflowDescription() {
        return this.repository.WORKFLOW_DESCRIPTION;
    }

    @Override
    public List<CaseCommenttInfo> getCompactCommentsBySubject(Issue note) {
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
    public void setWebsiteIssuesAppService(WebsiteIssuesAppService websiteIssuesAppService) {
        this.websiteIssuesAppService = websiteIssuesAppService;
    }

    @Autowired
    public void setIssueService(IssueService noteService) {
        this.issueService = noteService;
    }

    @Autowired
    public void setCaseStatusService(CaseStatusService caseStatusService) {
        this.caseStatusService = caseStatusService;
    }

    @Autowired
    public void setIssueCommentRepository(IssueCommentRepository noteCommentRepository) {
        this.noteCommentRepository = noteCommentRepository;
    }

    @Autowired
    public void setIssueTargetRepository(IssueTargetRepository noteTargetRepository) {
        this.noteTargetRepository = noteTargetRepository;
    }

    @Autowired
    public void setCaseWorkflowService(CaseWorkflowService caseWorkflowService) {
        this.caseWorkflowService = caseWorkflowService;
    }
}
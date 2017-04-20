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
import com.noteblox.restdude.repository.NoteCommentRepository;
import com.noteblox.restdude.repository.NoteRepository;
import com.noteblox.restdude.repository.NoteTargetRepository;
import com.noteblox.restdude.service.NoteService;
import com.noteblox.restdude.service.NoteTargetService;
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
import com.restdude.domain.error.service.impl.AbstractErrorServiceImpl;
import com.restdude.domain.misc.model.Host;
import com.restdude.domain.users.model.User;
import com.restdude.domain.users.service.UserService;
import com.restdude.mdd.service.AbstractAssignedIdModelServiceImpl;
import com.restdude.mdd.service.AbstractPersistableModelServiceImpl;
import com.restdude.mdd.service.PersistableModelService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Named("noteTargetService")
public class NoteTargetServiceImpl
        extends AbstractPersistableModelServiceImpl<NoteTarget, String, NoteTargetRepository>
        implements NoteTargetService {

}
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

import com.noteblox.restdude.model.WebsiteNotesApp;
import com.noteblox.restdude.repository.NoteCommentRepository;
import com.noteblox.restdude.repository.WebsiteNotesAppRepository;
import com.noteblox.restdude.service.WebsiteNotesAppService;
import com.restdude.domain.cases.model.CaseStatus;
import com.restdude.domain.cases.model.CaseWorkflow;
import com.restdude.domain.cases.model.Space;
import com.restdude.domain.cases.service.CaseStatusService;
import com.restdude.domain.cases.service.CaseWorkflowService;
import com.restdude.domain.cases.service.SpaceService;
import com.restdude.domain.error.service.impl.AbstractErrorServiceImpl;
import com.restdude.domain.misc.model.Host;
import com.restdude.domain.users.model.User;
import com.restdude.domain.users.service.UserService;
import com.restdude.mdd.service.AbstractPersistableModelServiceImpl;
import com.restdude.mdd.service.PersistableModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

@Named("websiteNotesAppService")
public class WebsiteNotesAppServiceImpl
        extends AbstractPersistableModelServiceImpl< WebsiteNotesApp, String, WebsiteNotesAppRepository>
        implements WebsiteNotesAppService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractErrorServiceImpl.class);
    public static final char INDEX_CHAR = '-';




}
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
package com.noteblox.restdude.init;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.noteblox.restdude.service.IssueService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Initial data
 */
@Slf4j
@Component
@Named("issuesDataLoader")
public class IssuesDataLoader {

    private NotesDataLoader notesDataLoader;
    private IssueService issueService;

    @Autowired
    public void setNotesDataLoader(NotesDataLoader notesDataLoader) {
        this.notesDataLoader = notesDataLoader;
    }

    @Autowired
    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostConstruct
    @Transactional(readOnly = false)
    public void run() {
        log.debug("run");
        issueService.initData();
    }
}

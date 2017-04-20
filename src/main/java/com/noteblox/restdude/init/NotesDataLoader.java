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

import com.noteblox.restdude.service.NoteService;
import com.restdude.domain.cases.init.SpaceDataLoader;
import com.restdude.domain.error.init.ErrorDataLoader;
import com.restdude.domain.error.service.BaseErrorService;
import com.restdude.domain.error.service.ClientErrorService;
import com.restdude.domain.error.service.SystemErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Named;

/**
 * Initial data
 */
@Slf4j
@Component
@Named("notesDataLoader")
public class NotesDataLoader {

    private ErrorDataLoader spaceDataLoader;
    private NoteService noteService;

    @Autowired
    public void setSpaceDataLoader(ErrorDataLoader spaceDataLoader) {
        this.spaceDataLoader = spaceDataLoader;
    }

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostConstruct
    @Transactional(readOnly = false)
    public void run() {
        log.debug("run");
        noteService.initData();
    }
}

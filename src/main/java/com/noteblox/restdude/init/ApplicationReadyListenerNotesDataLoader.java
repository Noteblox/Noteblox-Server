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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * Creates sample {@Notes} upon receiving an {@link ApplicationReadyEvent}. Listener order set to 20, i.e. after core spaces have been created.
 */
@Slf4j
@Component
public class ApplicationReadyListenerNotesDataLoader {

    private NoteService noteService;

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * Handle an parent event.

     * @param event the event to respond to
     */
    @Transactional(readOnly = false)
    @EventListener
    @Order(20)
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.warn("onApplicationEvent, event: {}", event);
        noteService.initData();
    }
}

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

import com.noteblox.restdude.service.IssueService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Creates initial data for issues upon receiving an {@link ApplicationReadyEvent}. Listener order set to 30, i.e. after {@link ApplicationReadyListenerNotesDataLoader}
 */
@Slf4j
@Component
public class ApplicationReadyListenerIssuesDataLoader {

    private IssueService issueService;

    @Autowired
    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    /**
     * Handle an application event.

     * @param event the event to respond to
     */
    @Transactional(readOnly = false)
    @EventListener
    @Order(30)
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.warn("onApplicationEvent, event: {}", event);
        issueService.initData();
    }
}

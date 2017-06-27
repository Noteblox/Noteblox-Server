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
package com.noteblox.restdude.config;

import com.noteblox.restdude.model.Issue;
import com.noteblox.restdude.model.Note;
import com.noteblox.restdude.model.enums.BloxActivity;
import com.restdude.domain.cases.listener.AbstractEntityEventsHandler;
import com.restdude.domain.cases.model.BaseCase;
import com.restdude.domain.cases.model.BaseContext;
import com.restdude.domain.cases.model.dto.CaseInfo;
import com.restdude.domain.cases.model.enums.CasesActivity;
import com.restdude.domain.event.EntityCreatedEvent;
import com.restdude.domain.event.EntityUpdatedEvent;
import com.restdude.domain.users.model.User;
import com.restdude.websocket.message.MessageResource;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Initializes sample data
 */
@Component
public class EntityEventsHandler extends AbstractEntityEventsHandler {
/*
	@EventListener
	public void onIssueCreated(EntityUpdatedEvent<Issue> event) {
		super.onIssueCreated((EntityUpdatedEvent<Issue>) event);
	}

	@EventListener
	public void onIssueUpdated(EntityCreatedEvent<Issue> event) {

		BaseCase model = event.getModel();
		User user = model.getCreatedBy();
		BaseContext context = model.getApplication();
		Enum predicate = CasesActivity.UPDATED;
		MessageResource objectMessageResource = CaseInfo.from(model);

		createLog(model, user, context, predicate, objectMessageResource);
	}
	*/
	@EventListener
	public final void onIssueCreatedListener(EntityCreatedEvent<Issue> event) {
		this.onIssueCreated(event);
	}

	public void onIssueCreated(EntityCreatedEvent<Issue> event) {

		Issue model = event.getModel();
		User user = model.getCreatedBy();
		BaseContext context = model.getParentCasted();
		Enum predicate = BloxActivity.CREATED_ISSUE;
		MessageResource objectMessageResource = CaseInfo.from(model);

		createLog(model, user, context, predicate, objectMessageResource);

	}

	@EventListener
	public final void onIssueUpdatedListener(EntityUpdatedEvent<Issue> event) {
		this.onIssueUpdated(event);
	}

	public void onIssueUpdated(EntityUpdatedEvent<Issue> event) {

		Issue model = event.getModel();
		User user = model.getCreatedBy();
		BaseContext context = model.getParentCasted();
		Enum predicate = BloxActivity.UPDATED_ISSUE;
		MessageResource objectMessageResource = CaseInfo.from(model);

		createLog(model, user, context, predicate, objectMessageResource);

	}

	@EventListener
	public final void onNoteCreatedListener(EntityCreatedEvent<Note> event) {
		this.onNoteCreated(event);
	}

	public void onNoteCreated(EntityCreatedEvent<Note> event) {

		Note model = event.getModel();
		User user = model.getCreatedBy();
		BaseContext context = model.getParentCasted();
		Enum predicate = BloxActivity.CREATED_NOTE;
		MessageResource objectMessageResource = CaseInfo.from(model);

		createLog(model, user, context, predicate, objectMessageResource);

	}

	@EventListener
	public final void onNoteUpdatedListener(EntityUpdatedEvent<Note> event) {
		this.onNoteUpdated(event);
	}

	public void onNoteUpdated(EntityUpdatedEvent<Note> event) {

		Note model = event.getModel();
		User user = model.getCreatedBy();
		BaseContext context = model.getParentCasted();
		Enum predicate = BloxActivity.UPDATED_NOTE;
		MessageResource objectMessageResource = CaseInfo.from(model);

		createLog(model, user, context, predicate, objectMessageResource);

	}
}

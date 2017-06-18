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
package com.noteblox.restdude.model;

import com.restdude.domain.cases.model.AbstractCaseComment;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.mdd.controller.AbstractNoDeletePersistableModelController;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

/**
 * {@value CLASS_DESCRIPTION}
 */
@Entity
@Table(name = "note_comment")
@ModelResource(pathFragment = NoteComment.API_PATH, controllerSuperClass = AbstractNoDeletePersistableModelController.class,
        apiName = "Note Comments", apiDescription = "Note comment operations")
@ApiModel(description = NoteComment.CLASS_DESCRIPTION)
public class NoteComment extends AbstractCaseComment<Note, NoteComment> {

    public static final String API_PATH = "noteComments";
    public static final String CLASS_DESCRIPTION = "Comments for discussing notes";


    public NoteComment() {

    }

    public NoteComment(Note note, String me) {
        this.setSubject(note);
    }
}

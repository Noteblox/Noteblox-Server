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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.restdude.domain.topic.model.AbstractTopicCommentModel;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.mdd.controller.AbstractNoDeletePersistableModelController;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * {@value CLASS_DESCRIPTION}
 */
@Entity
@Table(name = "note_comment")
@ModelResource(pathFragment = NoteComment.API_PATH, controllerSuperClass = AbstractNoDeletePersistableModelController.class,
        apiName = "Note Comments", apiDescription = "Note comment operations")
@ApiModel(description = NoteComment.CLASS_DESCRIPTION)
public class NoteComment extends AbstractTopicCommentModel<Note, NoteComment> {

    public static final String API_PATH = "noteComments";
    public static final String CLASS_DESCRIPTION = "Comments for discussing notes";

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic", referencedColumnName = "pk", nullable = false)
    @Getter @Setter
    private Note topic;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent", referencedColumnName = "pk", nullable = true)
    @Getter @Setter
    private NoteComment parent;

    @JsonIgnore
    @OneToMany(mappedBy="parent", fetch= FetchType.LAZY)
    @Getter @Setter
    private List<NoteComment> comments;


    public NoteComment() {

    }

    public NoteComment(Note topic, String me) {
        this.topic = topic;
    }
}

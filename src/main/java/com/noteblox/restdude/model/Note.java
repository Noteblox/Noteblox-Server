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
import com.restdude.domain.topic.model.AbstractTopicModel;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.mdd.controller.AbstractNoDeletePersistableModelController;
import com.restdude.mdd.model.CommentModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * {@value CLASS_DESCRIPTION}
 */
@Entity
@Table(name = "note")
@ModelResource(pathFragment = Note.API_PATH, controllerSuperClass = AbstractNoDeletePersistableModelController.class,
        apiName = "Notes", apiDescription = "Note operations")
@ApiModel(description = Note.CLASS_DESCRIPTION)
public class Note extends AbstractTopicModel<NoteComment> {

    public static final String API_PATH = "notes";
    public static final String CLASS_DESCRIPTION = "Entity model for page notes";


    @NotNull
    @ApiModelProperty(value = "The note text", required = true, notes = "Max byte length: " + CommentModel.DEFAULT_MAX_CONTENT_LENGTH)
    @Column(name = "text_content", nullable = false, updatable = false, length =CommentModel. DEFAULT_MAX_CONTENT_LENGTH)
    @Getter
    @Setter
    private String content;

    @JsonIgnore
    @OneToMany(mappedBy="topic", fetch= FetchType.LAZY)
    @Getter @Setter
    private List<NoteComment> comments;

    public Note() {

    }

    protected Note(HttpServletRequest request, String content) {
        this.content = content;


    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pk", this.getPk())
                .append("content", this.getContent())
                .toString();
    }

    @Override
    public void preSave() {
        super.preSave();
        if (this.getCreatedBy() != null && this.getCreatedBy().getPk() == null) {
            this.setCreatedBy(null);
        }
        if (StringUtils.isNotEmpty(this.content) && this.content.length() > com.restdude.domain.error.model.BaseError.MAX_MESSAGE_LENGTH) {
            this.content = StringUtils.abbreviate(this.content, com.restdude.domain.error.model.BaseError.MAX_MESSAGE_LENGTH);
        }
    }



}
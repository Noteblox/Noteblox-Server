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
package com.noteblox.restdude.model.dto;

import com.noteblox.restdude.model.SelectionRange;
import com.restdude.domain.users.model.UserDTO;
import com.restdude.mdd.annotation.model.ModelResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * {@value #API_MODEL_DESCRIPTION}}
 */
@ApiModel(description = Annotation.API_MODEL_DESCRIPTION)
@ModelResource(pathFragment = Annotation.API_PATH_FRAGMENT,
        apiName = "Annotations",
        apiDescription = "Annotation Operations")
public class Annotation {

    public static final String API_MODEL_DESCRIPTION = "An annotation is a JSON document that contains a number of fields describing the position and content of an annotation within a specified document. Data Transfer Object compatible with Annotator JS library ";
    public static final String API_PATH_FRAGMENT = "annotations";

    @Getter @Setter
    @ApiModelProperty(value = "Unique id", readOnly = true)
    private String id;

    @Getter @Setter
    @ApiModelProperty(value = "Annotator schema version, default v1.0", readOnly = true)
    private String annotatorSchemaVersion = "v1.0";

    @Getter @Setter
    @ApiModelProperty(value = "Created datetime in iso8601 format", readOnly = true)
    private LocalDateTime created;

    @Getter @Setter
    @ApiModelProperty(value = "Updated datetime in iso8601 format", readOnly = true)
    private LocalDateTime updated;

    @Getter @Setter
    @ApiModelProperty(value = "Content of annotation")
    private String text;

    @Getter @Setter
    @ApiModelProperty(value = "Comment of annotation")
    private String comment;

    @Getter @Setter
    @ApiModelProperty(value = "The annotated text selection")
    private String quote;

    @Getter @Setter
    @ApiModelProperty(value = "URL of annotated document")
    private String uri;

    @Getter @Setter
    @ApiModelProperty(value = "Annotation owner", readOnly = true)
    private UserDTO user;

    @Getter @Setter
    @ApiModelProperty(value = "Consumer key")
    private String consumer;

    @Getter @Setter
    @ApiModelProperty(value = "Annotation permissions (from Permissions/AnnotateItPermissions plugin)")
    private AnnotationPermissions permissions;

    @Getter @Setter
    @ApiModelProperty(value = "List of tags")
    private List<String> tags;

    @Getter @Setter
    @ApiModelProperty(value = "List of ranges covered by annotation (usually only one entry)")
    private List<SelectionRange> ranges;

    public Annotation() {
    }


    public Annotation(String id, LocalDateTime created, LocalDateTime updated, String text, String quote, String uri, UserDTO user, String consumer, AnnotationPermissions permissions, List<String> tags, List<SelectionRange> ranges) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.text = text;
        this.comment = text;
        this.quote = quote;
        this.uri = uri;
        this.user = user;
        this.consumer = consumer;
        this.permissions = permissions;
        this.tags = tags;
        this.ranges = ranges;
    }
}

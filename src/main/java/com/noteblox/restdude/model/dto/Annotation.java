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

    
    @ApiModelProperty(value = "Unique id", readOnly = true)
    private String id;

    
    @ApiModelProperty(value = "Annotator schema version, default v1.0", readOnly = true)
    private String annotatorSchemaVersion = "v1.0";

    
    @ApiModelProperty(value = "Created datetime in iso8601 format", readOnly = true)
    private LocalDateTime created;

    
    @ApiModelProperty(value = "Updated datetime in iso8601 format", readOnly = true)
    private LocalDateTime updated;

    
    @ApiModelProperty(value = "Content of annotation")
    private String text;

    
    @ApiModelProperty(value = "Comment of annotation")
    private String comment;

    
    @ApiModelProperty(value = "The annotated text selection")
    private String quote;

    
    @ApiModelProperty(value = "URL of annotated document")
    private String uri;

    
    @ApiModelProperty(value = "Annotation owner", readOnly = true)
    private UserDTO user;

    
    @ApiModelProperty(value = "Consumer key")
    private String consumer;

    
    @ApiModelProperty(value = "Annotation permissions (from Permissions/AnnotateItPermissions plugin)")
    private AnnotationPermissions permissions;

    @ApiModelProperty(value = "List of ranges covered by annotation (usually only one entry)")
    private List<SelectionRange> ranges;

    public Annotation() {
    }


    public Annotation(String id, LocalDateTime created, LocalDateTime updated, String text, String quote, String uri, UserDTO user, String consumer, AnnotationPermissions permissions, List<SelectionRange> ranges) {
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
        this.ranges = ranges;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnnotatorSchemaVersion() {
        return annotatorSchemaVersion;
    }

    public void setAnnotatorSchemaVersion(String annotatorSchemaVersion) {
        this.annotatorSchemaVersion = annotatorSchemaVersion;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public AnnotationPermissions getPermissions() {
        return permissions;
    }

    public void setPermissions(AnnotationPermissions permissions) {
        this.permissions = permissions;
    }

    public List<SelectionRange> getRanges() {
        return ranges;
    }

    public void setRanges(List<SelectionRange> ranges) {
        this.ranges = ranges;
    }
}

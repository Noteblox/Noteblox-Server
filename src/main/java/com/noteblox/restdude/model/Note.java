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
import com.noteblox.restdude.model.enums.CaseVisibilityType;
import com.restdude.domain.cases.model.BaseCase;
import com.restdude.domain.cases.model.CaseStatus;
import com.restdude.domain.cases.model.CaseTarget;
import com.restdude.domain.cms.model.Tag;
import com.restdude.domain.users.model.User;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.mdd.controller.AbstractNoDeletePersistableModelController;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

/**
 * {@value CLASS_DESCRIPTION}
 */

@Entity
@Table(name = "note")
@ModelResource(pathFragment = Note.API_PATH, controllerSuperClass = AbstractNoDeletePersistableModelController.class,
        apiName = "Notes", apiDescription = "Note operations")
@ApiModel(description = Note.CLASS_DESCRIPTION)
public class Note extends BaseCase<Note, NoteComment> {

    public static final String API_PATH = "notes";
    public static final String CLASS_DESCRIPTION = "Entity model for page notes";

    @ApiModelProperty(value = "The annotated text selection")
    private String quote;

    @NotNull
    @Column(name = "visibility", nullable = false)
    @ApiModelProperty(value = "Note visibility settings", allowableValues = "PERSONAL, WEBSITE", required = true)
    private CaseVisibilityType visibility = CaseVisibilityType.WEBSITE;
    
    @ApiModelProperty(value = "List of tags")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "note_tags", joinColumns = {@JoinColumn(name = "tag")}, inverseJoinColumns = {
            @JoinColumn(name = "note")})
    private List<Tag> tags;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name="note")
    private List<SelectionRange> ranges;

    public Note() {

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString())
                .append("quote", this.quote)
                .append("visibility", this.visibility)
                .append("status", this.getStatus())
                .toString();
    }


    protected Note(String title, String detail) {
       super(title, detail);
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public CaseVisibilityType getVisibility() {
        return visibility;
    }

    public void setVisibility(CaseVisibilityType visibility) {
        this.visibility = visibility;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<SelectionRange> getRanges() {
        return ranges;
    }

    public void setRanges(List<SelectionRange> ranges) {
        this.ranges = ranges;
    }

    public static class Builder {
        private String quote;
        private String title;
        private User assignee;
        private String detail;
        private String remoteAddress;
        private User user;
        private CaseStatus status;
        private CaseTarget target;
        private WebsiteNotesApp parent;

        private List<SelectionRange> ranges;


        public Builder assignee(User assignee) {
            this.assignee = assignee;
            return this;
        }

        public Builder status(CaseStatus status) {
            this.status = status;
            return this;
        }

        public Builder parent(WebsiteNotesApp application) {
            this.parent = application;
            return this;
        }

        public Builder target(CaseTarget target) {
            this.target = target;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder quote(String quote) {
            this.quote = quote;
            return this;
        }

        public Builder range(SelectionRange range) {
            if(this.ranges == null){
                ranges = new LinkedList<>();
            }
            this.ranges.add(range);
            return this;
        }

        public Builder ranges(List<SelectionRange> ranges) {
            this.ranges = ranges;
            return this;
        }

        public Builder remoteAddress(String remoteAddress) {
            this.remoteAddress = remoteAddress;
            return this;
        }


        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Note build() {
            return new Note(this);
        }
    }

    private Note(Builder builder) {
        this.setQuote(builder.quote);
        this.setTitle(builder.title);
        this.setRemoteAddress(builder.remoteAddress);
        this.setCreatedBy(builder.user);
        this.setAssignee(builder.assignee);
        this.setTarget(builder.target);
        this.setDetail(builder.detail);
        this.setParent(builder.parent);
        this.setStatus(builder.status);
        this.setRanges(builder.ranges);
    }


}
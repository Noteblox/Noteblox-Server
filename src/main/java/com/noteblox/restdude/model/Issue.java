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

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.noteblox.restdude.model.enums.CaseVisibilityType;
import com.restdude.domain.cases.model.BaseCase;
import com.restdude.domain.cases.model.CaseStatus;
import com.restdude.domain.cms.model.Tag;
import com.restdude.domain.users.model.User;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.mdd.controller.AbstractNoDeletePersistableModelController;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * {@value CLASS_DESCRIPTION}
 */
@Entity
@Table(name = "issue")
@ModelResource(pathFragment = Issue.API_PATH, controllerSuperClass = AbstractNoDeletePersistableModelController.class,
        apiName = "Issues", apiDescription = "Issue operations")
@ApiModel(description = Issue.CLASS_DESCRIPTION)
public class Issue extends BaseCase<Issue, IssueComment> {

    public static final String API_PATH = "issues";
    public static final String CLASS_DESCRIPTION = "Entity model for page issues";

    @ApiModelProperty(value = "Original given URL of the issue target")
    private String originalUrl;

    @NotNull
    @Column(name = "visibility", nullable = false)
    
    @ApiModelProperty(value = "Issue visibility settings", allowableValues = "PERSONAL, WEBSITE", required = true)
    private CaseVisibilityType visibility = CaseVisibilityType.WEBSITE;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "target", nullable = false, updatable = false)
    
    @ApiModelProperty(value = "The blox host", required = true)
    private CaseTarget target;

    public Issue() {

    }

    protected Issue(String title, String detail) {
       super(title, detail);
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public CaseVisibilityType getVisibility() {
        return visibility;
    }

    public void setVisibility(CaseVisibilityType visibility) {
        this.visibility = visibility;
    }

    public CaseTarget getTarget() {
        return target;
    }

    public void setTarget(CaseTarget target) {
        this.target = target;
    }

    public static class Builder {
        private String quote;
        private String title;
        private String originalUrl;
        private String detail;
        private User user;
        private User assignee;
        private CaseStatus status;
        private CaseTarget target;
        private WebsiteIssuesApp parent;

        private List<SelectionRange> ranges;

        public Builder assignee(User assignee) {
            this.assignee = assignee;
            return this;
        }

        public Builder status(CaseStatus status) {
            this.status = status;
            return this;
        }

        public Builder parent(WebsiteIssuesApp application) {
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

        public Builder originalUrl(String url) {
            this.originalUrl = url;
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

        public Issue build() {
            return new Issue(this);
        }
    }

    private Issue(Builder builder) {
        this.setTitle(builder.title);
        this.setOriginalUrl(builder.originalUrl);
        this.setCreatedBy(builder.user);
        this.setAssignee(builder.assignee);
        this.setTarget(builder.target);
        this.setDetail(builder.detail);
        this.setParent(builder.parent);
        this.setStatus(builder.status);
    }


}
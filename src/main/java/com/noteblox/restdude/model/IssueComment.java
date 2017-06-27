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

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.restdude.domain.cases.model.BaseCaseComment;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.mdd.controller.AbstractNoDeletePersistableModelController;
import io.swagger.annotations.ApiModel;

/**
 * {@value CLASS_DESCRIPTION}
 */
@JsonRootName("issueComment")
@Entity
@Table(name = "issue_comment")
@ModelResource(pathFragment = IssueComment.API_PATH, controllerSuperClass = AbstractNoDeletePersistableModelController.class,
        apiName = "Issue Comments", apiDescription = "Issue comment operations")
@ApiModel(description = IssueComment.CLASS_DESCRIPTION)
public class IssueComment extends BaseCaseComment<Issue, IssueComment> {

    public static final String API_PATH = "issueComments";
    public static final String CLASS_DESCRIPTION = "Comments for discussing issues";
    
    public IssueComment() {
        super();
    }

    public IssueComment(String content, Issue parent) {
        super(content, parent);
    }

    public IssueComment(String name, String content, Issue parent) {
        super(name, content, parent);
    }
}

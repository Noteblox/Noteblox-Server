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
package com.noteblox.restdude.service;

import java.util.List;

import com.noteblox.restdude.model.Issue;
import com.noteblox.restdude.model.IssueComment;
import com.noteblox.restdude.model.dto.Annotation;
import com.restdude.domain.cases.service.AbstractCaseService;


public interface IssueService  extends AbstractCaseService<Issue, IssueComment> {
    public static final String BEAN_ID = "issueService";

    /**
     * Find all annotations for the given URL
     * @param httpUrl
     * @return
     */
    List<Annotation> findAnnotationsForUrl(String httpUrl);
}

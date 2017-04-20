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
package com.noteblox.restdude.repository;

import com.noteblox.restdude.model.Note;
import com.noteblox.restdude.model.NoteTarget;
import com.noteblox.restdude.model.dto.Annotation;
import com.restdude.domain.cases.model.CaseWorkflow;
import com.restdude.domain.cases.model.dto.CaseCommenttInfo;
import com.restdude.domain.cases.repository.AbstractCaseModelRepository;
import com.restdude.domain.error.model.BaseError;
import com.restdude.domain.error.model.ClientError;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;

import java.util.List;

public interface NoteRepository  extends AbstractCaseModelRepository<Note>{

    String WORKFLOW_NAME = "NTBLX-NOTES";
    String WORKFLOW_TITLE = "Noteblox Website Notes";
    String WORKFLOW_DESCRIPTION = "Website note entries";


    @Query("select n from Note n where n.target = ?1")
    List<Note> findAnnotationsByTarget(NoteTarget noteTarget);

    @Query(value = "select count(c)+1 from  Note c where c.application = :#{#unIndexed.application}  and c.createdDate  <  :#{#unIndexed.createdDate} ")
    Integer getCaseIndex( @Param("unIndexed") Note unIndexed);
}

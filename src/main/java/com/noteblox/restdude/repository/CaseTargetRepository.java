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

import com.noteblox.restdude.model.CaseTarget;
import com.noteblox.restdude.model.Blox;
import com.restdude.mdd.repository.ModelRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CaseTargetRepository extends ModelRepository<CaseTarget, String> {


    @Query("select n from CaseTarget n where n.path = ?1 and n.blox = ?2")
    Optional<CaseTarget> findByPathAndWebsite(String path, Blox blox);
}

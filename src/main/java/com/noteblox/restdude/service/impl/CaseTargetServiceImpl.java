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
package com.noteblox.restdude.service.impl;

import com.noteblox.restdude.model.*;
import com.noteblox.restdude.repository.CaseTargetRepository;
import com.noteblox.restdude.service.CaseTargetService;
import com.restdude.mdd.service.AbstractPersistableModelServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Slf4j
@Named(CaseTargetService.BEAN_ID)
public class CaseTargetServiceImpl
        extends AbstractPersistableModelServiceImpl<CaseTarget, String, CaseTargetRepository>
        implements CaseTargetService {

}
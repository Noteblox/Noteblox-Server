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

import com.noteblox.restdude.model.SpaceBlock;
import com.noteblox.restdude.repository.BloxRepository;
import com.noteblox.restdude.service.SpaceBlockService;
import com.restdude.domain.cases.service.impl.AbstractContextServiceImpl;
import com.restdude.domain.misc.model.Host;
import com.restdude.domain.misc.repository.HostRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Slf4j
@Named(SpaceBlockService.BEAN_ID)
public class SpaceBlockServiceImpl
        extends AbstractContextServiceImpl<SpaceBlock, BloxRepository>
        implements SpaceBlockService {

}
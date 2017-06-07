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

import javax.inject.Named;

import com.noteblox.restdude.model.WebsiteIssuesApp;
import com.noteblox.restdude.repository.WebsiteIssuesAppRepository;
import com.noteblox.restdude.service.WebsiteIssuesAppService;
import com.restdude.domain.error.service.impl.AbstractErrorServiceImpl;
import com.restdude.mdd.service.AbstractPersistableModelServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named(WebsiteIssuesAppService.BEAN_ID)
public class WebsiteIssuesAppServiceImpl
        extends AbstractPersistableModelServiceImpl< WebsiteIssuesApp, String, WebsiteIssuesAppRepository>
        implements WebsiteIssuesAppService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractErrorServiceImpl.class);
    public static final char INDEX_CHAR = '-';




}
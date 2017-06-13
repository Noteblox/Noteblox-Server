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

import com.noteblox.restdude.model.Blox;
import com.noteblox.restdude.model.Note;
import com.noteblox.restdude.model.NoteComment;
import com.noteblox.restdude.model.Blox;
import com.restdude.domain.PersistableModel;
import com.restdude.domain.cases.service.CaseService;
import com.restdude.domain.misc.model.Host;
import com.restdude.mdd.service.PersistableModelService;

import java.net.URL;
import java.util.Optional;


public interface BloxService extends PersistableModelService<Blox, String> {
    public static final String BEAN_ID = "errorLogService";


    /**
     * Find the blox best matching the given host and path, if any
     *
     * @param path
     * @param host
     * @return
     */
    public Optional<Blox> findByPathAndHost(String path, Host host);


    /**
     * Find the blox best matching the given URL, if any
     *
     * @param url
     * @return
     */
    public Optional<Blox> findByUrl(String url);

    /**
     * Find the blox best matching the given URL, if any
     *
     * @param url
     * @return
     */
    public Optional<Blox> findByUrl(URL url);
}

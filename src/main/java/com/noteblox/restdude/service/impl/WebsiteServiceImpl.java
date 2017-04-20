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

import com.noteblox.restdude.model.Website;
import com.noteblox.restdude.model.WebsiteNotesApp;
import com.noteblox.restdude.repository.NoteCommentRepository;
import com.noteblox.restdude.repository.WebsiteNotesAppRepository;
import com.noteblox.restdude.repository.WebsiteRepository;
import com.noteblox.restdude.service.WebsiteNotesAppService;
import com.noteblox.restdude.service.WebsiteService;
import com.restdude.domain.cases.model.CaseStatus;
import com.restdude.domain.cases.model.CaseWorkflow;
import com.restdude.domain.cases.model.Space;
import com.restdude.domain.cases.service.CaseStatusService;
import com.restdude.domain.cases.service.CaseWorkflowService;
import com.restdude.domain.cases.service.SpaceService;
import com.restdude.domain.error.service.impl.AbstractErrorServiceImpl;
import com.restdude.domain.misc.model.Host;
import com.restdude.domain.misc.repository.HostRepository;
import com.restdude.domain.users.model.User;
import com.restdude.domain.users.service.UserService;
import com.restdude.mdd.service.AbstractPersistableModelServiceImpl;
import com.restdude.mdd.service.PersistableModelService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.ObjectUtils;

import javax.inject.Named;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Named("websiteService")
public class WebsiteServiceImpl
        extends AbstractPersistableModelServiceImpl<Website, String, WebsiteRepository>
        implements WebsiteService {

    HostRepository hostRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Website> findByUrl(String url){
        Optional<Website> website;
        try {
            website = this.findByUrl(new URL(url));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Could not process website URL: " + url, e);
        }

        return website;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Website> findByUrl(URL url){

        String path = url.getPath();
        Host host = getHost(url);
        Optional<Website> website = this.repository.findByPathAndHost(path, host);
        log.debug("findByUrl, path: {}, host: {}, httpUrl: {}, website: {}", path, host, url, website);

        return website;
    }

    protected Host getHost(URL url) {
        StringBuffer hostname =  new StringBuffer(url.getHost());
        if(url.getPort() != 80){
            hostname.append(":").append(url.getPort());
        }
        Optional<Host> host = this.hostRepository.findByName(hostname.toString());
        if(!host.isPresent()){
            throw new IllegalArgumentException("No registered host: te URL: " + url);
        }
        return host.get();
    }

    @Autowired
    public void setHostRepository(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }
}
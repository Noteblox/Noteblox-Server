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

import com.noteblox.restdude.model.Blox;
import com.noteblox.restdude.repository.BloxRepository;
import com.noteblox.restdude.service.BloxService;
import com.restdude.domain.misc.model.Host;
import com.restdude.domain.misc.repository.HostRepository;
import com.restdude.mdd.service.AbstractPersistableModelServiceImpl;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Slf4j
@Named("bloxService")
public class BloxServiceImpl
        extends AbstractPersistableModelServiceImpl<Blox, String, BloxRepository>
        implements BloxService {

    HostRepository hostRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Blox> findByUrl(@NonNull String url){
        Optional<Blox> website;
        // add dummy protocol if missing
        if(!url.startsWith("http")){
            url = "https://" + url;
        }
        log.debug("findByUrl: {}", url);
        try {
            website = this.findByUrl(new URL(url));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Could not process blox URL: " + url, e);
        }

        return website;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Blox> findByUrl(URL url){

        String path = url.getPath();
        // use default path if missing
        if(StringUtils.isBlank(path)){
            path = "/";
        }
        Host host = getHost(url);
        Optional<Blox> website = this.findByPathAndHost(path, host);
        log.debug("findByUrl, path: {}, host: {}, httpUrl: {}, blox: {}", path, host, url, website);

        return website;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Blox> findByPathAndHost(String path, Host host){
        return this.repository.findByPathAndHost(path, host);
    }

    protected Host getHost(URL url) {
        StringBuffer hostname =  new StringBuffer(url.getHost());

        Optional<Host> host = this.hostRepository.findByName(hostname.toString());
        log.debug("getHost '{}: {}", hostname, host);
        if(!host.isPresent()){
            hostname.append(":").append(url.getPort());
            host = this.hostRepository.findByName(hostname.toString());
            log.debug("getHost '{}: {}", hostname, host);
        }

        if(!host.isPresent()){
            throw new IllegalArgumentException("No registered host to hostname URL: " + url);
        }
        return host.get();
    }

    @Autowired
    public void setHostRepository(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }
}
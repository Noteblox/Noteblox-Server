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
package com.noteblox.server.init;

import com.restdude.init.DataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * {@inheritDoc}
 */
@Component
public class BootDataInitializer extends DataInitializer implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootDataInitializer.class);

    @Value("${restdude.testEmailDomain}")
    private String testEmailDomain;

    @Override
    public String getTestEmailDomain() {
        return this.testEmailDomain;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        super.run();
    }
}

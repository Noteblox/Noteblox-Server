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
package com.noteblox.restdude.util.email.service;


import com.restdude.util.email.service.AbstractEmailService;
import com.restdude.util.email.service.EmailService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
public class BootEmailService extends AbstractEmailService implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootEmailService.class);

    @Value("${mail.server.from}")
    private String defaultMailFrom;

    @Value("${spring.mail.test-connection}")
    private Boolean mailEnabled;

    @Value("${restdude.baseurl}")
    private String baseUrl;

    private JavaMailSender mailSender;

    private TemplateEngine templateEngine;

    private MessageSource messageSource;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void setTemplateEngine(TemplateEngine templateEngine) {
        LOGGER.debug("setTemplateEngine: {}", templateEngine);
        this.templateEngine = templateEngine;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected String getTemplateFilenameSuffix() {
        return StringUtils.EMPTY;
    }

    @Override
    public String getDefaultMailFrom() {
        return defaultMailFrom;
    }

    @Override
    public Boolean getMailEnabled() {
        return mailEnabled;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public JavaMailSender getMailSender() {
        return mailSender;
    }

    @Override
    public TemplateEngine getTemplateEngine() {
        return templateEngine;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }


}
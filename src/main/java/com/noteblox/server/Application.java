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
package com.noteblox.server;

import com.restdude.domain.users.model.User;
import com.restdude.mdd.repository.BaseRepositoryImpl;
import com.restdude.mdd.repository.ModelRepositoryFactoryBean;
import com.restdude.util.audit.AuditorBean;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@SpringBootApplication(scanBasePackages = {"com.restdude", "com.noteblox"}, exclude = {ErrorMvcAutoConfiguration.class})
@EnableTransactionManagement
@EntityScan({"**.restdude", "**.noteblox"})
@EnableJpaRepositories(basePackages =  {"**.restdude", "**.noteblox"},
        repositoryFactoryBeanClass = ModelRepositoryFactoryBean.class
)
@EnableJpaAuditing(auditorAwareRef = AuditorBean.BEAN_NAME)
@EnableScheduling
public class Application implements EmbeddedServletContainerCustomizer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        AnnotationConfigEmbeddedWebApplicationContext ctx = (AnnotationConfigEmbeddedWebApplicationContext) SpringApplication.run(Application.class, args);
        LOGGER.debug("main, context: {}", ctx);
    }

    @Bean(AuditorBean.BEAN_NAME)
    public AuditorAware<User> auditorProvider() {
        return new AuditorBean();
    }

    @Bean
    public JettyServerCustomizer jettyServerCustomizer() {

        return new JettyServerCustomizer() {

            @Override
            public void customize(Server server) {
                WebAppContext webAppContext = (WebAppContext) server.getHandler();
                //webAppContext.setErrorHandler(new ErrorHandler());
                // default error handler for resources out of "context" scope
                //server.addBean(new ErrorHandler());
/*
                try {
                    ClassPathResource classPathResource = new ClassPathResource("META-INF/resources");
                    String externalResource = classPathResource.getURI().toString();
                    String[] resources = new String[] { externalResource };
                    webAppContext.setBaseResource(new ResourceCollection(resources));

                    ClassLoader jspClassLoader = new URLClassLoader(new URL[0], this.getClass().getClassLoader());
                    webAppContext.setClassLoader(jspClassLoader);
                }
                catch (IOException exception) {
                    exception.printStackTrace();
                }
            */
            }
        };
    }

    public void customizeJetty(
            JettyEmbeddedServletContainerFactory containerFactory) {

        containerFactory.addServerCustomizers(jettyServerCustomizer());
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        //container.setErrorPages(new HashSet<ErrorPage>());
        if (container instanceof JettyEmbeddedServletContainerFactory) {
            customizeJetty((JettyEmbeddedServletContainerFactory) container);
        }
    }

    /**
     * Dummy error handler that disables any error pages or jetty related messages and returns our
     * ERROR status JSON with plain HTTP status instead. All original error messages (from our code) are preserved
     * as they are not handled by this code.
     */
    static class ErrorHandler extends ErrorPageErrorHandler {
        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
            LOGGER.error("ErrorHandler.handle target: {}", target);
            DefaultErrorAttributes errorAttributes = new DefaultErrorAttributes();
            RequestAttributes requestAttributes = new ServletRequestAttributes(request, response);
            Map<String, Object> errorInfoMap = errorAttributes.getErrorAttributes(requestAttributes, true);

            LOGGER.debug("ErrorHandler#handle requestAttributes: {}", requestAttributes.getAttributeNames(0));
            ;
            LOGGER.debug("ErrorHandler#handle requestAttributes: {}", requestAttributes.getAttributeNames(1));
            ;
            LOGGER.debug("ErrorHandler#handle requestAttributes: {}", requestAttributes.getAttributeNames(2));
            ;
            LOGGER.debug("ErrorHandler#handle target: {}", target);
            ;
            LOGGER.debug("ErrorHandler#handle errorInfoMap: {}", errorInfoMap);
            ;
            LOGGER.debug("ErrorHandler#handle error: {}", errorAttributes.getError(requestAttributes));
            ;

            response.getWriter()
                    .append("{\"status\":\"ERROR\",\"message\":\"HTTP ")
                    .append(String.valueOf(response.getStatus()))
                    .append("\"}");

        }
    }
}
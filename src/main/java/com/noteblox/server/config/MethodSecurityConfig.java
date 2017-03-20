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
package com.noteblox.server.config;

import com.restdude.auth.spel.binding.ModelDrivenMethodSecurityExpressionHandler;
import com.restdude.auth.spel.binding.ModelPermissionEvaluator;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.method.P;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.parameters.AnnotationParameterNameDiscoverer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        ModelDrivenMethodSecurityExpressionHandler expressionHandler = new ModelDrivenMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new ModelPermissionEvaluator());
        expressionHandler.setParameterNameDiscoverer(new AnnotationParameterNameDiscoverer(
                P.class.getCanonicalName(),
                Param.class.getCanonicalName(),
                PathVariable.class.getCanonicalName(),
                RequestBody.class.getCanonicalName(),
                RequestParam.class.getCanonicalName()));
        return expressionHandler;
    }

}
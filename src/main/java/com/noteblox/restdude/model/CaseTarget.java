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
package com.noteblox.restdude.model;

import com.restdude.domain.cases.model.AbstractCaseModel;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.mdd.controller.AbstractNoDeletePersistableModelController;
import com.restdude.mdd.model.AbstractSystemUuidPersistableModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * {@value CLASS_DESCRIPTION}
 */
@Entity
@Table(name = "note_target")
@ModelResource(pathFragment = CaseTarget.API_PATH, controllerSuperClass = AbstractNoDeletePersistableModelController.class,
        apiName = "CaseTarget", apiDescription = "Note operations")
@ApiModel(description = CaseTarget.CLASS_DESCRIPTION)
public class CaseTarget extends AbstractSystemUuidPersistableModel {

    public static final String API_PATH = "noteTargets";
    public static final String CLASS_DESCRIPTION = "Entity model for notes targets, typically web pages";

    @Getter @Setter
    @ApiModelProperty(value = "Path of this target within it's parent blox")
    private String path;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "host", nullable = false, updatable = false)
    @Getter @Setter
    @ApiModelProperty(value = "The blox host", required = true)
    private Blox blox;

    public CaseTarget() {

    }

    public CaseTarget(String path, Blox blox) {
        this.path = path;
        this.blox = blox;
    }

    public static class Builder {
        private Blox blox;

        public Builder blox(Blox blox) {
            this.blox = blox;
            return this;
        }

        public CaseTarget build() {
            return new CaseTarget(this);
        }
    }

    private CaseTarget(Builder builder) {
        this.setBlox(builder.blox);
    }


}
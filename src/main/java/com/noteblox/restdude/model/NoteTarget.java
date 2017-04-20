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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.noteblox.restdude.model.enums.NoteVisibilityType;
import com.restdude.domain.cases.model.AbstractCaseModel;
import com.restdude.domain.cms.model.Tag;
import com.restdude.domain.misc.model.Host;
import com.restdude.domain.users.model.User;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.mdd.controller.AbstractNoDeletePersistableModelController;
import com.restdude.mdd.model.AbstractSystemUuidPersistableModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

/**
 * {@value CLASS_DESCRIPTION}
 */
@Entity
@Table(name = "note_target")
@ModelResource(pathFragment = NoteTarget.API_PATH, controllerSuperClass = AbstractNoDeletePersistableModelController.class,
        apiName = "NoteTarget", apiDescription = "Note operations")
@ApiModel(description = NoteTarget.CLASS_DESCRIPTION)
public class NoteTarget extends AbstractSystemUuidPersistableModel {

    public static final String API_PATH = "noteTargets";
    public static final String CLASS_DESCRIPTION = "Entity model for notes targets, typically web pages";

    @Getter @Setter
    @ApiModelProperty(value = "Path of this target within it's parent website")
    private String path;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "host", nullable = false, updatable = false)
    @Getter @Setter
    @ApiModelProperty(value = "The website host", required = true)
    private Website website;

    public NoteTarget() {

    }

    public NoteTarget(String path, Website website) {
        this.path = path;
        this.website = website;
    }

    public static class Builder {
        private Website website;

        public Builder website(Website website) {
            this.website = website;
            return this;
        }

        public NoteTarget build() {
            return new NoteTarget(this);
        }
    }

    private NoteTarget(Builder builder) {
        this.setWebsite(builder.website);
    }


}
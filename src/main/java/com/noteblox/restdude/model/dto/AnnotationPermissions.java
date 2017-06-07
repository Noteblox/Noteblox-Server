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
package com.noteblox.restdude.model.dto;

import com.restdude.domain.users.model.UserDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * {@value #API_MODEL_DESCRIPTION}}
 */
@ApiModel(description = AnnotationPermissions.API_MODEL_DESCRIPTION)
public class AnnotationPermissions {

    public static final String API_MODEL_DESCRIPTION = "fromAnnotation permissions (for Permissions/AnnotateItPermissions plugin)";

    @Getter @Setter
    @ApiModelProperty(value = "Read permissions", example = "group:__world__")
    private List<String> read;
    @Getter @Setter
    @ApiModelProperty(value = "Admin permissions", example = "group:__world__")
    private List<String> admin;
    @Getter @Setter
    @ApiModelProperty(value = "Update permissions", example = "group:__world__")
    private List<String> update;
    @Getter @Setter
    @ApiModelProperty(value = "Delete permissions", example = "group:__world__")
    private List<String> delete;

    public AnnotationPermissions() {
    }

    public AnnotationPermissions(List<String> read, List<String> admin, List<String> update, List<String> delete) {
        this.read = read;
        this.admin = admin;
        this.update = update;
        this.delete = delete;
    }
}

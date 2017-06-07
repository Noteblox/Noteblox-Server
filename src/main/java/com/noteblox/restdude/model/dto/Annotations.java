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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.noteblox.restdude.model.SelectionRange;
import com.restdude.domain.users.model.UserDTO;
import com.restdude.mdd.annotation.model.ModelResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import scala.Int;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * {@value #API_MODEL_DESCRIPTION}}
 */
@ApiModel(description = Annotations.API_MODEL_DESCRIPTION)
public class Annotations implements Serializable{

    public static final String API_MODEL_DESCRIPTION = "A list of fromAnnotation entries ";

    @Getter @Setter
    List<Annotation> rows;

    public Annotations() {
        this.rows = rows;
    }

    public Annotations(List<Annotation> rows) {
        this.rows = rows;
    }

    @JsonProperty("total")
    public Integer getTotal(){
        return CollectionUtils.isNotEmpty(this.rows) ? this.rows.size() : 0;
    }


}

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

import com.restdude.domain.cms.model.AbstractSelectionRange;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * {@inheritDoc}
 */
@Entity
@Table(name = "selection_range")
@ApiModel(description = AbstractSelectionRange.API_MODEL_DESCRIPTION)
public class SelectionRange extends AbstractSelectionRange {


    public SelectionRange() {
        super();
    }

    public SelectionRange(String start, String end, Integer startOffset, Integer endOffset) {
        super(start, end, startOffset, endOffset);
    }
}

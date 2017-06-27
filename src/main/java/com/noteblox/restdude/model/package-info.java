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
@AnyMetaDef(name = "SpaceUserActivityObjectMetaDef", metaType = "string", idType = "string",
		metaValues = {
				@MetaValue(value = "1", targetEntity = Space.class),
				@MetaValue(value = "2", targetEntity = SpaceApp.class),
				@MetaValue(value = "3", targetEntity = BaseCase.class),
				@MetaValue(value = "4", targetEntity = BaseCaseComment.class),
				@MetaValue(value = "5", targetEntity = Membership.class),
				@MetaValue(value = "6", targetEntity = MembershipRequest.class),
				@MetaValue(value = "7", targetEntity = Issue.class),
				@MetaValue(value = "8", targetEntity = Note.class)
		}
)
package com.noteblox.restdude.model;
import com.restdude.domain.cases.model.BaseCaseComment;
import com.restdude.domain.cases.model.BaseCase;
import com.restdude.domain.cases.model.Membership;
import com.restdude.domain.cases.model.MembershipRequest;
import com.restdude.domain.cases.model.Space;
import com.restdude.domain.cases.model.SpaceApp;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;
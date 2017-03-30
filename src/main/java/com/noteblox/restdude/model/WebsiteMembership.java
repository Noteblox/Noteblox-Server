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

import com.restdude.domain.users.model.User;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.mdd.controller.AbstractReadOnlyPersistableModelController;
import com.restdude.mdd.model.AbstractSystemUuidPersistableModel;
import com.restdude.mdd.util.EntityUtil;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * {@value #API_MODEL_DESCRIPTION}
 */
@Entity
@Table(name = "ws_membership")
@ModelResource(pathFragment = WebsiteMembership.API_PATH_FRAGMENT,
	controllerSuperClass = AbstractReadOnlyPersistableModelController.class,
	apiName = "Website Memberships", 
	apiDescription = "Operations about website memberships")
@ApiModel(description = WebsiteMembership.API_MODEL_DESCRIPTION)
public class WebsiteMembership extends AbstractSystemUuidPersistableModel {

	public static final String API_PATH_FRAGMENT = "wsMemberships";
	public static final String API_MODEL_DESCRIPTION = "An entity model representing a website membership";

	@NotNull
	@Column(name = "is_admin", nullable = false)
	@Getter
	@Setter
	private Boolean admin = Boolean.FALSE;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "website", referencedColumnName = "pk", nullable = false, updatable = false)
	@Getter
	@Setter
	private Website website;

	@NotNull
	@ManyToOne
	@JoinColumn(referencedColumnName = "pk", nullable = false, updatable = false)
	@Getter
	@Setter
	private User user;

	public WebsiteMembership(){
		super();
	}
	
	public WebsiteMembership(Website website, User user) {
		super();
		this.website = website;
		this.user = user;
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder(11, 13).appendSuper(super.hashCode()).append(EntityUtil.idOrNEmpty(this.getWebsite())).append(EntityUtil.idOrNEmpty(this.getUser())).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}

		if (this == obj) {
			return true;
		}
		if (!WebsiteMembership.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		WebsiteMembership other = (WebsiteMembership) obj;
		EqualsBuilder builder = new EqualsBuilder();
		builder.appendSuper(super.equals(obj));
		builder.append(EntityUtil.idOrNEmpty(this.getWebsite()), EntityUtil.idOrNEmpty(other.getWebsite()));
		builder.append(EntityUtil.idOrNEmpty(this.getUser()), EntityUtil.idOrNEmpty(other.getUser()));
		return builder.isEquals();
	}

}

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
package com.noteblox.server.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.noteblox.server.model.enums.WebsiteMembershipRequestStatus;
import com.restdude.domain.users.model.User;
import com.restdude.domain.users.model.UserDTO;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.mdd.model.AbstractSystemUuidPersistableModel;
import com.restdude.mdd.util.EntityUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * {@value #API_MODEL_DESCRIPTION}
 */
@Entity
@Table(name = "ws_membership_request")
@ModelResource(pathFragment = WebsiteMembershipRequest.API_PATH_FRAGMENT,
		apiName = "Website Membership Requests",
		apiDescription = "Requests to join a Website")
@ApiModel(description = WebsiteMembershipRequest.API_MODEL_DESCRIPTION)
public class WebsiteMembershipRequest extends AbstractSystemUuidPersistableModel {

	public static final String API_PATH_FRAGMENT = "wsMembershipRequests";
	public static final String API_MODEL_DESCRIPTION = "A model representing a user request or invitation to join a Website.";

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	@ApiModelProperty(value = "The membership status", allowableValues = "SENT_REQUEST, SENT_INVITE, CONFIRMED, BLOCK_REQUEST, BLOCK_INVITE, DELETE", required = true)
	private WebsiteMembershipRequestStatus status;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "website", referencedColumnName = "pk", nullable = false, updatable = false)
	@ApiModelProperty(value = "The website this membership is appointed for", required = true)
	private Website website;

	@NotNull
	@ManyToOne
	@JoinColumn(referencedColumnName = "pk", nullable = false, updatable = false)
	@ApiModelProperty(value = "The user this membership is appointed to", required = true)
	private User user;

	public WebsiteMembershipRequest() {
		super();
	}

	public WebsiteMembershipRequestStatus getStatus() {
		return status;
	}

	public void setStatus(WebsiteMembershipRequestStatus status) {
		this.status = status;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	@JsonGetter("user")
	public UserDTO getUserDTO() {
		return UserDTO.fromUser(this.user);
	}

	@JsonProperty
	public void setUser(User user) {
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
		if (!WebsiteMembershipRequest.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		WebsiteMembershipRequest other = (WebsiteMembershipRequest) obj;
		EqualsBuilder builder = new EqualsBuilder();
		builder.appendSuper(super.equals(obj));
		builder.append(EntityUtil.idOrNEmpty(this.getWebsite()), EntityUtil.idOrNEmpty(other.getWebsite()));
		builder.append(EntityUtil.idOrNEmpty(this.getUser()), EntityUtil.idOrNEmpty(other.getUser()));
		return builder.isEquals();
	}

}

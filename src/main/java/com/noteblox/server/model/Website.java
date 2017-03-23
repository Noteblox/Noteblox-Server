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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.noteblox.server.model.enums.WebsiteVisibilityType;
import com.restdude.domain.misc.model.Host;
import com.restdude.mdd.annotation.model.FilePersistence;
import com.restdude.mdd.annotation.model.FilePersistencePreview;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.mdd.model.AbstractPersistableHierarchicalModel;
import com.restdude.util.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * {@value #API_MODEL_DESCRIPTION}
 */
@Entity
@Table(name = "website")
@ModelResource(pathFragment = Website.API_PATH_FRAGMENT,
		apiName = "Website",
		apiDescription = "Website Operations")
@ApiModel(description = Website.API_MODEL_DESCRIPTION)
public class Website extends AbstractPersistableHierarchicalModel<Website> {

	public static final String API_PATH_FRAGMENT = "websites";
	public static final String API_MODEL_DESCRIPTION = "A model representing a Website, essentially a group of resources under a base path.";

	@NotNull
	@Column(name = "title", nullable = false, unique = true)
	@Getter @Setter
	@ApiModelProperty(value = "The website's human-readable name", required = true)
	private String title;

	@NotNull
	@Column(name = "description", length = 500, nullable = false)
	@Getter @Setter
	@ApiModelProperty(value = "Short description", required = true)
	private String description;

	@FilePersistence(maxWidth = 130, maxHeight = 130)
	@FilePersistencePreview(maxWidth = 100, maxHeight = 100)
	@FilePersistencePreview(maxWidth = 50, maxHeight = 50)
	@Column(name = "avatar_url")
	@Getter @Setter
	@ApiModelProperty(value = "The website's avatar URL")
	private String avatarUrl = Constants.DEFAULT_AVATAR_URL;

	@FilePersistence(maxWidth = 1920, maxHeight = 1080)
	@FilePersistencePreview(maxWidth = 1280, maxHeight = 720)
	@FilePersistencePreview(maxWidth = 720, maxHeight = 480)
	@Column(name = "banner_url")
	@Getter @Setter
	@ApiModelProperty(value = "The website's banner URL")
	private String bannerUrl = Constants.DEFAULT_BANNER_URL;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "owner", nullable = false, updatable = false)
	@Getter @Setter
	@ApiModelProperty(value = "The account this website belongs to", required = true)
	private BillableAccount billableAccount;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "host", nullable = false, updatable = false)
	@Getter @Setter
	@ApiModelProperty(value = "The website host", required = true)
	private Host host;

	@NotNull
	@Column(name = "visibility", nullable = false)
	@Getter @Setter
	@ApiModelProperty(value = "Website visibility settings", allowableValues = "SECRET, CLOSED, OPEN, PUBLIC", required = true)
	WebsiteVisibilityType visibility = WebsiteVisibilityType.CLOSED;

    @OneToMany(mappedBy = "website", orphanRemoval = true)
    @JsonIgnore
	@Getter @Setter
	@ApiModelProperty(value = "The website memberships")
	private Set<WebsiteMembership> memberships;

    @OneToMany(mappedBy = "website", orphanRemoval = true)
    @JsonIgnore
	@Getter @Setter
	@ApiModelProperty(value = "The website membership requests/invites")
    private List<WebsiteMembershipRequest> membershipRequests;

	public Website() {
		super();
	}
	
	public Website(String id) {
		this();
		this.setPk(id);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.append("name", this.getName())
				.append("path", this.getPath())
				.toString();
	}
	

}

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
import com.restdude.domain.cases.model.Membership;
import com.restdude.domain.cases.model.Space;
import com.restdude.domain.cases.model.SpaceApp;
import com.restdude.domain.cases.model.enums.ContextVisibilityType;
import com.restdude.domain.misc.model.Host;
import com.restdude.domain.users.model.User;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.util.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * {@value #API_MODEL_DESCRIPTION}
 */
@Entity
@Table(name = "blox")
@ModelResource(pathFragment = SpaceBlock.API_PATH_FRAGMENT,
		apiName = "Blox",
		apiDescription = "Website Operations")
@ApiModel(description = SpaceBlock.API_MODEL_DESCRIPTION)
public class SpaceBlock extends Space {

	public static final String API_PATH_FRAGMENT = "blocks";
	public static final String API_MODEL_DESCRIPTION = "A model representing an organization, community, team or wWebsite";

	@NotNull
	@Column(name = "base_path", nullable = false)
	@Getter @Setter
	@ApiModelProperty(value = "The base path of applicable resources, default is '/'", required = true)
	private String basePath = "/";
/*
	@NotNull
	@ManyToOne
	@JoinColumn(name = "owner", nullable = false, updatable = false)
	@Getter @Setter
	@ApiModelProperty(value = "The account this blox belongs to", required = true)
	private BillableAccount billableAccount;
*/

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="context_domain_hosts")
	@ApiModelProperty("The domains registered with this block")
	@Getter @Setter
	private List<Host> domains;


	public SpaceBlock() {
		super();
	}
	
	public SpaceBlock(String id) {
		this();
		this.setId(id);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.append("name", this.getName())
				.append("basePath", this.getBasePath())
				.toString();
	}

	public static class Builder {

		private String name;
		private String title;
		private String detail;
		private String avatarUrl;
		private String bannerUrl;
		private User owner;
		private String basePath = "/";
		private List<Host> domains;
		private Space parent;
		private BillableAccount billableAccount;
		private ContextVisibilityType visibility;
		private Set<Membership> memberships;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder detail(String detail) {
			this.detail = detail;
			return this;
		}


		public Builder avatarUrl(String avatarUrl) {
			this.avatarUrl = avatarUrl;
			return this;
		}

		public Builder bannerUrl(String bannerUrl) {
			this.bannerUrl = bannerUrl;
			return this;
		}

		public Builder owner(User owner) {
			this.owner = owner;
			return this;
		}

		public Builder parent(Space parent) {
			this.parent = parent;
			return this;
		}

		public Builder basePath(String basePath) {
			this.basePath = basePath;
			return this;
		}


		public Builder domain(Host host) {
			if(this.domains == null){
				this.domains = new LinkedList<>();
			}
			this.domains.add(host);
			return this;
		}


		public Builder visibility(ContextVisibilityType visibility) {
			this.visibility = visibility;
			return this;
		}

		public SpaceBlock build() {
			return new SpaceBlock(this);
		}
	}

	private SpaceBlock(Builder builder) {
		this.setName(builder.name);
		this.setTitle(builder.title);
		this.setDetail(builder.detail);
		this.setAvatarUrl(builder.avatarUrl);
		this.setBannerUrl(builder.bannerUrl);
		this.setParent(builder.parent);
		this.setBasePath(builder.basePath);
		this.setOwner(builder.owner);
		this.setDomains(builder.domains);
		this.setVisibility(builder.visibility);
	}
}

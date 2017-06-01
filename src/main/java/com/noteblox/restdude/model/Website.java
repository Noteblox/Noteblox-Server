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

import com.restdude.domain.cases.model.Membership;
import com.restdude.domain.cases.model.Space;
import com.restdude.domain.cases.model.enums.ContextVisibilityType;
import com.restdude.domain.misc.model.Host;
import com.restdude.domain.users.model.User;
import com.restdude.mdd.annotation.model.ModelResource;
import com.restdude.util.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * {@value #API_MODEL_DESCRIPTION}
 */
@Entity
@Table(name = "context_website")
@ModelResource(pathFragment = Website.API_PATH_FRAGMENT,
		apiName = "Website",
		apiDescription = "Website Operations")
@ApiModel(description = Website.API_MODEL_DESCRIPTION)
public class Website extends Space {

	public static final String API_PATH_FRAGMENT = "websites";
	public static final String API_MODEL_DESCRIPTION = "A model representing a Website, essentially a group of resources under a base path.";

	@NotNull
	@Column(name = "base_path", nullable = false)
	@Getter @Setter
	@ApiModelProperty(value = "The base path of applicable resources, default is '/'", required = true)
	private String basePath = "/";

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

	public Website() {
		super();
	}
	
	public Website(String id) {
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
		private String basePath = "/";
		private String name;
		private String title;
		private String description;
		private Host host;
		private User owner;
		private Space parent;
		private BillableAccount billableAccount;
		private ContextVisibilityType visibility;
		private Set<Membership> memberships;

		public Builder parent(Space parent) {
			this.parent = parent;
			return this;
		}

		public Builder billableAccount(BillableAccount billableAccount) {
			this.billableAccount = billableAccount;
			return this;
		}

		public Builder basePath(String basePath) {
			this.basePath = basePath;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder host(Host host) {
			this.host = host;
			return this;
		}

		public Builder owner(User owner) {
			this.owner = owner;
			return this;
		}

		public Builder visibility(ContextVisibilityType visibility) {
			this.visibility = visibility;
			return this;
		}

		public Website build() {
			return new Website(this);
		}
	}

	private Website(Builder builder) {
		this.setParent(builder.parent);
		this.setBasePath(builder.basePath);
		this.setName(builder.name);
		this.setBillableAccount(builder.billableAccount);
		this.setTitle(builder.title);
		this.setDescription(builder.description);
		this.setOwner(builder.owner);
		this.setHost(builder.host);
		this.setVisibility(builder.visibility);
	}
}

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

import com.restdude.domain.cases.model.CaseWorkflow;
import com.restdude.domain.cases.model.Space;
import com.restdude.domain.cases.model.SpaceCasesApp;
import com.restdude.domain.cases.model.enums.ContextVisibilityType;
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

/**
 * {@value #API_MODEL_DESCRIPTION}
 */
@Entity
@Table(name = "context_website_notes_app")
@ModelResource(pathFragment = WebsiteNotesApp.API_PATH_FRAGMENT,
		apiName = "Website",
		apiDescription = "Website Operations")
@ApiModel(description = WebsiteNotesApp.API_MODEL_DESCRIPTION)
public class WebsiteNotesApp extends SpaceCasesApp<Note> {

	public static final String API_PATH_FRAGMENT = "websiteNoteApps";
	public static final String API_MODEL_DESCRIPTION = "A model representing a Website notes application.";

	@NotNull
	@Column(name = "base_path", nullable = false, unique = true)
	@Getter @Setter
	@ApiModelProperty(value = "The base path of applicable resources, default is '/'", required = true)
	private String basePath;


	public WebsiteNotesApp() {
		super();
	}

	public WebsiteNotesApp(String id) {
		this();
		this.setPk(id);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.append("name", this.getName())
				.toString();
	}

	public static class Builder {
		private String basePath = "/";
		private String name;
		private String title;
		private String description;
		private String avatarUrl = Constants.DEFAULT_AVATAR_URL;
		private String bannerUrl = Constants.DEFAULT_BANNER_URL;
		private User owner;
		private ContextVisibilityType visibility = ContextVisibilityType.CLOSED;
		private Space space;
		private CaseWorkflow workflow;

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

		public Builder space(Space space) {
			this.space = space;
			return this;
		}

		public Builder visibility(ContextVisibilityType visibility) {
			this.visibility = visibility;
			return this;
		}

		public Builder workflow(CaseWorkflow workflow) {
			this.workflow = workflow;
			return this;
		}

		public WebsiteNotesApp build() {
			return new WebsiteNotesApp(this);
		}
	}

	private WebsiteNotesApp(WebsiteNotesApp.Builder builder) {
		this.setBasePath(builder.basePath);
		this.setName(builder.name);
		this.setTitle(builder.title);
		this.setDescription(builder.description);
		this.setAvatarUrl(builder.avatarUrl);
		this.setBannerUrl(builder.bannerUrl);
		this.setOwner(builder.owner);
		this.setSpace(builder.space);
		this.setVisibility(builder.visibility);
		this.setWorkflow(builder.workflow);

	}

}

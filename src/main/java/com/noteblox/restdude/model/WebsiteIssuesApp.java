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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.restdude.domain.cases.model.CaseWorkflow;
import com.restdude.domain.cases.model.Space;
import com.restdude.domain.cases.model.SpaceCasesApp;
import com.restdude.domain.cases.model.enums.ContextVisibilityType;
import com.restdude.domain.users.model.User;
import com.restdude.mdd.annotation.model.ModelResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * {@value #API_MODEL_DESCRIPTION}
 */
@Entity
@Table(name = "context_website_issues_app")
@ModelResource(pathFragment = WebsiteIssuesApp.API_PATH_FRAGMENT,
		apiName = "Website",
		apiDescription = "Website Operations")
@ApiModel(description = WebsiteIssuesApp.API_MODEL_DESCRIPTION)
public class WebsiteIssuesApp extends SpaceCasesApp {

	public static final String API_PATH_FRAGMENT = "websiteIssueApps";
	public static final String API_MODEL_DESCRIPTION = "A model representing a Website notes parent.";

	@NotNull
	@Column(name = "base_path", nullable = false, unique = true)
	@Getter @Setter
	@ApiModelProperty(value = "The base path of applicable resources, default is '/'", required = true)
	private String basePath;


	public WebsiteIssuesApp() {
		super();
	}

	public WebsiteIssuesApp(String id) {
		this();
		this.setId(id);
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
		private String detail;
		private User owner;
		private ContextVisibilityType visibility = ContextVisibilityType.CLOSED;
		private Space parent;
		private Space space;
		private CaseWorkflow workflow;

		public Builder parent(Space parent) {
			this.parent = parent;
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

		public Builder detail(String detail) {
			this.detail = detail;
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

		public WebsiteIssuesApp build() {
			return new WebsiteIssuesApp(this);
		}
	}

	private WebsiteIssuesApp(WebsiteIssuesApp.Builder builder) {
		this.setBasePath(builder.basePath);
		this.setName(builder.name);
		this.setTitle(builder.title);
		this.setDetail(builder.detail);
		this.setOwner(builder.owner);
		this.setSpace(builder.space);
		this.setVisibility(builder.visibility);
		this.setWorkflow(builder.workflow);

	}

}

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
package com.noteblox.restdude.service;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by manos on 3/4/2017.
 */
@Slf4j
public class JiraService {

    public Issue getIssue(String id){
        String jiraServer = "https://noteblox.atlassian.net";
        URI jiraServerUri = toUri(jiraServer);
        String un = "admin";
        String pw = "admin";

        Issue issue = null;

        final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, un, pw);
        try {
            issue = restClient.getIssueClient().getIssue(id).claim();
            log.debug("getIssue: {}", issue);
        }
        finally {
            // cleanup the restClient
            try {
                restClient.close();
            } catch (IOException e) {
                log.warn("Failed closing rest client", e);
            }
        }
        return issue;
    }

    @Async
    public Promise<BasicIssue> createIssue(String id){
        String jiraServer = "https://noteblox.atlassian.net";
        URI jiraServerUri = toUri(jiraServer);
        String un = "admin";
        String pw = "admin";
        String summary = "NewIssue#";
        String projectKey = "TST";
        Long issueTypeId = 1L;

        Promise<BasicIssue> issue = null;

        final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, un, pw);
        try {

            final IssueRestClient issueClient = restClient.getIssueClient();

            final IssueInput newIssue = new IssueInputBuilder(projectKey, issueTypeId, summary).build();
            issue = issueClient.createIssue(newIssue);
            log.debug("createIssue: {}", issue);
        }
        finally {
            // cleanup the restClient
            try {
                restClient.close();
            } catch (IOException e) {
                log.warn("Failed closing rest client", e);
            }
        }
        return issue;
    }

    private URI toUri(String jiraServer){
        try {
            return new URI(jiraServer);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

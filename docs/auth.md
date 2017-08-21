---
layout: default
title: Auth Guide
description: Authentication, authorization and integration reference
isHome: true
---

# Introduction

NoteBLOX services provide a number of stateloess authentication mechanisms like HTTP Basic, JSON Web Token (JWT) and Social Sign-in with networks like Google+, Facebook, Linkedin, Github and others. Other notable features include configurable CORS for Cross-Origin requests and case forwarding to third party systems like JIRA and Github.

Under the hood, NoteBLOX uses technologies like <a href="https://projects.spring.io/spring-security/">Spring Security</a>, a framework that focuses on providing both authentication and authorization to Java applications and can be easily extended to meet custom requirements. Spring Security supports varuous technologies out of the box, including in-memory, JDBC, LDAP, OAuth, SAML, Kerberos and others.

This document provides a reference of core authentication, authorization and integration mechanisms available in NoteBLOX.

<!-- TOC depthFrom:2 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [Authentication](#authentication)
	- [Using JWT](#using-jwt)
		- [Access Tokens](#access-tokens)
		- [Refresh Tokens</h2>](#refresh-tokensh2)
		- [JWT Settings](#jwt-settings)
	- [Using HTTP Basic](#using-http-basic)
	- [Using Social Sign-in](#using-social-sign-in)
		- [Application Registration](#application-registration)
		- [Social Configuration](#social-configuration)
	- [Validating Credentials](#validating-credentials)
		- [Using a Database](#using-a-database)
		- [Using LDAP](#using-ldap)
		- [Using OAuth](#using-oauth)
		- [Custom Mechanisms](#custom-mechanisms)
	- [Misc](#misc)
		- [Anonymous Auth](#anonymous-auth)
		- [Cookies Configuration](#cookies-configuration)
		- [CSRF Protection](#csrf-protection)
		- [Dynamic CORS](#dynamic-cors)
- [Authorization](#authorization)
	- [Global Roles](#global-roles)
	- [Context Roles](#context-roles)
- [Integration](#integration)
	- [Client Side Integration](#client-side-integration)
		- [Widget Configuration](#widget-configuration)
		- [Github Pages Example](#github-pages-example)
	- [Case Forwarding](#case-forwarding)
		- [Forwarding to JIRA](#forwarding-to-jira)
		- [Forwarding to Github](#forwarding-to-github)

<!-- /TOC -->


## Authentication

### Using JWT

#### Access Tokens

To obtain a JWT Access token while authenticating, make an HTTP request as follows:

```javascript
// POST /api/auth/jwt/access
// Headers: Accept=application/json; charset=UTF-8
//		Content-Type=application/json; charset=UTF-8
// Body:
{
    "password": "foo",
    "username": "bar"
}
```

If the credentials are valid, you will receive a JSON response body with the corresponding user details and an `access_token` cookie that contains the compact, signed JWT Access token:

```javascript
// HTTP/1.1 201 Created
// Content-Type: application/json;charset=utf-8
// Set-Cookie: access_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2Vycy80ZjBmY2ZmMC1jOTQyLTRmYjEtYThjYS0xOGM4NDMyYWQwNmQiLCJuYW1lIjoiQWRtaW4gVXNlciIsImdpdmVuX25hbWUiOiJBZG1pbiIsImZhbWlseV9uYW1lIjoiVXNlciIsInByZWZlcnJlZF91c2VybmFtZSI6ImFkbWluIiwibG9jYWxlIjoiZW4iLCJzY29wZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJleHAiOjE0ODk3MDA2MjR9.NPuRqNojx1EsaE3r844aF6syj2Vg0qkrWpxWMFZRfTALygaugkmA95zmwIXM_utrmi5Z8BqDJyTLx32Pa7XItQ;Path=/;Domain=.localhost
// Set-Cookie: refresh_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlcyI6WyJST0xFX1JFRlJFU0hfVE9LRU4iXSwiZXhwIjoxNDg5NzAwNjI0fQ.hF76jw_BPReJy9W-dATjMbGFSO6j71KqNqIzaLfVPzRMU67bvxZ3jD9cW0Cd3IroJdR53GOq-wdCa4gQK9YhSg;Path=/;Domain=.localhost
// Body
    {
    "pk": "4f0fcff0-c942-4fb1-a8ca-18c8432ad06d",
    "username": "foo",
    "lastPassWordChangeDate": null,
    "emailHash": "6ae965c1342be697f5fd386090e2c22e",
    "firstName": "Foo",
    "lastName": "Bar",
    // other members...
    "roles": [
        {"authority": "ROLE_ADMIN"},
        {"authority": "ROLE_USER"}
    ]
}
```

#### Refresh Tokens</h2>

TBA

#### JWT Settings

The following application properties control JWT generation:

- `restdude.jwt.accessTokenMinutes`: The amount of minutes JWT Access tokens are valid.
- `restdude.jwt.refreshTokenMinutes`: The amount of minutes JWT Refresh tokens are valid. Should be greater than the above.
- `restdude.jwt.tokenIssuer`: The token issuer.
- `restdude.jwt.tokenSigningKey`: The secret key used to sign and verify JWTs.

### Using HTTP Basic

NoteBLOX supports Basic Authentication, mostly as a convenience during development. To login and further access the app using Basic Auth, make an HTTP request as follows:

```javascript
// POST /api/auth/basic
// Headers: Accept=application/json; charset=UTF-8
//		Content-Type=application/json; charset=UTF-8
// Body:
{
    "password": "foo",
    "username": "bar"
}
```


If the credentials are valid, you will receive a JSON response body with the corresponding user details and a ```restdude-sso``` cookie that will be the equivalent of an `Authentication: Basic` HTTP header when transparently sent back to the server in subsequent requests:


```javascript
// HTTP/1.1 201 Created
// Content-Type: application/json;charset=utf-8
// Set-Cookie: restdude-sso=YWRtaW46YWRtaW4=;Path=/;Domain=.localhost
// Body
{
    "pk": "4f0fcff0-c942-4fb1-a8ca-18c8432ad06d",
    "username": "foo",
    "lastPassWordChangeDate": null,
    "emailHash": "6ae965c1342be697f5fd386090e2c22e",
    "firstName": "Foo",
    "lastName": "Bar",
    // other members...
    "roles": [
        {"authority": "ROLE_ADMIN"},
        {"authority": "ROLE_USER"}
    ]
}
```

### Using Social Sign-in

NoteBLOX provides transparent registration and social sign-in using Spring Social. Linkedin, Facebook, Github and other networks are supported.

#### Application Registration

TBA

#### Social Configuration

TBA

### Validating Credentials

TBA

#### Using a Database

TBA

#### Using LDAP

TBA

#### Using OAuth

TBA

#### Custom Mechanisms

TBA

### Misc

#### Anonymous Auth

TBA

#### Cookies Configuration

Cookies are perhaps the most secure way of storing information on the client, provided SSL is used and cookies are configured as secure and HTTP-Only, i.e. not accessible by scripts. The following application properties control cookies configuration:

- `restdude.cookies.httpOnly`: true/false.
- `restdude.cookies.secure`: true/false.

#### CSRF Protection

TBA

#### Dynamic CORS

NoteBLOX supports both static and dynamic Cross-Origin Resource Sharing (CORS) configuration, the latter down to entity model instance.

TBA

## Authorization

TBA

### Global Roles

TBA

### Context Roles

TBA

## Integration

TBA

### Client Side Integration

TBA

#### Widget Configuration

TBA

#### Github Pages Example

TBA

### Case Forwarding

TBA

#### Forwarding to JIRA

TBA

#### Forwarding to Github

TBA

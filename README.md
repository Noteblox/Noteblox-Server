[![Build Status](https://travis-ci.org/Noteblox/Noteblox-Server.svg?branch=master)](https://travis-ci.org/Noteblox/Noteblox-Server)

<!-- TOC depthFrom:2 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [NoteBLOX - In Application Ticketing](#noteblox-in-application-ticketing)
		- [Without In Application Ticketing](#without-in-application-ticketing)
		- [With In Application Ticketing](#with-in-application-ticketing)
		- [How to use NoteBLOX](#how-to-use-noteblox)
		- [What do Users see?](#what-do-users-see)
		- [What do Developers see?](#what-do-developers-see)
- [Common Questions](#common-questions)
	- [How do I submit a ticket into Jira or GitHub Issues?](#how-do-i-submit-a-ticket-into-jira-or-github-issues)
	- [How much does NoteBLOX cost?](#how-much-does-noteblox-cost)
	- [Will NoteBLOX only work in a SaaS environment?](#will-noteblox-only-work-in-a-saas-environment)
	- [Will NoteBLOX work with other job ticketing or help desk systems?](#will-noteblox-work-with-other-job-ticketing-or-help-desk-systems)
	- [Will you or anybody support NoteBLOX for my company?](#will-you-or-anybody-support-noteblox-for-my-company)
- [When to Use NoteBLOX=](#when-to-use-noteblox)
	- [Configuration & Use Questions](#configuration-use-questions)
			- [Can NoteBLOX process be added/adjusted?](#can-noteblox-process-be-addedadjusted)
			- [Does NoteBLOX allow for two way communication?](#does-noteblox-allow-for-two-way-communication)
			- [How does NoteBLOX work with dynamic system like Sugar CRM?](#how-does-noteblox-work-with-dynamic-system-like-sugar-crm)
			- [How does NoteBLOX work with breadcrumb systems where every unique page has a URL?](#how-does-noteblox-work-with-breadcrumb-systems-where-every-unique-page-has-a-url)
			- [Why don't the screenshots always look perfect?](#why-dont-the-screenshots-always-look-perfect)
	- [Customize](#customize)
	- [Documentation](#documentation)
	- [Build and Run](#build-and-run)
		- [Prerequisites](#prerequisites)
		- [Checkout](#checkout)
		- [Build](#build)
		- [Run](#run)
		- [Run Integration Tests](#run-integration-tests)
	- [Architecture](#architecture)
	- [License](#license)

<!-- /TOC -->

## NoteBLOX - In Application Ticketing

We think the job ticketing process is broken. Or at least, it’s limping along. We think that the inbound emails, phones and the difficulty in submitting Jira (or GitHub Issues) for your GitHub applications can be improved.

NoteBLOX is the world's first In Application Ticketing System.  The whole idea of In Application Ticketing (NoteBLOX) revolves around users not having to leave what they are doing.

#### Without In Application Ticketing

Let’s say you have written a CRM system (could be any system) in GitHub and there is a problem. Does it make sense to have users make a phone call or send an email or log it in Jira or GitHub Issues? It really makes no sense. Fields must be filled out, screenshots made, etc.. I don’t need to go through all the details that must be shared - DOM tree, click path, device, browser and more. It's a PITA (pain in the ass).

#### With In Application Ticketing

Right from the system (CRM, PO system, Insurance, etc...) the user should be able to just demark the issue and have the technical information captured automatically.

Using NoteBLOX, users can bypass email, calls and Jira/GitHub Issues...and submit issues directly.

* NoteBLOX brings the ticketing process closer to the user, things are faster and smoother and less problematic.
* Users report issues with more accuracy.
* Users spend less time reporting issues.
* GitHub Developers spend less time understanding what the user is describing.
* GitHub Developers ask fewer questions on technical details.

Most of all, Users don't need to leave the application with the issue. They can do it right within your application.

#### How to use NoteBLOX

If your are on GitHub its easy. Simply add the line of code listed below, and fill in your Jira/GitHub Issues authentication and NoteBLOX is activated.

#### What do Users see?

(*animated gif*))

#### What do Developers see?

(*animated gif*)



## Common Questions

### How do I submit a ticket into Jira or GitHub Issues?

You can filter through and curate content in NoteBLOX, deciding what issue are worth ticketing. Or else, you can have Notes (from NoteBLOX) feed Jira or GitHub Issues automatically.

### How much does NoteBLOX cost?

NoteBLOX is opensource. You can use it here for free or you can download it and use it for free behind your firewall.  

### Will NoteBLOX only work in a SaaS environment?

We made NoteBLOX so it can run anywhere. NoteBLOX can run right here on GitHub or behind your firewall

### Will NoteBLOX work with other job ticketing or help desk systems?

NoteBLOX is build on an open API. The current version is integrated with GitHub Issues and Jira. However, if you like NoteBLOX and want to use it somewhere else, go right ahead. Use the API or contact us. We would be happy to help.

### Will you or anybody support NoteBLOX for my company?

If you need help, reach out to us at david.ostrov@noteblox.com  We are happy to help.

## When to Use NoteBLOX=

NoteBLOX is designed for the entire lifecycle of an application or web site.

Use NoteBLOX during the QA process

(*animation showing user submitting a QA issue*)

Use NoteBLOX on a deployed application
(*animation showing a user entering data and asking for a field revision*)


### Configuration & Use Questions

##### Can NoteBLOX process be added/adjusted?

Yes, NoteBLOX has an admin console that will allow you to adjust or change processes.

##### Does NoteBLOX allow for two way communication?

Yes, you can interact with your users right in NoteBLOX and get clarification.

##### How does NoteBLOX work with dynamic system like Sugar CRM?


##### How does NoteBLOX work with breadcrumb systems where every unique page has a URL?

##### Why don't the screenshots always look perfect?

Screenshots can be a simple thing are are a complicated thing. For most applications the screenshots will look perfect.

However, realize that a screenshot is a represenation of what we think the user is seeing based on the code. Browser secruity protocoals prevent you seeing exactly what a user is seeing. This is just the way it is. For more information on this feel free to read the details form Google, Firefox, Apple and Microfsoft.(*need links*)

If you are doing web site development and utilize technologies like Javascript for animations and movement a Note in NoteBLOX can be associated to the DOM element, but we don't really know exactly what the user is seeing.
.and you utilize technologies like The first animation/new techologies is mostly related to new web site development. Some technologies like javascript have movement. There are many others. To capture an image in a screenshot of an animation

visual
Realize that browser security settings can prevent you from being seeing everything your users are doing
If you want an exact representation of your user screenshot we reccomend you thry URL2PNG. Realize that ine


### Customize

You can make a NoteBLOX widget look anyway you want. We use the standard bootstrap library.
(*animated gif*)

You can collect additional data by adding funcationality. The NoteBLOX API allows for simple easy integration.
(*link to data collection api*)

NoteBLOX is an on-page support and collaboration tool for websites.

### Documentation

- [Project pages](https://noteblox.github.io/Noteblox-Server/)
- [REST API reference](https://noteblox.github.io/Noteblox-Server/restapi.html)

### Build and Run

#### Prerequisites

- Java Development Kit 1.8 (either OpenJDK or Oracle)
- Apache Maven 3.3.9

#### Checkout

```
git clone https://github.com/Noteblox/Noteblox-Server.git
```

#### Build

```
mvn  clean install
```

#### Run

```
mvn  spring-boot:run
```

<img src="docs/assets/images/run.png">

#### Run Integration Tests

```
mvn  clean install -P ci
```
<img src="docs/assets/images/tests.png">

### Architecture

<pre>
                    +-------------------------+   +---------------------+   +------------------+
 Browser, App,      | RESTful SCRUD           |   | Content negotiation |   | Websockets       |
 or other Client    +-------------+-----------+   +-----------+---------+   +-------+----------+
                                  |                           |                     |
--------------------------------- | ------------------------- | ------------------- | -----------
                                  |                           |                     |
                      JSON+HATEOAS or JSON-API 1.x            |                     |
 Network              with RSQL/FIQL or URL params            |                   STOMP
                                  |                           |                     |
                                  |                           |                     |
--------------------------------- | ------------------------- | ------------------- | -----------
                                  |                           |                     |
                    +-------------+---------------------------+----------+  +-------+----------+
                    |     <strong>Controller</strong>                                     +--+ Message Broker   |
                    +--------------------------+-------------------------+  +-------+----------+
                                               |                                    |
 Restdude                                      |                                    |
                    +--------------------------+------------------------------------+----------+
                    |                                    <strong>Service</strong>                               |
                    +--------+--------------------+---------------------+---------------+------+
                             |                    |                     |               |
                             |                    |                     |               |
                    +--------+-------+ +----------+-----------+ +-------+-------+ +-----+------+
                    |     <strong>Repository</strong> | | FileService (FS, S3) | | EmailService  | | Misc Util  |
                    +----------------+ +----------------------+ +---------------+ +------------+
</pre>


### License

NoteBLOX is distributed under the <a href="https://www.gnu.org/licenses/agpl-3.0-standalone.html">GNU Affero General Public License</a> <img style="float: right;" src="docs/assets/images/agplv3-155x51.png" alt="AGPL Logo" />

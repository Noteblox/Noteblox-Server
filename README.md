NoteBLOX - In Application Ticketing
===================================
![alt tag

Save a sh-tload of time when users create job tickets from inside your GitHub application. 





[![Build Status](https://travis-ci.org/Noteblox/Noteblox-Server.svg?branch=master)](https://travis-ci.org/Noteblox/Noteblox-Server)

# NoteBLOX Server

<!-- TOC depthFrom:2 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [Howto](#howto)
	- [Prerequisites](#prerequisites)
	- [Checkout](#checkout)
	- [Build](#build)
	- [Run](#run)
	- [Run Integration Tests](#run-integration-tests)
- [Architecture](#architecture)
- [License](#license)

<!-- /TOC -->

NoteBLOX is an on-page support and collaboration tool for websites.

## Documentation

- [Project pages](https://noteblox.github.io/Noteblox-Server/)
- [REST API reference](https://noteblox.github.io/Noteblox-Server/restapi.html)

## Build and Run

### Prerequisites

- Java Development Kit 1.8 (either OpenJDK or Oracle)
- Apache Maven 3.3.9

### Checkout

```
git clone https://github.com/Noteblox/Noteblox-Server.git
```

### Build

```
mvn  clean install
```

### Run

```
mvn  spring-boot:run
```

<img src="docs/assets/images/run.png">

### Run Integration Tests

```
mvn  clean install -P ci
```
<img src="docs/assets/images/tests.png">

## Architecture

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


## License

NoteBLOX is distributed under the <a href="https://www.gnu.org/licenses/agpl-3.0-standalone.html">GNU Affero General Public License</a> <img style="float: right;" src="docs/assets/images/agplv3-155x51.png" alt="AGPL Logo" />

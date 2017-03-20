[![Build Status](https://travis-ci.org/Noteblox/Noteblox-Server.svg?branch=master)](https://travis-ci.org/Noteblox/Noteblox-Server)

# NoteBLOX Server

NoteBLOX is an on-page support and collaboration tool for websites.


## Howto 

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
    
Restdude is distributed under the <a href="https://www.gnu.org/licenses/agpl-3.0-standalone.html">GNU Affero General Public License</a> <img style="float: right;" src="docs/assets/images/agplv3-155x51.png" alt="AGPL Logo" />

# Web Crawler

## Building requirements
* Apache Maven
* JDK 8

To build it generating an executable fat jar:

    mvn clean package

## Running

Using the fat jar generated in the target directory:

    java -jar webcrawler-1.0-SNAPSHOT.jar -p <page>

where the specified page must be a valid URL.

e.g.

    java -jar webcrawler-1.0-SNAPSHOT.jar -p http://wiprodigital.com

## Notes

* Very basic implementation, by default avoids to crawl twice the same link and the external ones.
* Therefore the map of a link will appear only once, but since no specific order has been provided, the children links could appear in any of the links occurences.

## ToDos

### Functionality

* More options, from verbosity, depth, output file, etc.
* More filters and the option to gather more information from the page (static content, etc.)
* Better control of network timeouts, adding retries, etc.
* Enable parallel processing and related config options.

### Development

Depending on the usage and lifecycle a better quality should be provided.
Among other things:

* Add logging and enhance the exception handling.
* Add proper unit testing plus cobertura (jacoco, pitest).
* Add RPM packaging to distribute the tool.

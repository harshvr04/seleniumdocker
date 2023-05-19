FROM openjdk:11.0.5-jre-slim-buster

RUN apt-get -y update
RUN apt-get -y install curl jq

#Workspace dir for the container/VM
WORKDIR /usr/share/seleniumdocker

#ADD .jar under target from host
ADD target/selenium-docker.jar selenium-docker.jar
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/libs libs

#ADD suite files
ADD testng.xml book-flight-module.xml
ADD search-module.xml search-module.xml

#ADD healthcheck.sh
ADD healthcheck.sh healthcheck.sh

ENTRYPOINT bash healthcheck.sh



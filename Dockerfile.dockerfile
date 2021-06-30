#adding the base image
FROM adoptopenjdk/openjdk13:x86_64-alpine-jre-13.0.2_8

#add curl and jq
RUN apk add curl jq



#adding workdir in the container
WORKDIR /user/testproj

##adding the jar files to the image
ADD target/selenium-docker.jar          selenium-docker.jar
ADD target/selenium-docker-tests.jar    selenium-docker-tests.jar
ADD target/libs                         libs

## adding the suite xml file to the image
ADD testng.xml testng.xml

#adding healthcheck script
ADD healthcheck.sh healthcheck.sh
RUN dos2unix healthcheck.sh

# executing the test jars as entrypoint so when we run the image, the test executes
ENTRYPOINT sh healthcheck.sh
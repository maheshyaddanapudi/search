# To Run this file try "docker build -t searcher:latest ."

FROM maven:3.6.3-jdk-8 as builder

# Setting JAVA_HOME for performing Maven build.
ENV JAVA_HOME /usr/local/openjdk-8
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Creating base directory
RUN mkdir /tmp/searcher

COPY pom.xml /tmp/searcher/
COPY src /tmp/searcher/src

# Building the executable.
RUN cd /tmp/searcher \
  && mvn clean install -Dmaven.test.skip=true -q

FROM openjdk:8-jdk as server

ENV DEBIAN_FRONT_END noninteractive

# Declaring internal / defaults variables
ENV MYSQL_HOST localhost
ENV MYSQL_PORT 3306
ENV MYSQL_USERNAME searcher
ENV MYSQL_PASSWORD searcher
ENV MYSQL_DATABASE searcher
ENV ELASTICSEARCH_CLUSTER_NAME searcher
ENV ELASTICSEARCH_HOST localhost
ENV ELASTICSEARCH_PORT 9200
ENV SPRING_PROFILES_ACTIVE default
ENV SEARCHER_VERSION 1.0.0

WORKDIR /

# Starting up as root user
USER root

# Installing all the base necessary packages for execution of embedded MariaDB4j i.e. Open SSL, libaio & libncurses5
RUN apt-get -y -qq update --ignore-missing --fix-missing \
  && apt-get -y -qq install sudo

# Creating necessary directory structures to host the platform
RUN mkdir /appln /appln/bin /appln/bin/searcher /appln/scripts

# Creating a dedicated user searcher
RUN groupadd -g 999 searcher \
  && useradd -u 999 -g searcher -G sudo --create-home -m -s /bin/bash searcher \
  && echo -n 'searcher:searcher' | chpasswd

# Delegating password less SUDO access to the user searcher
RUN sed -i.bkp -e \
      's/%sudo\s\+ALL=(ALL\(:ALL\)\?)\s\+ALL/%sudo ALL=NOPASSWD:ALL/g' \
      /etc/sudoers

# Taking the ownership of working directories
RUN chown -R searcher:searcher /appln

# Changing to the user searcher
USER searcher

# Moving the executable / build to the run location
COPY --from=builder /tmp/searcher/target/searcher*.jar /appln/bin/searcher/

# Creating the startup script, by passing the env variables to run the jar. Logs are written directly to continer logs.
RUN echo "#!/bin/bash" > /appln/scripts/startup.sh \
  && echo "cd /appln/bin/searcher" >> /appln/scripts/startup.sh \
  && echo "java \
  -Dspring.profiles.active=\$SPRING_PROFILES_ACTIVE \
  -DMYSQL_HOST=\$MYSQL_HOST \
  -DMYSQL_PORT=\$MYSQL_PORT \
  -DMYSQL_USERNAME=\$MYSQL_USERNAME \
  -DMYSQL_PASSWORD=\$MYSQL_PASSWORD \
  -DMYSQL_DATABASE=\$MYSQL_DATABASE \
  -DELASTICSEARCH_CLUSTER_NAME=\$ELASTICSEARCH_CLUSTER_NAME \
  -DELASTICSEARCH_HOST=\$ELASTICSEARCH_HOST \
  -DELASTICSEARCH_PORT=\$ELASTICSEARCH_PORT \
  -jar searcher-$SEARCHER_VERSION.jar" >> /appln/scripts/startup.sh

# Owning the executable scripts
RUN sudo chown -R searcher:searcher /appln/scripts /appln/bin \
    && sudo chmod -R +x /appln/scripts /appln/bin

# Exposing the necessary ports
EXPOSE 1987

# Enabling the startup
CMD ["/appln/scripts/startup.sh"]
ENTRYPOINT ["/bin/bash"]
# Searcher
Spring Boot - Elasticsearch - MYSQL Module for Faster Search Engine / Plugin

## With Embedded In-Memory Database(H2)
## With containerization (Docker & Docker Compose)


[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/dashboard?id=maheshyaddanapudi_searcher)

##### Note

      • Only MySQL is supported as external Database
      
      				Or
      
      • Embedded H2 is supported, which is in-memory and data is lost, on restarts


## Overview

The idea is to build a single production grade Spring Boot Jar with the following

      • Expose APIs to search data with FULL-TEXT search capability.
          This will be useful to create a search engine for any application in general.

## Motivation

### Flow

  API --> Cache --> Elasticearch <-- MySQL

### Requirement

      • Make an event search engine plugin which can return results faster across multiple records.

            • An event can be anything from a general data record in database to an actual event (both IT as in alerts received or logs written to database etc. / Real as in physical events like Sports / Concerts etc.)
      
      • Direct database queries will require both code enhancement and are taxing, build a data store layer - User Elasticsearch for this functionality.

      • Prepare a cache layer for returning reused results, instead of taxing the search layer.

      • Create a generic Database Table structure to host the event data.

## Tech / Framework used

      --> Docker Image to host the Jar. 
	  			
      --> Spring Boot Wrapper
            
            • H2
            
            • MySQL

            • Elasticsearch (Spring Data)

            • Flyway Initialiser

            • Logbook - for logging all http requests and responses.

## Code quality

SonarQube: [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=maheshyaddanapudi_searcher&metric=alert_status)](https://sonarcloud.io/dashboard?id=maheshyaddanapudi_searcher)

## Build using maven

		cd <to project root folder>
		mvn clean install
		
	The maven build should place the searcher-${serchear.version}.jar inside the target folder.

## Build CI (Continuous Integration)

| CI Provider | Status          |
| ------- | ------------------ |
| Circle CI   | [![maheshyaddanapudi](https://circleci.com/gh/maheshyaddanapudi/searcher.svg?style=shield)](https://circleci.com/gh/maheshyaddanapudi/searcher) |
| Java CI   | ![Java CI with Maven](https://github.com/maheshyaddanapudi/searcher/workflows/Java%20CI%20with%20Maven/badge.svg?branch=main) |
| Travis CI   | [![Build Status](https://travis-ci.com/maheshyaddanapudi/search.svg?branch=main)](https://travis-ci.com/maheshyaddanapudi/search) |

## Containerization CI (Continuous Integration)

| CI Provider | Status          |
| ------- | ------------------ |
| Docker   | ![Docker](https://github.com/maheshyaddanapudi/searcher/workflows/Docker/badge.svg?branch=main) |
| Docker Image CI   | ![Docker Image CI](https://github.com/maheshyaddanapudi/searcher/workflows/Docker%20Image%20CI/badge.svg?branch=main) |

Docker Image published to <a href="https://hub.docker.com/repository/docker/zzzmahesh/searcher" target="_blank">DockerHub here</a>

Image is equipped with basic tools like vim, curl, wget, net-tools(telnet), iputils-ping

To pull the image :

	docker pull zzzmahesh/searcher

## Run Searcher Boot : Java

		cd <to project root folder>/target
		
	Below command will start the Searcher Boot with Embedded H2 as Database (Required external Elasticsearch, if in local, the Searcher will try connecting to localhost:9200 by default. This can be overridden by passing runtime variable. See the configurables section below.)
		java -jar searcher-${searcher.version}.jar

    The profiles included by default are
        - h2

### Available Profiles

    1) mysql (cannot be selected alone, will need basic profile as well)
        This profile configures the external MySQL database details passed on to Integrated Searcher Server.
        Configurations available are as below. Shown are default values.
            MYSQL_DATABASE_HOST: localhost
            MYSQL_DATABASE_PORT: 3306
            MYSQL_DATABASE: searcher
            MYSQL_USER: searcher
            MYSQL_PASSWORD: searcher
            
    Default Startup will include - H2 (without-persistent-storage).

    For more detailed properties, refer to application-{profile}.yml file as per the required profile properties.

## Application URLs

		http://localhost:1987/ - To access the Swagger pertaining to APIs for searcher, as redirection is taken care to OpenAPI UI.

## Run Searcher Boot : Docker

To run the container :

    docker run --name searcher -p 1987:1987 -e ELASTICSEARCH_HOST=localhost -e ELASTICSEARCH_PORT=9200 -d zzzmahesh/searcher:latest

      NOTE: Assumption is that the Elasticsearch would be running in a container of it's own with 9200 port exposed. 

Few other examples / ways / configurations to run the container as:

    1) Running with external MySQL - The database is decoupled. The Searcher data is persistent as it uses MySQL Database.

        docker run --name searcher -p 1987:1987 \
            -e SPRING_PROFILES_ACTIVE=mysql \
            -e MYSQL_DATABASE_HOST=172.x.x.x \
            -e MYSQL_DATABASE_PORT=3306 \
            -e MYSQL_USER=searcher \
            -e MYSQL_PASSWORD=searcher \
            -d zzzmahesh/searcher:latest

    Similarly any combination of profile and configurations can be used.

#### All the below mentioned configurables / properties (under Available Profiles section) can be passed as Docker Container environment variables and will be set accordingly.

Available configurables - shown below with default values.

    MYSQL_DATABASE searcher
    MYSQL_USER searcher
    MYSQL_PASSWORD searcher
    MYSQL_DATABASE_HOST localhost
    MYSQL_DATABASE_PORT 3306
    ELASTICSEARCH_HOST localhost
    ELASTICSEARCH_PORT 9200
    SPRING_PROFILES_ACTIVE mysql


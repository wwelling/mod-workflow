# build base image
FROM maven:3-jdk-8-alpine as maven

# copy pom.xml
COPY ./pom.xml ./pom.xml

# copy components
COPY ./components ./components

# copy service
COPY ./service ./service

# install reactor modules
RUN mvn install

WORKDIR /service

# build service
RUN mvn package

# final base image
FROM openjdk:8u171-jre-alpine

# set deployment directory
WORKDIR /mod-workflow

# copy over the built artifact from the maven image
COPY --from=maven /service/target/workflow-service*.jar ./mod-workflow.jar

# settings
ENV LOGGING_LEVEL_FOLIO='INFO'
ENV SERVER_PORT='9001'
ENV SPRING_ACTIVEMQ_BROKER_URL='tcp://localhost:61616'
ENV SPRING_DATASOURCE_PLATFORM='h2'
ENV SPRING_DATASOURCE_URL='jdbc:h2:./mod-workflow;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE'
ENV SPRING_DATASOURCE_DRIVERCLASSNAME='org.h2.Driver'
ENV SPRING_DATASOURCE_USERNAME='folio'
ENV SPRING_DATASOURCE_PASSWORD='folio'
ENV SPRING_H2_CONSOLE_ENABLED='true'
ENV SPRING_H2_WEBALLOW='true'
ENV SPRING_JPA_DATABASE_PLATFORM='org.hibernate.dialect.H2Dialect'
ENV EVENT_QUEUE_NAME='event.queue'
ENV TENANT_DEFAULT_TENANT='tern'
ENV TENANT_INITIALIZE_DEFAULT_TENANT='false'
ENV OKAPI_URL='http://localhost:9130'

# expose ports
EXPOSE ${SERVER_PORT}
EXPOSE 61616

RUN mkdir -p activemq-data

# set the startup command to run your binary
CMD java -jar ./mod-workflow.jar \
  --logging.level.org.folio=${LOGGING_LEVEL_FOLIO} --server.port=${SERVER_PORT} --spring.activemq.broker-url=${SPRING_ACTIVEMQ_BROKER_URL} \
  --spring.datasource.platform=${SPRING_DATASOURCE_PLATFORM} --spring.datasource.url=${SPRING_DATASOURCE_URL} \
  --spring.datasource.driverClassName=${SPRING_DATASOURCE_DRIVERCLASSNAME} --spring.datasource.username=${SPRING_DATASOURCE_USERNAME} \
  --spring.datasource.password=${SPRING_DATASOURCE_PASSWORD} --spring.jpa.database-platform=${SPRING_JPA_DATABASE_PLATFORM} \
  --spring.h2.console.enabled=${SPRING_H2_CONSOLE_ENABLED} --spring.h2.console.settings.web-allow-others=${SPRING_H2_WEBALLOW} \
  --event.queue.name=${EVENT_QUEUE_NAME} --tenant.default-tenant=${TENANT_DEFAULT_TENANT} \
  --tenant.initialize-default-tenant=${TENANT_INITIALIZE_DEFAULT_TENANT} --okapi.url=${OKAPI_URL}

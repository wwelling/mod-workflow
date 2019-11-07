#Prerequisites JDK
FROM maven:3.6.1-jdk-8-alpine

#Settings
ENV ARTIFACT_VERSION='1.3.0-SNAPSHOT'
ENV MODULE_VERSION='sprint5-staging'
ENV LOGGING_LEVEL_FOLIO='INFO'
ENV SERVER_PORT='9001'
ENV SPRING_ACTIVEMQ_BROKER_URL='tcp://localhost:61616'
ENV SPRING_DATASOURCE_PLATFORM='h2'
ENV SPRING_DATASOURCE_URL='jdbc:h2:./workflow-db/workflow;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE'
ENV SPRING_DATASOURCE_DRIVERCLASSNAME='org.h2.Driver'
ENV SPRING_DATASOURCE_USERNAME='folio'
ENV SPRING_DATASOURCE_PASSWORD='folio'
ENV SPRING_H2_CONSOLE_ENABLED='true'
ENV SPRING_H2_WEBALLOW='true'
ENV SPRING_JPA_DATABASE_PLATFORM='org.hibernate.dialect.H2Dialect'
ENV EVENT_QUEUE_NAME='event.queue'
ENV TENANT_DEFAULT_TENANT='tern'
ENV OKAPI_LOCATION='http://localhost:9130'

#expose port
EXPOSE ${SERVER_PORT}
EXPOSE 61616

#Mvn
RUN apk add --no-cache curl git

#mod-data-extractor clone and MVN build
RUN mkdir -p /usr/local/bin/folio/
WORKDIR /usr/local/bin/folio
RUN git clone -b ${MODULE_VERSION} https://github.com/TAMULib/mod-workflow.git
WORKDIR /usr/local/bin/folio/mod-workflow
RUN mvn install
WORKDIR /usr/local/bin/folio/mod-workflow/service
RUN mkdir -p workflow-db
RUN mkdir -p activemq-data
RUN mvn package -DskipTests

#run java command
CMD java -jar /usr/local/bin/folio/mod-workflow/target/mod-workflow-${ARTIFACT_VERSION}.jar \
    --logging.level.org.folio=${LOGGING_LEVEL_FOLIO} --server.port=${SERVER_PORT} --spring.activemq.broker-url=${SPRING_ACTIVEMQ_BROKER_URL} \
    --spring.datasource.platform=${SPRING_DATASOURCE_PLATFORM} --spring.datasource.url=${SPRING_DATASOURCE_URL} \
    --spring.datasource.driverClassName=${SPRING_DATASOURCE_DRIVERCLASSNAME} --spring.datasource.username=${SPRING_DATASOURCE_USERNAME} \
    --spring.datasource.password=${SPRING_DATASOURCE_PASSWORD} --spring.h2.console.enabled=${SPRING_H2_CONSOLE_ENABLED} \
    --spring.jpa.database-platform=${SPRING_JPA_DATABASE_PLATFORM} --event.queue.name=${EVENT_QUEUE_NAME} \
    --tenant.default-tenant=${TENANT_DEFAULT_TENANT} --okapi.location=${OKAPI_LOCATION} --spring.h2.console.settings.web-allow-others=${SPRING_H2_WEBALLOW}
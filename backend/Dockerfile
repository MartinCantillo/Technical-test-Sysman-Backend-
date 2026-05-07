FROM tomcat:10.1-jdk17


RUN rm -rf /usr/local/tomcat/webapps/*


COPY target/backend-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war


ENV CATALINA_OPTS="-Djava.util.logging.level=INFO"


EXPOSE 8080

CMD ["catalina.sh", "run"]
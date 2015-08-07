FROM java:8

# This is the directory used by Tomcat to deploy the app 
VOLUME /tmp

ADD stokker-0.0.1-SNAPSHOT.jar app.jar

# Done to update the file timestamp
RUN bash -c 'touch /app.jar'

# urandom property modified for a faster startup (!!??)
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
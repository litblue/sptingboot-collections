FROM java:8
VOLUME /tmp
ADD springboot-fastjson-0.0.1-SNAPSHOT.jar /litblue-docker.jar
ENTRYPOINT ["java","-Djava.security.edg=file:/dev/./urandom","-jar","/litblue-docker.jar"]
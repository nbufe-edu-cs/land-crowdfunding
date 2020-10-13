FROM java:8
VOLUME /tmp
ADD land-crowdfunding.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
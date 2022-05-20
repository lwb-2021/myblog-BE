FROM centos

ADD jdk-11.0.2 /usr/local/

ADD my-blog-0.0.1-SNAPSHOT.jar /app.jar

ENV JAVA_HOME /usr/local/jdk-11.0.2
ENV PATH=$JAVA_HOME/bin:$PATH

VOLUME /root/my_blog_server_docker/file

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]

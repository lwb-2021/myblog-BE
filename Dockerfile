FROM centos

ADD jdk-11_linux-x64_bin.tar /usr/local/

ENV JAVA_HOME /usr/local/jdk-11
ENV PATH=$JAVA_HOME/bin:$PATH

VOLUME /root/my_blog_server_docker/file

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]

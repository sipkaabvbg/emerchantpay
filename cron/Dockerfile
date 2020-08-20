FROM eventstore/eventstore
RUN apt-get update && apt-get install -y cron 
ENV JAVA_HOME /usr/local/java
ENV PATH ${PATH}:/usr/local/java/bin
ADD jdk-11 /usr/local/java

ENV ANT_HOME /usr/local/ant   
ENV PATH ${PATH}:/usr/local/ant/bin
ADD apache-ant-1.9.15 /usr/local/ant

ADD . app

RUN export JAVA_HOME

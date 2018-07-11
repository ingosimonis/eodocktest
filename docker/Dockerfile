FROM openjdk:8-jdk

RUN apt-get update && apt-get install -y unzip wget

RUN wget https://github.com/ingosimonis/eodocktest/raw/master/zip/eodt.zip
RUN unzip eodt.zip
RUN rm eodt.zip


WORKDIR /


CMD java -jar eodt.jar

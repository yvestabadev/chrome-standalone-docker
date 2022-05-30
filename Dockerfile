FROM maven AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean compile assembly:single package

FROM openjdk:8-jdk-slim    
RUN apt-get update && \
    apt-get install -y gnupg wget curl unzip --no-install-recommends && \
    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list && \
    apt-get update -y && \
    wget -q --continue -P /chromedriver "http://chromedriver.storage.googleapis.com/101.0.4951.41/chromedriver_linux64.zip" && \
    unzip /chromedriver/chromedriver* -d /usr/bin
COPY --from=build /home/app/target/neopets-0.0.1-SNAPSHOT-jar-with-dependencies.jar /usr/local/lib/neopets.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/neopets.jar"]
FROM ubuntu:22.04
COPY target/HotelProject-1.0.0.jar app/HotelProject.jar
WORKDIR app/

# Install package for x11 hosting and openjdk
RUN apt-get update && \
    apt-get install -y libxext6 libxrender1 libxtst6 openjdk-17-jdk

# Run the project
CMD java -jar HotelProject.jar

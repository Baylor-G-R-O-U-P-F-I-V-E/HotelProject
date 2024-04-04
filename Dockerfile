FROM ubuntu:22.04
COPY . app
WORKDIR app/

# Install package for x11 hosting and openjdk
RUN apt-get update && \
	apt-get install -y libxext6 libxrender1 libxtst6 openjdk-20-jdk

# Run the project
CMD java -jar target/HotelProject-0.0.1-SNAPSHOT.jar

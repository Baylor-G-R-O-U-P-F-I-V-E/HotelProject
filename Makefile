$(JAR) := HotelProject-0.0.1-SNAPSHOT.jar

build:
	mvn package
.PHONY: build

run:
	java -jar target/$(JAR)
.PHONY: run

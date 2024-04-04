JAR = HotelProject-0.0.1-SNAPSHOT.jar
DOCKER_IMG = hotel_project

build:
	mvn package
.PHONY: build

run:
	java -jar target/$(JAR)
.PHONY: run

docker-build:
	docker build -t $(DOCKER_IMG) .
.PHONY: dockerize

docker-run:
	docker run -it -e DISPLAY=$(shell ipconfig getifaddr en0):0 $(DOCKER_IMG) || (echo "Did you run \`xhost \$$(ipconfig getifaddr en0)\` in an xquartz session?" && exit 1)
.PHONY: docker-run

run-no-tests:
	rm -r FinalProject
	mvn clean install -DskipTests
	mvn compile -DskipTests
	make run
.PHONY: run-no-tests

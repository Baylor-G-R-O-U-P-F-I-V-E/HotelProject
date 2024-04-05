JAR = HotelProject-0.0.1-SNAPSHOT.jar
DOCKER_IMG = hotel_project

help:
	@echo " \
		<target>       | Description \n\
		---------------|------------ \n\
		package        | Run \`mvn package\` \n\
		update         | Pull all dependencies without compiling project \n\
		compile        | Compile the project into a jar \n\
		run            | Run the jar \n\
		run-no-tests   | Compile and run the jar, skipping all test methods \n\
		docker-build   | Build Docker image \n\
		docker-run     | Run Docker container \n\
		clean          | Delete FinalProject/ for a fresh run \n\
		"
.PHONY: help

package:
	mvn package
.PHONY: package

update:
	mvn dependency:copy-dependencies
.PHONY: update

compile:
	mvn compile
.PHONY: compile

run:
	java -jar target/$(JAR)
.PHONY: run

run-no-tests:
	mvn clean install -DskipTests
	mvn compile -DskipTests
	make run
.PHONY: run-no-tests

docker-build:
	docker build -t $(DOCKER_IMG) .
.PHONY: dockerize

docker-run:
	docker run -it -e DISPLAY=$(shell ipconfig getifaddr en0):0 $(DOCKER_IMG) || (echo "Did you run \`xhost \$$(ipconfig getifaddr en0)\` in an xquartz session?" && exit 1)
.PHONY: docker-run

clean:
	rm -r FinalProject
.PHONY: clean

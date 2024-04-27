JAR = HotelProject-0.0.1-SNAPSHOT.jar
JVD = target/site/index.html

DFLAGS = -it -e DISPLAY=$(shell ipconfig getifaddr en0):0
DIMG = hotel_project

help:
	@echo " \
		<target>       | Description \n\
		---------------|------------ \n\
		build          | Runs \`mvn package\` \n\
		build-no-tests | Runs \`mvn package -Dmaven.test.skip=true\` \n\
		               | This packages the project without running test cases \n\
		update         | Pull all dependencies without compiling project \n\
		run            | Run the jar \n\
		docker-build   | Build Docker image \n\
		docker-run     | Run Docker container \n\
		clean-db       | Delete FinalProject/ for a fresh run \n\
		jvd            | Generate javadoc and open in browser \n\
		"
.PHONY: help

build:
	mvn package
.PHONY: build

build-no-tests:
	mvn package -Dmaven.test.skip=true
.PHONY: build-no-tests

update:
	mvn dependency:copy-dependencies
.PHONY: update

run:
	java -jar target/$(JAR)
.PHONY: run

docker-build:
	docker build -t $(DIMG) .
.PHONY: dockerize

docker-run:
	docker run $(DFLAGS) $(DIMG) || (echo "Did you run \`xhost \$$(ipconfig getifaddr en0)\` in an xquartz session?" && exit 1)
.PHONY: docker-run

clean-db:
	rm -ir FinalProject
.PHONY: clean-db

jvd:
	mvn site
	open $(JVD)
.PHONY: jvd

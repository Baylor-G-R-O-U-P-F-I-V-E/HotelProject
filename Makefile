JAR = HotelProject-0.0.1-SNAPSHOT.jar
JVD = target/site/index.html
DB = HotelDatabase
TEST_DB = TestHotelDatabase

DFLAGS = -it -e DISPLAY=$(shell ipconfig getifaddr en0):0
DIMG = hotel_project

help:
	@echo " \
		<target>       | Description \n\
		---------------|------------ \n\
		build          | Runs \`mvn package\` \n\
		update         | Pull all dependencies without compiling project \n\
		run            | Run the jar \n\
		docker-build   | Build Docker image \n\
		docker-run     | Run Docker container \n\
		clean-db       | Delete FinalProject/ for a fresh run \n\
		jvd            | Generate javadoc and open in browser \n\
		"
.PHONY: help

# Please don't ask why we are deleting the db
clean-build:
	rm -r $(DB)
	make build
.PHONY: clean-build

build:
	mvn package -Dmaven.test.skip=true
.PHONY: build

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
	@if [ -d "${DB}" ]; then \
		echo "rm -r ${DB}"; \
		rm -r $(DB); \
	fi
	@if [ -d "${TEST_DB}" ]; then \
		echo "rm -r ${TEST_DB}"; \
		rm -r $(TEST_DB); \
	fi
.PHONY: clean-db

jvd:
	mvn site
	open $(JVD)
.PHONY: jvd

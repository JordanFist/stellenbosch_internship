project_name='carcassonne'

.PHONY: all
all: build run

.PHONY: build
build: 
	@gradle build
	@clear

.PHONY: run
run:
	@cd build/classes/main/; java ${project_name}/Main

.PHONY: test
test: build
	@cd build/classes/test/; java ${project_name}/Main

.PHONY: clean
clean:
	@gradle clean

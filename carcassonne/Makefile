project_name='carcassonne'

.PHONY: all
all: run

.PHONY: build
build: 
	@gradle build

.PHONY: run
run:
	@cd build/classes/main/; java ${project_name}/Main

.PHONY: clean
clean:
	@gradle clean

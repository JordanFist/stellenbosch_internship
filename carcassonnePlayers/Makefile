project_name='carcassonne'

.PHONY: all
all: build run

.PHONY: build
build: 
	gradle build
	@clear

.PHONY: run
run:
	@cd build/classes/java/main/; java ${project_name}/Main

.PHONY: test
test:
	@cd build/classes/java/test/; java testSetTile

.PHONY: clean
clean:
	gradle clean

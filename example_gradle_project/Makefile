project_name='cars'

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
	gradle clean

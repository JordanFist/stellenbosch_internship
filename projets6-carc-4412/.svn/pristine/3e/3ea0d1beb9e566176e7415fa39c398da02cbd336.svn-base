CC=gcc
CFLAGS=-std=c99 -Wall -Wextra -I ${SRC_DIR}

SRC_DIR=src

.PHONY: all
all: build install test

build : client.so server

client.o: ${SRC_DIR}/client.c
	${CC} -fPIC ${CFLAGS} -c src/client.c


#Création des bibliothèques
client.so: client.o set_graph.o tile.o node.o 
	${CC} -fPIC ${CFLAGS} -shared client.o tile.o set_graph.o node.o -o client.so


server.o: ${SRC_DIR}/server.c ${SRC_DIR}/server.h
	${CC} -fPIC ${CFLAGS} -c ${SRC_DIR}/server.c -o server.o

deck.o: ${SRC_DIR}/deck.c ${SRC_DIR}/deck.h
	${CC} -fPIC ${CFLAGS} -c ${SRC_DIR}/deck.c -o deck.o

moves.o: ${SRC_DIR}/moves.c ${SRC_DIR}/moves.h
	${CC} -fPIC ${CFLAGS} -c ${SRC_DIR}/moves.c -o moves.o

tile.o: ${SRC_DIR}/tile.c ${SRC_DIR}/tile.h 
	${CC} -fPIC ${CFLAGS} -c ${SRC_DIR}/tile.c -o tile.o


set_graph.o: ${SRC_DIR}/set_graph.c ${SRC_DIR}/set_graph.h
	${CC} -fPIC ${CFLAGS} -c ${SRC_DIR}/set_graph.c -o set_graph.o

player.o: ${SRC_DIR}/player.c ${SRC_DIR}/player.h
	${CC} -fPIC ${CFLAGS} -c ${SRC_DIR}/player.c -o player.o

node.o: ${SRC_DIR}/node.c ${SRC_DIR}/node.h
	${CC} -fPIC ${CFLAGS} -c ${SRC_DIR}/node.c -o node.o

display.o: ${SRC_DIR}/display.c ${SRC_DIR}/display.h
	${CC} -fPIC ${CFLAGS} -c ${SRC_DIR}/display.c -o display.o

server: server.o deck.o moves.o set_graph.o tile.o player.o node.o display.o
	${CC} ${CFLAGS} -ggdb server.o deck.o moves.o set_graph.o tile.o player.o node.o display.o -o server -ldl

test: build install
	./install/server install/client.so install/client-2.so install/client-3.so

.PHONY: tests
tests:
	make -C tests

.PHONY: install
install: 
	cp server install/
	cp *.so install
	cp client.so install/client.so
	cp client.so install/client-2.so
	cp client.so install/client-3.so


.PHONY: clean
clean:
	rm -f *.o *.so 
	rm -f install/*.so
	rm -f install/server
	rm -f server
	make -C tests clean

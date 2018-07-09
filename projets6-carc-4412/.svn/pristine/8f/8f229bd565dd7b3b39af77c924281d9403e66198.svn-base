#ifndef TILE_H
#define TILE_H

#include <server.h>
#include <node.h>

#define SIDES 4
#define NEIGHBOUR_CARDS 4

struct tile {
  struct node *nodes[NUMBER_OF_DIRECTIONS];
  enum direction orientation;
  enum card_id card;
  struct position pos;
  //struct tile *neighbour_tiles[NEIGHBOUR_CARDS];
};

struct tile* init_tile(enum card_id ,enum direction,struct position);
void free_tile(struct tile *t);
void rotation(struct tile* t, enum direction dir);
boolean match_side(struct tile *t1, struct tile *t2, enum direction d);
enum landscape get_face(struct tile *t, enum direction d);
enum direction opposite_face(enum direction d);
boolean is_empty_tile(struct tile *t);

#endif

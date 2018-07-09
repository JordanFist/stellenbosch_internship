#ifndef SET_GRAPH_H
#define SET_GRAPH_H

#include <server.h>
#include <tile.h>
#include <player.h>
#include <node.h>

struct graph {
  struct tile *tiles[NUMBER_OF_CARDS];
  int number_of_tiles;
};

struct graph* init_graph();
void free_graph(struct graph*);
boolean match_card(struct graph *g, struct tile *t);
int is_playable(struct graph*, enum card_id card); 

struct tile *find_tile(struct graph *g, struct position const *p);
struct position neighbour_position(struct position *p, enum direction d);
struct tile *compute_neighbour(struct graph *g, struct tile *t, enum direction d);
boolean is_connectable(struct graph *g, struct tile *t);
int add_tail_tile(struct graph *g, struct move const *m);
boolean valid_move_card(struct graph *g, struct move *m);

#endif


#ifndef NODES_H
#define NODES_H

#include <server.h>

#define CONNEXIONS_PER_SIDE 4
#define NO_PLAYER -1

struct node {
  enum landscape land_type;
  boolean meeple;
  int meeple_owner;
  struct node *next_nodes[CONNEXIONS_PER_SIDE];
  int number_of_nodes;
};

struct node *init_node(enum card_id card, int i);
void free_node(struct node *n);

#endif

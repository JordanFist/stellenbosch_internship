#include <stdio.h>
#include <stdlib.h>
#include <tile.h>
#include <node.h>

// Initialise a tile, nodes of the tile and make the right rotation of the card
struct tile* init_tile(enum card_id id ,enum direction dir, struct position position) {
  struct tile *t = (struct tile *) malloc(sizeof(struct tile));
  if (t == NULL)
    exit(1);
  t->orientation = dir;
  t->pos = position;
  t->card = id;
  for (int i = 0; i < NUMBER_OF_DIRECTIONS; i++)
    t->nodes[i] = init_node(id, i);
  rotation(t, dir);
  return t;  
}

// Free a tile
void free_tile(struct tile* t) {
  for (int i = 0; i < NUMBER_OF_DIRECTIONS; ++i)
    free_node(t->nodes[i]);
  free(t);
}

// Check if the tile is empty
boolean is_empty_tile(struct tile *t) {
  if (t == NULL)
    return TRUE;
  return FALSE;
}

// Make a rotation on a tile
void rotation(struct tile* t, enum direction dir) {
  struct node *swap;
  for (unsigned int j = 0; j < 3 * dir; ++j) {
    swap = t->nodes[NUMBER_OF_DIRECTIONS - 2];
    for (int i = NUMBER_OF_DIRECTIONS - 2; i > 0; --i)
      t->nodes[i] = t->nodes[i - 1];
    t->nodes[0] = swap;
  }
}

// Return the landscape face tile
enum landscape get_face(struct tile *t, enum direction d) {
  return t->nodes[3 * d + 1]->land_type;
}

// Return the landscape opposite face tile
enum direction opposite_face(enum direction d) {
  return ((d + 2) % SIDES);
}

// Check if 2 cards match(but only 1 side)
boolean match_side(struct tile *t1, struct tile *t2, enum direction d) {
  if (t2 == NULL)
    return TRUE;
  enum landscape face1 = get_face(t1, d);
  enum landscape face2 = get_face(t2, opposite_face(d));
  return (face1 == face2);
}



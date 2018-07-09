#include <stdio.h>
#include <stdlib.h>
#include <graph.h>
#include <nodes.h>

struct tile* init_tile(enum card_id id ,enum direction dir, struct position position) {
  struct tile *t = (struct tile *) malloc(sizeof(struct tile));
  if (t == NULL)
    exit(1);
  t->orientation = dir;
  t->pos = position;
  t->card = id;
  for (int i = 0; i < NUMBER_OF_DIRECTIONS; i++)
    t->nodes[i] = init_node(id, i);
  init_next_nodes(t);
  rotation(t, dir);
  return t;  
}

void init_next_nodes(struct tile *t) {
  int check = 0;
  
  if (equal_landscape_node(t->nodes[0], t->nodes[NUMBER_OF_DIRECTIONS - 2]) == TRUE) 
    node_connection(t->nodes[0], t->nodes[NUMBER_OF_DIRECTIONS - 2]);
  
  for (int i = 0; i < NUMBER_OF_DIRECTIONS - 2; ++i) {
    if (landscape_node(t->nodes[i]) == landscape_node(t->nodes[i + 1])) {
      node_connection(t->nodes[i], t->nodes[i + 1]);
    }
    if (i > 0 && equal_landscape_node(t->nodes[i], t->nodes[NUMBER_OF_DIRECTIONS - 1]) && equal_landscape_node(t->nodes[i + 1], t->nodes[i]) == FALSE) {
      if (landscape_node(t->nodes[i]) == ROAD)
	node_connection(t->nodes[i], t->nodes[NUMBER_OF_DIRECTIONS - 1]);
      else if (check == 0) {
	node_connection(t->nodes[i], t->nodes[NUMBER_OF_DIRECTIONS - 1]);
	check = 1;
      }
    }
  }
  
  if (t->card == CARD_ROAD_TURN_RIGHT_CITY)
    node_connection(t->nodes[POS_SOUTH_EAST], t->nodes[POS_EAST_NORTH]);

  if (t->card == CARD_PLAIN_CITY_ROAD_SHLD)
    node_connection(t->nodes[POS_SOUTH_EAST], t->nodes[POS_EAST_NORTH]);

  if (t->card == CARD_PLAIN_CITY_ROAD)
    node_connection(t->nodes[POS_SOUTH_EAST], t->nodes[POS_EAST_NORTH]);

  if (t->card == CARD_JUNCTION_CITY)
    node_connection(t->nodes[POS_NORTH_EAST], t->nodes[POS_SOUTH_EAST]);

  if (t->card == CARD_PLAIN_TWO_CITIES) 
    node_disconnection(t->nodes[POS_NORTH_WEST], t->nodes[POS_WEST_NORTH]);

  if (t->card == CARD_ROAD_STRAIGHT_CITY)
    node_connection(t->nodes[POS_NORTH_EAST], t->nodes[POS_SOUTH_EAST]);

  if (t->card == CARD_ROAD_TURN_LEFT_CITY)
    node_connection(t->nodes[POS_NORTH_EAST], t->nodes[POS_WEST_SOUTH]);
  
  if (t->card == CARD_PLAIN_TUNNEL) 
    node_connection(t->nodes[POS_CENTER], t->nodes[POS_SOUTH_EAST]);

  if (t->card == CARD_CITY_ONE_SIDE)
    node_connection(t->nodes[POS_CENTER], t->nodes[POS_WEST_NORTH]);

  if (t->card == CARD_PLAIN_CITY)
    node_connection(t->nodes[POS_SOUTH_WEST], t->nodes[POS_CENTER]);

  if (t->card == CARD_PLAIN_CITY_SHLD)
    node_connection(t->nodes[POS_SOUTH_WEST], t->nodes[POS_CENTER]);
  
  if (t->card == CARD_CITY_THREE_ROAD) 
    node_disconnection(t->nodes[POS_CENTER], t->nodes[POS_SOUTH]);

  if (t->card == CARD_CITY_ALL_SIDES) {
    node_disconnection(t->nodes[POS_NORTH_EAST], t->nodes[POS_EAST_NORTH]);
    node_connection(t->nodes[POS_CENTER], t->nodes[POS_NORTH_EAST]);
  }

}

void free_tile(struct tile* t) {
  for (int i = 0; i < NUMBER_OF_DIRECTIONS; ++i)
    free_node(t->nodes[i]);
  free(t);
}

boolean is_empty_tile(struct tile *t) {
  if (t == NULL)
    return TRUE;
  return FALSE;
}

void rotation(struct tile* t, enum direction dir) {
  struct node *swap;
  for (unsigned int j = 0; j < 3 * dir; ++j) {
    swap = t->nodes[NUMBER_OF_DIRECTIONS - 2];
    for (int i = NUMBER_OF_DIRECTIONS - 2; i > 0; --i)
      t->nodes[i] = t->nodes[i - 1];
    t->nodes[0] = swap;
  }
}

enum landscape get_face(struct tile *t, enum direction d) {
  return t->nodes[3 * d + 1]->land_type;
}

enum direction opposite_face(enum direction d) {
  return ((d + 2) % SIDES);
}

//d direction de la carte 2 par rapport a la carte 1
boolean match_side(struct tile *t1, struct tile *t2, enum direction d) {
  if (t2 == NULL)
    return TRUE;
  enum landscape face1 = get_face(t1, d);
  enum landscape face2 = get_face(t2, opposite_face(d));
  return (face1 == face2);
}



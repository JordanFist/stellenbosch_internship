#include <stdio.h>
#include <stdlib.h>
#include <set_graph.h>

// Initialise a graph
struct graph* init_graph() {
  struct graph *g = (struct graph *) malloc(sizeof(struct graph));
  if (g == NULL)
    exit(1);
  g->number_of_tiles = 0;
  return g;
}

// Find a tile in the graph
struct tile *find_tile(struct graph *g, struct position const *p) {
  for (int i = 0; i < g->number_of_tiles; ++i) {
    if (g->tiles[i]->pos.x == p->x && g->tiles[i]->pos.y == p->y)
      return g->tiles[i];
  }
  return NULL;
}

// Return the neighbour_position
struct position neighbour_position(struct position *p, enum direction d) {
  struct position pos = {p->x, p->y};
  if (d == NORTH) 
    ++pos.y;
  if (d == WEST) 
    --pos.x;
  if (d == SOUTH) 
    --pos.y;
  if (d == EAST) 
    ++pos.x;
  return pos;
}

// Return the neighbour tile
struct tile *compute_neighbour(struct graph *g, struct tile *t, enum direction d) {
  struct position pos = neighbour_position(&t->pos, d);
  return find_tile(g, &pos);
}    

// Check around if a card can be placed (only one card direction)
boolean match_card(struct graph *g, struct tile *t) {
  int cpt = 0;
  for (int i = 0; i < SIDES; ++i) {
    if (match_side(t, compute_neighbour(g, t, i), i) == TRUE) {
      ++cpt;
    }
  }
  if (cpt == SIDES)
    return TRUE;
  return FALSE;
}

// Check around if a card can be placed in any card direction
boolean is_connectable(struct graph *g, struct tile *t) {
  for (int i = 0; i < SIDES; ++i) {
    if (match_card(g, t) == TRUE) {
      t->orientation = i;
      return TRUE;
    }
    rotation(t, WEST);
  }
  return FALSE;
}

// Check if a card can be played on the board
boolean is_playable(struct graph* g, enum card_id card) {
  struct position pos = {0, 0};
  struct tile *tile = init_tile(card, NORTH, pos);
  for (int i = 0 ; i < g->number_of_tiles ; i++) {
    for (int j = 0; j < NEIGHBOUR_CARDS; j++) {
      tile->pos = neighbour_position(&g->tiles[i]->pos, j);
      if((is_empty_tile(compute_neighbour(g, g->tiles[i], j)) == TRUE) && (is_connectable(g, tile) == TRUE)) {
	free_tile(tile);
	return TRUE;
      }
    }
  }
  free_tile(tile);
  return FALSE;
}

// Check if a player move is valid
boolean valid_move_card(struct graph *g, struct move *m) {
  struct tile *t = init_tile(m->card, m->dir, m->onto);
  if (match_card (g, t) == TRUE) {
    free_tile(t);
    m->check = VALID;
    return TRUE;
  }
  free_tile(t);
  return FALSE;
}

// Add a tile in the graph
int add_tail_tile(struct graph *g, struct move const *m) {
  struct tile *tile_add = init_tile(m->card, m->dir, m->onto);
  if (m->check == VALID) {
    g->tiles[g->number_of_tiles] = tile_add;
    ++g->number_of_tiles;
    return EXIT_SUCCESS; 
  } 
  free_tile(tile_add);
  return EXIT_FAILURE;
}

// Free the graph
void free_graph(struct graph *g) {
  for (int i = 0; i < g->number_of_tiles; ++i)
    free_tile(g->tiles[i]);
  free(g);
}




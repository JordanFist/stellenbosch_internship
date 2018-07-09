#include <server.h>
#include <stdio.h>
#include <stdlib.h>
#include <set_graph.h>
#include <tile.h>
#include <node.h>

#define FIRST_MOVE -1

static struct graph *graph;
unsigned int identity;


// Return the player name
char const *get_player_name() {
  char const *name = "Julien";
  return name;
}

// Look for a valid position to put a card on the board
struct move *search_valid_position_card(struct graph *g, struct move *m) {
  struct tile *tile = init_tile(m->card, m->dir, m->onto);
  for (int i = 0 ; i < g->number_of_tiles; i++) {
    for (int j = 0; j < NEIGHBOUR_CARDS; j++) {
      tile->pos = neighbour_position(&g->tiles[i]->pos, j);
      if((is_empty_tile(compute_neighbour(g, g->tiles[i], j)) == TRUE) && (is_connectable(g, tile) == TRUE)) {
	m->onto = tile->pos;
	m->dir = tile->orientation;
	free_tile(tile);
	return m;
      }
    }
  }
  free_tile(tile);
  return m;
}

// Initialise the player, first move and graph
void initialize(unsigned int id, unsigned int n_players) {
  identity = id;
  (void)n_players;
  graph = init_graph();
  struct move first_move = {VALID, FIRST_MOVE, CARD_ROAD_STRAIGHT_CITY, {0, 0}, NORTH, NO_MEEPLE};
  add_tail_tile(graph, &first_move);
  printf("Initialisation du client \n");
}

// Return the move played by the player
struct move play(enum card_id card, struct move const previous_moves[], size_t n_moves) {
  for (unsigned int i = 0; i < n_moves; ++i)
    add_tail_tile(graph, &previous_moves[i]);
  struct move m = {FAILED, identity, card, {0, 0}, NORTH, NO_MEEPLE};
  search_valid_position_card(graph, &m);
  return m; 
}

// Finalise the player, free graph
void finalize(){
  free_graph(graph);
  printf("finalisation du jeu du client \n");
}

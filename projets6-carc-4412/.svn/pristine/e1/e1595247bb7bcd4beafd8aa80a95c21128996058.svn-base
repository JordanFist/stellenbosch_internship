#include <stdlib.h>
#include <stdio.h>
#include <dlfcn.h>
#include <time.h>
#include <server.h>
#include <deck.h>
#include <moves.h>
#include <tile.h>
#include <set_graph.h>
#include <player.h>
#include <display.h>
#include <carcassonne_interface.h>

// Check if libraries are correctly loaded
void *sym(void *p, char *str) {
  void *val = dlsym(p, str);
  if (val == NULL) {
    printf("\nERREUR FUNC %s %s\n", str, dlerror());
    exit(1);
  }
  return val;
}

// Allow libraries to load
void *open(const char *str) {
  void *retval = dlopen(str, RTLD_NOW); 
  if (retval == NULL){
    printf("\nERREUR LIB %s %s\n", str, dlerror());
    exit(1);
  }
  return retval; 
}

// Load functions and initialise a struct player_base
void load_functions(void *p, struct player_base *base) {  
   base->initialize = sym(p, "initialize"); 
   base->get_player_name = sym(p, "get_player_name");
   base->play = sym(p, "play");
   base->finalize = sym(p, "finalize");
}

// Return the smallest size_t between x and y
size_t min(size_t x,size_t y) {
  if (x < y)
    return x;
  return y;
}

int main(int argc, char *argv[]) {

  srand(time(NULL));

  /* Initialisation of the game */
  
  struct graph *graph = init_graph();
  struct deck *deck = init_deck();
  struct players *players = init_players(argc - 1);
  struct moves *moves = init_moves();

  void *tab_pointeurs[players->number_of_players];
  printf("\n");
  for (int i = 0; i < players->number_of_players; i++) {
    tab_pointeurs[i] = open(argv[i + 1]);
    load_functions(tab_pointeurs[i], &players->players[i].p);
    players->players[i].p.initialize(i, players->number_of_players);
  }

  struct position init_pos = {0, 0};
  struct move first_move = {VALID, -1, CARD_ROAD_STRAIGHT_CITY, init_pos, NORTH, NO_MEEPLE};
  add_tail_tile(graph, &first_move);
  
  printf("\n******************************************** ROUND 0 ********************************************\n");
  display_information(CARD_ROAD_STRAIGHT_CITY, &init_pos, NORTH, NO_MEEPLE); 

  int id_player = 0;
  int round = 1;
  enum card_id card;
  int number_of_moves;
  struct move *last_moves;
  struct move move_player;
  
  /* Game Loop */
  
  while (deck_is_empty(deck) == FALSE && remaining_players(players) > 1) {
    printf("******************************************** ROUND %d ********************************************\n", round);
    ++round;
    card = draw_card(deck);

    if (is_playable(graph, card) == TRUE) {
      printf("Joueur %d\n", id_player);
      number_of_moves = min(moves->count_moves, remaining_players(players));
      last_moves = last_n_moves(moves, number_of_moves);
      move_player = players->players[id_player].p.play(card, last_moves, number_of_moves);
      
      if (valid_move_card(graph, &move_player) == TRUE) {
	printf("le coup joué est valide\n\n");
	display_information(card, &move_player.onto, move_player.dir, move_player.place);
	add_tail_tile(graph, &move_player);
      } else {
	eject_player(players, &players->players[id_player]);
	printf("\033[31;1mle coup joué par le joueur %d n'est pas valide\033[0m\n\n", id_player);
      }
      move_player.player = id_player;
      id_player = compute_next_player(players, &players->players[id_player]);
      
      add_move(moves, &move_player);
    } else
      printf("\nLa carte %d n'est pas jouable\n\n", card);
  }
  
  for(int i = 0 ; i < players->number_of_players; i++) {
    players->players[i].p.finalize();
    dlclose(tab_pointeurs[i]);
  }
  free_deck(deck);
  free_players(players);
  free_moves(moves);
  free_graph(graph);
  
  return EXIT_SUCCESS;
}


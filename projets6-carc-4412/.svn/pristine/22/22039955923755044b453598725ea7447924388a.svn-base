#include <player.h>
#include <server.h>
#include <stdlib.h>
#include <stdio.h>

struct players *init_players(int number_of_players) {
  struct players *p = (struct players *) malloc(sizeof(struct players));
   if (p == NULL)
    exit(1);
   p->players = (struct player *) malloc(number_of_players * sizeof(struct player));
   if (p->players == NULL)
     exit(1);
  p->number_of_players = number_of_players;
  for (int i = 0; i < number_of_players; ++i) {
    p->players[i].id_player = i;
    p->players[i].points = 0;
    p->players[i].player_alive = TRUE;
    p->players[i].number_of_meeples = STARTING_NUMBER_OF_MEEPLES;
  }
  return p;
}

int remaining_players(struct players *players) {
  int res = 0;
  for (int i = 0; i < players->number_of_players; ++i) {
    if (players->players[i].player_alive == TRUE)
      ++res;
  }
  return res;
}

unsigned int compute_next_player(struct players *players, struct player *player) {
  int id = player->id_player;
  if (id == players->number_of_players - 1)
    id = 0;
  else
    ++id;
  while (players->players[id].player_alive == FALSE) {
    if (id == players->number_of_players - 1)
      id = 0;
    else
      ++id;
  }
  return id;
}

void eject_player(struct players* players, struct player *player){
  players->players[player->id_player].player_alive = FALSE;
}

void free_players(struct players *p) {
  free(p->players);
  free(p);
}

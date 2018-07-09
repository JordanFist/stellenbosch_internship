#ifndef _DECK_H
#define _DECK_H

#include <server.h>
 
struct deck {
  enum card_id tab[NUMBER_OF_CARDS];
  int next_card;
};

struct deck *init_deck();
void fill_deck(struct deck *d);
enum card_id draw_card(struct deck *d); //renvoyer la 1ere carte  diponible
void shuffle(struct deck *d);
void free_deck(struct deck* deck);
int deck_is_empty(struct deck *d);
void print_deck (struct deck* d);

#endif

#include <deck.h>
#include <stdlib.h>
#include <stdio.h>
#include <server.h>

// Initialise the deck struct
struct deck *init_deck() {
  struct deck* deck = (struct deck *) malloc(sizeof(struct deck));
  if (deck == NULL)
    exit(1);
  deck->next_card = 0;
  fill_deck(deck);
  shuffle(deck);
  return deck;
}

// Shuffle the deck
void shuffle(struct deck *d) {
  int x;
  int swap;
  for (int i = 0; i < NUMBER_OF_CARDS - 1; ++i) {
    x = rand() % (NUMBER_OF_CARDS - 1);
    swap = d->tab[i];
    d->tab[i] = d->tab[x];
    d->tab[x] = swap;
  }
}

// Fill the deck with the right number of cards
void fill_deck(struct deck *d) {
  int p = 0;
  for (int j = 0; j < LAST_CARD; j++) {
      for (int i = 0; i < effective[j]; i++) {
	d->tab[p] = j;
	p++;
      }
  }
}

//Return the next card (on the deck top)
enum card_id draw_card(struct deck *d) {
  enum card_id aux = d->tab[d->next_card];
  if (d->next_card < NUMBER_OF_CARDS - 1) {
    ++d->next_card;
    return aux;
  }
  return ERROR;
}

// Check if the deck is empty
int deck_is_empty(struct deck *d) {
  return (d->next_card == NUMBER_OF_CARDS - 1);
}

// Free the deck
void free_deck(struct deck *deck) {
  free(deck);
}


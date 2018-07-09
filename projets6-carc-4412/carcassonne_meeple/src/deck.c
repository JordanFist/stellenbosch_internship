#include <deck.h>
#include <stdlib.h>
#include <stdio.h>
#include <server.h>

//creation d'un deck vide
struct deck *init_deck() {
  struct deck* deck = (struct deck *) malloc(sizeof(struct deck));
  if (deck == NULL)
    exit(1);
  deck->next_card = 0;
  fill_deck(deck);
  shuffle(deck);
  return deck;
}

void print_deck(struct deck *d) {
  for (int i = 0; i < NUMBER_OF_CARDS; i++)
    printf("%d\n",d->tab[i]);
}

//fonction qui melange le deck
void shuffle(struct deck *d) {
  int x;
  int swap;
  for (int i = 0; i < NUMBER_OF_CARDS; ++i) {
    x = rand() % NUMBER_OF_CARDS;
    swap = d->tab[i];
    d->tab[i] = d->tab[x];
    d->tab[x] = swap;
  }
}

//fonction qui remplit le deck par 72 cartes 
void fill_deck(struct deck *d) {
  int p = 0;
  for (int j = 0; j < LAST_CARD; j++) {
      for (int i = 0; i < effective[j]; i++) {
	d->tab[p] = j;
	p++;
      }
  }
}

//fonction qui fait la pioche
enum card_id draw_card(struct deck *d) {
  enum card_id aux = d->tab[d->next_card];
  if (d->next_card < NUMBER_OF_CARDS) {
    ++d->next_card;
    return aux;
  }
  return ERROR;
}

//fonction qui verifie que le deck est vide
int deck_is_empty(struct deck *d) {
  //return (d->element - d->tab >= NUMBER_OF_CARDS);
  //return (d->next_card == &d->tab[NUMBER_OF_CARDS]
  return (d->next_card == NUMBER_OF_CARDS);
}

//fonction qui libère la mémoire allouée au deck
void free_deck(struct deck *deck) {
  free(deck);
}


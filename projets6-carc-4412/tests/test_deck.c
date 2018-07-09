#include <stdio.h>
#include <stdlib.h>
#include <deck.h>
#include <assert.h>
#include <server.h>
#include <time.h>

#define SUM_EFFECTIVE 896

/*POUR L'AFFICHAGE*/
char PASSED[]="\033[32;1mPASSED\033[0m";
char FAIL[]="\033[31;1mFAIL\033[0m";

void test_init_deck() {
  printf("Test de init_deck ........");
  struct deck *d = init_deck();

  assert(d->next_card == 0);
  free_deck(d);
  
  printf("%s\n",PASSED);
}

void test_draw_card() {
  printf("Test de la fonction draw_card........");
  
  struct deck *d = init_deck();
  enum card_id c = draw_card(d);
  assert(d->tab[d->next_card] == d->tab[1]);
  assert(c == d->tab[0]);
  free_deck(d);

  printf("%s\n",PASSED);
}
  
void test_deck_is_empty() {
  printf("Test de la fonction deck_is_empty ......");

  struct deck *d = init_deck();
  for (int i = 0; i < NUMBER_OF_CARDS; ++i)
    draw_card(d);
  assert(deck_is_empty(d) == TRUE);
  free_deck(d);

  printf("%s\n",PASSED);
}

int sum(struct deck *d) {
  int res = 0;
  for (int i = 0; i < NUMBER_OF_CARDS - 1; ++i)
    res += d->tab[i];
  return res;
}

void test_fill_table() {
  printf("Test de la fonction fill_table.........");

  struct deck d;
  fill_deck(&d);
  
  assert(sum(&d) == SUM_EFFECTIVE);
  
  printf("%s\n",PASSED);
}

void test_shuffle() {
  printf("Test de la fonction shuffle ......");

  struct deck d1;
  fill_deck(&d1);
  struct deck d2;
  fill_deck(&d2);
  shuffle(&d2);

  assert(sum(&d1) == SUM_EFFECTIVE);
  assert(sum(&d2) == SUM_EFFECTIVE);

  int dif = 0;
  for (int i = 0; i < NUMBER_OF_CARDS; ++i) {
    if (d1.tab[i] != d2.tab[i])
      ++dif;
  }
  assert(dif > 40);
  
  printf("%s\n",PASSED);
}

int main() {
  srand(time(NULL));
  
  test_init_deck();
  test_draw_card();
  test_deck_is_empty();
  test_fill_table();
  test_shuffle();
  
  return EXIT_SUCCESS;
}

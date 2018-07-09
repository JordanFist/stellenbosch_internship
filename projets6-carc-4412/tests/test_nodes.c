#include <stdio.h>
#include <stdlib.h>
#include <nodes.h>
#include <assert.h>
#include <set_graph.h>
#include <graph.h>

/*POUR L'AFFICHAGE*/
char FAIL[]="\033[31;1mFAIL\033[0m";
char PASSED[]="\033[32;1mPASSED\033[0m";

void test_init_node() {
  printf("Test de init_node .............");
  
  struct node *n = init_node(0, 0);

  assert(n->land_type == PLAIN);
  assert(n->meeple == FALSE);
  assert(n->meeple_owner == NO_PLAYER);
  assert(n->number_of_nodes == 0);
  for (int i = 0; i < CONNEXIONS_PER_SIDE; ++i)
    assert(n->next_nodes[i] == NULL);

  free_node(n);
  printf("%s\n",PASSED);
}

int main() {
  test_init_node();
  
  return EXIT_SUCCESS;
}

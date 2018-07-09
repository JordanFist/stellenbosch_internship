#include <node.h>
#include <server.h>
#include <stdio.h>
#include <stdlib.h>

// Initialise a node
struct node *init_node(enum card_id card, int i) {
  struct node *n = (struct node *) malloc(sizeof(struct node));
  if (n == NULL)
    exit(1);
  n->land_type = cards_content[card][i];
  n->meeple = FALSE;
  n->meeple_owner = NO_PLAYER;
  n->number_of_nodes = 0;
  for (int i = 0; i < CONNEXIONS_PER_SIDE; ++i)
    n->next_nodes[i] = NULL;
  return n;
}

// Free a node
void free_node(struct node *n) {
  free(n);
}
 

#include <set_graph.h>
#include <server.h>
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

/*POUR L'AFFICHAGE*/

char FAIL[]="\033[31;1mFAIL\033[0m";
char PASSED[]="\033[32;1mPASSED\033[0m"; 

struct move m1 = {0, 0, 0, {0, 0}, 0, 0};
struct move m2 = {1, 1, 1, {1, 1}, 1, 1};
struct move m3 = {0, 2, 2, {2, 2}, 0, 2};
struct move m4 = {1, 3, 3, {3, 3}, 0, 3};
struct move m5 = {0, 4, 4, {4, 4}, 0, 4};
struct move m6 = {0, 4, 23, {0, -1}, 0, 5};
struct move m7 = {0, 6, 23, {2, 1}, 0, 6};
struct move m8 = {0, 7, 8, {4, 5}, 0, 7};
struct move m9 = {0, 8, 1, {1, -1}, 0, 8};
struct move m10 = {0, 8, 1, {0, 1}, 0, 8};
struct move m11 = {0, 9, 1, {1, 0}, 0, 9};
struct move m12 = {0, 10, 1, {-1, 0}, 0, 10};

void test_init_graph() {
  printf("Test de init_graph()............. ");
  
  struct graph *g = init_graph();
  if(g->number_of_tiles == 0)
    printf("%s\n",PASSED);
  else
    printf("%s\n",FAIL);
  free_graph(g);
}

void test_neighbour_position() {
  printf("Test de neighbour_position ............. ");

  struct position p = {0, 0};
  struct position pos = neighbour_position(&p, NORTH);
  assert(pos.x == 0);
  assert(pos.y == 1);

  struct position p2 = {0, 0};
  pos = neighbour_position(&p2, WEST);
  assert(pos.x == -1);
  assert(pos.y == 0);
  
  struct position p3 = {0, 0};
  pos = neighbour_position(&p3, SOUTH);
  assert(pos.x == 0);
  assert(pos.y == -1);

  struct position p4 = {0, 0};
  pos = neighbour_position(&p4, EAST);
  assert(pos.x == 1);
  assert(pos.y == 0);
  
  printf("%s\n",PASSED);
}

void test_compute_neighbour() {
  printf("Test de compute_neighbour ............. ");

  struct graph *g = init_graph();
  add_tail_tile(g, &m1);
  add_tail_tile(g, &m6);
  add_tail_tile(g, &m9);
  struct position pos = {0, -1};
  struct tile *t = init_tile(1, NORTH, pos);

  assert(compute_neighbour(g, t, NORTH) != NULL);
  assert(compute_neighbour(g, t, WEST) == NULL);
  assert(compute_neighbour(g, t, SOUTH) == NULL);
  assert(compute_neighbour(g, t, EAST) != NULL);

  free_graph(g);
  free_tile(t);
  
  printf("%s\n",PASSED);
}

void test_match_card() {
  printf("Test de match_card ............. ");

  struct graph *g = init_graph();
  add_tail_tile(g, &m1);
  struct position pos = {0, -1};
  struct tile *t = init_tile(3, SOUTH, pos);
  assert(match_card(g, t) == TRUE);

  struct graph *g2 = init_graph();
  add_tail_tile(g2, &m1);
  add_tail_tile(g2, &m9);
  struct position pos2 = {0, -1};
  struct tile *t2 = init_tile(3, SOUTH, pos2);
  assert(match_card(g2, t2) == TRUE);
  
  free_graph(g);
  free_graph(g2);
  free_tile(t);
  free_tile(t2);
  printf("%s\n",PASSED);
}

void print(struct tile* tile){
  for (int i=0 ;i<NUMBER_OF_DIRECTIONS; i++)
    printf("%d ",tile->nodes[i]->land_type);
  printf("\n");
}

void test_is_connectable() {
  printf("Test de is_connectable ............. ");

  struct graph *g = init_graph();
  add_tail_tile(g, &m1);
  struct position pos = {0, -1};
  struct tile *t = init_tile(3, NORTH, pos);
  assert(is_connectable(g, t) == TRUE);
  assert(t->orientation == NORTH);

  struct graph *g2 = init_graph();
  add_tail_tile(g2, &m1);
  add_tail_tile(g2, &m9);
  struct position pos2 = {0, -1};
  struct tile *t2 = init_tile(3, NORTH, pos2);
  assert(is_connectable(g2, t2) == TRUE);
  assert(t->orientation == 0);

  free_graph(g);
  free_graph(g2);
  free_tile(t);
  free_tile(t2);
  
  printf("%s\n",PASSED);
}


void test_is_playable() {
  printf("Test de is_playable............. ");

  struct graph *g = init_graph();
  add_tail_tile(g, &m2);
  add_tail_tile(g, &m3);
  add_tail_tile(g, &m4);
  add_tail_tile(g, &m5);
  int res = is_playable(g, 5);
  assert(res == TRUE);

  res = is_playable(g, CARD_JUNCTION_FOUR);
  assert(res == FALSE);
  free_graph(g);

  printf("%s\n",PASSED);
  }

void test_valid_move_card() {
  printf("Test valid_move_card ............. ");
  
  struct graph *g = init_graph();
  add_tail_tile(g, &m3);
  add_tail_tile(g, &m5);
  int res = valid_move_card(g, &m7);
  assert(res == FALSE);
  res = valid_move_card(g, &m8);
  assert(res == TRUE);
  
  free_graph(g);
  printf("%s\n",PASSED);
}

void test_find_tile() {
  printf("Test de test_find_tile.......... ");

  struct graph *g = init_graph();
  add_tail_tile(g, &m1);
  add_tail_tile(g, &m2);
  add_tail_tile(g, &m3);
  add_tail_tile(g, &m4);

  struct tile *t1 = find_tile(g, &m1.onto);
  struct tile *t2 = find_tile(g, &m2.onto);
  struct tile *t3 = find_tile(g, &m3.onto);
  struct tile *t4 = find_tile(g, &m4.onto);
  struct tile *t5 = find_tile(g, &m5.onto);
  assert(t1->pos.x == 0 && t1->pos.y == 0);
  assert(t2 == NULL);
  assert(t3->pos.x == 2 && t3->pos.y == 2);
  assert(t4 == NULL);
  assert(t5 == NULL);
  
  free_graph(g);
  printf("%s\n", PASSED);
}

void test_add_tile() {
  printf("Test de add_tile......... ");

  struct graph *g = init_graph();
  int res = add_tail_tile(g, &m1);
  add_tail_tile(g, &m2);
  add_tail_tile(g, &m6);

  assert(res == EXIT_SUCCESS);
  assert(find_tile(g, &m1.onto) != NULL);
  assert(find_tile(g, &m2.onto) == NULL);
  assert(find_tile(g, &m3.onto) == NULL);
  assert(find_tile(g, &m6.onto) != NULL);
  assert(g->number_of_tiles == 2);
  
  free_graph(g);
  printf("%s\n", PASSED);
}

void test_remove_tile() {
  printf("Test de remove_tile ......... ");

  struct graph *g = init_graph();
  add_tail_tile(g, &m1);
  add_tail_tile(g, &m2);
  add_tail_tile(g, &m6);
  int res = remove_tail_tile(g);

  assert(res == EXIT_SUCCESS);
  assert(find_tile(g, &m1.onto) != NULL);
  assert(find_tile(g, &m2.onto) == NULL);
  assert(find_tile(g, &m6.onto) == NULL);
  assert(g->number_of_tiles == 1);

  free_graph(g);
  
  printf("%s\n", PASSED);
}

void test_add_card_connection() {
  printf("Test de add_card_connection ......... ");
  
  struct graph *g = init_graph();
  struct tile *t0 = init_tile(m1.card, m1.dir, m1.onto); //milieu
  struct tile *t1 = init_tile(m10.card, m10.dir, m10.onto); //dessus
  struct tile *t2 = init_tile(m12.card, m12.dir, m12.onto); //gauche
  struct tile *t3 = init_tile(m6.card, m6.dir, m6.onto); //dessous
  struct tile *t4 = init_tile(m11.card, m11.dir, m11.onto); //droite
  
  g->tiles[0] = t0;
  g->tiles[1] = t1;
  g->tiles[2] = t2;
  g->tiles[3] = t3;
  g->tiles[4] = t4;
  g->number_of_tiles = 5;
  
  add_card_connection(g, t0);
  
  assert(g->tiles[0]->nodes[0]->next_nodes[2] == g->tiles[1]->nodes[8]);
  assert(g->tiles[0]->nodes[1]->next_nodes[2] == g->tiles[1]->nodes[7]);
  assert(g->tiles[0]->nodes[2]->next_nodes[2] == g->tiles[1]->nodes[6]);
  assert(g->tiles[0]->nodes[3]->next_nodes[2] == g->tiles[2]->nodes[11]);
  assert(g->tiles[0]->nodes[4]->next_nodes[2] == g->tiles[2]->nodes[10]);
  assert(g->tiles[0]->nodes[5]->next_nodes[2] == g->tiles[2]->nodes[9]);
  assert(g->tiles[0]->nodes[6]->next_nodes[1] == g->tiles[3]->nodes[2]);
  assert(g->tiles[0]->nodes[7]->next_nodes[0] == g->tiles[3]->nodes[1]);
  assert(g->tiles[0]->nodes[8]->next_nodes[1] == g->tiles[3]->nodes[0]);
  assert(g->tiles[0]->nodes[9]->next_nodes[2] == g->tiles[4]->nodes[5]);
  assert(g->tiles[0]->nodes[10]->next_nodes[2] == g->tiles[4]->nodes[4]);
  
  assert(g->tiles[0]->nodes[11]->next_nodes[2] == g->tiles[4]->nodes[3]);

  assert(g->tiles[4]->nodes[5]->next_nodes[2] == g->tiles[0]->nodes[9]);
  assert(g->tiles[4]->nodes[4]->next_nodes[2] == g->tiles[0]->nodes[10]);
  assert(g->tiles[4]->nodes[3]->next_nodes[2] == g->tiles[0]->nodes[11]);
  
  free_graph(g);
  
  printf("%s\n", PASSED);
}

void test_remove_card_connexion() {
  printf("Test de remove_card_connection ......... ");

  struct graph *g = init_graph();
  add_tail_tile(g, &m1);
  add_tail_tile(g, &m10);
  add_tail_tile(g, &m12);
  add_tail_tile(g, &m6);
  add_tail_tile(g, &m11);
  remove_card_connection(g, g->tiles[4]);
  remove_card_connection(g, g->tiles[2]);
  
  assert(g->tiles[0]->nodes[0]->next_nodes[2] == g->tiles[1]->nodes[8]);
  assert(g->tiles[0]->nodes[1]->next_nodes[2] == g->tiles[1]->nodes[7]);
  assert(g->tiles[0]->nodes[2]->next_nodes[2] == g->tiles[1]->nodes[6]);
  assert(g->tiles[0]->nodes[3]->next_nodes[2] == NULL);
  assert(g->tiles[0]->nodes[4]->next_nodes[2] == NULL);
  assert(g->tiles[0]->nodes[5]->next_nodes[2] == NULL);
  assert(g->tiles[0]->nodes[6]->next_nodes[1] == g->tiles[3]->nodes[2]);
  assert(g->tiles[0]->nodes[7]->next_nodes[0] == g->tiles[3]->nodes[1]);
  assert(g->tiles[0]->nodes[8]->next_nodes[1] == g->tiles[3]->nodes[0]);
  assert(g->tiles[0]->nodes[9]->next_nodes[2] == NULL);
  assert(g->tiles[0]->nodes[10]->next_nodes[2] == NULL);
  assert(g->tiles[0]->nodes[11]->next_nodes[2] == NULL);
  
  free_graph(g);

  printf("%s\n", PASSED);
}

int main () {
  test_init_graph();
  test_match_card();
  test_is_connectable();
  test_neighbour_position();
  test_compute_neighbour();
  test_is_playable();
  test_valid_move_card();
  test_find_tile();
  test_add_tile();
  test_remove_tile();
  
  test_add_card_connection();
  test_remove_card_connexion();
  
  return EXIT_SUCCESS;
}

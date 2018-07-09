#include <moves.h>
#include <server.h>
#include <stdlib.h>
#include <stdio.h>

//Return an empty struct moves
struct moves *init_moves() {
  struct moves *m = (struct moves *) malloc(sizeof(struct moves));
  if (m == NULL)
    exit(1);
  m->count_moves = 0;
  return m;
}

struct move *last_n_moves(struct moves *m, int number_of_moves) {
  return &m->move_player[m->count_moves - number_of_moves];
}

//Add a move in the move_player array
int add_move(struct moves *m, struct move *m_add) {
  if (m_add != NULL) {
    m->move_player[m->count_moves] = *m_add;
    ++m->count_moves;
  }
  return (&m->move_player[m->count_moves] == m_add);
}

//Free the struct moves
void free_moves(struct moves *m) {
  free(m);
}


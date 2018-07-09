#include <stdio.h>
#include <stdlib.h>
#include <set_dynamic.h>
#include <nodes.h>
#include <server.h>

struct set *set__empty() {
  struct set *set = (struct set *) malloc(sizeof(struct set));
  if (set == NULL)
    exit(1);
  set->n = NULL;
  set->capacity = 0;
  set->size = 0;
  return set;
}

int set__is_empty(const struct set *set) {
  if (set__size(set) == 0)
    return TRUE;
  return FALSE;
}

int set__size(const struct set *set) {
  return set->size;
}

int set__find(const struct set *set, struct node *n) {
  int a = 0;
  int b = set__size(set);
  int moy = (a + b) / 2;
  while (a < b) {
    if (set->n[moy] == n)
      return TRUE;
    if (set->n[moy] > n)
      b = moy;
    else
      a = moy + 1;
    moy = (a + b) / 2;
  }
  return FALSE;
}

void init_set(struct set *set, int i) {
  for (int j = i; j < set->capacity; ++j)
    set->n[j] = NULL;
}

int add_memory(struct set *set) {
  if (set->capacity == 0) 
    set->capacity = 1;
  else
    set->capacity *= 2;
  set->n = (struct node **) realloc(set->n, set->capacity * sizeof(struct node *));
  if (set->n == NULL)
    exit(1);
  init_set(set, set->capacity/2);
  return EXIT_SUCCESS;
}

int remove_memory(struct set *set) {
  set->capacity /= 2;
  set->n = (struct node **) realloc(set->n, set->capacity * sizeof(struct node **));
  if (set->n == NULL && set->capacity != 0)
    exit(1);
  return EXIT_SUCCESS;
}

void shift_right_dynamic(struct set *set, int i) {
  int len = set__size(set);
  struct node *node;
  while (i != len) {
    node = set->n[len - 1];
    set->n[len] = node;
    --len;
  }
}

int set__add(struct set *set, struct node *n) {
  if (set__find(set, n) == TRUE)
    return EXIT_FAILURE;
  int len = set__size(set);
  if (len == set->capacity) {
    if (add_memory(set) == EXIT_FAILURE)
      return EXIT_FAILURE;
  }
  for (int i = 0; i < len; ++i) {
    if (n < set->n[i]) {
      shift_right_dynamic(set, i);
      set->n[i] = n;
      ++set->size;
      return EXIT_SUCCESS;
    }
  }
  set->n[len] = n;
  ++set->size;
  return EXIT_SUCCESS;
}

struct node *set__remove(struct set *set) {
  struct node *n_remove = NULL;
  if (set__size(set) > 0) {
    n_remove = set->n[set__size(set) - 1];
    set->n[set__size(set) - 1] = NULL;
    --set->size;
    if (set__size(set) <= set->capacity / 2) 
      remove_memory(set);
  }
  return n_remove;
}

void set__free(struct set *set) {
  free(set->n);
  free(set);
}



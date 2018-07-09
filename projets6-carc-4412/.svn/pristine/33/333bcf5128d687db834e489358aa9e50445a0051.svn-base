#include <set_dynamic.h>
#include <stdlib.h>

#include <stdio.h>
#include <assert.h>
#include <server.h>
#include <nodes.h>

/*POUR L'AFFICHAGE*/
char FAIL[]="\033[31;1mFAIL\033[0m";
char PASSED[]="\033[32;1mPASSED\033[0m";

void test_set__empty() {
  printf("test de set__empty ");
  
  struct set *s1 = set__empty();
  
  assert(s1->n == NULL);
  assert(s1->size == 0);
  assert(s1->capacity == 0);
  set__free(s1);

  printf("%s\n", PASSED);
}

void test__set__is_empty() {
  printf("test de set__is_empty ");
  
  struct set *s1 = set__empty();
  
  assert(set__is_empty(s1) == TRUE);
  set__free(s1);
  
  struct set *s2 = set__empty();
  struct node n;
  set__add(s2, &n);
  
  assert(s2->n[0] == &n);
  assert(s2->capacity == 1);
  assert(set__is_empty(s2) == FALSE);
  set__free(s2);

  printf("%s\n", PASSED);
}

void test__set__size() {
  printf("test de set__size ");

  struct set *s1 = set__empty();
  struct node n1;
  struct node n2;
  
  set__add(s1, &n1);
  set__add(s1, &n2);
  assert(set__size(s1) == 2);
  set__free(s1);

  struct set *s2 = set__empty();
  
  assert(set__size(s2) == 0);
  set__free(s2);

  struct set *s3 = set__empty();
  set__add(s3, &n2);
  set__add(s3, &n1);
  set__remove(s3);

  assert(set__size(s3) == 1);
  set__free(s3);

  printf("%s\n", PASSED);
}

void test__set__find() {
  printf("test de set__find ");
  
  struct set *s1 = set__empty();
  struct node n1;
  struct node n2;
  struct node n3;
  set__add(s1, &n1);
  set__add(s1, &n2);
  
  assert(set__find(s1, &n2) == TRUE);
  assert(set__find(s1, &n3) == FALSE);
  set__free(s1);
  
  struct set *s2 = set__empty();
  set__add(s2, &n1);
  set__add(s2, &n2);
  set__add(s2, &n3);
  set__remove(s2);
  /*
  if (&n1 > &n2 && &n1 > &n3)
    assert(set__find(s2, &n1) == FALSE);
  else if (&n2 > &n1 && &n2 > &n3)
    assert(set__find(s2, &n2) == FALSE);
  else
    assert(set__find(s2, &n3) == FALSE);
  */
  set__free(s2);

  printf("%s\n", PASSED);
}

void test__set__add() {
  printf("test de set__add ");
  
  struct set *s1 = set__empty();
  struct node n1;
  struct node n2;
  struct node n3;
  set__add(s1, &n1);
  
  int res = set__add(s1, &n2);
  int res2 = set__add(s1, &n2);

  assert(res2 == EXIT_FAILURE);
  assert(set__size(s1) == 2);
  assert(set__find(s1, &n1) == TRUE);
  assert(set__find(s1, &n2) == TRUE);
  assert(s1->capacity == 2);
  assert(res == EXIT_SUCCESS);
  set__free(s1);

  struct set *s2 = set__empty();
  set__add(s2, &n1);
  set__add(s2, &n2);
  set__add(s2, &n3);

  assert(set__find(s1, &n1) == TRUE);
  assert(set__find(s1, &n2) == TRUE);
  assert(set__find(s1, &n3) == TRUE);
  assert(s2->capacity == 4);
  assert(set__size(s2) == 3);
  set__free(s2);

  printf("%s\n", PASSED);
}

void test__set__remove() {
  printf("test de set__remove ");

  struct set *s1 = set__empty();
  struct node n1;
  set__remove(s1);
  
  assert(set__find(s1, &n1) == FALSE);
  set__free(s1);

  struct set *s2 = set__empty();
  set__add(s2, &n1);
  set__remove(s2);

  assert(set__is_empty(s2) == TRUE);
  set__free(s2);

  struct set *s3 = set__empty();
  struct node n2;
  struct node n3;
  set__add(s3, &n2);
  set__add(s3, &n1);
  set__add(s3, &n3);
  set__remove(s3);

  if (&n1 > &n2 && &n1 > &n3)
    assert(set__find(s2, &n1) == FALSE);
  else if (&n2 > &n1 && &n2 > &n3)
    assert(set__find(s2, &n2) == FALSE);
  else
    assert(set__find(s2, &n3) == FALSE);
  assert(s3->capacity == 2);
  assert(set__size(s3) == 2);
  set__free(s3);
  
  printf("%s\n", PASSED);
}

void test_add_memory() {
  printf("test de add_memory ");

  struct set *s1 = set__empty();
  add_memory(s1);

  assert(s1->capacity == 1);
  assert(s1->size == 0);
  set__free(s1);

  struct set *s2 = set__empty();
  struct node n1;
  set__add(s2, &n1);
  add_memory(s2);

  assert(s2->capacity == 2);
  assert(s2->size == 1);
  set__free(s2);

  printf("%s\n", PASSED);
}

void test_remove_memory() {
  printf("test de remove_memory ");
    
  struct set *s1 = set__empty();
  struct node n1;
  set__add(s1, &n1);
  remove_memory(s1);

  assert(s1->capacity == 0);
  set__free(s1);

  struct set *s2 = set__empty();
  struct node n2;
  set__add(s2, &n1);
  set__add(s2, &n2);
  remove_memory(s2);

  assert(s2->capacity == 1);
  set__free(s2);

  struct set *s3 = set__empty();
  int res = remove_memory(s3);

  assert(res == EXIT_SUCCESS);
  set__free(s3);

  printf("%s\n", PASSED);
}

void test_init_set() {
  printf("test de init_set ");

  struct set *s1 = set__empty();
  struct node n1;
  struct node n2;
  struct node n3;
  struct node n4;
  set__add(s1, &n1);
  set__add(s1, &n2);
  set__add(s1, &n3);
  set__add(s1, &n4);
  init_set(s1, 2);

  assert(s1->n[2] == NULL);
  assert(s1->n[3] == NULL);
  set__free(s1);

  printf("%s\n", PASSED);
}

int main() {
  test_set__empty();
  test__set__is_empty();
  test__set__size();
  test__set__find();
  test__set__add();
  test__set__remove();
  test_add_memory();
  test_remove_memory();
  test_init_set();
  
  return EXIT_SUCCESS;
}

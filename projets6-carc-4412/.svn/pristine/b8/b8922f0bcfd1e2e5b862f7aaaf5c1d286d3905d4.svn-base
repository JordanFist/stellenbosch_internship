#include <server.h>
#include <stdio.h>

void display_card_name(enum card_id card) {
  switch(card) {
  case(CARD_MONASTERY_ROAD): {
    printf("CARD_MONASTERY_ROAD");
    break;
  }
  case(CARD_MONASTERY_ALONE): {
    printf("CARD_MONASTERY_ALONE");
    break;
  }
  case(CARD_CITY_ALL_SIDES): {
    printf("CARD_CITY_ALL_SIDES");
    break;
  }
  case(CARD_ROAD_STRAIGHT_CITY): {
    printf("CARD_ROAD_STRAIGHT_CITY");
    break;
  }
  case(CARD_CITY_ONE_SIDE): {
    printf("CARD_CITY_ONE_SIDE");
    break;
  }
  case(CARD_CITY_TUNNEL_SHLD): {
    printf("CARD_CITY_TUNNEL_SHLD");
    break;
  }
  case(CARD_CITY_TUNNEL): {
    printf("CARD_CITY_TUNNEL");
    break;
  }
  case(CARD_PLAIN_TUNNEL): {
    printf("CARD_PLAIN_TUNNEL");
    break;
  }
  case(CARD_PLAIN_TWO_CITIES): {
    printf("CARD_PLAIN_TWO_CITIES");
    break;
  }
  case(CARD_ROAD_TURN_RIGHT_CITY): {
    printf("CARD_ROAD_TURN_RIGHT_CITY");
    break;
  }
  case(CARD_ROAD_TURN_LEFT_CITY): {
    printf("CARD_ROAD_TURN_LEFT_CITY");
    break;
  }
  case(CARD_JUNCTION_CITY): {
    printf("CARD_JUNCTION_CITY");
    break;
  }
  case(CARD_PLAIN_CITY_SHLD): {
    printf("CARD_PLAIN_CITY_SHLD");
    break;
  }
  case(CARD_PLAIN_CITY): {
    printf("CARD_PLAIN_CITY");
    break;
  }
  case(CARD_PLAIN_CITY_ROAD_SHLD): {
    printf("CARD_PLAIN_CITY_ROAD_SHLD");
    break;
  }
  case(CARD_PLAIN_CITY_ROAD): {
    printf("CARD_PLAIN_CITY_ROAD");
    break;
  }
  case(CARD_CITY_THREE_SHLD): {
    printf("CARD_CITY_THREE_SHLD");
    break;
  }
  case(CARD_CITY_THREE): {
    printf("CARD_CITY_THREE");
    break;
  }
  case(CARD_CITY_THREE_ROAD_SHLD): {
    printf("CARD_CITY_THREE_ROAD_SHLD");
    break;
  }
  case(CARD_CITY_THREE_ROAD): {
    printf("CARD_CITY_THREE_ROAD");
    break;
  }
  case(CARD_ROAD_STRAIGHT): {
    printf("CARD_ROAD_STRAIGHT");
    break;
  }
  case(CARD_ROAD_TURN): {
    printf("CARD_ROAD_TURN");
    break;
  }
  case(CARD_JUNCTION_THREE): {
    printf("CARD_JUNCTION_THREE");
    break;
  }
  case(CARD_JUNCTION_FOUR): {
    printf("CARD_JUNCTION_FOUR");
    break;
  }
  default: {
    printf("ERROR");
    break;
  }
  }
}

void display_meeple_position(enum place p) {
  switch(p) {
  case(POS_NORTH_EAST): {
    printf("POS_NORTH_EAST");
    break;
  }
  case(POS_NORTH): {
    printf("POS_NORTH");
    break;
  }
  case(POS_NORTH_WEST): {
    printf("POS_NORTH_WEST");
    break;
  }
  case(POS_WEST_NORTH): {
    printf("POS_WEST_NORTH");
    break;
  }
  case(POS_WEST): {
    printf("POS_WEST");
    break;
  }
  case(POS_WEST_SOUTH): {
    printf("POS_WEST_SOUTH");
    break;
  }
  case(POS_SOUTH_WEST): {
    printf("POS_SOUTH_WEST");
    break;
  }
  case(POS_SOUTH): {
    printf("POS_SOUTH");
    break;
  }
  case(POS_SOUTH_EAST): {
    printf("POS_SOUTH_EAST");
    break;
  }
  case(POS_EAST_SOUTH): {
    printf("POS_EAST_SOUTH");
    break;
  }
  case(POS_EAST): {
    printf("POS_EAST");
    break;
  }
  case(POS_EAST_NORTH): {
    printf("POS_EAST_NORTH");
    break;
  }
  case(POS_CENTER): {
    printf("POS_CENTER");
    break;
  }
  case(NO_MEEPLE): {
    printf("NO_MEEPLE");
    break;
  }
  default: {
    printf("ERROR");
    break;
  }
  }
}

void display_orientation(enum direction d) {
  switch(d) {
  case(NORTH): {
    printf("NORTH");
    break;
  }
  case(WEST): {
    printf("WEST");
    break;
  }
  case(SOUTH): {
    printf("SOUTH");
    break;
  }
  case(EAST): {
    printf("EAST");
    break;
  }
  default: {
    printf("ERROR");
    break;
  }
  }
}

void display_information(enum card_id c, struct position *pos, enum direction d, enum place p) {
  printf("card: ");
  display_card_name(c);
  printf("\t");
  printf("x: %d\t y: %d \t", pos->x, pos->y);
  printf("orientation: ");
  display_orientation(d);
  printf("\t");
  printf("meeple : ");
  display_meeple_position(p);
  printf("\n\n");
}

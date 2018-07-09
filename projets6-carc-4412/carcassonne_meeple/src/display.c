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
  }
  case(POS_NORTH): {
    printf("POS_NORTH");
  }
  case(POS_NORTH_WEST): {
    printf("POS_NORTH_WEST");
  }
  case(POS_WEST_NORTH): {
    printf("POS_WEST_NORTH");
  }
  case(POS_WEST): {
    printf("POS_WEST");
  }
  case(POS_WEST_SOUTH): {
    printf("POS_WEST_SOUTH");
  }
  case(POS_SOUTH_WEST): {
    printf("POS_SOUTH_WEST");
  }
  case(POS_SOUTH): {
    printf("POS_SOUTH");
  }
  case(POS_SOUTH_EAST): {
    printf("POS_SOUTH_EAST");
  }
  case(POS_EAST_SOUTH): {
    printf("POS_EAST_SOUTH");
  }
  case(POS_EAST): {
    printf("POS_EAST");
  }
  case(POS_EAST_NORTH): {
    printf("POS_EAST_NORTH");
  }
  case(POS_CENTER): {
    printf("POS_CENTER");
  }
  case(NO_MEEPLE): {
    printf("NO_MEEPLE");
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
  }
  case(WEST): {
    printf("WEST");
  }
  case(SOUTH): {
    printf("SOUTH");
  }
  case(EAST): {
    printf("EAST");
  }
  default: {
    printf("ERROR");
    break;
  }
  }
}

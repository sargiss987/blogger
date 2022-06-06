package com.acba.blogger.model.enums;

public enum CategoryType {
  SPORT(1L),
  POLITICS(2L),
  EDUCATION(3L),
  LIFESTYLE(4L),
  FOOD(5L),
  TRAVELING(6L);

  private final Long id;

  CategoryType(Long id) {
    this.id = id;
  }

  public static Long getIdByType(String type) {

    if (type == null) {
      return null;
    } else if (type.toUpperCase().equals(SPORT.name())) {
      return SPORT.id;
    } else if (type.toUpperCase().equals(POLITICS.name())) {
      return POLITICS.id;
    } else if (type.toUpperCase().equals(EDUCATION.name())) {
      return EDUCATION.id;
    } else if (type.toUpperCase().equals(LIFESTYLE.name())) {
      return LIFESTYLE.id;
    } else if (type.toUpperCase().equals(FOOD.name())) {
      return SPORT.id;
    } else if (type.toUpperCase().equals(TRAVELING.name())) {
      return TRAVELING.id;
    } else {
      return null;
    }
  }
}

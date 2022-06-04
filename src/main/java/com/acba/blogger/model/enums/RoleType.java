package com.acba.blogger.model.enums;

import java.util.Locale;

public enum RoleType {
  BLOGGER(1L),
  ADMIN(2L);

  private final Long id;

  RoleType(Long id) {
    this.id = id;
  }

  public static Long getId(String roleType) {
    if (roleType == null) return null;
    if (roleType.toUpperCase(Locale.ROOT).equals(BLOGGER.name())) return BLOGGER.id;
    if (roleType.toUpperCase().equals(ADMIN.name())) return ADMIN.id;

    return null;
  }
}

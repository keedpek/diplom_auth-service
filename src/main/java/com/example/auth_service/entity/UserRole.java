package com.example.auth_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_roles")
public class UserRole {
  @EmbeddedId
  private UserRoleId id;

  @MapsId("userId")
  @JoinColumn(name = "user_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @MapsId("roleId")
  @JoinColumn(name = "role_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Role role;

  public UserRole(User user, Role role) {
    this.user = user;
    this.role = role;
    this.id = new UserRoleId(user.getId(), role.getId());
  }
}


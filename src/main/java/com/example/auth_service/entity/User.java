package com.example.auth_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

  @Id
  private UUID id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String hashedPassword;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Builder.Default
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UserRole> userRoles = new HashSet<>();

  public void addRole(Role role) {
    UserRole userRole = new UserRole(this, role);
    userRoles.add(userRole);
  }
}

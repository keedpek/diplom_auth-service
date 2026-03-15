package com.example.auth_service.entity;

import com.example.auth_service.enums.RoleTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Short id;

  @Enumerated(EnumType.STRING)
  @Column(unique = true, nullable = false)
  private RoleTypes name;

  @Column()
  private String description;
}

package com.example.auth_service.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleId implements Serializable {
  private UUID userId;
  private Short roleId;
}

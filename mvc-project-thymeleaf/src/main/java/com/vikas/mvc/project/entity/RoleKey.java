package com.vikas.mvc.project.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoleKey implements Serializable {
    private String username;
    private Roles role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleKey roleKey = (RoleKey) o;
        return Objects.equals(username, roleKey.username) && role == roleKey.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }
}
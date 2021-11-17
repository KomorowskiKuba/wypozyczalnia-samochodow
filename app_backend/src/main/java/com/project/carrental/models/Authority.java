package com.project.carrental.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    public Authority(String roleCode, String roleDescription) {
        this.roleCode = roleCode;
        this.roleDescription = roleDescription;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleCode;

    private String roleDescription;

    @Override
    public String getAuthority() {
        return roleCode;
    }
}

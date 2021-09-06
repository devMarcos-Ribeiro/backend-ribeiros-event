package com.marcosgrs.ribeirosevents.domain.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "profiles")
public class Profile implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}

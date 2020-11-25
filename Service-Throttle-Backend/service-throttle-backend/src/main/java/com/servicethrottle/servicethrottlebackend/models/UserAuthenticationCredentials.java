package com.servicethrottle.servicethrottlebackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "st_user_authentication_credentials")
public class UserAuthenticationCredentials implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(length = 100, unique = true)
    private String username;

    @JsonIgnore
    @NotNull
    @Size(min = 8)
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @ManyToMany(fetch = LAZY)
    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "st_user_authentication_credentials_auth",
            joinColumns = @JoinColumn( name = "st_user_authentication_credentials_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities = new HashSet<>();

}

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@MappedSuperclass
@Table(name = "st_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 50)
    @Column(length = 50)
    private String firstname;

    @Size(max = 50)
    @Column(length = 50)
    private String lastname;

    @Size(min = 9, max = 12)
    @Column(length = 12, unique = true)
    private String phoneNumber;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(length = 100, unique = true)
    private String username;

    @NotNull
    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, nullable = false, unique = true)
    private String email;

    @Size(max = 254)
    @Column(length = 254)
    private String address;

    private Instant created;


//    @ManyToMany(fetch = LAZY)
//    @Fetch(FetchMode.SELECT)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @JoinTable(
//            name = "users_authorities",
//            joinColumns = @JoinColumn( name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn( name = "authority_id", referencedColumnName = "id"))
//    private Set<Authority> authorities = new HashSet<>();

}

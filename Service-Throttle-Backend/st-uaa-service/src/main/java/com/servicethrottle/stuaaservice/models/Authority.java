package com.servicethrottle.stuaaservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Authority implements Serializable {

    @Id
    @NotNull
    @Size(max = 50)
    @Column(length = 50)
    private String name;

}

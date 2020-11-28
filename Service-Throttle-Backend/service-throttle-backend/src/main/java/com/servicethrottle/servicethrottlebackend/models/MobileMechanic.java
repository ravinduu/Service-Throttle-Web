package com.servicethrottle.servicethrottlebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "st_mobile_mechanic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileMechanic extends User {
    @NotNull
    @Column(nullable = false)
    private boolean isAvailable = true;

    @NotNull
    @Column(nullable = false)
    private boolean isAssignToTask = false;
}

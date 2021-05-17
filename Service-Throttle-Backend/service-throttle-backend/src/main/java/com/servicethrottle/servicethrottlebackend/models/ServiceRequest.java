package com.servicethrottle.servicethrottlebackend.models;

import com.servicethrottle.servicethrottlebackend.models.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "st_service_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "st_customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "st_customer_vehicle_id", nullable = false)
    private CustomerVehicle customerVehicle;

    @ManyToMany(fetch = LAZY)
    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "st_customer_service_request_services",
            joinColumns = @JoinColumn( name = "st_service_request_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name = "st_vehicle_service_id", referencedColumnName = "id"))
    private Set<STVehicleService> vehicleServices = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "st_supervisor_id")
    private Supervisor supervisor;

    @ManyToOne
    @JoinColumn(name = "st_mobile_mechanic_id")
    private MobileMechanic mobileMechanic;

    @ManyToOne
    @JoinColumn(name = "st_promotion_id")
    private Promotion promotion;

    @NotNull
    private Instant createdAt;

    private String comments;

    @NotNull
    private double totalCost;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus;

}

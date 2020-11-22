package com.servicethrottle.stuaaservice.models.enums;

import com.google.common.collect.Sets;
import java.util.Set;

import static com.servicethrottle.stuaaservice.models.enums.Privilege.*;

public enum Role {
    ADMIN(Sets.newHashSet(CREATE_CUSTOMER,READ_CUSTOMER,WRITE_CUSTOMER,DELETE_CUSTOMER)),
    SUPERVISOR(Sets.newHashSet(READ_CUSTOMER)),
    MECHANIC(Sets.newHashSet(READ_CUSTOMER)),
    CUSTOMER(Sets.newHashSet(CREATE_CUSTOMER,READ_CUSTOMER,WRITE_CUSTOMER,DELETE_CUSTOMER));

    private final Set<Privilege> privileges;

    Role(Set<Privilege> privileges) {
        this.privileges = privileges;
    }
}

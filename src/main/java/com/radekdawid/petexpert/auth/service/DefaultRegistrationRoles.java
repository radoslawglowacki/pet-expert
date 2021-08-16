package com.radekdawid.petexpert.auth.service;

public enum DefaultRegistrationRoles {
    USER(1L),
    PROVIDER(2L);

    long roleId;

    private DefaultRegistrationRoles(long roleId){
        this.roleId = roleId;
    }
}

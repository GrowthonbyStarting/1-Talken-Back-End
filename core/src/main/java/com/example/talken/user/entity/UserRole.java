package com.example.talken.user.entity;

public enum UserRole {
    MENTI(Authority.MENTI),
    MENTO(Authority.MENTO),
    ADMIN(Authority.ADMIN)
    ;

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String MENTI = "ROLE_MENTI";
        public static final String MENTO = "ROLE_MENTO";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}

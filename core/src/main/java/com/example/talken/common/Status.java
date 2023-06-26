package com.example.talken.common;

public class Status {

    public enum Auth {
        AUTHENTICATED,
        UNAUTHENTICATED
        ;
    }

    public enum User {
        ALIVE,
        DELETED,
        ;
    }
    public enum Resume {

        PUBLIC,
        PRIVATE,
        ;

    }

    public enum Feedback {
        WANTED,
        NOTWANTED
    }
}

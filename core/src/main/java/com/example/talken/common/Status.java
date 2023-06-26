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

        PUBLIC("PUBLIC"),
        PRIVATE("PRIVATE"),
        ;

        String publicStatus;

        Resume(String publicStatus) {
            this.publicStatus = publicStatus;
        }
    }

    public enum Feedback {
        WANTED,
        NOTWANTED
    }
}

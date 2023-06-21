package com.example.talken.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonConstant {

    @UtilityClass
    public static class RegExp {
        public static final String EMAIL = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        public static final String PHONE = "/^\\d{2,4}-\\d{3,4}-\\d{4}$/";
    }
}

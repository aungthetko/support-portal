package com.demo.supportportal.constants;

public class Authority {
    public static final String[] USER_AUTHORITIES = {"user:read"};
    public static final String[] HR_AUTHORITIES = {"user:read", "user:update"};
    public static final String[] MANAGER_AUTHORITIES = {"user:read", "user:update"};
    public static final String[] ADMIN_AUTHORITIES = {"user:read", "user:update", "user:delete"};
    public static final String[] SUPER_USER_AUTHORITIES = {"user:read", "user:update", "user:create", "user:delete"};
}

package com.sp.library.jpaconfig;


public class TenantContext {
    private static ThreadLocal<String> tenant = new InheritableThreadLocal<>();

    public static String getTenant() {
        return tenant.get();
    }

    public static void setTenant(String aTenant) {
        tenant.set(aTenant);
    }

    public static void clear() {
        tenant.remove();
    }
}
package com.hotelium.mainservice.util;

/**
 * @author Melih ŞİNİK
 * @since 17.10.2021
 */
public class SessionContext {
    private static final ThreadLocal<SessionData> THREAD_LOCAL_SESSION_DATA = new ThreadLocal<>();

    private SessionContext(){}

    public static SessionData getSessionData() {
        return THREAD_LOCAL_SESSION_DATA.get();
    }

    public static void setSessionData(SessionData sessionData) {
        THREAD_LOCAL_SESSION_DATA.set(sessionData);
    }

    public static void removeSessionData() {
        THREAD_LOCAL_SESSION_DATA.remove();
    }

}

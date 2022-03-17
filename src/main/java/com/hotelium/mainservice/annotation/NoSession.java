package com.hotelium.mainservice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author Melih ŞİNİK
 * @since 18.10.2021
 */
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface NoSession {
}

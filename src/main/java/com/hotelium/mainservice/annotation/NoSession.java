package com.hotelium.mainservice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author Mete Aydın
 * @since 18.03.2022
 */
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface NoSession {
}

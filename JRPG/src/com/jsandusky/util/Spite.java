package com.jsandusky.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Annotations for the Tag Editor - SPite

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Spite {
	public String group();
	public String tip();
}

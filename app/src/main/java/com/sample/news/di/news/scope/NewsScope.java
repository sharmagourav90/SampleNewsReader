package com.sample.news.di.news.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * News screen level scope.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface NewsScope {
}

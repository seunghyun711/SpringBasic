package com.example.firstproject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD}) // 어노테이션 적용 대상
@Retention(RetentionPolicy.RUNTIME) // 애너테이션 유지 기간 -> 런타임 시점까지 유지한다.
public @interface RunningTime {
}

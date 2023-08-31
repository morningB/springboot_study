package dev.example.jpa.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 어떠한 것에 붙을 수 있는지에 대해 즉 이것은 함수에 대해만 붙을 수 있음
@Retention(RetentionPolicy.RUNTIME) // 이것이 쓰인것이 어느 시점까지 컴퓨터에 존재할 것인가에 대해 알 수 있음
public @interface LogExecutionTime {

}

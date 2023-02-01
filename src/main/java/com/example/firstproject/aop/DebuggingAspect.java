package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

@Aspect // AOP선언 : 부가 기능을 주입하는 클래스다. -> 이 기능이 필요한 곳에 적절하게 넣는다.
@Component // IoC 컨테이너가 해당 객체를 생성 및 관리한다.
@Slf4j
public class DebuggingAspect {
    // 어느 대상을 타겟으로 하여 부가기능을 주입할 것인지 정하는 애너테이션=> api 패키지 내 모든 메서드
    // DTO가 ObjectMapper를 통해 Json으로 변환된다.
    @Pointcut("execution(* com.example.firstproject.api.*.*(..))") // *은 CommentService의 모든 메서드에 적용하게 한다.
    private void cut(){}

    // 실행 시점 선택하는 애너테이션 before
    @Before("cut()")// 즉 "()안의 메서드가 실행되기 이전 시점에 부가기능을 수행하게 한다는 의미 -> cut()의 대상이 수행되기 전 해당 부가기능이 수행된다.
    public void loggingArgs(JoinPoint joinPoint) { // cut의 대상 메서드
        // 입력값을 가져온다.
        Object[] args = joinPoint.getArgs();

        // 클래스명 가져오기
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메서드명 가져오기
        String methodName = joinPoint.getSignature()
                .getName();

        // 입력값 로깅하기
        //CommentSetvice#create()의 입력값 => 5
        for (Object obj : args) {
            log.info("{}#{}의 입력값 => {}",className,methodName,obj);
        }
        // 여기까지 logginArgs라는 파라미터에 들어오는 값들을 출력하는 부가기능을 Aop로 설정한 것이다.
    }

    // 실행 시점 설정
    @AfterReturning(value = "cut()",returning = "returnObj") // -> cut()에 지정된 대상 호출 성공 후 아래 메서드 실행한다는 의미
    public void loggingReturnValue(JoinPoint joinPoint, // cut()의 대상 메서드
                                   Object returnObj)  {// 리턴 값 -> @AterReturning에서 returning으로 return obj를 넣어야 한다.

        // 클래스명 가져오기
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메서드명 가져오기
        String methodName = joinPoint.getSignature()
                .getName();

        // 반환값 로깅
        // CommentService#create()반환값 => CommentDto(id=10.///)

        log.info("{}#{}의 반환값 => {}",className,methodName,returnObj);

    }
}

package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    // 특정 애너테이션을 대상으로 지정
    @Pointcut("@annotation(com.example.firstproject.annotation.RunningTime)")
    private void enableRunningTime(){}

    // 기본 패키지의 모든 메서드를 대상으로 지정
    @Pointcut("execution(* com.example.firstproject..*.*(..))") // 하위 의 모든 메서드를 지정
    private void cut(){}

    // 실행 시점 설정 :  두 조건을 모두 만족하는 대상을 전후로 부가기능을 삽입
    @Around("cut() && enableRunningTime()") // 기본 패키지의 모든 메서드와 동시에 enableRunningTime애너테이션을 가진 것으로 조건을 제한
    public void loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 메서드 수행, 전 측정 시작
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 메서드 수행
        Object retuningObj = joinPoint.proceed();

        // 메서드 수행 후, 측정 종료 및 로깅
        // 메서드명 가져오기
        String methodName = joinPoint.getSignature()
                .getName();
        stopWatch.stop();
        log.info("{}의 총 수행시간 => {} sec", methodName, stopWatch.getTotalTimeSeconds());
    }
}

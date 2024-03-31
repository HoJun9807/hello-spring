package hello.hellospring.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect // AOP로 사용하기 위한 어노테이션
public class TimeTraceAop { // 시간 측정 AOP
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    // hello.hellospring 패키지 하위에 모두 적용

    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs +
                "ms");
        }
    }

}
// AOP: Aspect Oriented Programming
// 공통 관심 사항(cross-cutting concern)과 핵심 관심 사항(core concern)을 분리하는 프로그래밍 기법
// 이미 만들어 놓은 기능을 일일히 변경하는 것이 아닌 필요한 곳에 적용할 수 있다.

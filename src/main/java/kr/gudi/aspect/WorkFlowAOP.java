package kr.gudi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class WorkFlowAOP {
	
	private long start, end;
	@Pointcut("within(kr.gudi.app..*)") private void pointcut() {}
	
	@Before("pointcut()")
	public void before(JoinPoint jp) {
		start = System.currentTimeMillis();
	}
	
	@After("pointcut()")
	public void after(JoinPoint jp) {
		end = System.currentTimeMillis();
		System.out.println(jp.getSignature().toShortString() + " > 진행 시간 : " + ((end - start) / 1000.0) + "초 < 종료!");
	}
	
	@AfterReturning(pointcut = "pointcut()", returning = "retVal")
	public void afterReturning(JoinPoint jp, Object retVal) throws Throwable {
		if(retVal == null) {
			System.out.println(jp.getSignature().toShortString() + " > 메소드 리턴유형 : void");
		} else {
			System.out.println(jp.getSignature().toShortString() + " > 메소드 리턴유형 : " + retVal.getClass());
		}
	}
	
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		System.out.println(jp.getSignature().toShortString() + " > 시작!");
		Object[] obj = jp.getArgs();
		System.out.println(jp.getSignature().toShortString() + " > 매개변수 수 : " + obj.length);
		for(int i = 0; i < obj.length; i++) {
			Object object = obj[i]; 
			System.out.println(jp.getSignature().toShortString() + " > 매개변수유형 - " + i + " : " + object.getClass());
		}
		return jp.proceed();
	}
	
}

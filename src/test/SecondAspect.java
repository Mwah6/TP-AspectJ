package test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SecondAspect {
	@Pointcut("execution(* test.*.ma(..))")
	public void pc1() {
		
	}
//	@Before("pc1()")
//	public void beforeMain() {
//		System.out.println("-------------------------------------------");
//		System.out.println("Before main from Aspect with Class syntax");
//		System.out.println("-------------------------------------------");
//	}
//	@After("pc1()")
//	public void afterMain() {
//		System.out.println("-------------------------------------------");
//		System.out.println("After main from Aspect with Class syntax");
//		System.out.println("-------------------------------------------");	
//	}
	@Around("pc1()") 
	public void aroundMain(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("-------------------------------------------");
		System.out.println("Before main from Aspect with Class syntax");
		System.out.println("-------------------------------------------");
		proceedingJoinPoint.proceed();
		System.out.println("-------------------------------------------");
		System.out.println("After main from Aspect with Class syntax");
		System.out.println("-------------------------------------------");	
	
	}
}


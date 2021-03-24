package main.java.aspects;

import java.util.Scanner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SecurityAspect {
	@Pointcut("execution(* test.Application.start(..))")
	public void startAppPointcut() {}
	
	@Around("startAppPointcut()")
	public void autourStart(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Username : ");
		String username = scanner.next();
		System.out.print("Password : ");
		String password = scanner.next();
		if (username.equals("root") && password.contentEquals("1234")) {
			proceedingJoinPoint.proceed();
		}
		else {
			System.out.println("Access denied...");
		}
		scanner.close();
	}
}

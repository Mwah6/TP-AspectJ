package main.java.aspects;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	Logger logger = Logger.getLogger(LoggingAspect.class.getName());
	
	
	public LoggingAspect() throws IOException {
		logger.addHandler(new FileHandler("log.xml"));
		logger.setUseParentHandlers(false); // dit de ne plus logger dans la console
	}
//	long t1, t2;
	@Pointcut("execution(* main.java.metier.MetierBanqueImpl.*(..)) || initialization(main.java.metier.MetierBanqueImpl.new(..))")
	// Spring AOP ne supporte pas "initialization" mais c'est quelque chose qui fait parti de AspectJ
	// "initialization" ne fonctionne pas avec un around, mais avec before et after
	public void pc1() {}
	
//	@Before("pc1()")
//	public void avant(JoinPoint joinPoint) {
//		t1 = System.currentTimeMillis();
//		logger.info("-------------------------------------------------------------");
//		logger.info("Avant l'exécution de la méthode " + joinPoint.getSignature());
//	}
//	@After("pc1()")
//	public void apres(JoinPoint joinPoint) {
//		logger.info("Après l'exécution de le méthode " + joinPoint.getSignature());
//		t2 = System.currentTimeMillis();
//		logger.info("Durée d'exécution de la méthode : " + (t2-t1) + " ms");
//		logger.info("-------------------------------------------------------------");
//
//	}
	@Around("pc1()")
	public Object autour(ProceedingJoinPoint proceedingJoinPoint, JoinPoint joinPoint) throws Throwable {
		long t1 = System.currentTimeMillis();
		logger.info("-------------------------------------------------------------");
		logger.info("Avant l'exécution de la méthode " + joinPoint.getSignature());
		Object result = proceedingJoinPoint.proceed();
		logger.info("Après l'exécution de le méthode " + joinPoint.getSignature());
		long t2 = System.currentTimeMillis();
		logger.info("Durée d'exécution de la méthode : " + (t2-t1) + " ms");
		logger.info("-------------------------------------------------------------");
		return result;
	}
}

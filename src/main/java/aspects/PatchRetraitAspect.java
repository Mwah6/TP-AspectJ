package main.java.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import main.java.metier.Compte;
import main.java.metier.MetierBanqueImpl;

@Aspect
public class PatchRetraitAspect {
	@Pointcut("execution(* main.java.metier.MetierBanqueImpl.retirer(..))")
	public void pc1() {}
	

	@Around("pc1() && args(code, montant)") // La cible est pc1()) + ces argurments
	public Object autourRetirer(Long code, double montant, ProceedingJoinPoint proceedingJoinPoint, JoinPoint joinPoint) throws Throwable {
		MetierBanqueImpl metierBanque = (MetierBanqueImpl) joinPoint.getTarget();
		Compte compte = metierBanque.consulter(code);
		if (compte.getSolde()<montant) throw new RuntimeException("Solde insuffisant");
		return proceedingJoinPoint.proceed();
	}
}

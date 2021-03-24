package test;

import java.util.Scanner;

import main.java.metier.Compte;
import main.java.metier.IMetierBanque;
import main.java.metier.MetierBanqueImpl;

public class Application {

	public static void main(String[] args) {
		new Application().start();

	}

	public void start() {
		System.out.println("Démarrage de l'application");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Donner le code du compte ;");
		long code = scanner.nextLong();
		System.out.println("Donner le solde initiale du compte :");
		double solde = scanner.nextDouble();
		IMetierBanque metierBanque = new MetierBanqueImpl();
		metierBanque.addCompte(new Compte(code, solde));
		while (true) {
			try {
				System.out.println("Type d'opération :");
				String type = scanner.next();
				if (type.equals("quitter")) break;
				System.out.println("Montant :");
				double montant =scanner.nextDouble();
				if (type.equals("v")) {
					metierBanque.verser(code, montant);
					
				}
				else if (type.equals("r")) {
					metierBanque.retirer(code, montant);
					
				}
				Compte compte = metierBanque.consulter(code);
				System.out.println("Solde = " + compte.toString());
				System.out.println("Solde = " + compte);
			} catch (Exception e) {
				// Auto-generated catch block
				System.out.println(e.getMessage());
//				e.printStackTrace();
			}
			
		}
		System.out.println("-- Fin de l'application --");
	}

}

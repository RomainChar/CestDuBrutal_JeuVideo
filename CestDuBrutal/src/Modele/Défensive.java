package Modele;

import java.io.Serializable;

/**
 * La classe Defensive implemente l'interface Strategie. Les etudiants en strategie defensive ajoutent des points de vie aux coequipiers.
 * Cette classe implemente Serializable, c'est a dire qu'elle peut etre convertie en un tableau d'octets afin de pouvoir la manipuler.
 * 
 * @author Romain CHARRONDIERE et Tanguy SCHENBERG
 * @version 1.2
 */
public class Défensive implements Stratégie, Serializable{
	/** 
	 * Va réaliser l'action de l'étudiant.
	 * L'étudiant soigne l'étudiant allié avec lez moins de crédits (en réalisant les calculs du nombre de crédits en fonction des caractréristiques des étudiants)
	 * 
	 * @param etuCombattant L'etudiant qui fait l'action
	 * @param etuCreditsMinJ1 L'etudiantdu joueur 1 avec le moins de crédits
	 * @param L'étudiant du joueur 2 avec le moins de crédits
	 */
	public void faireAction(Etudiant etuCombattant, Etudiant etuCreditsMinJ1, Etudiant etuCreditsMinJ2) {
		Etudiant etuCombattu=etuCreditsMinJ1;
		if (etuCombattant.getJoueur()==etuCreditsMinJ2.getJoueur()){
			etuCombattu=etuCreditsMinJ2;
		}
		int x=Math.toIntExact(Math.round(Math.random()*100));
		if (x>=0 && x<=20+ 6* etuCombattant.getDexterité()) {
			
			double y=Math.random()*0.6;
			int CreditsAjoute = (int)Math.floor(y * (10+etuCombattu.getconstitution()));
			CreditsAjoute=etuCombattu.affecterCredits(CreditsAjoute);
			}
	}
	/** 
	 * Retourne le nom de la stratégie (utile pour l'affichage)
	 * 
	 * @return une chaîne de caractère du nom de la stratégie, ici "Defensive"
	 */
	public String getNomStrategie() {
		return "Defensive";
	}
	/** 
	 * Retourne le numéro correspondant à lka stratégie, afin de connître l'index à utiliser lors de l'affichage de la stratégie (dans une comboBox par exemple)
	 * 
	 * @return l'entier du numéro de la stratégie, ici 2
	 */
	public int getNumStrategie() {
		return 2;
	}
}

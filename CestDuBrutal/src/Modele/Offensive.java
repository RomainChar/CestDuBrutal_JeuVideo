package Modele;
import java.io.Serializable;
/**
 * La classe Offensive implemente l'interface Stratégie. Les joueurs en stratégie offensive enl�ve des points de vie aux joueurs adverses.
 *  Cette classe implemente Serializable, c'est à dire qu'elle peut être convertie en un tableau d'octets afin de pouvoir la manipuler.
 * @author Romain CHARRONDIERE et Tanguy SCHENBERG
 * @version 1.2
 */

public class Offensive implements Stratégie, Serializable{
	/** 
	 * Va réaliser l'action de l'étudiant
	 * L'étudiant inflige des dégâts à l'étudiant ennemi avec lez moins de crédits (en réalisant les calculs du nombre de crédits en fonction des caractréristiques des étudiants)
	 * 
	 * @param etuCombattant L'etudiant qui fait l'action
	 * @param etuCreditsMinJ1 L'etudiantdu joueur 1 avec le moins de crédits
	 * @param L'étudiant du joueur 2 avec le moins de crédits
	 */
	public void faireAction(Etudiant etuCombattant, Etudiant etuCreditsMinJ1, Etudiant etuCreditsMinJ2) {
		Etudiant etuCombattu=etuCreditsMinJ1;
		if (etuCombattant.getJoueur()==etuCreditsMinJ1.getJoueur()){
			etuCombattu=etuCreditsMinJ2;
		}
		int x=Math.toIntExact(Math.round(Math.random()*100));
		if (x>=0 && x<=40+ 3*etuCombattant.getDexterité()) {
			
			double y=Math.random()*1;
			int coeffDegats=Math.max(0, Math.min(100, 10*etuCombattant.getForce()-5*etuCombattu.getResistance()) / 100);
			int CreditsEnleve = (int)Math.floor(y * (1+coeffDegats) * 10);
			etuCombattu.retirerCredits(CreditsEnleve);
		}
	}
	/** 
	 * Retourne le nom de la stratégie (utile pour l'affichage)
	 * 
	 * @return une chaîne de caractère du nom de la stratégie, ici "Offesnsive"
	 */
	public String getNomStrategie() {
		return "Offensive";
	}
	/** 
	 * Retourne le numéro correspondant à lka stratégie, afin de connître l'index à utiliser lors de l'affichage de la stratégie (dans une comboBox par exemple)
	 * 
	 * @return l'entier du numéro de la stratégie, ici 1
	 */
	public int getNumStrategie() {
		return 1;
	}

}

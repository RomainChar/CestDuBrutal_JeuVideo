package Modele;
/**
 * L'interface stratégie donne les caractéristiques à implémenter par les différentes stratégies qui sont Aléatoire, Défensive et Offensive.
 * Cette classe implemente Serializable, c'est à dire qu'elle peut être convertie en un tableau d'octets afin de pouvoir la manipuler.
 * 
 * @author Romain CHARRONDIERE et Tanguy SCHENBERG
 * @version 1.2
 */

public interface Stratégie {
	
	/**
	* Définit les actions que font les etudiants lors du combat.
	* Les actions faites dépendent de la stratégie de combat.
	* 
	* @param etuCombattant L'etudiant qui fait l'action
	* @param etuCreditsMinJ1 L'etudiantdu joueur 1 avec le moins de crédits
	* @param L'étudiant du joueur 2 avec le moins de crédits
	*/
	public void faireAction(Etudiant etuCombattant, Etudiant etuCreditsMinJ1, Etudiant etuCreditsMinJ2);
	/** 
	 * Retourne le nom de la stratégie (utile pour l'affichage)
	 * 
	 * @return une chaîne de caractère du nom de la stratégie
	 */
	public String getNomStrategie();
	/** 
	 * Retourne le numéro correspondant à lka stratégie, afin de connître l'index à utiliser lors de l'affichage de la stratégie (dans une comboBox par exemple)
	 * 
	 * @return l'entier du numéro de la stratégie
	 */
	public int getNumStrategie()
;}

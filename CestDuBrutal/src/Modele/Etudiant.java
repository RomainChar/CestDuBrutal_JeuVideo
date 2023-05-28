package Modele;

import java.io.Serializable;
import java.util.*;
/**
 * La classe Etudiant decrit les etudiants, elle contient les informations sur leurs caracteristiques et sur leur zone d'affectation. 
 * Cette classe implemente Serializable, c'est e dire qu'elle peut etre convertie en un tableau d'octets afin de pouvoir la manipuler.
 * 
 * @author Romain CHARRONDIERE et Tanguy SCHENBERG
 * @version 1.2
 */ 
public class Etudiant implements Serializable{
	/**
	 * Un entier correspondant aux crédits actuels de l'étudiant
	 */
	private int credits;
	/**
	 * La zone à laquelle est affecté l'étudiant
	 */
	private Zone zone;
	/**
	 * La stratégie de l'étudiant (défensive, aléatoire ou offensive)
	 */
	private Stratégie strategie;
	/**
	 * Une map des caractéristiques de l'étudiant, avec le nom de la caractéristique en clé et sa valeur en valeur
	 */
	private HashMap<String, Integer> caracteristiques;
	/**
	 * Une chaine de caractère du type de l'étudiant (etudiant, etudiant d'élite ou maitre du gobi)
	 */
	private String type;
	/**
	 * Le joueur qui possède l'étudiant
	 */
	private Joueur joueur;
		
	/** 
	* Constructeur de Etudiant: Initialise l'etat de l'etudiant.
	* 
	* @param force la force affectée initialement à l'étudiant
	* @param dextérité la dextérité affectée initialement à l'étudiant
	* @param résistance la résistance affectée initialement à l'étudiant
	* @param constitution la cosntituion affectée initialement à l'étudiant
	* @param initiative l'initiative affectée initialement à l'étudiant
	* @param type le type de l'etudiant (etudiant, etudiant d'etlite ou maitre du gobi)
	* @param joueur Le joueur qui possède l'étudiant
	*
	*/	
	public Etudiant(int force, int dextérité, int résistance, int constitution, int initiative, String type, Joueur joueur) {
		
		this.caracteristiques = new HashMap<String, Integer>();
		this.caracteristiques.put("constitution", constitution);
		this.caracteristiques.put("force", force);
		this.caracteristiques.put("dexterite", dextérité);
		this.caracteristiques.put("resistance", résistance);
		this.caracteristiques.put("initiative", initiative);
		this.credits=30+constitution;
		this.type=type;
		this.joueur=joueur;
		this.strategie= new Offensive();
		
	}
	/** 
	* Constructeur de Etudiant: Initialise l'etat de l'etudiant avec une zone initiale
	* 
	* @param force la force affectée initialement à l'étudiant
	* @param dextérité la dextérité affectée initialement à l'étudiant
	* @param résistance la résistance affectée initialement à l'étudiant
	* @param constitution la cosntituion affectée initialement à l'étudiant
	* @param initiative l'initiative affectée initialement à l'étudiant
	* @param type le type de l'etudiant (etudiant, etudiant d'etlite ou maitre du gobi)
	* @param joueur Le joueur qui possède l'étudiant
	* @param zone La zone dans laquelle sera l'étudiant initialement
	*
	*/
	public Etudiant(int force, int dextérité, int résistance, int constitution, int initiative, String type, Joueur joueur, Zone zone) {
		
		this.caracteristiques = new HashMap<String, Integer>();
		this.caracteristiques.put("constitution", constitution);
		this.caracteristiques.put("force", force);
		this.caracteristiques.put("dexterite", dextérité);
		this.caracteristiques.put("resistance", résistance);
		this.caracteristiques.put("initiative", initiative);
		this.credits=30+constitution;
		this.type=type;
		this.joueur=joueur;
		this.strategie= new Offensive();
		this.zone=zone;
		
	}
	/** 
	* Obtenir les caracteristiques de l'etudiant.
	* 
	* @return Une map des caracteristiques de l'etudiant avec le nom de la caractéristique en clé et sa valeur en valeur
	*/
	public HashMap<String, Integer> getCaracteristiques(){
		
		return this.caracteristiques;
	}
	/** 
	* Obtenir la force de l'etudiant.
	* 
	* @return force de l'etudiant
	*/
	public int getForce() {
		
		return this.caracteristiques.get("force");
	}
	/** 
	* Obtenir la dexterite de l'etudiant.
	* 
	* @return dexterite de l'etudiant
	*/
	public int getDexterité() {
		
		return this.caracteristiques.get("dexterite");
	}
	/** 
	* Obtenir la resistance de l'etudiant.
	* 
	* @return resistance de l'etudiant
	*/
	public int getResistance() {
		
		return this.caracteristiques.get("resistance");
	}
	/** 
	* Obtenir la constitution de l'etudiant.
	* 
	* @return constitution de l'etudiant
	*/
	public int getconstitution() {
		
		return this.caracteristiques.get("constitution");
	}
	/** 
	* Obtenir l'initiative de l'etudiant.
	* 
	* @return initiative de l'etudiant
	*/
	public int getInitiative() {
		
		return this.caracteristiques.get("initiative");
	}
	/** 
	* Obtenir les credits de l'etudiant.
	* 
	* @return credits de l'etudiant
	*/
	public int getCredit() {
		
		return this.credits;
	}
	/** 
	* Obtenir le type de l'etudiant.
	* 
	* @return type (etudiant, etudiant d'elite ou Maitre du Gobi) de l'etudiant
	*/
	public String getType() {
		
		return this.type;
	}
	/** 
	* Obtenir la zone d'affectation de l'etudiant.
	* 
	* @return zone d'affectation de l'etudiant
	*/
	public Zone getZone() {
		
		return this.zone;
	}
	/** 
	* Obtenir la strategie de l'etudiant.
	* 
	* @return strategie ( aleatoire, defensive ou offensive) de l'etudiant
	*/
	public Stratégie getStratégie() {
		
		return this.strategie;
	}
	/** 
	 * Obtenir le nom de la strategie de l'etudiant.
	 * 
	 * @return nom de la strategie ( aleatoire, defensive ou offensive) de l'etudiant
	 */
	public String getNomStratégie() {
		return this.strategie.getNomStrategie();
		}
	/** 
	 * Obtenir la joueur qui possède l'étudiant
	 * 
	 * @return joueur de l'etudiant
	 */
	public Joueur getJoueur() {
		return this.joueur;
	}
	/** 
	* Savoir si un etudiant est mort ou non.
	* @return un booléen (false si l'étudiant est en vie et true s'il est mort)
	*/
	public boolean estMort() {
		
		if (this.credits <= 0) {
			return true;
		}
		return false;
	}
	
	/** 
	* Affecter une zone a un etudiant.
	* 
	* @param zone la zone à laquelle sera affecté l'étudiant
	*/
	public void affecterZone(Zone zone) {
	
		this.zone=zone;
	}
	/** 
	* Affecter une nouvelle zone à un etudiant entre deux manches.
	* 
	* @param zone la zone à laquelle sera réaffécté l'étudiant 
	* @param partie la partie en cours (sert à effectuer les vérification pour autoriser l'étudiant à se déplacer
	*/
	public void reaffecterZone(Zone zone,Partie partie) {
		
		HashMap<Integer, Zone> map = partie.getMap();
		if (zone.getControlleur()==null) {
			if (this.zone == map.get(0)) {
				this.zone=zone;
			}
			else if (zone != map.get(0)) {
				int nbEtuDansLaZone=0;
				for (int i=0; i<20; i++) {
					if (partie.getJoueurs().get(partie.getJoueurSelectionne()).getTroupes().get(i).getZone()==this.zone && partie.getJoueurs().get(partie.getJoueurSelectionne()).getTroupes().get(i).estMort()==false) {
						nbEtuDansLaZone++;
					}
				}
				if (nbEtuDansLaZone>1) {
					this.zone=zone;
				}
			}
			
		}
		
	}
		
	/** 
	* Affecter une strategie à un etudiant.
	* 
	* @param stratégbie la stratégie. 
	*/
	public void choisirStratégie(Stratégie stratégie) {
		
		this.strategie=stratégie;
	}
	/** 
	* Mettre en action un etudiant durant le combat.
	* 
	* @param etuCreditsMinJ1 etudiant avec le minimum de credits du joueur 1
	* @param etuCreditsMinJ2 etudiant avec le minimum de credits du joueur 2
	*/
	public void combattre(Etudiant etuCreditsMinJ1, Etudiant etuCreditsMinJ2) {
		
		strategie.faireAction(this, etuCreditsMinJ1, etuCreditsMinJ2);
	}
	/** 
	* Definir la force d'un etudiant.
	* 
	* @param force valeur de la force
	*/
	public void setForce(int force) {
		
		this.caracteristiques.put("force", force);		
	}
	/** 
	* Definir la dexterite d'un etudiant.
	* 
	* @param dexterite valeur de la dexterite
	*/
	public void setDexterite(int dexterite) {
		
		this.caracteristiques.put("dexterite", dexterite);		
	}
	/** 
	* Definir l'initiative d'un etudiant.
	* 
	* @param initiative valeur de l'initiative
	*/
	public void setInitiative(int initiative) {
		
		this.caracteristiques.put("initiative", initiative);		
	}
	/** 
	* Definir la resitance d'un etudiant.
	* 
	* @param resistance valeur de la resistance
	*/
	public void setResistance(int resistance) {
		
		this.caracteristiques.put("resistance", resistance);		
	}
	/** 
	* Definir la constitution d'un etudiant.
	* 
	* @param constitution valeur de la constitution
	*/
	public void setConstitution(int constitution) {
		
		this.caracteristiques.put("constitution", constitution);
		this.setCredits(constitution);
		
	}
	/** 
	* Definir le nombre de crédits ( 30 + constitution) d'un etudiant.
	* 
	* @param constitution valeur de la constition de l'etudiant
	*/
	public void setCredits(int constitution) {
		
		this.credits=30+constitution;
	}
	/** 
	 * Ajouter des credits à un étudiant.
	 * 
	 * @param creditsAjoutes nombre de crédits à ajouter
	 */
	public int affecterCredits(int creditsAjoutes) {
			if (this.credits+creditsAjoutes < 30+this.caracteristiques.get("constitution")) {
				this.credits += creditsAjoutes;
				return creditsAjoutes;
			}
			else {
				creditsAjoutes=this.getconstitution()+30 - this.credits;
				this.credits=this.getconstitution()+30;
				return creditsAjoutes;
			}
	}
	/** 
	 * Retirer des credits à un étudiant.
	 * 
	 * @param creditsEnleves nombre de crédits à retirer
	 */
	public void retirerCredits(int creditsEnleves) {
		
			this.credits -= creditsEnleves;
			if (this.credits<0) {
				this.credits=0;
			}
}
	
/** 
	 * Donne l'etat ( credits, zone d'affectation, strategie et joueur associé) d'un etudiant à un moment donné.
	 * 
	 * @return Une chaine de caractère décrivant l'état d'un étudiant.
	 */
	public String toString() {
	
	 
		StringBuffer sb = new StringBuffer ();
		
		sb.append(this.type+" - ");
		Iterator<String> itmap = this.caracteristiques.keySet().iterator();
		while (itmap.hasNext()) {
			String key=itmap.next();
			Integer value=this.caracteristiques.get(key);
			sb.append(key+" : "+value+" / ");
			
		}
		sb.append("/ credits : " +this.credits+". ");
		if (this.zone!=null) {
			sb.append("Il est dans la zone "+this.zone.getNom()+". ");
		}
		else {
			sb.append("Il n'appartient a aucune zone. ");
		}
		if (this.strategie!=null) {
			sb.append("La strategie de cet etudiant est "+this.strategie.getNomStrategie()+". ");
		}
		else {
			sb.append("Il n'a aucune strategie. ");
		}
		
		sb.append("C'est un etudiant de "+this.joueur.getNom());
		return sb.toString();
	}
	
}

package Modele;


import java.io.Serializable;
import java.util.LinkedList;
/**
 * La classe Zone décrit les différentes zones de combats. 
 * Cette classe implemente Serializable, c'est à dire qu'elle peut être convertie en un tableau d'octets afin de pouvoir la manipuler.
 * 
 * @author Romain CHARRONDIERE et Tanguy SCHENBERG
 * @version 1.2
 */
public class Zone extends Thread implements Serializable{
	/**
	 * Une chaine de caractère du nom de la zone
	 */
	private String nom;
	/**
	 * Une entier représentatn le numéro de la zone
	 */
	private int numZone;
	/**
	 * Un joueur qui est le controlleur de la zone
	 */
	private Joueur controleur;
	/**
	 * Une liste d'étudiants, ce sont les étudiants du joueur 1 affectés à cette zone
	 */
	private LinkedList<Etudiant> troupesJ1;
	/**
	 * Une liste d'étudiants, ce sont les étudiants du joueur 2 affectés à cette zone
	 */
	private LinkedList<Etudiant> troupesJ2;
	/**
	 * Une liste d'étudiants, ce sont tous les étudiants affectés à cette zone triés par initiative décroisssante
	 */
	private LinkedList<Etudiant> allTroupes;
	/**
	 * Une booléen qui indique si le combat est en cours dans la zone
	 */
	private boolean combatEnCours;
	/**
	 * Un Thread qui permet de lancer le combat dans cette zone en même temps que dans les autres zones
	 */
	private Thread combatThread;
	
	/**
	 * Permet de changer la couleur du texte affiché dans la console en vert
	 */
	public static final String ANSI_GREEN = "\u001B[32m";
	/**
	 * Permet de réinitialiser la couleur du texte affiché dans la console
	 */
	public static final String ANSI_RESET = "\u001B[0m";
	
	/** 
	* Constructeur de Zone: initialise les zones avec leur nom en déffinissant combatEnCours comme false.
	* 
	* @param nomZone Le nom de la zone.
	*/
	public Zone(String nomZone) {
		
		this.nom=nomZone;
		this.combatEnCours=false;
	}
	/** 
	* Constructeur de Zone: initialise les zones avec leur nom et un numéro en définissant combatEnCours comme false.
	* 
	* @param nomZone Le nom de la zone
	* @param numZone Le numéro de la zone
	*/
	public Zone(String nomZone, int numZone) {
		
		this.nom=nomZone;
		this.numZone=numZone;
		this.combatEnCours=false;
	}
	/** 
	* Permet d'obtenir le nom de la zone.
	* 
	* @return Le nom de la zone
	0*/
	public String getNom() {
		
		return this.nom;
	}
	/** 
	* Permet d'obtenir le numéro de la zone.
	* 
	* @return Le numéro de la zone
	*/
	public int getNum() {
		
		return this.numZone;
	}
	/** 
	* Indiquer les étudiants de la zone par ordre d'initiative décroissante
	* 
	* @param La liste des etudiants ordonnée par initiative
	*/
	public void setAllTroupes(LinkedList<Etudiant> allTroupes) {
		
		this.allTroupes=allTroupes;
	}
	/** 
	* Indiquer quels sont les étudiants du joueur 1 dans la zone
	* 
	* @param La liste des etudiants du joueur 1.
	*/
	public void setTroupesJ1(LinkedList<Etudiant> troupesJ1) {
		
		this.troupesJ1=troupesJ1;
	}
	/** 
	* Indiquer quels sont les étudiants du joueur 2 dans la zone
	* 
	* @param La liste des etudiants du joueur 2.
	*/
	public void setTroupesJ2(LinkedList<Etudiant> troupesJ2) {
		
		this.troupesJ2=troupesJ2;
	}
	/**
	 * Indiquer si le combat est en cours ou non
	 * @param combat un booléen true si le combat est en cours et false sinon
	 */
	public void setCombatEnCours(boolean combat) {
		this.combatEnCours=combat;
	}
	/** 
	* Définir le controleur d'une zone à la fin d'un affrontement d'étudiants.
	* Si un joueur a tous ses joueurs dans la zone mort, alors le joueur adverse devient controlleur
	* 
	* @param troupesJ1 La liste des étudiants du joueur 1 dans la zone
	* @param troupesJ1 La liste des étudiants du joueur 2 dans la zone
	* 
	* @return true si un joueur controle la zone, false sinon pour que le combat continue
	*/
	public boolean définirControleur(LinkedList<Etudiant> troupesJ1, LinkedList<Etudiant> troupesJ2) {
		
		boolean J2Controlle = true;
		for (int iEtu=0; iEtu<troupesJ1.size();iEtu++) {
			if (troupesJ1.get(iEtu).estMort()==false) {
				J2Controlle=false;
				
			}
		}
		boolean J1Controlle = true;
		for (int iEtu=0; iEtu<troupesJ2.size();iEtu++) {
			if (troupesJ2.get(iEtu).estMort()==false) {
				J1Controlle=false;
			}
		}
		if (J1Controlle) {
			this.controleur=troupesJ1.get(0).getJoueur();
			troupesJ1.get(0).getJoueur().nouvelleZoneControllee();
			return true;
		}
		else if (J2Controlle) {
			this.controleur=troupesJ2.get(0).getJoueur();
			troupesJ2.get(0).getJoueur().nouvelleZoneControllee();
			return true;
		}
		else {
			return false;
		}
	}
	/** 
	* Obtenir le controleur de la zone
	* 
	* @return le joueur qui controlle la zone
	*/
	public Joueur getControlleur() {
		
		return this.controleur;
	}
	/**
	 * Crée un Thread de la zone qui va effectuer des combats tant qu'aucun Thread de zone ne se termine 
	 */
	public void combatDeZone() {
		combatThread = new Thread(this);
		combatThread.start();

	}
	/** 
	* Thread du combat de la zone, fait s'affronter les étudiants jusqu'à ce que tous les étudiants d'un joueurs soient morts ou que le combat se termine dans une autre zone.
	* 
	*/
	public void run() {
		
		try {
			Thread.sleep( 1000 );
		} catch (InterruptedException e) {}
		this.combatEnCours=true;
		while (définirControleur(troupesJ1, troupesJ2)==false && combatEnCours) {
			int iEtuInitial2=0;
			while (troupesJ2.get(iEtuInitial2).estMort()) {
				iEtuInitial2++;
			}
				Etudiant etuCreditsMinJ2=troupesJ2.get(iEtuInitial2);
			for (int iEtu=1; iEtu<troupesJ2.size();iEtu++) {
				if (troupesJ2.get(iEtu).getCredit()< etuCreditsMinJ2.getCredit() && troupesJ2.get(iEtu).estMort()==false) {
					etuCreditsMinJ2=troupesJ2.get(iEtu);
				}
			}
			int iEtuInitial1=0;
			while (troupesJ1.get(iEtuInitial1).estMort()) {
				iEtuInitial1++;
			}
				Etudiant etuCreditsMinJ1=troupesJ1.get(iEtuInitial1);
			for (int iEtu=1; iEtu<troupesJ1.size();iEtu++) {
				if (troupesJ1.get(iEtu).getCredit()< etuCreditsMinJ1.getCredit() && troupesJ1.get(iEtu).estMort()==false ) {
					etuCreditsMinJ1=troupesJ1.get(iEtu);
				}
			}
			while (allTroupes.get(0).estMort()){
				allTroupes.remove(0);
			}
			allTroupes.get(0).combattre(etuCreditsMinJ1, etuCreditsMinJ2);
			
			Etudiant etuCombattant=allTroupes.get(0);
			for (int iEtu=0; iEtu<allTroupes.size()-1;iEtu++) {
				allTroupes.set(iEtu, allTroupes.get(iEtu+1));
			}
			allTroupes.set(allTroupes.size()-1, etuCombattant);
			try {
				Thread.sleep( 100 );
			} catch (InterruptedException e) {}
		}
		
		if (this.controleur!=null) {
			System.out.println(ANSI_GREEN+"Le combat est termine dans "+this.nom+". Le gagnant de cette zone est : "+this.controleur.getNom()+ANSI_RESET);
		}
	}
}

package Modele;
import java.io.Serializable;
import java.util.*;

/**
 * La classe partie contient le déroulement du jeu mettant en action les autres classes de Modele.
 * Cette classe implemente Serializable, c'est à dire qu'elle peut être convertie en un tableau d'octets afin de pouvoir la manipuler.
 * Cette classe hérite de Observable, c'est à dire qu'elle peut être observée par une classe qui implemente l'interface Observer.
 * Dans notre cas, certaines actions de la classe Partie seront notifiees à la classe MonInterface
 * 
 * @author Romain CHARRONDIERE et Tanguy SCHENBERG
 * @version 1.2
 */


public class Partie extends Observable implements Serializable{
	private static final long serialVersionUID = 4L;
	/**
	 * La liste des joueurs de la partie (il y en a 2)
	 */
	private LinkedList<Joueur> listJ;
	/**
	 * La map des zones de la partie (le numéro de la zone en clé et la zone en valeur)
	 */
	private HashMap<Integer, Zone> mapDeLaPartie;
	/**
	 * le numéro de l'étudiant sélectionné (sur l'interface graphique)
	 */
	private int etudiantSelectionne=0;
	/**
	 * le numéro du joueur sélectionné (sur l'interface graphique)
	 */
	private int joueurSelectionne=0;
	/**
	 * un entier des points dépensés par le joueur sélectionné (sur l'interface graphique)
	 */
	private int pointsDepenses=0;
	/**
	 * un booléen qui indique si les crédits/zone du joueur 1 sont affichés (sur l'interface graphique)
	 */
	private boolean affichageCreditsJ1=false;
	/**
	 * un booléen qui indique si les crédits/zone du joueur 2 sont affichés (sur l'interface graphique)
	 */
	private boolean affichageCreditsJ2=false;
	/**
	 * un booléen qui indique si le combat est en cours
	 */
	private boolean combatEnCours=false;
	/**
	 * un booléen qui indique si l'affectation des étudiants est en cours
	 */
	private boolean affectationEnCours=true;
	/**
	 * un booléen qui indique si la réaffectation des étudiants est en cours
	 */
	private boolean reaffectationEnCours=false;
	/**
	 * un booléen qui indique si l'affectation actuelle des étudiants respecte els règles
	 */
	private boolean affectationValide=false;
	/**
	 * Permet de réinitialiser la couleur du texte affiché dans la console
	 */
	private static final String ANSI_RESET = "\u001B[0m";
	/**
	 * Permet de changer la couleur du texte affiché dans la console en jaune
	 */
	private static final String ANSI_YELLOW = "\u001B[33m";
	/**
	 * Permet de changer la couleur du texte affiché dans la console en rouge
	 */
	private static final String ANSI_RED = "\u001B[31m";
	/**
	 * Permet de changer la couleur du texte affiché dans la console en noir
	 */
	private static final String ANSI_BLACK = "\u001B[30m";
	/**
	 * Permet de changer la couleur du texte affiché dans la console en vert
	 */
	private static final String ANSI_GREEN = "\u001B[32m";
	/**
	 * Permet de changer la couleur du texte affiché dans la console en bleu
	 */
	private static final String ANSI_BLUE = "\u001B[34m";
	/**
	 * Permet de changer la couleur du texte affiché dans la console en violet
	 */
	private static final String ANSI_PURPLE = "\u001B[35m";
	/**
	 * Permet de changer la couleur du texte affiché dans la console en cyan
	 */
	private static final String ANSI_CYAN = "\u001B[36m";
	/** 
	 * Constructeur de Partie: Initialise les caractéristiques de la partie
	 * 
	 * @param j1 le premier joueur
	 * @param j2 le deuxieme joueur
	 * @param mapDeLaPartie une map des zones dans laquelle aura lieu la partie (le numéro de la zone en clé et la zone en valeur)
	 */
	public Partie(Joueur j1, Joueur j2, HashMap<Integer, Zone> mapDeLaPartie) {
		this.listJ=new LinkedList<Joueur>();
		this.mapDeLaPartie=mapDeLaPartie;
		this.listJ.add(j1);
		this.listJ.add(j2);	
	}
	/** 
	* Donne l'état de la partie à un moment donné.
	* 
	* @return Une chaine de caractère décrivant la partie à un moment donné.
	*/
	
	public String toString() {		
		
		StringBuffer sb = new StringBuffer ();
		sb.append("Joueurs : ");
		for (int nbj=0; nbj<2;nbj++) {
			sb.append("\nJoueur "+(nbj+1)+" "+ this.listJ.get(nbj).getNom()+" du programme "+ this.listJ.get(nbj).getProgramme());
			sb.append("\nIl possede les troupes suivantes : \n");
			for(int nbEtu=0; nbEtu<20;nbEtu++) {
				sb.append(this.listJ.get(nbj).getTroupes().get(nbEtu).toString()+"\n");
			}
		}
		return sb.toString();
	}
	/** 
	 * Met à jour l'affichage de l'interface graphique
	 * 
	 */
	public void update() {
		this.setChanged();
		this.notifyObservers();
	}
	/** 
	* Indique si la partie est en cours ou non, en fonction du nombre de zones controlées.
	* 
	* @return false si un joueur controlle plus de 3 zones (partie terminée), true sinon
	*/
	public boolean estEnCoursPartie() {
		
		if (this.listJ.get(0).getZonesControllees() >=3) {
			return false;
		}
		if (this.listJ.get(1).getZonesControllees() >=3) {
			return false;
		}
		return true;
		
	}
	/** 
	 * Désactive l'affichage des credits/zone des 2 joueurs 
	 * 
	 */
	public void desactiverAffichagesCredits() {
			affichageCreditsJ1=false;
			affichageCreditsJ2=false;
			this.setChanged();
			this.notifyObservers();
	}
	/** 
	* 
	* Affiche les credits du joueur s'ils ne sont pas affichés, mais les cache s'ils le sont déjà.
	* 
	* @param Un entier correspondant au numéro de joueur dont on veut afficher les crédits
	*/

	public void switchAffichageCredits(int numJoueur) {
		
		if (numJoueur==0) {
			if (this.affichageCreditsJ1) {
				affichageCreditsJ1=false;
			}
			else {
				affichageCreditsJ1=true;
			}
		}
		else {
			if (this.affichageCreditsJ2) {
				affichageCreditsJ2=false;
			}
			else {
				affichageCreditsJ2=true;
			}
		}
		this.setChanged();
		this.notifyObservers();
		
	}
	/** 
	 * Donne l'état de  l'affichage des credits/zone du joueur 1
	 * 
	 * @return true si les credits/zone du joueur 1 son affichés, false sinon
	 */
	public boolean getAffichageCreditsJ1() {
			return this.affichageCreditsJ1;
	}
	/** 
	 * Donne l'état de  l'affichage des credits/zone du joueur 2
	 * 
	 * @return true si les credits/zone du joueur 2 son affichés, false sinon
	 */
	public boolean getAffichageCreditsJ2() {
		return this.affichageCreditsJ2;
	}
	/** 
	* Connaître le total de crédits que le joueur possède dans chaque zone
	* 
	* @param numJoueur Un entier correspondant au numéro de joueur.
	* 
	* @return Une map avec le nom des zones en clé et le total de crédits en valeur
	*/
	public HashMap<String, Integer> getCreditsZones(int numJoueur){
		
		HashMap<String, Integer> CreditsZone = new HashMap<String, Integer>();
		CreditsZone.put("Reservistes", 0);
		CreditsZone.put("Bibliotheque", 0);
		CreditsZone.put("BDE", 0);
		CreditsZone.put("Quartier Administratif", 0);
		CreditsZone.put("Halles Industrielles", 0);
		CreditsZone.put("Halle Sportive", 0);
		for (int i=0; i<20; i++) {
			switch(this.listJ.get(numJoueur).getTroupes().get(i).getZone().getNum()) {
			case 0 : CreditsZone.put("Reservistes", CreditsZone.get("Reservistes")+this.listJ.get(numJoueur).getTroupes().get(i).getCredit());break;
			case 1 : CreditsZone.put("Bibliotheque", CreditsZone.get("Bibliotheque")+this.listJ.get(numJoueur).getTroupes().get(i).getCredit());break;
			case 2 : CreditsZone.put("BDE", CreditsZone.get("BDE")+this.listJ.get(numJoueur).getTroupes().get(i).getCredit());break;
			case 3 : CreditsZone.put("Quartier Administratif", CreditsZone.get("Quartier Administratif")+this.listJ.get(numJoueur).getTroupes().get(i).getCredit());break;
			case 4 : CreditsZone.put("Halles Industrielles", CreditsZone.get("Halles Industrielles")+this.listJ.get(numJoueur).getTroupes().get(i).getCredit());break;
			case 5 : CreditsZone.put("Halle Sportive", CreditsZone.get("Halle Sportive")+this.listJ.get(numJoueur).getTroupes().get(i).getCredit());
			}
		}
		
		return CreditsZone;
	}
	/** 
	* Permet de connaître le joueur actif à un moment donné.
	* 
	* @return Le numéro joueur selectionné.
	*/
	public int getJoueurSelectionne() {
		
		return this.joueurSelectionne;
	}
	/** 
	* Permet de connaître l'étudiant sélectionné actuellement par le joueur.
	* @return Le numéro de l'étudiant selectionné.
	*/
	public int getEtudiantSelectionne() {
		
		return this.etudiantSelectionne;
	}
	/** 
	* Permet de selectionner le joueur actif.
	* 
	* @param numJoueur Entier correspondant au numéro de joueur
	* 
	*/
	public void selectionnerJoueur(int numJoueur) {
		
		this.joueurSelectionne = numJoueur ;

	}
	/** 
	* Permet de selectionner un étudiant.
	* 
	* @param numEtudiant Entier correspondant au numéro d'etudiant.
	* 
	*/
	public void selectionnerEtudiant(int numEtudiant) {
		
		this.etudiantSelectionne = numEtudiant;

	}
	/** 
	* Permet d'imcrémenter le numéro de l'étudiant sélectionné (sélectionner l'étudiant suivant)
	* 
	*/
	public void incrementerEtudiantSelectionne() {
		
		if (this.etudiantSelectionne<19) {
			this.etudiantSelectionne++;
		}
	}
	/** 
	* Permet de decrementer le numéro de l'étudiant sélectionné (sélectionner l'étudiant précédent)
	* 
	*/
	public void decrementerEtudiantSelectionne() {
		
		if (this.etudiantSelectionne>0) {
			this.etudiantSelectionne--;
		}
	}
	/** 
	 * Donne la map utilisée dans la partie
	 * 
	 * @return une map avec le numéro de la zone en clé et la zone en valeur
	 */
	public HashMap<Integer, Zone> getMap(){
		return mapDeLaPartie;
	}
	/** 
	* Indique si la partie est à l'étape de réaffectation
	* 
	* @return Un booléen décrivant l'état de la réaffectation.
	*/
	public boolean getEtatReaffectation() {
		
		return this.reaffectationEnCours;
	}
	/** 
	* Indique si la partie est à l'étape de l'affectation
	* 
	* @return Un booléen décrivant l'état de l'affectation.
	*/
	public boolean getEtatAffectation() {
		
		return this.affectationEnCours;
	}
	/** 
	* Vérifie l'affectation puis la termine en passant au joueur suivant.
	* 
	* @param nom nom du joueur qui termine son affectation
	* @param programme Programme du joueur qui termine son affectation
	*/
	public void terminerAffectation(String nom, String programme) {
		
		this.listJ.get(joueurSelectionne).setNom(nom);
		this.listJ.get(joueurSelectionne).setProgramme(programme);
		if (this.affectationValide) {
			if (this.joueurSelectionne==0) {
				this.joueurSelectionne=1;
			}
			else {
				this.joueurSelectionne=0;
				this.affectationEnCours=false;
				this.reaffectationEnCours=false;
				if (this.estEnCoursPartie()) {
					this.combatEnCours=true;
					this.setChanged();
					this.notifyObservers();

				}
			}
		}
		this.affectationValide=false;
		this.etudiantSelectionne=0;
		this.pointsDepenses=0;
		this.setChanged();
		this.notifyObservers();
	}
	/** 
	* Vérifie la validité de l'affectation actuelle
	* 
	*/
	public void validerAffectation() {
		
		if (this.affectationEnCours) {
			int pointsUtilises=0;
			int nbReserviste=0;
			int nbBibliotheque=0;
			int nbBDE=0;
			int nbQA=0;
			int nbHallesI=0;
			int nbHalleS=0;
			for (int i=0; i<20; i++) {
				pointsUtilises+=this.listJ.get(joueurSelectionne).getTroupes().get(i).getconstitution();
				pointsUtilises+=this.listJ.get(joueurSelectionne).getTroupes().get(i).getForce();
				pointsUtilises+=this.listJ.get(joueurSelectionne).getTroupes().get(i).getDexterité();
				pointsUtilises+=this.listJ.get(joueurSelectionne).getTroupes().get(i).getInitiative();
				pointsUtilises+=this.listJ.get(joueurSelectionne).getTroupes().get(i).getResistance();
				switch(this.listJ.get(joueurSelectionne).getTroupes().get(i).getZone().getNum()) {
					case 0:nbReserviste++;break;
					case 1:nbBibliotheque++;break;
					case 2:nbBDE++;break;
					case 3:nbQA++;break;
					case 4:nbHallesI++;break;
					case 5:nbHalleS++;
			}
			}
			pointsUtilises-=54;
			pointsDepenses=pointsUtilises;
			System.out.println(" Points :"+pointsUtilises+ "\n Reservistes : "+nbReserviste+"\n Bibliotheque : "+nbBibliotheque+"\n BDE : "+nbBDE+"\n Quartier Administratif : "+nbQA+"\n Halles industrielles : "+nbHallesI+"\n Halle Sportive : "+nbHalleS);
			if (pointsUtilises<=400 && nbReserviste==5 && nbBibliotheque>0 && nbBDE>0 && nbQA>0 && nbHallesI>0 && nbHalleS>0 ) {
				this.affectationValide=true;
				if (this.joueurSelectionne==1) {
					if (this.listJ.get(0).getNom().equals(this.listJ.get(1).getNom()) || this.listJ.get(0).getProgramme().equals(this.listJ.get(1).getProgramme())){
						this.affectationValide=false;
					}
				}
			}
			else {
				this.affectationValide=false;
			}
		}
		else if (this.reaffectationEnCours) {
			this.affectationValide=true;
		}	
		this.setChanged();
		this.notifyObservers();

	}
	/** 
	* Indique le nombre de points dépensés par le joueur actuel
	* 
	* @return Un entier indiquant le nombre de points dépensés.
	*/
	public int getPointsDepenses() {
		
		return this.pointsDepenses;
	}
	/** 
	* Savoir si l'affectation actuelle est valide
	* 
	* @return Un booléen indiquant si l'affectation est valide
	*/
	public boolean getAffectation() {
		
		return this.affectationValide;
	}
	/** 
	* Annonce le joueur ayant gagner la partie dans la console.
	* 
	*/
	public void annoncerGagnant() {
		
		if (this.listJ.get(0).getZonesControllees() >=3) {
			System.out.println(ANSI_CYAN+"Le joueur "+this.listJ.get(0).getNom()+" a gagne la partie ! Felicitations"+ANSI_RESET);
		}
		if (this.listJ.get(1).getZonesControllees() >=3) {
			System.out.println(ANSI_CYAN+"Le joueur "+this.listJ.get(1).getNom()+" a gagne la partie ! Felicitations"+ANSI_RESET);
		}
	}
	/** 
	* Obtenir les joueurs qui jouent la partie
	* 
	* @return une liste contenant les 2 joueurs de la partie.
	*/
	public LinkedList<Joueur> getJoueurs(){
		return this.listJ;
	}
	/** 
	* Savoir si le combat est en cours
	* 
	* @return Un booléen décrivant l'état du combat.
	*/
	public boolean getEtatCombat() {
		
		return this.combatEnCours;
	}
	/** 
	* Commencer la reaffectation des étudiants.
	* 
	*/
	public void lancerReaffectation() {
		
		this.reaffectationEnCours=true;
		this.setChanged();
		this.notifyObservers();
	}
	/** 
	* Commencer le combat. Cette méthode effectue le tri des étudiants par initiative dans chaque zone non controllée et lance un combat de zone dans chacune d'elles avec ces étudiants.
	* 
	* @param mapDeJeu La map des zones de la partie (leur numéro en clé et la zone en valeur)
	*/
	public void lancerLeCombat(HashMap<Integer, Zone> mapDeJeu) {
		
		if (this.estEnCoursPartie()) {
		HashMap<Integer, Zone> mapDeJeuNonControllee = new HashMap<Integer, Zone>();
		int iMapNonControllee=0;
		for (int iMap=1;iMap<mapDeJeu.size(); iMap++) {
			if (mapDeJeu.get(iMap).getControlleur()==null) {
				mapDeJeuNonControllee.put(iMapNonControllee, mapDeJeu.get(iMap));
				iMapNonControllee++;
			}
		}
		for (int i=0; i<mapDeJeuNonControllee.size(); i++) {
				System.out.println(ANSI_GREEN+"*******************Lancement du combat dans "+mapDeJeuNonControllee.get(i).getNom()+"********************"+ANSI_RESET);
				LinkedList<Etudiant> troupesJ1 = new LinkedList<Etudiant>();
				LinkedList<Etudiant> troupesJ2 = new LinkedList<Etudiant>();
				LinkedList<Etudiant> allTroupes = new LinkedList<Etudiant>();
				Zone zoneDeCombat = mapDeJeuNonControllee.get(i);
				for (int iEtu=0; iEtu<20;iEtu++) {
					if (this.listJ.get(0).getTroupes().get(iEtu).estMort()==false && this.listJ.get(0).getTroupes().get(iEtu).getZone()==zoneDeCombat) {
						troupesJ1.add(this.listJ.get(0).getTroupes().get(iEtu));
					}
					if (this.listJ.get(1).getTroupes().get(iEtu).estMort()==false && this.listJ.get(1).getTroupes().get(iEtu).getZone()==zoneDeCombat) {
						troupesJ2.add(this.listJ.get(1).getTroupes().get(iEtu));
					}
				}
				allTroupes.addAll(troupesJ1);
				allTroupes.addAll(troupesJ2);
				//Tri des étudiants par initiative
				for (int iEtu=0; iEtu<allTroupes.size();iEtu++) {
					Etudiant etuInitMax=allTroupes.get(iEtu);
					int indexEtuInitMax=iEtu;
					for (int iEtuInitMax=iEtu+1; iEtuInitMax<allTroupes.size();iEtuInitMax++) {
						if (allTroupes.get(iEtuInitMax).getInitiative() > etuInitMax.getInitiative()) {
							etuInitMax=allTroupes.get(iEtuInitMax);
							indexEtuInitMax=iEtuInitMax;
						}
					}
					Etudiant etuTemp = etuInitMax;
					allTroupes.set(indexEtuInitMax,allTroupes.get(iEtu));
					allTroupes.set(iEtu, etuTemp);
					
				}
				mapDeJeuNonControllee.get(i).setAllTroupes(allTroupes);
				mapDeJeuNonControllee.get(i).setTroupesJ1(troupesJ1);
				mapDeJeuNonControllee.get(i).setTroupesJ2(troupesJ2);
				mapDeJeuNonControllee.get(i).combatDeZone();
			}
		try {
			Thread.sleep( 1000 );
		} catch (InterruptedException e) {}
		boolean debutTreve=false;
		while (debutTreve==false) {
			for (int i=0; i<mapDeJeuNonControllee.size(); i++) {
				if (mapDeJeuNonControllee.get(i).getControlleur()!=null) {
					debutTreve=true;
				}
			}
			for (int i=0; i<mapDeJeuNonControllee.size(); i++) {
				if (debutTreve) {
					mapDeJeuNonControllee.get(i).setCombatEnCours(false);
				}
			}
		}
		try {
			Thread.sleep( 1000 );
		} catch (InterruptedException e) {}
		}
		
		this.combatEnCours=false;
		this.setChanged();
		this.notifyObservers();
		
	}
}

package Modele;

import java.io.Serializable;
import java.util.*;

/**
 * La classe Joueur décrit les Joueurs, elle contient les informations sur le joueur, ses troupes et les zones qu'il controlle. 
 * Cette classe implemente Serializable, c'est à dire qu'elle peut être convertie en un tableau d'octets afin de pouvoir la manipuler.
 * 
 * @author Romain CHARRONDIERE et Tanguy SCHENBERG
 * @version 1.2
 */ 
public class Joueur implements Serializable {
	/**
	 * Une chaine de caractère représentant le nom du joueur
	 */
	private String nom;
	/**
	 * Une chaine de caractère représentant le programme du joueur
	 */
	private String programme;
	/**
	 * Une liste d'étudiants, ce sont els étudiants qui combattront pour le joueur
	 */
	private LinkedList<Etudiant> troupes;
	/**
	 * Un entier correspondant au nombre de zones controllées par le joueur
	 */
	private int zonesControllees;
	
	/** 
	 * Constructeur de Joueur : Initialise les caractéristiques du joueur.
	* 
	* @param nom nom du joueur
	* @param programme Programme du joueur
	*/
	public Joueur(String nom, String programme) {
		
		this.troupes=new LinkedList<Etudiant>();
		this.nom=nom;
		this.programme=programme;
		this.zonesControllees=0;
		for(int nbEtu=0; nbEtu<15;nbEtu++) {
			Etudiant etudiant = new Etudiant(0,0,0,0,0, "Etudiant", this);
			this.troupes.add(etudiant);
		}
		for(int nbEtuE=0; nbEtuE<4;nbEtuE++) {
			Etudiant etudiant = new Etudiant(1,1,1,5,1, "Etudiant d'elite", this);
			this.troupes.add(etudiant);
		}
		Etudiant etudiant = new Etudiant(2,2,2,10,2,"Maitre du Gobi", this);
		this.troupes.add(etudiant);
	}
	/** 
	* Constructeur de Joueur : Initialise les caractéristiques du joueur en précisant la zone d'origine des étudiants.
	* 
	* @param nom nom du joueur
	* @param programme Programme du joueur
	* @param zones Une map des zones avec le numéro de la zone en clé et la zone en valeur
	*/
	public Joueur(String nom, String programme, HashMap<Integer, Zone> zones) {
		
		this.troupes=new LinkedList<Etudiant>();
		this.nom=nom;
		this.programme=programme;
		this.zonesControllees=0;
		for(int nbEtu=0; nbEtu<15;nbEtu++) {
			Etudiant etudiant = new Etudiant(0,0,0,0,0, "Etudiant", this, zones.get(0));
			this.troupes.add(etudiant);
		}
		for(int nbEtuE=0; nbEtuE<4;nbEtuE++) {
			Etudiant etudiant = new Etudiant(1,1,1,5,1, "Etudiant d'elite", this, zones.get(0));
			this.troupes.add(etudiant);
		}
		Etudiant etudiant = new Etudiant(2,2,2,10,2,"Maitre du Gobi", this, zones.get(0));
		this.troupes.add(etudiant);
	}
	/** 
	* Obtenir le nom du joueur.
	* 
	* @return  Nom du joueur
	*/
	public String getNom() {
		
		return this.nom;
	}
	/** 
	* Obtenir le programme du joueur.
	* 
	* @return Programme du joueur
	*/
	public String getProgramme() {
		
		return this.programme;
	}
	/** 
	* Definir le nom du joueur.
	* 
	* @param nom Nom du joueur
	*
	*/
	public void setNom(String nom) {
		
		this.nom=nom;
	}
	/** 
	* Definir le programme du joueur.
	* 
	* @param programme Programme du joueur
	*
	*/
	public void setProgramme(String programme) {
		
		this.programme=programme;
	}
	/** 
	* Obtenir les troupes du joueur.
	* 
	* @return Liste d'étudiants (les troupes du joueur)
	*
	*/
	public LinkedList<Etudiant> getTroupes(){
		
		return this.troupes;
	}
	/** 
	* Enregistrer une zone controllée, cette méthode ajoute 1 au total des eoens controllées par le joueur
	* 
	*/
	public void nouvelleZoneControllee() {
		
		this.zonesControllees++;
	}
	/** 
	* Obtenir le nombre de zones controllées par le joueur.
	* 
	* @return un entier : le nombre zones controllées par le joueur
	*
	*/
	public int getZonesControllees() {
		
		return this.zonesControllees;
	}
}

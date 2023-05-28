package Controleur;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import Modele.Aléatoire;
import Modele.Défensive;
import Modele.Joueur;
import Modele.Offensive;
import Modele.Partie;
import Vue.MonInterface;

/**
 * La classe Controleur définit le controleur du programme. Il fait le lien entre le modèle et l'interface graphique (la vue)
 * @see package Controleur
 * 
 * @author Romain CHARRONDIERE et Tanguy SCHENBERG
 * @version 1.2
 */ 
public class ControleurPartie {
	private MonInterface window;
	private Partie unePartie;
	private JButton unBoutonCombat;
	private JButton unBoutonContinuer;
	private JButton unBoutonTerminer;
	private JButton unBoutonSuivant;
	private JButton unBoutonPrecedent;
	private JButton unBoutonEtudiant;
	private JButton unBoutonEtudiantElite;
	private JButton unBoutonMaitreGobi;
	private JButton unBoutonValider;
	private JSpinner unSpinnerConstitution;
	private JSpinner unSpinnerForce;
	private JSpinner unSpinnerDexterite;
	private JSpinner unSpinnerResistance;
	private JSpinner unSpinnerInitiative;
	private JComboBox uneComboBoxStrategie;
	private JComboBox uneComboBoxZone;
	private JComboBox uneComboBoxProgramme;
	private JTextField unTextFieldNom;
	private JButton unBoutonCreditsJ1;
	private JButton unBoutonCreditsJ2;
	private JButton unBoutonSauvegarder;
	private JButton unBoutonCharger;
	
	/** 
	 * Constructeur du controleur, il initialise l'ensemble des éléments dont le controleur récupérera des informations pour appeler les méthodes.
	 * Il indique en fonction de chaque action effectuée sur ces éléments les méthodes à appeler et avec quels paramètres
	 * 
	 * @param buttonTerminer un bouton qui permet au joueur de terminer sona ffectation ou sa réaffectation de troupes
	 * @param buttonEtudiant un bouton qui affiche le premier etudiant normal
	 * @param buttonEtudiant un bouton qui affiche le premier etudiant d'élite
	 * @param buttonEtudiant un bouton qui affiche le maitre du gobi
	 * @param buttonSuivant un bouton qui affiche l'étudiant suivant
	 * @param buttonprecedent un bouton qui affiche l'étudiant précédent
	 * @param buttonValider un bouton qui affecte les caractéristiques sélectionnées à l'étudiant sélectionné
	 * @param spinnerComposition un spinner contenant la caractéristique de composition que l'on souhaite affecter à l'étudiant
	 * @param spinnerForce un spinner contenant la caractéristique de force que l'on souhaite affecter à l'étudiant
	 * @param spinnerDexterite un spinner contenant la caractéristique de dexterite que l'on souhaite affecter à l'étudiant
	 * @param spinnerInitiative un spinner contenant la caractéristique d'initiative que l'on souhaite affecter à l'étudiant
	 * @param spinnerResistance un spinner contenant la caractéristique de resistance que l'on souhaite affecter à l'étudiant
	 * @param comboBoxStrategie une comboBox contenant la stratégie que l'on souhaite affecter à l'étudiant
	 * @param comboBoxStrategie une comboBox contenant la zone que l'on souhaite affecter à l'étudiant
	 * @param comboBoxStrategie une comboBox contenant le programme que l'on souhaite affecter au joueur
	 * @param textFieldNom un textField contenant le nom que l'on souhaite affecter au joueur
	 * @param buttonCombat un bouton permettant de lancer le combat
	 * @param buttonContinuer un bouton permettant de lancer la reaffectation des troupes.
	 * @param buttonCreditsJ1 un bouton permettant d'afficher ou de retirer l'affichage des credits/zone du joueur 1
	 * @param buttonCreditsJ1 un bouton permettant d'afficher ou de retirer l'affichage des credits/zone du joueur 2
	 * @param buttonSave un bouton permettant de sauvegarder la partie en cours
	 * @param buttonCharger un bouton permettant de charger la partie enregistrée précedemment
	 * @param window l'interface graphique
	 */
	public ControleurPartie(Partie partie, JButton buttonTerminer,JButton buttonEtudiant,JButton buttonEtudiantElite,JButton buttonMaitreGobi,JButton buttonSuivant,JButton buttonPrecedent, JButton buttonValider, JSpinner spinnerComposition,JSpinner spinnerForce,JSpinner spinnerDexterite,JSpinner spinnerInitiative,JSpinner spinnerResistance, JComboBox comboBoxStrategie, JComboBox comboBoxZone, JComboBox comboBoxProgramme, JTextField textFieldNom, JButton buttonCombat, JButton buttonContinuer, JButton buttonCreditsJ1, JButton buttonCreditsJ2, JButton buttonSave, JButton buttonCharger, MonInterface window) {
		unePartie=partie;
		unBoutonTerminer=buttonTerminer;
		unBoutonSuivant=buttonSuivant;
		unBoutonPrecedent=buttonPrecedent;
		unBoutonEtudiant=buttonEtudiant;
		unBoutonEtudiantElite=buttonEtudiantElite;
		unBoutonMaitreGobi=buttonMaitreGobi;
		unBoutonValider=buttonValider;
		unSpinnerConstitution = spinnerComposition;
		unSpinnerForce = spinnerForce;
		unSpinnerResistance = spinnerResistance;
		unSpinnerInitiative = spinnerInitiative;
		unSpinnerDexterite = spinnerDexterite;
		uneComboBoxStrategie=comboBoxStrategie;
		uneComboBoxZone=comboBoxZone;
		uneComboBoxProgramme=comboBoxProgramme;
		unTextFieldNom=textFieldNom;
		unBoutonCombat=buttonCombat;
		unBoutonContinuer=buttonContinuer;
		unBoutonCreditsJ1=buttonCreditsJ1;
		unBoutonCreditsJ2=buttonCreditsJ2;
		unBoutonSauvegarder=buttonSave;
		unBoutonCharger=buttonCharger;
		this.window=window;
		
		
		unBoutonTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unePartie.terminerAffectation(unTextFieldNom.getText(), (String)uneComboBoxProgramme.getSelectedItem());
				unePartie.validerAffectation();
			}
			
		});
		unBoutonSauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream fileOutput = new FileOutputStream("partie.txt");
					ObjectOutputStream oos = new ObjectOutputStream(fileOutput);
					oos.writeObject(unePartie);
					oos.close();
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
			
		});
		unBoutonCharger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileInputStream fileInput = new FileInputStream("partie.txt");
					ObjectInputStream ois = new ObjectInputStream(fileInput);
					unePartie = (Partie)ois.readObject();
					unePartie.selectionnerEtudiant(0);
					unePartie.selectionnerJoueur(0);
					unePartie.validerAffectation();
					window.setPartie(unePartie);
					ois.close();
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
			
		});
		unBoutonCreditsJ1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unePartie.switchAffichageCredits(0);
			}
			
		});
		unBoutonCreditsJ2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unePartie.switchAffichageCredits(1);
			}
			
		});
		
		unBoutonSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int joueurSelectionne=unePartie.getJoueurSelectionne();
				int etudiantSelectionne=unePartie.getEtudiantSelectionne();
				if (unePartie.getEtatAffectation()) {
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setConstitution((int)unSpinnerConstitution.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setForce((int)unSpinnerForce.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setResistance((int)unSpinnerResistance.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setInitiative((int)unSpinnerInitiative.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setDexterite((int)unSpinnerDexterite.getValue());
					switch(uneComboBoxStrategie.getSelectedIndex()) {
						case 0 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Aléatoire());break;
						case 1 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Offensive());break;
						case 2 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Défensive());
					}
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).affecterZone(unePartie.getMap().get(uneComboBoxZone.getSelectedIndex()));
					unePartie.getJoueurs().get(joueurSelectionne).setNom(unTextFieldNom.getText());
					switch(uneComboBoxProgramme.getSelectedIndex()) {
					case 0: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("ISI");;break;
					case 1: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("RT");break;
					case 2: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("MTE");break;
					case 3: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("A2I");break;
					case 4: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("GI");break;
					case 5: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("GM");break;
					case 6: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("MM");
					}
				}
				
				else if(unePartie.getEtatReaffectation()) {
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).reaffecterZone(unePartie.getMap().get(uneComboBoxZone.getSelectedIndex()), unePartie);
					switch(uneComboBoxStrategie.getSelectedIndex()) {
					case 0 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Aléatoire());break;
					case 1 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Offensive());break;
					case 2 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Défensive());
				}
				}
				System.out.print("\nControleur de la partie: L'etudiant suivant est selectionne \n");
				unePartie.incrementerEtudiantSelectionne();
				unePartie.validerAffectation();
			}
			
		});
		
		unBoutonPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int joueurSelectionne=unePartie.getJoueurSelectionne();
				int etudiantSelectionne=unePartie.getEtudiantSelectionne();
				if (unePartie.getEtatAffectation()) {
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setConstitution((int)unSpinnerConstitution.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setForce((int)unSpinnerForce.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setResistance((int)unSpinnerResistance.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setInitiative((int)unSpinnerInitiative.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setDexterite((int)unSpinnerDexterite.getValue());
					switch(uneComboBoxStrategie.getSelectedIndex()) {
						case 0 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Aléatoire());break;
						case 1 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Offensive());break;
						case 2 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Défensive());
					}
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).affecterZone(unePartie.getMap().get(uneComboBoxZone.getSelectedIndex()));
					unePartie.getJoueurs().get(joueurSelectionne).setNom(unTextFieldNom.getText());
					switch(uneComboBoxProgramme.getSelectedIndex()) {
					case 0: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("ISI");;break;
					case 1: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("RT");break;
					case 2: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("MTE");break;
					case 3: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("A2I");break;
					case 4: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("GI");break;
					case 5: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("GM");break;
					case 6: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("MM");
					}
				}
				else if(unePartie.getEtatReaffectation()) {
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).reaffecterZone(unePartie.getMap().get(uneComboBoxZone.getSelectedIndex()), unePartie);
					switch(uneComboBoxStrategie.getSelectedIndex()) {
					case 0 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Aléatoire());break;
					case 1 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Offensive());break;
					case 2 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Défensive());
				}
				}
				System.out.print("\nControleur de la partie: L'etudiant precedent est selectionne \n");
				unePartie.decrementerEtudiantSelectionne();
				unePartie.validerAffectation();
			}
		});
		
		unBoutonEtudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int joueurSelectionne=unePartie.getJoueurSelectionne();
				int etudiantSelectionne=unePartie.getEtudiantSelectionne();
				if (unePartie.getEtatAffectation()) {
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setConstitution((int)unSpinnerConstitution.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setForce((int)unSpinnerForce.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setResistance((int)unSpinnerResistance.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setInitiative((int)unSpinnerInitiative.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setDexterite((int)unSpinnerDexterite.getValue());
					switch(uneComboBoxStrategie.getSelectedIndex()) {
						case 0 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Aléatoire());break;
						case 1 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Offensive());break;
						case 2 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Défensive());
					}
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).affecterZone(unePartie.getMap().get(uneComboBoxZone.getSelectedIndex()));
					unePartie.getJoueurs().get(joueurSelectionne).setNom(unTextFieldNom.getText());
					switch(uneComboBoxProgramme.getSelectedIndex()) {
					case 0: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("ISI");;break;
					case 1: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("RT");break;
					case 2: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("MTE");break;
					case 3: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("A2I");break;
					case 4: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("GI");break;
					case 5: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("GM");break;
					case 6: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("MM");
					}
				}
				else if(unePartie.getEtatReaffectation()) {
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).reaffecterZone(unePartie.getMap().get(uneComboBoxZone.getSelectedIndex()), unePartie);
					switch(uneComboBoxStrategie.getSelectedIndex()) {
					case 0 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Aléatoire());break;
					case 1 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Offensive());break;
					case 2 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Défensive());
				}
				}
				System.out.print("\nControleur de la partie: Selection d'un etudiant\n");
				unePartie.selectionnerEtudiant(0);
				unePartie.validerAffectation();
			}
		});
		
		unBoutonEtudiantElite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int joueurSelectionne=unePartie.getJoueurSelectionne();
				int etudiantSelectionne=unePartie.getEtudiantSelectionne();
				if (unePartie.getAffectation()) {
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setConstitution((int)unSpinnerConstitution.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setForce((int)unSpinnerForce.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setResistance((int)unSpinnerResistance.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setInitiative((int)unSpinnerInitiative.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setDexterite((int)unSpinnerDexterite.getValue());
					switch(uneComboBoxStrategie.getSelectedIndex()) {
						case 0 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Aléatoire());break;
						case 1 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Offensive());break;
						case 2 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Défensive());
					}
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).affecterZone(unePartie.getMap().get(uneComboBoxZone.getSelectedIndex()));
					unePartie.getJoueurs().get(joueurSelectionne).setNom(unTextFieldNom.getText());
					switch(uneComboBoxProgramme.getSelectedIndex()) {
					case 0: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("ISI");;break;
					case 1: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("RT");break;
					case 2: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("MTE");break;
					case 3: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("A2I");break;
					case 4: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("GI");break;
					case 5: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("GM");break;
					case 6: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("MM");
					}
				}
				else if(unePartie.getEtatReaffectation()) {
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).reaffecterZone(unePartie.getMap().get(uneComboBoxZone.getSelectedIndex()), unePartie);
					switch(uneComboBoxStrategie.getSelectedIndex()) {
					case 0 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Aléatoire());break;
					case 1 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Offensive());break;
					case 2 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Défensive());
				}
				}
				System.out.print("\nControleur de la partie: Selection d'un Etudiant d'Elite\n");
				unePartie.selectionnerEtudiant(15);
				unePartie.validerAffectation();
			}
		});
		
		unBoutonMaitreGobi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int joueurSelectionne=unePartie.getJoueurSelectionne();
				int etudiantSelectionne=unePartie.getEtudiantSelectionne();
				if (unePartie.getEtatAffectation()) {
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setConstitution((int)unSpinnerConstitution.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setForce((int)unSpinnerForce.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setResistance((int)unSpinnerResistance.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setInitiative((int)unSpinnerInitiative.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setDexterite((int)unSpinnerDexterite.getValue());
					switch(uneComboBoxStrategie.getSelectedIndex()) {
						case 0 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Aléatoire());break;
						case 1 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Offensive());break;
						case 2 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Défensive());
					}
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).affecterZone(unePartie.getMap().get(uneComboBoxZone.getSelectedIndex()));
					unePartie.getJoueurs().get(joueurSelectionne).setNom(unTextFieldNom.getText());
					switch(uneComboBoxProgramme.getSelectedIndex()) {
					case 0: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("ISI");;break;
					case 1: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("RT");break;
					case 2: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("MTE");break;
					case 3: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("A2I");break;
					case 4: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("GI");break;
					case 5: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("GM");break;
					case 6: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("MM");
					}
				}
				else if(unePartie.getEtatReaffectation()) {
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).reaffecterZone(unePartie.getMap().get(uneComboBoxZone.getSelectedIndex()), unePartie);
					switch(uneComboBoxStrategie.getSelectedIndex()) {
					case 0 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Aléatoire());break;
					case 1 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Offensive());break;
					case 2 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Défensive());
				}
				}
				System.out.print("\nControleur de la partie: Selection d'un Maitre du Gobi\n");
				unePartie.selectionnerEtudiant(19);
				unePartie.validerAffectation();
			}
		});
		unBoutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int joueurSelectionne=unePartie.getJoueurSelectionne();
				int etudiantSelectionne=unePartie.getEtudiantSelectionne();
				
				if (unePartie.getEtatAffectation()) {
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setConstitution((int)unSpinnerConstitution.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setForce((int)unSpinnerForce.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setResistance((int)unSpinnerResistance.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setInitiative((int)unSpinnerInitiative.getValue());
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).setDexterite((int)unSpinnerDexterite.getValue());
					switch(uneComboBoxStrategie.getSelectedIndex()) {
						case 0 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Aléatoire());break;
						case 1 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Offensive());break;
						case 2 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Défensive());
					}
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).affecterZone(unePartie.getMap().get(uneComboBoxZone.getSelectedIndex()));
					unePartie.getJoueurs().get(joueurSelectionne).setNom(unTextFieldNom.getText());
					switch(uneComboBoxProgramme.getSelectedIndex()) {
					case 0: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("ISI");;break;
					case 1: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("RT");break;
					case 2: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("MTE");break;
					case 3: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("A2I");break;
					case 4: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("GI");break;
					case 5: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("GM");break;
					case 6: unePartie.getJoueurs().get(joueurSelectionne).setProgramme("MM");
					}
				}
				else if(unePartie.getEtatReaffectation()) {
					unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).reaffecterZone(unePartie.getMap().get(uneComboBoxZone.getSelectedIndex()), unePartie);
					switch(uneComboBoxStrategie.getSelectedIndex()) {
					case 0 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Aléatoire());break;
					case 1 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Offensive());break;
					case 2 : unePartie.getJoueurs().get(joueurSelectionne).getTroupes().get(etudiantSelectionne).choisirStratégie(new Défensive());
				}
				}
				
				unePartie.validerAffectation();
				System.out.println("Controleur de la partie : Caracteristiques enregistrees");
			}
			
		});
		unBoutonCombat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unePartie.lancerLeCombat(unePartie.getMap());;
			}
			
		});
		unBoutonContinuer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unePartie.lancerReaffectation();
				unePartie.desactiverAffichagesCredits();
				unePartie.validerAffectation();
				System.out.println("Controleur de la partie : Debut de la treve");
			}
			
		});

	}

}

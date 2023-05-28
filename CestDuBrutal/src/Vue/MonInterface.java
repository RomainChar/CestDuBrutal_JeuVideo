package Vue;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Controleur.ControleurPartie;
import Modele.Etudiant;
import Modele.Joueur;
import Modele.Partie;
import Modele.Zone;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPanel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.awt.event.ActionEvent;

/**
 * La classe MonInterface définit la vue du programme. Elle permet à l'utilisateur d'intéragir avec l'application via une interface graphique, principalement des boutons, des spinners ou des TextField
 * @see package Vue.
 * 
 * Cette classe implemente Observer,c'est à dire qu'elle peut observer une classe qui hérite de Observable.
 * Dans notre cas la classe MonInterface est notifiée de certaines actions de la classe Partie.
 * 
 * @author Romain CHARRONDIERE et Tanguy SCHENBERG
 * @version 1.2
 */ 
public class MonInterface implements Observer{

	/**
	 * une frame contenant tous les composants de l'interface graphique
	 */
	private JFrame frame;
	/**
	 * Un textField pour saisir le nom du joueur
	 */
	private JTextField textFieldJoueur;
	/**
	 * Un label qui indique quel joueur contrôle quelle zone
	 */
	private JLabel labelVainqueurZone;
	/**
	 * Un label qui indique le joueur sélectionné
	 */
	private JLabel labelJoueur;
	/**
	 * Un label qui indique le programme du joueur
	 */
	private JLabel labelProgramme;
	/**
	 * Un label qui indique la force de l'etudiant 
	 */
	private JLabel labelForce;
	/**
	 * Un spinner pour sélectionner la force de l'etudiant 
	 */
	private JSpinner spinnerForce;
	/**
	 * Un label qui indique la dexterité de l'etudiant 
	 */
	private JLabel labelDexterite;
	/**
	 * Un spinner pour sélectionner la dextérité de l'etudiant 
	 */
	private JSpinner spinnerDexterite;
	/**
	 * Un label qui indique l'initiative de l'etudiant 
	 */
	private JLabel labelInititative;
	/**
	 * Un spinner pour sélectionner l'initiative de l'etudiant 
	 */
	private JSpinner spinnerInitiative;
	/**
	 * Un label qui indique la resistance de l'etudiant 
	 */
	private JLabel labelResistance;
	/**
	 * Un spinner pour sélectionner la résistance de l'etudiant 
	 */
	private JSpinner spinnerResistance;
	/**
	 * Un label qui indique la compisition de l'etudiant 
	 */
	private JLabel labelComposition;
	/**
	 * Un spinner pour sélectionner la composition de l'etudiant 
	 */
	private JSpinner spinnerComposition;
	/**
	 * Un label qui indique le type de l'étudiant sélectionné et son numéro
	 */
	private JLabel labelEtudiantSelectionne;
	/**
	 * Un bouton pour passer à la manche suivante
	 */
	private JButton buttonContinuer;
	/**
	 * Un bouton pour lancer le combat une fois que les joueurs sont prêts
	 */
	private JButton buttonCombat;
	/**
	 * Un bouton pour terminer l'affectation ou la réaffectation de ses étudiants
	 */
	private JButton buttonTerminer;
	/**
	 * Un bouton pour sélectionner l'étudiant suivant
	 */
	private JButton buttonSuivant;
	/**
	 * Un bouton pour sélectionner l'étudiant précédent
	 */
	private JButton buttonPrecedent;
	/**
	 * Un bouton pour sélectionner le premier étudiant
	 */
	private JButton buttonEtudiant;
	/**
	 * Un bouton pour sélectionner le premier étudiant d'élite
	 */
	private JButton buttonEtudiantElite;
	/**
	 * Un bouton pour sélectionner maitre du gobi
	 */
	private JButton buttonMaitreGobi;
	/**
	 * Une comboBox pour sélectionner la stratégie de l'étudiant
	 */
	private JComboBox comboBoxStrategie;
	/**
	 * Une comboBox pour sélectionner la zone de l'étudiant
	 */
	private JComboBox comboBoxZone;
	/**
	 * Une comboBox pour sélectionner le programme de l'étudiant
	 */
	private JComboBox comboBoxProgramme;
	/**
	 * La partie de jeu en cours
	 */
	private Partie unePartie;
	/**
	 * Un bouton pour valider les caractéristiques sélectionnées de l'étudiant
	 */
	private JButton buttonValider;
	/**
	 * Un label qui indique si l'affectation est valide ou non
	 */
	private JLabel labelAffectation;
	/**
	 * Un label qui indique le nombre de points dépensés par le joueur 
	 */
	private JLabel labelPointsDepenses;
	/**
	 * Un label qui indique la stratégie de l'étudiant
	 */
	private JLabel labelStrategie;
	/**
	 * Un label qui indique la  zone de l'étudiant
	 */
	private JLabel labelZone;
	/**
	 * Un bouton pour afficher les crédits/zone du joueur 1
	 */
	private JButton buttonCreditsJ1;
	/**
	 * Un bouton pour afficher les crédits/zone du joueur 2
	 */
	private JButton buttonCreditsJ2;
	/**
	 * Un label qui indique les crédits/zone du joueur 1
	 */
	private JLabel labelCreditsZoneJ1;
	/**
	 * Un label qui indique les crédits/zone du joueur 2
	 */
	private JLabel labelCreditsZoneJ2;
	/**
	 * Un label qui indique le gagnant de la partie
	 */
	private JLabel labelGagnant;
	/**
	 * Un bouton pour sauvegarder la partie
	 */
	private JButton buttonSave;
	/**
	 * Un bouton pour charger la partie précédemment sauvegardée
	 */
	private JButton buttonCharge;
	
	
	/** 
	 * update permet de mettre à jour l'interface graphique lorsque l'état de la partie change, elle vérifie l'ensemble des attributs de la partie pour mettre à jour ce que voit l'utilisateur
	 * 
	 * @param instanceObservable L'instance observable (la partie en cours)
	 * @param arg1 les arguments optionnels pour l'update
	 */
	public void update(Observable instanceObservable, Object arg1){
	if (instanceObservable instanceof Partie){
		if (unePartie.getAffichageCreditsJ1()) {
			HashMap<String, Integer> creditsZone = unePartie.getCreditsZones(0);
			String textCredits = "<html>";
			Iterator<String> itZone = creditsZone.keySet().iterator();
			while (itZone.hasNext()) {
				String key=itZone.next();
				textCredits+=(key+" : "+creditsZone.get(key)+"<br>");
			}
			labelCreditsZoneJ1.setText(textCredits);
			labelCreditsZoneJ1.setVisible(true);
		}
		else {
			labelCreditsZoneJ1.setVisible(false);
		}
		if (unePartie.getAffichageCreditsJ2()) {
			HashMap<String, Integer> creditsZone = unePartie.getCreditsZones(1);
			String textCredits = "<html>";
			Iterator<String> itZone = creditsZone.keySet().iterator();
			while (itZone.hasNext()) {
				String key=itZone.next();
				textCredits+=(key+" : "+creditsZone.get(key)+"<br>");
			}
			labelCreditsZoneJ2.setText(textCredits);
			labelCreditsZoneJ2.setVisible(true);
	}
		else {
			labelCreditsZoneJ2.setVisible(false);
		}
		if (unePartie.getEtatAffectation()) {
			buttonSave.setVisible(true);
			labelEtudiantSelectionne.setForeground(new Color(0,0,0));
			comboBoxProgramme.setEnabled(true);
			comboBoxZone.setEnabled(true);
			comboBoxStrategie.setEnabled(true);
			textFieldJoueur.setEnabled(true);
		}
		else {
			buttonSave.setVisible(false);
		}
		if ((unePartie.getEtatReaffectation() || unePartie.getEtatAffectation()) && unePartie.estEnCoursPartie()) {
			buttonTerminer.setVisible(true);
			buttonSuivant.setVisible(true);
			buttonPrecedent.setVisible(true);
			buttonEtudiant.setVisible(true);
			buttonEtudiantElite.setVisible(true);
			buttonMaitreGobi.setVisible(true);
			textFieldJoueur.setVisible(true);
			textFieldJoueur.setText(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getNom());
			labelJoueur.setVisible(true);
			labelProgramme.setVisible(true);
			labelForce.setVisible(true);
			labelDexterite.setVisible(true);
			labelInititative.setVisible(true);
			labelComposition.setVisible(true);
			labelResistance.setVisible(true);
			spinnerForce.setVisible(true);
			spinnerDexterite.setVisible(true);
			spinnerInitiative.setVisible(true);
			spinnerComposition.setVisible(true);
			spinnerResistance.setVisible(true);
			labelEtudiantSelectionne.setVisible(true);
			comboBoxStrategie.setVisible(true);
			comboBoxZone.setVisible(true);
			comboBoxProgramme.setVisible(true);
			switch((String)unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getProgramme()) {
			case "ISI": comboBoxProgramme.setSelectedIndex(0);break;
			case "RT": comboBoxProgramme.setSelectedIndex(1);break;
			case "MTE": comboBoxProgramme.setSelectedIndex(2);break;
			case "A2I": comboBoxProgramme.setSelectedIndex(3);break;
			case "GI": comboBoxProgramme.setSelectedIndex(4);break;
			case "GM": comboBoxProgramme.setSelectedIndex(5);break;
			case "MM": comboBoxProgramme.setSelectedIndex(6);
			
			}
			buttonValider.setVisible(true);
			labelAffectation.setVisible(true);
			labelPointsDepenses.setVisible(true);
			labelStrategie.setVisible(true);
			labelZone.setVisible(true);
			buttonCombat.setVisible(false);
			labelVainqueurZone.setVisible(false);
			buttonContinuer.setVisible(false);
			buttonCreditsJ1.setVisible(false);
			buttonCreditsJ2.setVisible(false);
			labelGagnant.setVisible(false);
		}
		else if (unePartie.getEtatCombat()) {
			buttonTerminer.setVisible(false);
			buttonSuivant.setVisible(false);
			buttonPrecedent.setVisible(false);
			buttonEtudiant.setVisible(false);
			buttonEtudiantElite.setVisible(false);
			buttonMaitreGobi.setVisible(false);
			textFieldJoueur.setVisible(false);
			labelJoueur.setVisible(false);
			labelProgramme.setVisible(false);
			labelForce.setVisible(false);
			labelDexterite.setVisible(false);
			labelInititative.setVisible(false);
			labelComposition.setVisible(false);
			labelResistance.setVisible(false);
			spinnerForce.setVisible(false);
			spinnerDexterite.setVisible(false);
			spinnerInitiative.setVisible(false);
			spinnerComposition.setVisible(false);
			spinnerResistance.setVisible(false);
			labelEtudiantSelectionne.setVisible(false);
			comboBoxStrategie.setVisible(false);
			comboBoxZone.setVisible(false);
			comboBoxProgramme.setVisible(false);
			buttonValider.setVisible(false);
			labelAffectation.setVisible(false);
			labelPointsDepenses.setVisible(false);
			labelStrategie.setVisible(false);
			labelZone.setVisible(false);
			buttonCombat.setVisible(true);
			labelVainqueurZone.setVisible(false);
			buttonContinuer.setVisible(false);
			buttonCreditsJ1.setVisible(true);
			buttonCreditsJ2.setVisible(true);
			buttonCreditsJ1.setText("<html>\r\nCredits / Zone <br>\r\n"+unePartie.getJoueurs().get(0).getNom()+" \r\n</html>");
			buttonCreditsJ2.setText("<html>\r\nCredits / Zone <br>\r\n"+unePartie.getJoueurs().get(1).getNom()+" \r\n</html>");
		}
		else {
			String zonesControllees = new String("<html>\r");
			for (int iMap=1; iMap<6; iMap++) {
				if (unePartie.getMap().get(iMap).getControlleur()!=null) {
					zonesControllees+=(unePartie.getMap().get(iMap).getNom()+" : "+unePartie.getMap().get(iMap).getControlleur().getNom()+"<br>");
					
				}
				labelVainqueurZone.setText(zonesControllees);

				
			}
			buttonCombat.setVisible(false);
			labelVainqueurZone.setVisible(true);
			buttonContinuer.setVisible(true);
			buttonCreditsJ1.setVisible(false);
			buttonCreditsJ2.setVisible(false);
			labelCreditsZoneJ1.setVisible(false);
			labelCreditsZoneJ2.setVisible(false);
			if (unePartie.estEnCoursPartie()==false) {
				buttonContinuer.setVisible(false);
				labelGagnant.setVisible(true);
				if (unePartie.getJoueurs().get(0).getZonesControllees()>=3) {
					labelGagnant.setText("<html>"+unePartie.getJoueurs().get(0).getNom()+"<br> du programme "+unePartie.getJoueurs().get(0).getProgramme()+"<br> a gagné !<br> Félicitations</html>");
				}
				else {
					labelGagnant.setText("<html>"+unePartie.getJoueurs().get(1).getNom()+"<br> du programme "+unePartie.getJoueurs().get(1).getProgramme()+"<br> a gagné !<br> Félicitations</html>");
				}
			}
		}
		if (unePartie.getEtatAffectation()) {
			if (unePartie.getAffectation()) {
				labelAffectation.setText("<html>\r\nL'affectation<br>respecte <br>les règles !\r\n</html>");
				labelAffectation.setForeground(new Color(0, 255, 0));
				
			}
			else {
				labelAffectation.setText("<html>\r\nL'affectation<br>ne respecte pas<br>les règles\r\n</html>");
				labelAffectation.setForeground(new Color(255, 0, 0));
			}
			labelJoueur.setText("Joueur "+(unePartie.getJoueurSelectionne()+1));
			labelPointsDepenses.setText("Points Dépensés : "+unePartie.getPointsDepenses()+"/400");
		 	labelEtudiantSelectionne.setText(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getType()+" "+(unePartie.getEtudiantSelectionne()+1));
		 	if (unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getType().equals("Etudiant")){
		 		spinnerComposition.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getconstitution(), 0, 30, 1));
			 	spinnerDexterite.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getDexterité(), 0, 10, 1));
			 	spinnerForce.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getForce(), 0, 10, 1));
			 	spinnerInitiative.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getInitiative(), 0, 10, 1));
			 	spinnerResistance.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getResistance(), 0, 10, 1));
		 	}
		 	else if (unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getType().equals("Etudiant d'elite")) {
		 		spinnerComposition.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getconstitution(), 5, 30, 1));
			 	spinnerDexterite.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getDexterité(), 1, 10, 1));
			 	spinnerForce.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getForce(),1, 10, 1));
			 	spinnerInitiative.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getInitiative(), 1, 10, 1));
			 	spinnerResistance.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getResistance(), 1, 10, 1));
		 	}
		 	else {
		 		spinnerComposition.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getconstitution(), 10, 30, 1));
			 	spinnerDexterite.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getDexterité(), 2, 10, 1));
			 	spinnerForce.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getForce(), 2, 10, 1));
			 	spinnerInitiative.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getInitiative(), 2, 10, 1));
			 	spinnerResistance.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getResistance(), 2, 10, 1));
		 	}
		 	comboBoxStrategie.setSelectedIndex(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getStratégie().getNumStrategie());
		 	comboBoxZone.setSelectedIndex(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getZone().getNum());
		 }
		else if(unePartie.getEtatReaffectation()) {
			if (unePartie.getAffectation()) {
				labelAffectation.setText("<html>\r\nL'affectation<br>respecte <br>les règles !\r\n</html>");
				labelAffectation.setForeground(new Color(0, 255, 0));
				
			}
			else {
				labelAffectation.setText("<html>\r\nL'affectation<br>ne respecte pas<br>les règles\r\n</html>");
				labelAffectation.setForeground(new Color(255, 0, 0));
			}
			labelJoueur.setText("Joueur "+(unePartie.getJoueurSelectionne()+1));
			textFieldJoueur.setText(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getNom());
			textFieldJoueur.setEnabled(false);
			comboBoxProgramme.setEnabled(false);
			
			labelPointsDepenses.setVisible(false);
			
			String etuSelect= new String("<html>");
			etuSelect+=(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getType()+" "+(unePartie.getEtudiantSelectionne()+1)+"<br>");
			if (unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).estMort()) {
				etuSelect+="MORT";
				labelEtudiantSelectionne.setForeground(new Color(255, 0, 0));
				comboBoxZone.setEnabled(false);
				comboBoxStrategie.setEnabled(false);
			}
			else {
				etuSelect+=("Credits restants : "+unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getCredit());
				labelEtudiantSelectionne.setForeground(new Color(0,0, 0));
				comboBoxZone.setEnabled(true);
				comboBoxStrategie.setEnabled(true);
			}
			
		 	labelEtudiantSelectionne.setText(etuSelect);
		 	
		 	spinnerComposition.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getconstitution(), unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getconstitution(), unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getconstitution(), 1));
		 	spinnerDexterite.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getDexterité(), unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getDexterité(), unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getDexterité(), 1));
		 	spinnerForce.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getForce(), unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getForce(), unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getForce(), 1));
		 	spinnerInitiative.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getInitiative(), unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getInitiative(), unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getInitiative(), 1));
		 	spinnerResistance.setModel(new SpinnerNumberModel(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getResistance(), unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getResistance(), unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getResistance(), 1));
		 	
		 	
		 	comboBoxStrategie.setSelectedIndex(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getStratégie().getNumStrategie());
		 	comboBoxZone.setSelectedIndex(unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getZone().getNum());
		 	if (unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getZone().getControlleur()==null && unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getZone().getNum()!=0) {
		 		
			 	comboBoxStrategie.setEnabled(false);
			 	comboBoxZone.setEnabled(false);
		 	}
		 	else if (unePartie.getJoueurs().get(unePartie.getJoueurSelectionne()).getTroupes().get(unePartie.getEtudiantSelectionne()).getZone().getNum()!=0){

		 	}
		 	
		}
	}
	}
	/**
	* Lancement de l'application, initialise les zones et les joeurs pour créer la partie.
	*/
	public static void main(String[] args) {

		
		int numManche=0;
			Zone bibliothèque = new Zone("Bibliotheque", 1);
			Zone bde = new Zone("BDE", 2);
			Zone quartierAdministratif = new Zone("Quartier Administratif", 3);
			Zone hallesIndustrielles = new Zone("Halles Industrielles", 4);
			Zone halleSportive = new Zone("Halle Sportive", 5);
			//La zone reservistes est une zone dans laquelle il n'y a pas de combat
			Zone reservistes = new Zone("Reservistes", 0);
			HashMap<Integer, Zone> mapDeJeu = new HashMap<Integer, Zone>();
			mapDeJeu.put(0, reservistes);
			mapDeJeu.put(1, bibliothèque);
			mapDeJeu.put(2, bde);
			mapDeJeu.put(3, quartierAdministratif);
			mapDeJeu.put(4, hallesIndustrielles);
			mapDeJeu.put(5, halleSportive);
			//Instanciation des joueurs de la partie
			Joueur Joueur1 = new Joueur("Joueur 1", "ISI", mapDeJeu);
			Joueur Joueur2 = new Joueur("Joueur 2", "ISI", mapDeJeu);
			//Instanciation de la partie
			Partie Partie = new Partie(Joueur1, Joueur2, mapDeJeu);
			EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonInterface window = new MonInterface(Partie);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	/** 
	 * Change la partie en cours, est utilisé lorsque l'utilisateur charge une partie enregistrée précédemment
	 * 
	 * @param partieChargee La nouvelle partie
	 */
	public void setPartie(Partie partieChargee) {
		this.unePartie=partieChargee;
		unePartie.addObserver(this);
		unePartie.update();
	}
	/**
	 * Creation de l'application et lancement de l'interface graphique
	 * @param partie La partie qui va être jouée
	 */
	public MonInterface(Partie partie) {
	
		initialize();	
		// L’interface graphique Observe la partie
	    this.unePartie = partie;
	    LinkedList<Etudiant> etudiants=partie.getJoueurs().get(0).getTroupes();
	    etudiants.addAll(partie.getJoueurs().get(1).getTroupes());
	    unePartie.addObserver(this);
	    // Création du controleur (avec tous les boutons de l'interface graphique)
	    new ControleurPartie(partie, buttonTerminer, buttonEtudiant, buttonEtudiantElite, buttonMaitreGobi, buttonSuivant, buttonPrecedent, buttonValider, spinnerComposition, spinnerForce, spinnerDexterite, spinnerInitiative, spinnerResistance, comboBoxStrategie, comboBoxZone, comboBoxProgramme, textFieldJoueur, buttonCombat, buttonContinuer, buttonCreditsJ1, buttonCreditsJ2, buttonSave, buttonCharge, this);
	    
	    
	}

	/**
	 * Initialisation du contenu de l'interface graphique
	 */
	private void initialize() {
	
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 128, 255));
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldJoueur = new JTextField();
		textFieldJoueur.setToolTipText("Choisissez le nom du joueur");
		textFieldJoueur.setText("Joueur 1");
		textFieldJoueur.setBounds(314, 22, 130, 38);
		frame.getContentPane().add(textFieldJoueur);
		textFieldJoueur.setColumns(10);
		
		comboBoxProgramme = new JComboBox();
		comboBoxProgramme.setModel(new DefaultComboBoxModel(new String[] {"ISI", "RT", "MTE", "A2I", "GI", "GM", "MM"}));
		comboBoxProgramme.setBounds(542, 22, 117, 39);
		frame.getContentPane().add(comboBoxProgramme);
		
		spinnerForce = new JSpinner();
		spinnerForce.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerForce.setBounds(692, 364, 100, 38);
		frame.getContentPane().add(spinnerForce);
		
		labelJoueur = new JLabel("Joueur 1");
		labelJoueur.setBounds(242, 29, 62,23);
		frame.getContentPane().add(labelJoueur);
		
		labelProgramme = new JLabel("Programme");
		labelProgramme.setBounds(465, 27, 78, 26);
		frame.getContentPane().add(labelProgramme);
		
		labelForce = new JLabel("Force");
		labelForce.setBounds(614, 370, 150, 26);
		frame.getContentPane().add(labelForce);
		
		labelDexterite = new JLabel("Dexterite");
		labelDexterite.setBounds(74, 364, 150, 26);
		frame.getContentPane().add(labelDexterite);
		
		spinnerDexterite = new JSpinner();
		spinnerDexterite.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerDexterite.setBounds(178, 358, 100, 38);
		frame.getContentPane().add(spinnerDexterite);
		
		labelInititative = new JLabel("Initiative");
		labelInititative.setBounds(74, 416, 150, 26);
		frame.getContentPane().add(labelInititative);
		
		spinnerInitiative = new JSpinner();
		spinnerInitiative.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerInitiative.setBounds(178, 410, 100, 38);
		frame.getContentPane().add(spinnerInitiative);
		
		labelResistance = new JLabel("Resistance");
		labelResistance.setBounds(320, 364, 150, 26);
		frame.getContentPane().add(labelResistance);
		
		spinnerResistance = new JSpinner();
		spinnerResistance.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerResistance.setBounds(460, 358, 100, 38);
		frame.getContentPane().add(spinnerResistance);
		
		labelComposition = new JLabel("Composition");
		labelComposition.setBounds(318, 410, 150, 26);
		frame.getContentPane().add(labelComposition);
		
		spinnerComposition = new JSpinner();
		spinnerComposition.setModel(new SpinnerNumberModel(0, 0, 30, 1));
		spinnerComposition.setBounds(460, 410, 100, 38);
		frame.getContentPane().add(spinnerComposition);
		
		labelAffectation = new JLabel("<html>\r\nL'affectation<br>ne respecte pas<br>les règles\r\n</html>");
	    labelAffectation.setHorizontalAlignment(SwingConstants.CENTER);
	    labelAffectation.setForeground(new Color(255, 0, 0));
	    labelAffectation.setBounds(642, 166, 202, 98);
	    frame.getContentPane().add(labelAffectation);
	    
	    labelPointsDepenses = new JLabel("Points Dépensés : 0/400");
	    labelPointsDepenses.setBounds(20, 318, 712, 26);
	    frame.getContentPane().add(labelPointsDepenses);    
	    
		ImageIcon iconElite = new ImageIcon(getClass().getResource("/ressources/elite.png"));	
		ImageIcon iconMaitre = new ImageIcon(getClass().getResource("/ressources/maitre.png"));
		ImageIcon iconEtudiant = new ImageIcon(getClass().getResource("/ressources/etudiant.png"));
		
		buttonEtudiant = new JButton(iconEtudiant);
		buttonEtudiant.setBounds(20, 70, 168, 170);
		frame.getContentPane().add(buttonEtudiant);
		
		buttonEtudiantElite = new JButton(iconElite);
		buttonEtudiantElite.setBounds(232, 74, 170, 166);
		frame.getContentPane().add(buttonEtudiantElite);
		
		buttonMaitreGobi = new JButton(iconMaitre);
		buttonMaitreGobi.setBounds(432, 78, 170, 162);
		frame.getContentPane().add(buttonMaitreGobi);
		
		labelZone = new JLabel("Zone");
		labelZone.setBounds(20, 474,150, 26);
		frame.getContentPane().add(labelZone);
		
		comboBoxZone = new JComboBox();
		comboBoxZone.setModel(new DefaultComboBoxModel(new String[] {"Reserviste", "Bibliotheque", "BDE", "Quartier Administratif", "Halles Industrielles", "Halle Sportive"}));
		comboBoxZone.setBounds(86, 464, 192, 46);
		frame.getContentPane().add(comboBoxZone);
		
		labelStrategie = new JLabel("Stratégie");
		labelStrategie.setBounds(306, 474, 150, 26);
		frame.getContentPane().add(labelStrategie);
		
		comboBoxStrategie = new JComboBox();
		comboBoxStrategie.setModel(new DefaultComboBoxModel(new String[] {"Aléatoire", "Offensive", "Défensive"}));
		comboBoxStrategie.setBounds(410, 462, 192, 48);
		frame.getContentPane().add(comboBoxStrategie);
		comboBoxStrategie.setSelectedIndex(1);
		
		buttonTerminer = new JButton("<html>\r\nTerminer <br>\r\nl'affectation\r\n</html>");
		buttonTerminer.setBounds(622, 70, 222, 98);
		frame.getContentPane().add(buttonTerminer);
		
		buttonValider = new JButton("<html>\r\nValider <br>\r\nl'étudiant\r\n</html>");
	    buttonValider.setBounds(614, 422, 230, 86);
	    frame.getContentPane().add(buttonValider);
	    
		buttonSuivant = new JButton(">");
		buttonSuivant.setBounds(762, 260, 90, 42);
		frame.getContentPane().add(buttonSuivant);
		
		buttonPrecedent = new JButton("<");
		buttonPrecedent.setBounds(20, 260,90, 42);
		frame.getContentPane().add(buttonPrecedent);
		
		labelEtudiantSelectionne = new JLabel("Etudiant 1");
		labelEtudiantSelectionne.setHorizontalAlignment(SwingConstants.CENTER);
		labelEtudiantSelectionne.setBounds(130, 250, 602, 74);
		frame.getContentPane().add(labelEtudiantSelectionne);
		
		buttonCombat = new JButton("Lancer le combat");
	    buttonCombat.setBounds(298, 416, 170, 74);
	    frame.getContentPane().add(buttonCombat);
	    buttonCombat.setVisible(false);
	    
	    labelVainqueurZone = new JLabel("test");
	    labelVainqueurZone.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    labelVainqueurZone.setBounds(100, 100, 286, 294);
	    frame.getContentPane().add(labelVainqueurZone);
	    labelVainqueurZone.setVisible(false);
	    
	    buttonContinuer = new JButton("Continuer");
	    buttonContinuer.setBounds(472, 188, 372, 136);
	    frame.getContentPane().add(buttonContinuer);
	    buttonContinuer.setVisible(false);
	    
	    buttonCreditsJ1 = new JButton("<html>\r\nCredits / Zone <br>\r\nJoueur1 \r\n</html>");
	    buttonCreditsJ1.setBounds(20, 89, 192, 60);
	    frame.getContentPane().add(buttonCreditsJ1);
	    buttonCreditsJ1.setVisible(false);
	    
	    buttonCreditsJ2 = new JButton("<html>\r\nCredits / Zone <br>\r\nJoueur2\r\n</html>");
	    buttonCreditsJ2.setBounds(595, 89, 192, 60);
	    frame.getContentPane().add(buttonCreditsJ2);
	    buttonCreditsJ2.setVisible(false);
	    
	    labelCreditsZoneJ1 = new JLabel("<html>\r\nReservistes : 0 <br>\r\nBibliotheque : 0 <br>\r\nDBE: 0 <br>\r\nQuartier Administratif : 0 <br>\r\nHalles Industrielles : 0 <br>\r\nHalle Sportive : 0 \r\n</html>");
	    labelCreditsZoneJ1.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    labelCreditsZoneJ1.setBounds(20, 106,382, 328);
	    frame.getContentPane().add(labelCreditsZoneJ1);
	    labelCreditsZoneJ1.setVisible(false);
	    
	    labelCreditsZoneJ2 = new JLabel("<html>\r\nReservistes : 0 <br>\r\nBibliotheque : 0 <br>\r\nDBE: 0 <br>\r\nQuartier Administratif : 0 <br>\r\nHalles Industrielles : 0 <br>\r\nHalle Sportive : 0 \r\n</html>");
	    labelCreditsZoneJ2.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    labelCreditsZoneJ2.setBounds(526, 106, 318, 328);
	    frame.getContentPane().add(labelCreditsZoneJ2);
	    labelCreditsZoneJ2.setVisible(false);
	    
	    labelGagnant = new JLabel("Joueur a gagné ! Félicitations");
	    labelGagnant.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    labelGagnant.setForeground(new Color(0, 255, 0));
	    labelGagnant.setBounds(560, 100, 286, 294);
	    frame.getContentPane().add(labelGagnant);
	    labelGagnant.setVisible(false);
	    
	    buttonSave = new JButton("Sauvegarder");
	    buttonSave.setBounds(20, 10, 181, 50);
	    frame.getContentPane().add(buttonSave);
	    
	    buttonCharge = new JButton("Charger");
	    buttonCharge.setBounds(692, 10, 181, 50);
	    frame.getContentPane().add(buttonCharge);
	}
}

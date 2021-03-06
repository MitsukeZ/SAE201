package app1.ihm;

import javax.swing.JFrame;
import javax.swing.JLabel;

import app1.Controleur;
import app1.ihm.panels.*;
import app1.metier.Cuve;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;

public class FramePrincipale extends JFrame 
{
	//Indice des menus, de -1 à 3
	public static final int MENU_TUBE  = 1;
	public static final int GENERATION = 3;

	//Attributs
	private Controleur ctrl;
	private int        etape;

	private int        nbCuves;
	private int        compteur;
	private int        cptTubes;
	private String     msgErreur;

	//Panels
	private PanelValider           panelValider;
	private PanelNbCuves           panelNbCuves;
	private PanelCreerCuve         panelCreerCuves;
	private PanelChoixCreationTube panelChoixCreation;
	private PanelCreationTube      panelCreationTube;
	private PanelChoixStructure    panelChoixStructure;

	public FramePrincipale(Controleur ctrl) 
	{
		//Initialisation des attributs
		this.etape    = -1;
		this.compteur = 1;
		this.cptTubes = 0;
		this.msgErreur= "";

		this.ctrl     = ctrl;
		this.panelValider = new PanelValider(this);

		//Propriétés de la Frame
		this.setSize(800, 400);

		//Fenêtre centrée sur l'écran
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) (tailleEcran.getWidth()/2-400) , (int) (tailleEcran.getHeight()/2-200));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.maj();

		//Activation de la frame
		this.setVisible(true);
	}

	public void setTitre()
	{
		switch (this.etape)
		{
			case 0: this.setTitle("Création des cuves"   ); break;
			case 2: this.setTitle("Choix d'une action"   ); break;
			case 3: this.setTitle("Création d'un tube"   ); break;
			case 4: this.setTitle("Choix d'une structure"); break;
		}
	}

	public void setPanelsAdequats()
	{
		this.getContentPane().removeAll();
		
		switch (this.etape) 
		{
			case 0: 
				this.panelNbCuves = new PanelNbCuves("");    
				this.add(this.panelNbCuves, BorderLayout.CENTER); 
				break;
			case 1:
				this.panelCreerCuves = new PanelCreerCuve("", this.compteur);
				this.add(this.panelCreerCuves, BorderLayout.CENTER);
				break;
			case 2:
				this.panelChoixCreation = new PanelChoixCreationTube(this);
				this.add(this.panelChoixCreation);
				this.revalidate();
				return;
			case 3:
				if (this.cptTubes > (this.nbCuves*(this.nbCuves-1))/2) 
				{
					this.setEtape(FramePrincipale.MENU_TUBE); 
					this.maj(); 
					return;
				}

				this.panelCreationTube = new PanelCreationTube("");
				this.add(this.panelCreationTube, BorderLayout.CENTER);
				break;
			case 4:
				this.panelChoixStructure = new PanelChoixStructure(this);
				this.add(this.panelChoixStructure);
				this.revalidate();
				return;
			default:
				this.add(new JLabel("Fichiers générés, vous pouvez quitter le programme", JLabel.CENTER), BorderLayout.CENTER);
				this.revalidate();
				return;
		}

		this.add(this.panelValider, BorderLayout.SOUTH);
		this.revalidate();
		this.repaint();
	}

	public boolean verification()
	{
		boolean condition;
		Cuve cuve1, cuve2;

		//Vérifications des valeurs nécessaires pour passer à l'étape suivante
		switch (this.etape) 
		{
			case 0: this.nbCuves = this.panelNbCuves.getNbCuves(); return this.nbCuves > -1 && this.nbCuves <= 26;

			case 1: condition =  (this.panelCreerCuves.getCapacite() != -1 &&
								  this.panelCreerCuves.getPosX()     != -1 &&
								  this.panelCreerCuves.getPosY()     != -1 &&
								  this.ctrl.creerCuve(this.panelCreerCuves.getCapacite(), 
													  this.panelCreerCuves.getPosX(), 
													  this.panelCreerCuves.getPosY(), 
													  this.panelCreerCuves.getPosInfo()));
					if (condition) {this.compteur++;} else {return false;}
					if (this.compteur <= this.nbCuves) {this.etape--;}
					return condition;
			case 3: 
				try {cuve1 = this.ctrl.getCuves().get(this.panelCreationTube.getCuve1()-1);}
				catch (Exception e) {return false;}

				try {cuve2 = this.ctrl.getCuves().get(this.panelCreationTube.getCuve2()-1);}
				catch (Exception e) {return false;}

				if (this.ctrl.creerTube(cuve1, cuve2, this.panelCreationTube.getEpaisseur()))
				{
					this.cptTubes++;
					this.setEtape(FramePrincipale.MENU_TUBE);
					return true;
				}

				return false;

		}
		return true;
	}

	public void maj() 
	{
		//Passe à l'étape suivante et affiche le contenu adéquat
		this.etape++;
		this.setTitre();
		this.setPanelsAdequats();
	}

	public void erreur() 
	{
		this.getContentPane().removeAll();

		//Recréation des panels avec les messages d'erreur
		switch (this.etape) 
		{
			case 0: 
				this.panelNbCuves = new PanelNbCuves("Valeur Invalide ! Car : " + this.msgErreur);    
				this.add(this.panelNbCuves, BorderLayout.CENTER); 
				break;
			case 1:
				this.panelCreerCuves = new PanelCreerCuve("Valeurs Invalides ! Car : " + this.msgErreur, this.compteur);
				this.add(this.panelCreerCuves);
				break;
			case 3:
				this.panelCreationTube = new PanelCreationTube("Valeurs Invalides ! Car : " + this.msgErreur);
				this.add(this.panelCreationTube);
				break;
		}

		this.add(this.panelValider, BorderLayout.SOUTH);
		this.revalidate();
	}

	public void generer(char structure)
	{
		this.ctrl.generer(structure);
	}

	public void setEtape(int etape)
	{
		if (etape < -1 || etape > 3) {return;}

		this.etape = etape;
	}

	public void setErreur (String e)
	{
		this.msgErreur = e;
	}
}

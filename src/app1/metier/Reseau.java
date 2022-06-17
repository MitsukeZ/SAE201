package app1.metier;

import java.util.List;

import app1.Controleur;

import java.util.ArrayList;
import java.math.*;

public class Reseau 
{
	private List<Cuve> lstCuve;
	private List<Tube> lstTube;
	private Controleur ctrl;

	//la classe réseau simule l'ensemble des cuves et des tubes d'où la création de 2 arraylist pour les cuves et les tubes
	public Reseau(Controleur ctrl)
	{
		this.ctrl    = ctrl;
		this.lstCuve = new ArrayList<Cuve>();
		this.lstTube = new ArrayList<Tube>();
	}

	//cette méthode permet de créer une cuve si c'est impossible alors la méthode retourne false
	public boolean creerCuve(int capacite, int posX, int posY, String posInfo) 
	{
		Cuve cuveACreer = Cuve.fabrique(capacite, posX, posY, posInfo);

		if (cuveACreer == null)																				//on vérifie si la cuve a put être crée
		{
			this.ctrl.setMesgErreur("les paramètres sont invalides");
			return false;
		}

		for (Cuve c : this.lstCuve) {
			if (c.getPosX() == posX && c.getPosY() == posY) {return false;}									//si on peu crée la cuve on vérifie qu'elle n'est pas situé au même endroit qu'une autre
			if ( 125 >=  Math.abs((c.getPosX() - posX)) + Math.abs((c.getPosY() - posY)) )					//on choisit un écart de 125 entre les différentes cuves car chaque cuve a un rayon de 50 donc 50+50=100 les 25 restants sont fait pour les tubes
			{
				this.ctrl.setMesgErreur("une cuve est déjà trop proche");
				return false;
			}
		}

		this.lstCuve.add(cuveACreer);																		//si oui on l'ajoute à la liste des cuves
		return true;
	}

	public boolean creerTube(Cuve cv1, Cuve cv2, double epaisseur) 
	{
		Tube tubeACreer;

		if (!this.lstCuve.contains(cv1) && !this.lstCuve.contains(cv2)) 									//on vérifie si les cuves qu'on veut lier existe
		{
			this.ctrl.setMesgErreur("la cuve n'existe pas");
			return false;
		}

		tubeACreer = Tube.creerTube(cv1, cv2, epaisseur);

		if (tubeACreer == null) 																			//si le tube n'a pas à être créé
		{
			this.ctrl.setMesgErreur("les paramètres sont faux");
			return false;
		}

		for (Tube t : this.lstTube)
		{
			if ((t.getCuve1() == cv1 && t.getCuve2() == cv2) || (t.getCuve1() == cv2 && t.getCuve2() == cv1)) 
			{
				this.ctrl.setMesgErreur("les deux cuves rentrées sont les mêmes");
				return false;
			}
		}

		this.lstTube.add(tubeACreer);
		return true;
	}

	//accesseurs
	public List<Tube> getTubes()
	{
		return new ArrayList<Tube>(this.lstTube);
	}

	public List<Cuve> getCuves()
	{
		return new ArrayList<Cuve>(this.lstCuve);
	}

	public void generer(char structure)
	{
		new Generateur(this.lstCuve, this.lstTube, structure);
	}
}

package main.metier;

import java.util.List;
import java.util.ArrayList;

import main.Controleur;

public class Reseau 
{
	private List<Cuve> lstCuve;
	private List<Tube> lstTube;

	private Controleur ctrl;

	//la classe réseau simule l'ensemble des cuves et des tubes d'où la création de 2 arraylist pour les cuves et les tubes
	public Reseau(Controleur ctrl)
	{
		this.ctrl = ctrl;
		
		this.lstCuve = new ArrayList<Cuve>();
		this.lstTube = new ArrayList<Tube>();
	}

	//cette méthode permet de créer une cuve si c'est impossible alors la méthode retourne false
	public boolean creerCuve(int capacite, int posX, int posY, String posInfo) 
	{
		Cuve cuveACreer = Cuve.fabrique(capacite, posX, posY, posInfo);

		if (cuveACreer == null) {return false;}															//on vérifie si la cuve a put être crée

		for (Cuve c : this.lstCuve) {
			if (c.getPosX() == posX && c.getPosY() == posY) {return false;}								//si on peu crée la cuve on vérifie qu'elle n'est pas situé au même endroit qu'une autre
		}

		this.lstCuve.add(cuveACreer);																	//si oui on l'ajoute à la liste des cuves
		return true;
	}

	public boolean creerTube(Cuve cv1, Cuve cv2, double epaisseur) 
	{
		Tube tubeACreer;
		
		if (!this.lstCuve.contains(cv1) && !this.lstCuve.contains(cv2)) {return false;}					//on vérifie si les cuves qu'on veut lier existe
		
		tubeACreer = Tube.creerTube(cv1, cv2, epaisseur);

		if (tubeACreer == null) {return false;}															//si le tube n'a pas à être créé

		for (Tube t : this.lstTube) 																	
		{	
			if ((t.getCuve1() == cv1 && t.getCuve2() == cv2) || (t.getCuve1() == cv2 && t.getCuve2() == cv1)) {
				return false;
			}
		}

		this.lstTube.add(tubeACreer);
		return true;
	}

	//accesseurs
	public List<Tube> getTubes() {
		return new ArrayList<Tube>(this.lstTube);
	}

	public List<Cuve> getCuves() {
		return new ArrayList<Cuve>(this.lstCuve);
	}

	public void generer(char structure) {
		new Generateur(this.lstCuve, this.lstTube, structure);
	}
}

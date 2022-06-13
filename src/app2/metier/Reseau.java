package app2.metier;

import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.util.ArrayList;

import app2.Controleur;

public class Reseau 
{
	private List<Cuve> lstCuve;
	private List<Tube> lstTube;

	private Controleur ctrl;
	
	public  Reseau(Controleur ctrl)
	{
		this.ctrl = ctrl;
		
		this.lstCuve = new ArrayList<Cuve>();
		this.lstTube = new ArrayList<Tube>();

		//this.lireFichier();
	}

	public boolean creerCuve(int capacite, int posX, int posY, String posInfo)
	{
		Cuve cuveACreer = Cuve.fabrique(capacite, posX, posY, posInfo);

		if (cuveACreer == null) {return false;}

		for (Cuve c : this.lstCuve)
			if (c.getPosX() == posX && c.getPosY() == posY) {return false;}

		this.lstCuve.add(cuveACreer);
		return true;
	}

	public boolean creerTube(Cuve cv1, Cuve cv2, double epaisseur)
	{
		Tube tubeACreer;
		
		if (!this.lstCuve.contains(cv1) || !this.lstCuve.contains(cv2)) {return false;}
		
		tubeACreer = Tube.creerTube(cv1, cv2, epaisseur);

		if (tubeACreer == null) {return false;}

		this.lstTube.add(tubeACreer);
		return true;
	}

	public List<Tube> getTubes() { return new ArrayList<Tube>(this.lstTube); }

	public List<Cuve> getCuves() { return new ArrayList<Cuve>(this.lstCuve); }

	/*-------------------------------*/
	/*--Methodes de l'application 2--*/
	/*-------------------------------*/

	public void passerAuTourSuivant()
	{
		for (Tube tubeTmp : this.lstTube) 
		{
			tubeTmp.transvaser();
		}
	}
}

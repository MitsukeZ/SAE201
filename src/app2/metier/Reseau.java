package app2.metier;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Reseau 
{
	private List<Cuve> lstCuve;
	private List<Tube> lstTube;

	public  Reseau()
	{
		this.lstCuve = new ArrayList<Cuve>();
		this.lstTube = new ArrayList<Tube>();
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
		List<Cuve> cuvesNonVides  = new ArrayList<Cuve>();
		List<Tube> tubesAUtiliser = new ArrayList<Tube>();

		//Détection des cuves contenant du liquide
		for (Cuve c: this.lstCuve)
			if (c.getContenu() > 0) {cuvesNonVides.add(c);}

		//Détection des tubes de ces cuves
		for (Cuve c: cuvesNonVides)
			for (Tube t: this.lstTube)
				if (t.contains(c) && !tubesAUtiliser.contains(t)) {tubesAUtiliser.add(t);}

		Collections.sort(tubesAUtiliser);

		//Transvaser
		for (Tube t: tubesAUtiliser)
			t.transvaser(t.getEpaisseur());
	}

	public boolean remplirCuve(String cuveRecherchee, String qte)
	{
		if (!qte.matches("[0-9]+"))
			return false;

		double quantite;
		char   cuve = cuveRecherchee.toUpperCase().charAt(0);

		try {quantite = Double.valueOf(qte);} catch (Exception e) {return false;}

		if (cuve < 'A' || (int) (cuve - 'A') > lstCuve.size() )
			return false;

		Cuve cuveEnQuestion = null;

		for (Cuve cuveBoucle : lstCuve) 
			if (cuveBoucle.getIdentifiant() == cuve )
				cuveEnQuestion = cuveBoucle;

		if (cuveEnQuestion == null)
			return false;

		if (quantite < 0) {return false;}

		if (cuveEnQuestion.getQuantiteLibre() < quantite)
			cuveEnQuestion.remplir(cuveEnQuestion.getQuantiteLibre());
		else
			cuveEnQuestion.remplir(quantite);

		return true;
	}

	public int getPosXMax()
	{
		int max = 0;

		for (Cuve c: this.lstCuve)
			if (c.getPosX() > max) {max = c.getPosX();}

		return max;
	}

	public int getPosYMax()
	{
		int max = 0;

		for (Cuve c: this.lstCuve)
			if (c.getPosY() > max) {max = c.getPosY();}

		return max;
	}
}

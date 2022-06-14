package main.metier;
public class Tube
{
	//le tube connait les deux cuves qu'il relit pour simplifier la création du fichier par le générateur
	private Cuve cuve1;
	private Cuve cuve2;	

	private double epaisseur;

	//Le constructeur est en privé car il ne doit être appelé que par la factory
	private Tube (Cuve cv1, Cuve cv2, double epaisseur)
	{
		this.cuve1     = cv1;
		this.cuve2     = cv2;
		this.epaisseur = epaisseur;
	}

	//Une farbrique est utile dans le cas présent car on peut prévoir l'incapacité de créer un tube
	public static Tube creerTube(Cuve cv1, Cuve cv2, double epaisseur)
	{
		if (epaisseur > 10 || epaisseur < 2)								//gestion du cas ou l'épaisseur ne rentre pas dans les standards
		{
			return null;
		}

		if (cv1 == null || cv2 == null )									//gestion du cas ou l'une des cuve n'existe pas 
		{
			return null;
		}
		if (cv1 == cv2) 													//gestion du cas ou les deux cuves sont les memes
		{
			return null;
		}
		return new Tube(cv1, cv2, epaisseur);
	}


	//accesseurs de la classe
	public double getEpaisseur ()
	{
		return this.epaisseur;
	}

	public Cuve getCuve1()
	{
		return this.cuve1;
	}

	public Cuve getCuve2()
	{
		return this.cuve2;
	}



}
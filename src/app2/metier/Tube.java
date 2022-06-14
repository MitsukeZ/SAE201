package app2.metier;

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

	//classe permettant de transvaser une quantite de la valeur epaisseur de la plus grosse cuve à la plus petite 
	public boolean transvaser()
	{
		Cuve grande;
		Cuve petite;

		grande = this.cuve1;
		petite = this.cuve2;
		if (this.cuve2.getContenu() > this.cuve1.getContenu())								//vérification permettant de voir quelle cuve est la plus grande 
		{
			grande = this.cuve2;
			petite = this.cuve1;
		}
		if (grande.getContenu() != petite.getContenu())										//vérification si les deux cuves ont la meme contenance
		{
			if (petite.getQuantiteLibre() > this.epaisseur)									//vérification si la quantité libre de la petite cuve est plus grande que le contenu qu'elle va recevoir
			{
				if (grande.getContenu() > this.epaisseur)									//vérification si le contenu de la grande cuve est plus grand que le contenu qu'on va lui enlever
				{
					petite.remplir(this.epaisseur);
					grande.vider  (this.epaisseur);
				}
				else																		//si le contenu de la grande cuve n'est pas plus grand que le contenu qu'on va lui enlever alors on enlève le contenu de la grande
				{
					petite.remplir(grande.getContenu());
					grande.vider  (grande.getContenu());
				}
			}
			else
			{
				if (grande.getContenu() > petite.getQuantiteLibre())						//vérification si la quantité libre de la petite cuve est plus petite que le contenu de la grande qu'elle va recevoir en sachant que les deux sont plus petits que l'épaisseur du fichier est faux
				{
					grande.vider  (petite.getQuantiteLibre());
					petite.remplir(petite.getQuantiteLibre());
				}
				else
				{
					petite.remplir(grande.getContenu());
					grande.vider  (grande.getContenu());
				}			
			}
			return true;
		}
		return false;
	}
}
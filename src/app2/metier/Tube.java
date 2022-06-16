package app2.metier;

public class Tube implements Comparable<Tube>
{
	//le tube connait les deux cuves qu'il relit pour simplifier la création du fichier par le générateur
	private Cuve   cuve1;
	private Cuve   cuve2;	

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
		if (epaisseur > 10 || epaisseur < 2) {return null;}				    //gestion du cas ou l'épaisseur ne rentre pas dans les standards
		if (cv1 == null    || cv2 == null  ) {return null;}					//gestion du cas ou l'une des cuve n'existe pas
		if (cv1 == cv2                     ) {return null;}					//gestion du cas ou les deux cuves sont les memes 

		return new Tube(cv1, cv2, epaisseur);
	}

	//accesseurs de la classe
	public double getEpaisseur() {return this.epaisseur;}
	public Cuve   getCuve1    () {return this.cuve1;    }
	public Cuve   getCuve2    () {return this.cuve2;    }

	//méthode permettant de transvaser une quantite de la valeur epaisseur de la plus grosse cuve à la plus petite 
	public boolean transvaser(double valeur)
	{
		Cuve grande;
		Cuve petite;

		if (this.cuve1.getContenu() == this.cuve2.getContenu()) {return false;}

		grande = (this.cuve1.getContenu() < this.cuve2.getContenu() ? this.cuve2 : this.cuve1);
		petite = (this.cuve1.getContenu() < this.cuve2.getContenu() ? this.cuve1 : this.cuve2);
		
		if (this.epaisseur                            < valeur) {valeur = this.epaisseur;                           }
		if (grande.getContenu()                       < valeur) {valeur = grande.getContenu();                      }
		if (petite.getQuantiteLibre()                 < valeur) {valeur = petite.getQuantiteLibre();                }
		if (grande.getContenu() - petite.getContenu() < valeur) {valeur = (grande.getContenu() - petite.getContenu())/2;}
		
		petite.remplir(valeur);
		grande.vider  (valeur);
		
		return true;
	}

	public boolean contains(Cuve c) {return cuve1 == c || cuve2 == c;}

	public int compareTo(Tube t) {return (int) ((this.getCuve1().getContenu()+this.getCuve2().getContenu()) -
		                                        (t   .getCuve1().getContenu()+t   .getCuve2().getContenu()));}
}
package app2.metier;

public class Cuve
{
	//caractère auto-incrémenté allant de A à Z
	private static char identifiants = 'A';
	private        char identifiant;

	private int    capacite;
	private double contenu;
	private int    posX;
	private int    posY;
	private String posInfo;
	
	//constructeur privé qui oblige à passer par la factory
	private Cuve(int capacite, int posX, int posY, String posInfo)
	{
		this.identifiant = Cuve.identifiants++;

		this.capacite    = capacite;
		this.contenu     = 0;
		this.posX        = posX;
		this.posY        = posY;
		this.posInfo     = posInfo;
	}
	
	//factory qui permet de créer des objets Cuve null si les conditions ne sont pas remplis
	public static Cuve fabrique ( int capacite, int posX, int posY, String posInfo )
	{
		if ( Cuve.identifiants > 'Z'          )                       return null;								//cas si le nombre de cuves est supérieur à 26
		if ( capacite < 200 || capacite > 1000)                       return null;								//cas si la capacité de la cuve ne sont pas bonnes
		if ( posX < 0 || posY < 0             )                       return null; 								//cas si la position n'est pas possible sur l'écran
		if ( posInfo == null                  )                       return null;								//cas si la position des informations n'est pas présente

		posInfo = posInfo.toUpperCase();

		if ( !posInfo.equals("HAUT"  ) && !posInfo.equals("BAS"   ) && 
			 !posInfo.equals("GAUCHE") && !posInfo.equals("DROITE") ) return null;			 //cas si la position des informations ne fait pas partie de HAUT, BAS, GAUCHE, DROITE

		return new Cuve ( capacite, posX, posY, posInfo);
	}

	//Accesseurs
	public char   getIdentifiant() {return this.identifiant;}
	public int    getCapacite   () {return this.capacite;   }
	public double getContenu    () {return this.contenu;    }
	public int    getPosX       () {return this.posX;       }
	public int    getPosY       () {return this.posY;       }
	public String getPosInfo    () {return this.posInfo;    }



	//Méthodes destniné a transvaser les valeurs
	public double getQuantiteLibre () {return this.capacite - ((double) (this.contenu));}

	public void remplir (double i) {this.contenu += i;}
	public void vider   (double i) {this.contenu -= i;}

	public String toString()
	{
		return  "Identifiant    : "     + this.identifiant                            + "\n" +
				"Capacité       : "     + this.capacite                               + "\n" +
				"Contenu        : "     + this.contenu     							  + "\n" +
				"Position       : (x: " + this.posX    + "; y:" + this.posY + ")"     + "\n" +
				"PosInformation : "     + this.posInfo ;
	} 
}

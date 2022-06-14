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
	

	private Cuve(int capacite, int posX, int posY, String posInfo)
	{
		this.identifiant = Cuve.identifiants++;
		
		this.capacite = capacite;
		this.contenu  = 0;
		this.posX     = posX;
		this.posY     = posY;
		this.posInfo  = posInfo;
		
	}
	
	public static Cuve fabrique ( int capacite, int posX, int posY, String posInfo )
	{
		if ( Cuve.identifiants > 'Z' )                                return null;
		if ( capacite < 200 || capacite > 1000)                       return null;
		if ( posX < 0 || posY < 0 )                                   return null;
		if ( posInfo == null )                                        return null;
		posInfo = posInfo.toUpperCase();
		if ( !posInfo.equals("HAUT")   && !posInfo.equals("BAS") && 
		     !posInfo.equals("GAUCHE") && !posInfo.equals("DROITE") ) return null;
		
		return new Cuve ( capacite, posX, posY, posInfo);
	}
	
    public char getIdentifiant() 
	{
        return this.identifiant;
    }

    public int getCapacite() 
	{
        return this.capacite;
    }

    public double getContenu() 
	{
        return this.contenu;
    }

    public int getPosX() 
	{
        return this.posX;
    }

    public int getPosY() 
	{
        return this.posY;
    }

    public String getPosInfo() 
	{
        return this.posInfo;
    }

	public int getQuantiteLibre ()
	{
		return this.capacite - ((int) (this.contenu));
	}

	public void remplir (double i)
	{
		this.contenu += i;
	}
	public void vider (double i)
	{
		this.contenu -= i;
	}

	public String toString()
	{
		return  "Identifiant    : "     + this.identifiant                            + "\n" +
				"Capacité       : "     + this.capacite                               + "\n" +
				"Contenu        : "     + this.contenu     							  + "\n" +
				"Position       : (x: " + this.posX    + "; y:" + this.posY + ")" + "\n" +
				"PosInformation : "     + this.posInfo ;
	} 
}

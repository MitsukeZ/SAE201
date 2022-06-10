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
	

	public Cuve(int capacite, int posX, int posY, String posInfo)
	{
		this.identifiant = Cuve.identifiants++;
		
		this.capacite = capacite;
		this.contenu  = 0;
		this.posX     = posX;
		this.posY     = posY;
		this.posInfo  = posInfo;
		
	}
	
	public Cuve fabrique ( int capacite, int posX, int posY, String posInfo )
	{
		if ( Cuve.identifiants > 'Z' )                    return null;
		if ( capacite < 200 && capacite > 1000)           return null;
		if ( posX < 0 || posY < 0 )                       return null;
		if ( posInfo != "Haut"   || posInfo != "Bas" || 
			 posInfo != "Gauche" || posInfo != "Droite" ) return null;
		
		return new Cuve ( capacite, posX, posY, posInfo);
	}
	

}

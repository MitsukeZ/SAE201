package src.main.metier;
public class Tube
{
	private Cuve cuve1;
	private Cuve cuve2;	

	private int epaisseur;

	private Tube (Cuve cv1, Cuve cv2, int epaisseur)
	{
		this.cuve1     = cv1;
		this.cuve2     = cv2;
		this.epaisseur = epaisseur;
	}

	public static Tube creerTube(Cuve cv1, Cuve cv2, int epaisseur)
	{
		if (epaisseur > 10 || epaisseur < 2)
		{
			return null;
		}

		if (cv1 == null || cv2 == null )
		{
			return null;
		}
		if (cv1 == cv2) 
		{
			return null;
		}
		return new Tube(cv1, cv2, epaisseur);
	}

	public int getEpaisseur ()
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
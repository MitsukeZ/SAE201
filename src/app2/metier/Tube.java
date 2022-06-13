package main.metier;
public class Tube
{
	private Cuve cuve1;
	private Cuve cuve2;	

	private double epaisseur;

	private Tube (Cuve cv1, Cuve cv2, double epaisseur)
	{
		this.cuve1     = cv1;
		this.cuve2     = cv2;
		this.epaisseur = epaisseur;
	}

	public static Tube creerTube(Cuve cv1, Cuve cv2, double epaisseur)
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


	public boolean transvaser()
	{
		Cuve grande;
		Cuve petite;
		grande = this.cuve1;
		petite = this.cuve2;
		if (this.cuve2.getContenu() > this.cuve1.getContenu())
		{
			grande = this.cuve2;
			petite = this.cuve1;
		}
		if (grande.getContenu() != petite.getContenu())
		{
			if (petite.getQuantiteLibre() > this.epaisseur)
			{
				if (grande.getContenu() > this.epaisseur)
				{
					petite.remplir(this.epaisseur);
					grande.vider  (this.epaisseur);
				}
				else
				{
					petite.remplir(grande.getContenu());
					grande.vider  (grande.getContenu());
				}
			}
			else
			{
				if (grande.getContenu() > petite.getQuantiteLibre())
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
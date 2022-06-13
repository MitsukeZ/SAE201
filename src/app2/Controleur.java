package app2;

import app2.metier.*;
import app2.ihm.*;

import java.util.List;

public class Controleur
{
    private Reseau        metier;
    private FrameReseau   ihm;

    public Controleur(boolean modeConsole)
    {
        this.metier     = new Reseau(this);
		this.ihm        = new FrameReseau(this);
		
    }

    public boolean creerCuve(int capacite, int posX, int posY, String posInfo) 
    {
        return metier.creerCuve(capacite, posX, posY, posInfo);
    }

    public boolean creerTube(Cuve cv1, Cuve cv2, int epaisseur) 
    {
        return metier.creerTube(cv1, cv2, epaisseur);
    }

    public List<Cuve> getCuves() 
    {
       return  metier.getCuves();
    }

    public List<Tube> getTubes() {
       return metier.getTubes();
    }

    public void passerAuTourSuivant()
    {
       this.metier.passerAuTourSuivant(); 
    }

<<<<<<< HEAD
    public void generer(char structure) {
        this.metier.generer(structure);
    }
    
    public static void main(String[] args) 
    {
		if (args.length == 0)
		{
        	new Controleur(false);
		}
		else
		{
			new Controleur(true);
		}
=======
	public void passerAuTourSuivant()
	{
		this.metier.passerAuTourSuivant();
	}

	public static void main(String[] args) 
	{
		new Controleur();	
>>>>>>> 1d0a54b35902b0f7e0aa0566d5e1bf194fc64adf
	}
}
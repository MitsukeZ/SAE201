package main;

import main.metier.*;
import main.ihm.*;

public class Controleur
{
    private Reseau           metier;
    private FrameFormulaire  ihm;

    public Controleur(boolean modeConsole)
    {
        this.metier     = new Reseau(this);
		if (modeConsole)
		{
			this.ihm    = new FormulaireConsole(this);
		}
        else
		{
			this.ihm    = new FrameFormulaire(this);
		}
    }

    public boolean creerCuve(int capacite, int posX, int posY, String posInfo) 
    {
        metier.creerCuve(capacite, posX, posY, posInfo);

    }

    public boolean creerTube(Cuve cv1, Cuve cv2, int epaisseur) 
    {
        metier.creerTube(cv1, cv2, epaisseur) ;

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
	}
}
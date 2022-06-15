package main;

import main.metier.*;
import main.ihm.*;
import main.ihm.FramePrincipale;

import java.util.List;

public class Controleur
{
    private Reseau            metier;
    private FramePrincipale   ihm;
    private FormulaireConsole ihmCUI;

    public Controleur(boolean modeConsole)
    {
        this.metier     = new Reseau(this);
		if (modeConsole)
		{
			this.ihmCUI    = new FormulaireConsole(this);
		}
        else
		{
			this.ihm       = new FramePrincipale(this);
		}
    }

    public boolean creerCuve(int capacite, int posX, int posY, String posInfo) 
    {
        return metier.creerCuve(capacite, posX, posY, posInfo);
    }

    public boolean creerTube(Cuve cv1, Cuve cv2, double epaisseur) 
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
	}

    
}
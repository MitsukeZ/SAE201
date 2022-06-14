package app2;

import app2.metier.*;
import app2.ihm.*;

import java.util.List;

public class Controleur
{
    private Reseau        metier;
    private FrameReseau   ihm;
    private Lecteur       lecteur;

    public Controleur()
    {
        this.metier     = new Reseau(this);
		this.ihm        = new FrameReseau(this);
		this.lecteur    = new Lecteur(this);
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

    public void passerAuTourSuivant()
    {
       this.metier.passerAuTourSuivant(); 

    }

    public boolean remplirCuve(String s1, String s2)
    {
        return this.metier.remplirCuve(s1,s2);
    }
    
    public void repaint()
    {
        this.ihm.repaint();
    }

	public static void main(String[] args) 
	{
		new Controleur();	
	}


}
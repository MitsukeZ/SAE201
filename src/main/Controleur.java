package main;
import iut.algo.Clavier;

public class Controleur
{
    private Reseau           metier;
    private FrameFormulaire  ihm;

    public Controleur()
    {
        this.metier     = new Reseau(this);
        this.ihm        = new FrameFormulaire(this);
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
        new Controleur();
    }
}
package src.main;
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
        

    }
    
    public static void main(String[] args) 
    {
        new Controleur();
    }
}
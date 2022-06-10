import iut.algo.Clavier;

public class Controleur
{
    public Controleur()
    {
        int nbCuves;
        
        System.out.print("Nombre de cuves du réseau : ");
        nbCuves = Clavier.lire_int();

        for (int cpt = 0; cpt < nbCuves; cpt++)
        System.out.print("");
    }
    
    public static void main(String[] args) 
    {
        new Controleur();
    }
}
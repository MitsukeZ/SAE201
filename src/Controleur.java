import iut.algo.Clavier;

public class Controleur
{
    private Reseau           metier;
    private Generateur       generateur;
    private FrameFormulaire  ihm;

    public Controleur()
    {
        this.metier     = new Reseau(this);
        this.generateur = new Generateur(this);
        this.ihm        = new FrameFormulaire(this);
    }
    
    public static void main(String[] args) 
    {
        new Controleur();
    }
}
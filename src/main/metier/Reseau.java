package main.metier;

import java.util.List;
import java.util.ArrayList;

import main.Controleur;

public class Reseau 
{
    private List<Cuve> lstCuve;
    private List<Tube> lstTube;

    private Controleur ctrl;
    
    public  Reseau()
    {
        this.lstCuve = new ArrayList<Cuve>();
        this.lstTube = new ArrayList<Tube>();
    }

    public boolean creerCuve(int capacite, int posX, int posY, String posInfo) {
        Cuve cuveACreer = Cuve.fabrique(capacite, posX, posY, posInfo);

        if (cuveACreer == null) {return false;}

        this.lstCuve.add(cuveACreer);
        return true;
    }

    public boolean creerTube(Cuve cv1, Cuve cv2, int epaisseur) {
        Tube tubeACreer;
        
        
        
        tubeACreer = Tube.creerTube(cv1, cv2, epaisseur);

        if (tubeACreer == null) {return false;}

        this.lstTube.add(tubeACreer);
        return true;
    }

    public List<Tube> getTubes() {
        return new ArrayList<Tube>(this.lstTube);
    }
}

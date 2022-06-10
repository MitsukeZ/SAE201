package main.metier;

import java.util.List;
import java.util.ArrayList;

import main.Controleur;

public class Reseau 
{
    private List<Cuve> lstCuve;
    private List<Tube> lstTube;

    private Controleur ctrl;
    
    public  Reseau(Controleur ctrl)
    {
        this.ctrl = ctrl;
        
        this.lstCuve = new ArrayList<Cuve>();
        this.lstTube = new ArrayList<Tube>();
    }

    public boolean creerCuve(int capacite, int posX, int posY, String posInfo)
    {
        Cuve cuveACreer = Cuve.fabrique(capacite, posX, posY, posInfo);

        if (cuveACreer == null) {return false;}

        for (Cuve c : this.lstCuve)
            if (c.getPosX() == posX && c.getPosY() == posY) {return false;}

        this.lstCuve.add(cuveACreer);
        return true;
    }

    public boolean creerTube(Cuve cv1, Cuve cv2, int epaisseur)
    {
        Tube tubeACreer;
        
        if (!this.lstCuve.contains(cv1) && !this.lstCuve.contains(cv2)) {return false;}
        
        tubeACreer = Tube.creerTube(cv1, cv2, epaisseur);

        if (tubeACreer == null) {return false;}

        this.lstTube.add(tubeACreer);
        return true;
    }

    public List<Tube> getTubes() { return new ArrayList<Tube>(this.lstTube); }

    public List<Cuve> getCuves() { return new ArrayList<Cuve>(this.lstCuve); }

    public void lireFichier()
    {
        try
		{
			Scanner sc = new Scanner ( new FileReader ( "tube.data" ) );

			while ( sc.hasNextLine() )
			{
				String[] tabS = sc.nextLine().split("\t");
				
				String date = tabS[0].substring(6, 8) + "/" + tabS[0].substring(4, 6) + "/" + tabS[0].substring(0, 4);

				alVideo.add( new Video(date, tabS[1], tabS[2], tabS[3], tabS[4]) );
			}
		}catch (Exception e){ e.printStackTrace(); }
    }
}
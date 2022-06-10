package main.metier;
import java.util.List;
import java.util.ArrayList;

import java.io.PrintWriter;
import java.io.File;

public class Generateur
{
	public Generateur(Cuve[] tCuve, List<Tube> tTube, char structure)
	{
		PrintWriter pw = null;

		try
		{	
			pw = new PrintWriter ( new File ( "tube.data"), "utf-8" );
		}	
		catch (Exception e){e.printStackTrace();}

		for (Cuve cuve : tCuve)
		{
			pw.println(cuve.getCapacite() + "\t" + cuve.getPosX() + "\t" + cuve.getPosY() + "\t" + cuve.getPosInfo());
		}

		int[][] tabLien = new int[tCuve.length][tCuve.length];

		for (Tube tube : tTube)
		{
			tabLien[tube.getCuve1().getIdentifiant() - 'A'][tube.getCuve2().getIdentifiant() - 'A'] = tube.getEpaisseur();
					
			tabLien[tube.getCuve2().getIdentifiant() - 'A'][tube.getCuve1().getIdentifiant() - 'A'] = tube.getEpaisseur();
		}

		switch (structure)
		{
			case 'L':

				pw.println("liste d'adjacence");

				for (Tube tube : tTube)
				{
					pw.println(tube.getCuve1().getIdentifiant() + "\t" + tube.getCuve2().getIdentifiant() + "\t" + tube.getEpaisseur());
				}
				
				break;

			case 'M':

				pw.println("matrice de cout");

				for (int i = 0; i < tabLien.length; i++)
				{
					String ligne = "";

					for (int j = 0; j < tabLien[0].length; j++)
					{
						ligne += tabLien[i][j] + "\t";
					}

					pw.println(ligne);
				}
				
				break;

			case 'O':

				pw.println("matrice de cout optimisee");

				for (int i = 1; i < tabLien.length; i++)
				{
					String ligne = "";

					for (int j = 0; j < i; j++)
					{
						ligne += tabLien[i][j] + "\t";
					}

					pw.println(ligne);
				}
				
				break;
		
			default:
				break;
		}

		pw.close();
	}

	public static void main(String[] args)
	{
		Cuve[] tCuve = new Cuve[4];

		tCuve[0] = Cuve.fabrique(1000,   0,   0, "Haut"  );
		tCuve[1] = Cuve.fabrique(900 , 100,   0, "Droite");
		tCuve[2] = Cuve.fabrique(200 ,  50, 100, "Bas"   );
		tCuve[3] = Cuve.fabrique(700 , 100, 100, "Droite");

		List<Tube> tTube = new ArrayList<Tube>();

		tTube.add(Tube.creerTube(tCuve[0], tCuve[1], 2));
		tTube.add(Tube.creerTube(tCuve[0], tCuve[2], 6));
		tTube.add(Tube.creerTube(tCuve[2], tCuve[1], 4));
		tTube.add(Tube.creerTube(tCuve[1], tCuve[3], 8));

		new Generateur(tCuve, tTube, 'M');
	}
}

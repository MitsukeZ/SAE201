package src.main.metier;
import java.util.ArrayList;

import java.io.PrintWriter;
import java.io.File;

public class Generateur
{
	public Generateur(Cuve[] tCuve, ArrayList<Tube> tTube, char structure)
	{
		PrintWriter pw = null;

		try
		{	
			pw = new PrintWriter ( new File ( "tube.data"), "utf-8" );
		}	
		catch (Exception e){e.printStackTrace();}

		switch (structure)
		{
			case 'L':

				pw.println("liste d'adjacence");

				for (Tube tube : tTube)
				{
					
				}
				
				break;

			case 'M':

				pw.println("matrice de cout");

				int[][] tabLien = new int[tCuve.length][tCuve.length];

				for (Tube tube : tTube)
				{
					
				}

				for (Cuve cuve : tCuve)
				{
					int[] lien = new int[tCuve.length];

					for (Tube tube : tTube) 
					{
						if (tube.getCuve1() == cuve)
							lien[tube.getCuve2().getIdentifiant() - 'A'] = tube.getEpaisseur();
					}

					String ligne = "";
						
					for (int i = 0; i < lien.length; i++)
					{
						ligne += lien[i] + "\t";
					}

					pw.println(ligne);
				}
				
				break;

			case 'O':

				pw.println("matrice de cout optimisee");

				for (Tube tube : tTube)
				{
					
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

		tCuve[0] = Cuve.fabrique(1000, 0  ,   0, "Haut"  );
		tCuve[1] = Cuve.fabrique(900 , 100,   0, "Droite");
		tCuve[2] = Cuve.fabrique(200 ,  50, 100, "Bas"   );
		tCuve[3] = Cuve.fabrique(700 , 100, 100, "Droite");

		ArrayList<Tube> tTube = new ArrayList<Tube>();

		tTube.add(Tube.creerTube(tCuve[0], tCuve[1], 2));
		tTube.add(Tube.creerTube(tCuve[0], tCuve[2], 6));
		tTube.add(Tube.creerTube(tCuve[2], tCuve[1], 4));
		tTube.add(Tube.creerTube(tCuve[1], tCuve[3], 8));

		new Generateur(tCuve, tTube, 'M');
	}
}

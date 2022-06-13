package main.metier;

import java.util.List;

import java.io.PrintWriter;
import java.io.File;

public class Generateur
{
	public Generateur(List<Cuve> tCuve, List<Tube> tTube, char structure)
	{
		PrintWriter pw = null;

		try
		{	
			pw = new PrintWriter ( new File ( "cuve.data"), "utf-8" );
		}	
		catch (Exception e){e.printStackTrace();}

		for (Cuve cuve : tCuve)
		{
			pw.println(cuve.getCapacite() + "\t" + cuve.getPosX() + "\t" + cuve.getPosY() + "\t" + cuve.getPosInfo());
		}

		pw.close();

		try
		{	
			pw = new PrintWriter ( new File ( "tube.data"), "utf-8" );
		}
		catch (Exception e){e.printStackTrace();}

		double[][] tabLien = new double[tCuve.size()][tCuve.size()];

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

				for (int i = 0; i < tabLien.length; i++)
				{
					String ligne = "";

					for (int j = 0; j <= i; j++)
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
}

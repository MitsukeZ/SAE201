package app1.metier;

import java.util.List;

import java.io.PrintWriter;
import java.io.File;

public class Generateur
{
	public Generateur(List<Cuve> tCuve, List<Tube> tTube, char structure)
	{
		PrintWriter pw = null;

		/*-- Création du fichier des cuves --*/

		try
		{
			pw = new PrintWriter ( new File ( "reseau.data"), "utf-8" );
		}
		catch (Exception e){e.printStackTrace(); return;}

		for (Cuve cuve : tCuve)
			pw.println(cuve.getCapacite() + "\t" + cuve.getPosX() + "\t" + cuve.getPosY() + "\t" + cuve.getPosInfo());

		/*-- Création du fichier des tubes  --*/

		double[][] tabLien = new double[tCuve.size()][tCuve.size()];

		//Génération de la matrice des coûts

		for (Tube tube : tTube)
		{
			tabLien[tube.getCuve1().getIdentifiant() - 'A'][tube.getCuve2().getIdentifiant() - 'A'] = tube.getEpaisseur();

			tabLien[tube.getCuve2().getIdentifiant() - 'A'][tube.getCuve1().getIdentifiant() - 'A'] = tube.getEpaisseur();
		}

		//Écriture dans le fichier tube.data selon la structure choisie

		switch (structure)
		{
			case 'L':

				pw.println("liste d'adjacence");

				for (Tube tube : tTube)
					pw.println(tube.getCuve1().getIdentifiant() + "\t" + tube.getCuve2().getIdentifiant() + "\t" + tube.getEpaisseur());

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

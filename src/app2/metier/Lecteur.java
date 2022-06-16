package app2.metier;

import app2.*;

import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Lecteur
{
	private JFileChooser jfc;
	
	public Lecteur(Controleur ctrl, String cheminFichier)
	{
		try
		{
			Scanner sc = new Scanner ( new FileReader ( cheminFichier ) );

			String structure = "";

			int ligne = 0;

			while ( sc.hasNextLine() )
			{
				while ( ! (structure.equals("liste d'adjacence") || structure.equals("matrice de cout") || structure.equals("matrice de cout optimisee")))
				{
					String[] tabS = sc.nextLine().split("\t");

					if (tabS[0].equals("liste d'adjacence") || tabS[0].equals("matrice de cout") || tabS[0].equals("matrice de cout optimisee"))
						structure = tabS[0];
					else
						ctrl.creerCuve(Integer.parseInt(tabS[0]), Integer.parseInt(tabS[1]), Integer.parseInt(tabS[2]), tabS[3]);
				}

				String[] tabS = sc.nextLine().split("\t");

				if (structure.equals("liste d'adjacence"))
				{
					ctrl.creerTube(ctrl.getCuves().get(tabS[0].charAt(0) - 'A'), ctrl.getCuves().get(tabS[1].charAt(0) - 'A'), Double.parseDouble(tabS[2]) );
				}
				else
				{
					for (int i = 0; i < ligne; i++)
					{
						if (!tabS[i].equals("0"))
							ctrl.creerTube(ctrl.getCuves().get(ligne), ctrl.getCuves().get(i), Double.parseDouble(tabS[i]));
					}
				}

				ligne++;
			}

		}catch (Exception e){ e.printStackTrace(); }
	}
}

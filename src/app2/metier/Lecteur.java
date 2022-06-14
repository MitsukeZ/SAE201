package app2.metier;

import app2.*;

import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.util.ArrayList;

public class Lecteur
{
	private Controleur ctrl;

	public Lecteur(Controleur ctrl)
	{
		this.ctrl = ctrl;

		try
		{
			Scanner sc = new Scanner ( new FileReader ( "cuve.data" ) );

			while ( sc.hasNextLine() )
			{
				String[] tabS = sc.nextLine().split("\t");

				ctrl.creerCuve(Integer.parseInt(tabS[0]), Integer.parseInt(tabS[1]), Integer.parseInt(tabS[2]), tabS[3]);
			}
		}catch (Exception e){ e.printStackTrace(); }

		try
		{
			Scanner sc = new Scanner ( new FileReader ( "tube.data" ) );

			String structure = sc.nextLine();

			int ligne = 0;

			while ( sc.hasNextLine() )
			{
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

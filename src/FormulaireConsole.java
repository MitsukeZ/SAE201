import iut.algo.Clavier;
import java.util.ArrayList;

public class FormulaireConsole 
{
	public static void main(String[] args) 
	{
		//Variables
		int nbCuves = 0, capaciteTmp, posXTmp, posYTmp;
		String positionInfo;
		Cuve[] tabCuves;
		ArrayList<Tube> listeTubes = new ArrayList<Tube>();

		//Instructions
		do
		{
			System.out.print("Veuillez entrer le nombre de Cuves a generer : ");
			nbCuves = Clavier.lire_int();

			if (nbCuves < 0) 
			{
				System.out.println("La valeur entree n'est pas valide");
			}
		}
		while (nbCuves < 0);
		tabCuves = new Cuve[nbCuves];

		for (int i = 1; i < nbCuves + 1; i++) 
		{
			System.out.print("Veuillez entrer la capacite de la Cuve "+ i +" : ");
			capaciteTmp = Clavier.lire_int();

			System.out.print("Veuillez entrer la position X de la Cuve "+ i +" : ");
			posXTmp = Clavier.lire_int();

			System.out.print("Veuillez entrer la position Y de la Cuve "+ i +" : ");
			posYTmp = Clavier.lire_int();

			System.out.print("Veuillez entrer la position des informations de la Cuve "+ i +" : ");
			positionInfo = Clavier.lireString();

			tabCuves[i-1] = Cuve.fabrique(capaciteTmp, posXTmp, posYTmp);
		}
	}
}

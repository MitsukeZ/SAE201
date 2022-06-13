import java.time.chrono.ThaiBuddhistChronology;

public class Controleur 
{
	private Reseau		metier;
	private FrameReseau ihm;

	public Controleur()
	{
		this.metier = new Reseau(this);
		this.ihm	= new FrameReseau(this);
	}

	public void passerAuTourSuivant()
	{
		this.metier.passerAuTourSuivant();
	}

	public static void main(String[] args) 
	{
		new Controleur();	
	}
}

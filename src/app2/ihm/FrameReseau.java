import javax.swing.*;
import app2.Controleur;

public class FrameReseau extends JFrame
{
	private PanelReseau    panelReseau;
	private PanelBtnPasser panelBtnPasser;

	public FrameReseau(Controleur ctrl)
	{
		this.panelReseau 	= new PanelReseau();
		this.panelBtnPasser = new PanelBtnPasser();

		this.setSize(700, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
package AStar;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AStar extends JFrame{
	static AStarPanel MainPanel = new AStarPanel();
	public AStar() {
		setSize(950,640);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setFocusable(false);
		setLocationRelativeTo(null);
		add(MainPanel);
		add(new SidePanel());
	}
	public AStarPanel getMainPanel() {
		return MainPanel;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AStar();
	}

}

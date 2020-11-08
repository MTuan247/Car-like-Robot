package AStar;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AStar extends JFrame{
	public AStar() {
		setSize(1000,640);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
//		setResizable(false);
		setFocusable(false);
		setLocationRelativeTo(null);
		add(new AStarPanel());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AStar();
	}

}

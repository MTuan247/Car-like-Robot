package AStar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SidePanel extends JPanel {
	AStarPanel MainPanel = AStar.MainPanel;
	Node startNode = MainPanel.startNode;
	Node endNode = MainPanel.endNode;
	JButton start = new JButton("START");
	JButton reset = new JButton("RESET");
	public SidePanel() {
		this.setBackground(Color.BLUE);
		setLayout(null);
		setBounds(800,0,200,600);
//		setSize(200,600);
		setFocusable(true);
		setVisible(true);
		reset.setBounds(50, 100, 100, 25);
		reset.setVisible(true);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.hybridAstar.stopTimer();
			}
		});
		add(reset);
		start.setBounds(50, 50, 100, 25);
		start.setVisible(true);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Distance(endNode);
				MainPanel.hybridAstar.obstacleDistance = new ObstacleDistance();
				MainPanel.hybridAstar.setTimer(startNode,endNode);
			}
		});
		add(start);
	}
}

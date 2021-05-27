package AStar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SidePanel extends JPanel{
	AStarPanel MainPanel = AStar.MainPanel;
	Node startNode = MainPanel.startNode;
	Node endNode = MainPanel.endNode;
	JButton start = new JButton("START");
	JButton choseStart = new JButton("S");
	JButton reset = new JButton("RESET");
	JButton choseEnd = new JButton("G");
	JButton Obstacle = new JButton("OBSTACLE");
	static JLabel startL = new JLabel("",JLabel.CENTER);
	static JLabel endL = new JLabel("",JLabel.CENTER);
	public SidePanel() {
		this.setBackground(Color.DARK_GRAY);
		setLayout(null);
		setBounds(800,0,150,600);
		setFocusable(true);
		setVisible(true);
		reset.setBounds(5, 50, 100, 25);
		reset.setVisible(true);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.hybridAstar.stopTimer();
			}
		});
		add(reset);
		start.setBounds(5, 10, 100, 25);
		start.setVisible(true);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.mouseMode = 0;
				new Distance(endNode);
				MainPanel.hybridAstar.obstacleDistance = new ObstacleDistance();
				MainPanel.hybridAstar.setTimer(startNode,endNode);
			}
		});
		add(start);
		Obstacle.setBounds(5, 90, 100, 25);
		Obstacle.setVisible(true);
		Obstacle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.mouseMode = 1;
			}
		});
		add(Obstacle);
		choseStart.setBounds(105, 130, 30, 20);
		choseStart.setVisible(true);
		choseStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.mouseMode = 2;
			}
		});
		add(choseStart);
		startL.setText(""+ startNode.getX() + "  |  "+startNode.getY());
		startL.setBounds(5, 130, 100, 20);
		startL.setOpaque(true);
		startL.setBackground(Color.WHITE);
		startL.setForeground(Color.BLACK);
		add(startL);
		choseEnd.setBounds(105, 170, 30, 20);
		choseEnd.setVisible(true);
		choseEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.mouseMode = 3;
			}
		});
		add(choseEnd);
		endL.setText(""+ endNode.getX() + "  |  "+endNode.getY());
		endL.setBounds(5, 170, 100, 20);
		endL.setOpaque(true);
		endL.setBackground(Color.WHITE);
		endL.setForeground(Color.BLACK);
		add(endL);
	}
}

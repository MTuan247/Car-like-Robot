package AStar;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AStarPanel extends JPanel implements MouseListener{
	static TileMap tileMap = new TileMap("tilemap.txt");//file chua ma tran de tao gridmap
	private Tile[][] tile = tileMap.getTile(); //mang cac tile coi nhu 1 nut
	JButton start = new JButton();
	Node startNode = new Node(500,500); Node endNode = new Node(300,300);
	HybridAstar hybridAstar = new HybridAstar(startNode,endNode);
	public AStarPanel() {
		setLayout(null);
		setSize(1000,600);
		setFocusable(true);
		setVisible(true);
		addMouseListener(this);
		Timer timer = new Timer (200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		timer.start();
		start.setText("START");
		start.setBounds(850, 50, 100, 25);
		start.setVisible(true);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Distance(endNode);
				hybridAstar.setTimer(startNode,endNode);
			}
		});
		add(start);
	}
	//ve len frame
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		tileMap.draw(g);
		tileMap.drawBox(g);
		hybridAstar.drawPath2(g);
		hybridAstar.drawPath(g);
	}
	public void mouseClicked(MouseEvent event) {
		System.out.println("Clicked at [" + event.getX() + ", " + event.getY() + "]");
		int x = event.getX();int y = event.getY();//x la chieu ngang, y la chieu doc
		x/=25;y/=25;
		if(tile[y][x].getNum()==0) {
			tile[y][x].setNum(1);
		} else tile[y][x].setNum(0);
	}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
}

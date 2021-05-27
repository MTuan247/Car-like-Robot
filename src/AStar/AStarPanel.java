package AStar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AStarPanel extends JPanel implements MouseListener{
	static int height = 600;
	static int width = 800;
	static int mouseMode = 0;
	static TileMap tileMap = new TileMap();//file chua ma tran de tao gridmap
	private Tile[][] tile = tileMap.getTile(); //mang cac tile coi nhu 1 nut
	HybridAstar hybridAstar = new HybridAstar();
	Node startNode = new Node(500,500,0); 
	Node endNode = new Node(300,300,Math.PI/2);
	static ObstacleDistance obstacleDistance = new ObstacleDistance();
	public AStarPanel() {
		setLayout(null);
		setSize(width,height);
		setFocusable(true);
		setVisible(true);
		addMouseListener(this);
		Timer timer = new Timer (200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		timer.start();
	}
	//ve len frame
	@Override
	protected void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2;
		super.paintComponent(g);
		tileMap.draw(g);
		tileMap.drawBox(g);
		hybridAstar.drawClosePath(g);
		hybridAstar.drawPath(g);
//		hybridAstar.drawEndT(g);
		drawNode(g);
	}
	public void mouseClicked(MouseEvent event) {
		System.out.println("Clicked at [" + event.getX() + ", " + event.getY() + "]");
		int x = event.getX();int y = event.getY();//x la chieu ngang, y la chieu doc
		if(mouseMode==1) {
			x/=25;y/=25;
			if(tile[y][x].getNum()==0) {
				tile[y][x].setNum(1);
			} else tile[y][x].setNum(0);
		} else if (mouseMode==2) {
			startNode.setX(x);
			startNode.setY(y);
			SidePanel.startL.setText(""+ x + "  |  "+y);
		} else if (mouseMode==3) {
			endNode.setX(x);
			endNode.setY(y);
			SidePanel.endL.setText(""+ x + "  |  "+y);
		}
		
	}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
	public void drawNode(Graphics2D g) {
		Stroke stroke = new BasicStroke(2f);
		g.setStroke(stroke);
		int s_x = startNode.getX();
		int s_y = startNode.getY();
		int g_x = endNode.getX();
		int g_y = endNode.getY();
		g.setColor(Color.RED);
		g.drawLine(s_x-5,s_y-5,s_x+5,s_y+5);
		g.drawLine(s_x+5,s_y-5,s_x-5,s_y+5);
		g.drawLine(g_x-5,g_y-5,g_x+5,g_y+5);
		g.drawLine(g_x+5,g_y-5,g_x-5,g_y+5);
	}
}

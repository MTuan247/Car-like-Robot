package AStar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.Timer;

public class HybridAstar {
	LinkedList <Node> path = new LinkedList<>();
	LinkedList <Node> open = new LinkedList<>();
	LinkedList <Node> close = new LinkedList<>();
//	LinkedList <Node> endT = new LinkedList<>();
//	double steeringAngle[] = {0,Pi/18,-Pi/18,Pi/9,-Pi/9,Pi/6,-Pi/6};
	double steeringAngle[] = {0,Pi/12,-Pi/12,Pi/6,-Pi/6};
	static final double Pi = Math.PI;
	static final double v = 8;
	static final double l = 20;
	AStarPanel MainPanel = AStar.MainPanel;
	ObstacleDistance obstacleDistance = AStarPanel.obstacleDistance;
	Timer AStimer;
	Node temp = null;
	boolean stop = false;
	public HybridAstar() {	
	}
	public void setTimer(Node start,Node end) {
//		endT(end);
		open.add(start);
		start.setG(0);
		start.f(end);
		AStimer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(open.peek() != null && !stop) {
					if (open.peek()==null) stop = true;
					temp = getMin();
//					if(temp.distance(end)<v) {
					if(temp.compareEnd(end)) {
						stop = true;
						addPath(temp);
						path.add(end);
					}
//					for(int i = 0;i<endT.size();i++) {
//						if(temp.compareEnd(endT.get(i))) {
//							stop = true;
//							addPath(temp);
//							path.add(end);
//						}
//					}
					addNode(temp,end);
					close.add(temp);
					open.remove(temp);
				}
			}
		});
		AStimer.start();
	}
	public void stopTimer() {
		open.clear();
		close.clear();
		path.clear();
		AStimer.stop();
		stop = false;
	}
	public void addNode(Node temp,Node end) {
		Node next;
		for(int i = 0;i<steeringAngle.length;i++) {
			next = temp.next(steeringAngle[i]);
			if (checkNotContains(next) && obstacleDistance.getMinDistance(next)>20) {
//			if (obstacleDistance.getMinDistance(next)>20) {
				calculate(temp,next,end);
				open.add(next);
			}
		}
	}
//	public void endT(Node end) {
//		Node tmp_end = end;
//		reverseTheta(tmp_end);
//		Node next;
//		Node nextJ;
//		for(int i = 0;i<steeringAngle.length;i++) {
//			next = tmp_end.next(steeringAngle[i]);
//			for(int j = 0;j<steeringAngle.length;j++) {
//				nextJ = next.next(steeringAngle[i]);
//				reverseTheta(nextJ);
//				next.setPrev(next);
//				endT.add(nextJ);
//			}
//			reverseTheta(next);
//			next.setPrev(end);
//			endT.add(next);
//		}
//	}
	public void reverseTheta(Node tmp) {
		double theta = tmp.getTheta();
		if(theta > 0) theta -= Pi;
		else theta +=Pi;
		tmp.setTheta(theta);
	}
	public Node getMin() {//lay node co f nho nhat
		Node min = open.peek();
		for(int i = 1;i<open.size();i++) {
			if(min.getF()>open.get(i).getF()) {
				min = open.get(i);
			}
		}
		return min;
	}
	public void addPath(Node end){//danh dau duong di tim duoc
		Node temp = end;
		while(temp.getPrev()!=null) {
			path.add(temp);
			temp = temp.getPrev();
		}
	}
	public void calculate(Node temp,Node next,Node end) {
		next.setG(temp.getG()+v);					//tinh toan g(x)
		next.f(end);								//tinh toan f(x)
		next.setPrev(temp);							//dat tile phia truoc la temp de co the de dang ve path
	}
	public boolean checkNotContains(Node temp) {//kiem tra node co trong open hoac close
		if(temp == null) return false;
		for(int i = 1;i<open.size();i++) {
			if(open.get(i).compareNode(temp)) return false;
		}
		for(int i = 1;i<close.size();i++) {
			if(close.get(i).compareNode(temp)) return false;
		}
		return true;
	}
	static public void draw(Node start,Node end,Graphics2D g) {
		g.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
	}
	public void drawPath(Graphics2D g) {
		Stroke stroke = new BasicStroke(2f);
		g.setStroke(stroke);
		for(int i =0;i<path.size();i++) {
			if(path.get(i).getPrev()!=null) {
//				drawArrow(g,path.get(i));
				g.setColor(Color.GREEN);
				draw(path.get(i),path.get(i).getPrev(),g);
			}
		}
	}
	public void drawClosePath(Graphics2D g) {
		g.setColor(new Color(205,133,63));
		for(int i =0;i<close.size();i++) {
			if(close.get(i).getPrev()!=null) {
				draw(close.get(i),close.get(i).getPrev(),g);
			}
		}
	}
//	public void drawEndT(Graphics2D g) {
//		System.out.print(endT.size());
//		for(int i =0;i<endT.size();i++) {
//			g.setColor(Color.GREEN);
//			draw(endT.get(i),endT.get(i).getPrev(),g);
//		}
//	}
	public void drawArrow(Graphics2D g, Node node) {
		int x = node.getX();
		int y = node.getY();
		double theta = node.getTheta();
		
		int t_x = (int)(x + Math.cos(theta)*v*5);
		int t_y = (int)(y + Math.sin(theta)*v*5);
		
		g.setColor(Color.ORANGE);
		g.drawLine(x, y, t_x, t_y);
	}
}

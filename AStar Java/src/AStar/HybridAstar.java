package AStar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.Timer;

public class HybridAstar {
	LinkedList <Node> path = new LinkedList<>();
	LinkedList <Node> open = new LinkedList<>();
	LinkedList <Node> close = new LinkedList<>();
	double steeringAngle[] = {0,Pi/18,-Pi/18,Pi/9,-Pi/9,Pi/6,-Pi/6};
	static final double Pi = Math.PI;
	static final double v = 4;
	static final double l = 5;
	AStarPanel MainPanel = AStar.MainPanel;
	ObstacleDistance obstacleDistance = new ObstacleDistance();
	Timer AStimer;
	Node temp = null;
	boolean stop = false;
	public HybridAstar(Node start,Node end) {
		
	}
	public void setTimer(Node start,Node end) {
		open.add(start);
		start.setG(0);
		start.f(end);
		AStimer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(open.peek() != null && !stop) {
					if (open.peek()==null) stop = true;
					temp = getMin();
//					if(temp.getInTile(MainPanel.tileMap).compare(end.getInTile(MainPanel.tileMap))) {
					if(temp.distance(end)<4) {
						stop = true;
						addPath(temp);
					}
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
				calculate(temp,next,end);
				open.add(next);
			}
		}
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
	public void draw(Node start,Node end,Graphics g) {
		g.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
	}
	public void drawPath(Graphics g) {
		g.setColor(Color.GREEN);
		for(int i =0;i<path.size();i++) {
			if(path.get(i).getPrev()!=null) draw(path.get(i),path.get(i).getPrev(),g);
		}
	}
	public void drawPath2(Graphics g) {
		g.setColor(Color.red);
		for(int i =0;i<close.size();i++) {
			if(close.get(i).getPrev()!=null) draw(close.get(i),close.get(i).getPrev(),g);
		}
	}
}

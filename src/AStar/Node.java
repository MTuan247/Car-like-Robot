package AStar;

import java.util.LinkedList;

public class Node {
	AStarPanel MainPanel = AStar.MainPanel;
	ObstacleDistance obstacleDistance = AStarPanel.obstacleDistance;
	private int x,y;
	private double theta;
	private double f,h;
	private double g;
	double v = HybridAstar.v;
	double l = HybridAstar.l;
	double Pi = HybridAstar.Pi;
	private double minDeltaTheta = v*Math.tan(Pi/18)/l;
	private double maxDeltaTheta = v*Math.tan(Pi/6)/l;
	private Node Prev = null;
	public Node(int x,int y) {
		this.x = x;
		this.y = y;
		this.theta = 0;
	}
	public Node(int x,int y, double theta) {
		this.x=x;
		this.y=y;
		this.theta = theta;
	}
	public Node(int x,int y, double theta,Node prev) {
		this.x=x;
		this.y=y;
		this.theta = theta;
		this.Prev = prev;
	}
	public Node next(double steeringAngle) {//tinh x,y,theta cua diem tiep theo
		Node node = null;
		double deltaTheta = v*Math.tan(steeringAngle)/l;
		double nextTheta = (theta + deltaTheta);
		if(nextTheta >= Pi) nextTheta-=2*Pi;
		if(nextTheta < -Pi) nextTheta+=2*Pi;
		int nextX = (int) (x + v*Math.cos(theta));
		int nextY = (int) (y - v*Math.sin(theta));
		if((nextX<800)&&(nextY<600)&&(nextX>0)&&(nextY>0)) {
			node = new Node(nextX,nextY,nextTheta,this);
		}
		return node;
	}
	public Tile getInTile(TileMap tileMap) {
		int i = y/tileMap.TileHeight;
		int j = x/tileMap.TileWidth;
		return tileMap.tile[i][j];
	}
	public double h(Node end) {//tinh h(X)
		double d = distance(end);
		double obD = obstacleDistance.getMinDistance(this);
		if(obD>100) obD = 0;
		if (d<100) h= Math.sqrt(0.8*d*d+0.2*Math.pow(theta-end.theta,2));
		else h = d + this.getInTile(MainPanel.tileMap).getD() - obD;
//		h=d + this.getInTile(MainPanel.tileMap).getD() - obD;
		return h;
	}
//	public double h(Node end) {
//		double d = distance(end);
////		double obD = obstacleDistance.getMinDistance(this);
////		if(obD>100) obD = 0;
//		h = DubinsDistance(this, end);
//		return h;
//	}
	public double distance(Node end) {//tinh h(X)
		int X = end.getX() - x;
		int Y = end.getY() - y;
		return Math.sqrt(X*X+Y*Y);
	}
	public boolean compareNode(Node node) {
		double deltaTheta = Math.abs(node.getTheta()-theta);
		if (distance(node)<3 && deltaTheta<minDeltaTheta) {
			return true;
		}
		return false;
	}
	public boolean compareEnd(Node node) {
		double deltaTheta = Math.abs(node.getTheta()-theta);
		if (distance(node)<v && deltaTheta<maxDeltaTheta) {
			return true;
		}
		return false;
	}
	public double f(Node end) {
		double d = distance(end);
//		if (d<40) f = h(end);
//		else f = g + h(end);
		f = g + h(end);
		return f;
	}
	public void setG(double g) {
		this.g = g;
	}
	public double getG() {
		return g;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getF() {
		return f;
	}
	public void setF(double f) {
		this.f = f;
	}
	public Node getPrev() {
		return Prev;
	}
	public void setPrev(Node prev) {
		Prev = prev;
	}
	public double getTheta() {
		return theta;
	}
	public void setTheta(double alpha) {
		this.theta = alpha;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
										//TEST
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public double DubinsDistance(Node tmp,Node endNode) {
		LinkedList <Node> start = new LinkedList<>();
		LinkedList <Node> goal = new LinkedList<>();
		Node next = endNode.next(Pi/6);
		while (next.distance(endNode)>7) {
			goal.add(next);
			next = next.next(Pi/6);
		}
		next = endNode.next(-Pi/6);
		while (next.distance(endNode)>7) {
			goal.add(next);
			next = next.next(-Pi/6);
		}
		next = tmp.next(Pi/6);
		while (next.distance(endNode)>7) {
			start.add(next);
			next = next.next(Pi/6);
		}
		next = tmp.next(-Pi/6);
		while (next.distance(endNode)>7) {
			start.add(next);
			next = next.next(-Pi/6);
		}
		return distance(tmp,endNode,start,goal);
	}
	public double distance(Node tmp,Node endNode,LinkedList<Node> start,LinkedList<Node> goal) {
		double min=99999;
		double d=0;
		for(int i =0;i<start.size();i++) {
			for(int j=0;j<goal.size();j++) {
				if(Math.abs(start.get(i).getTheta()-goal.get(j).getTheta())<Pi/12) {
					d = start.get(i).distance(goal.get(j)) + distanceInR(tmp,start.get(i)) + distanceInR(goal.get(j),goal.getLast())+8;
					if(d<min) min = d;
				}
			}
		}
		return min;
		
	}
	public double distanceInR(Node tmp1,Node tmp2) {
		double d = 0;
		while(tmp2.getPrev()!= null) {
			tmp2 = tmp2.getPrev();
			d+=8;
		}
		if(tmp2==tmp1) {
			return d;
		}
		return 99999;
	}
}

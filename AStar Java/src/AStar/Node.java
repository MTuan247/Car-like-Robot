package AStar;

public class Node {
	AStarPanel MainPanel = AStar.MainPanel;
	private int x,y;
	private double theta;
	private double f,h;
	private double g;
	double v = HybridAstar.v;
	double l = HybridAstar.l;
	double Pi = HybridAstar.Pi;
	private double maxDeltaTheta = v*Math.tan(Pi/6)/l;
	private Node Prev = null;
	public Node(int x,int y) {
		this.x = x;
		this.y = y;
		this.theta = 0;
	}
	public Node(int x,int y, double alpha) {
		this.x=x;
		this.y=y;
		this.theta = alpha;
	}
	public Node(int x,int y, double alpha,Node prev) {
		this.x=x;
		this.y=y;
		this.theta = alpha;
		this.Prev = prev;
	}
//	public Node next(double v, int l, double steeringAngle) {
//		Node node = null;
//		double theta = v*Math.tan(steeringAngle)/l;
//		double nextAlpha = (alpha + theta);
//		if(nextAlpha > 3.14) nextAlpha-=3.14;
//		if(nextAlpha < -3.14) nextAlpha+=3.14;
//		int nextX = (int) (x + v*Math.cos(alpha + theta));
//		int nextY = (int) (y + v*Math.sin(alpha + theta));
//		if((nextX<800)&&(nextY<600)&&(nextX>0)&&(nextY>0)) {
//			node = new Node(nextX,nextY,nextAlpha,this);
//		}
//		return node;
//	}
	public Node next(double steeringAngle) {//tinh x,y,theta cua diem tiep theo
		Node node = null;
		double deltaTheta = v*Math.tan(steeringAngle)/l;
		double nextTheta = (theta + deltaTheta);
		if(nextTheta > Pi) nextTheta-=2*Pi;
		if(nextTheta < -Pi) nextTheta+=2*Pi;
		int nextX = (int) (x + v*Math.cos(theta));
		int nextY = (int) (y + v*Math.sin(theta));
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
		int endX = end.getX();
		int endY = end.getY();
		h = Math.sqrt((endX - x)*(endX -x)+(endY-y)*(endY-y));
		return h;
	}
	public double distance(Node end) {//tinh h(X)
		int endX = end.getX();
		int endY = end.getY();
		h = Math.sqrt((endX - x)*(endX -x)+(endY-y)*(endY-y));
		return h;
	}
	public boolean compareNode(Node node) {
		int deltaX = Math.abs(node.getX()-x);
		int deltaY = Math.abs(node.getY()-y);
		double deltaAlpha = Math.abs(node.getTheta()-theta);
		if (deltaX<4 && deltaY<4 && deltaAlpha<Math.PI/20) {
			return true;
		}
		return false;
	}
	public double f(Node end) {
		f = g + h(end)+this.getInTile(MainPanel.tileMap).getD();
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
	
}

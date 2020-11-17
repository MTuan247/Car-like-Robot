package AStar;

public class Tile {
	private int Height = 25,Width = 25;
	private int num,i,j;
	private double d=0;
	
	public Tile(int num,int Height,int Width) {
		this.num = num;
		this.Height = Height;
		this.Width = Width;
	}
	//method kiem tra tile nay co the di vao k
	public boolean checkMovable() {
		if(num==0) return true;
		return false;
	}
	//method so sanh ( dung de kiem tra xem tile co phai dich ko)
	public boolean compare(Tile goal) {
		if((this.getI()==goal.getI())&&(this.getJ()==goal.getJ())) return true;
		return false;
	}
	public double getD() {
		return d;
	}
	public void setD(double d) {
		this.d = d;
	}
	public void setNum(int i) {
		this.num = i;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int J) {
		this.j = J;
	}
	public int getNum() {
		return num;
	}
	public int getHeight() {
		return Height;
	}
	public void setHeight(int height) {
		Height = height;
	}
	public int getWidth() {
		return Width;
	}
	public void setWidth(int width) {
		Width = width;
	}
}

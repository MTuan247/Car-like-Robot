package AStar;

import java.util.LinkedList;

public class Distance {//Tinh khoang cach tu diem goal den cac node
	int d = 50;
	Tile end;
	AStarPanel MainPanel = AStar.MainPanel;
	TileMap tileMap = MainPanel.tileMap;
	private Tile[][] tile = tileMap.getTile();
	LinkedList <Tile> open = new LinkedList<>();
	LinkedList <Tile> close = new LinkedList<>();
	public Distance(Node goal) {
		end = goal.getInTile(tileMap);
		Tile temp = null;
		open.add(end);
		while(open.peek()!=null) {
			temp = open.poll();
			close.add(temp);
			addNeighbor(temp);
		}
	}
	public boolean checkNotContains(Tile temp) {
		if(temp == null) return false;
		if(open.contains(temp) || (close.contains(temp))) return false;
		return true;
	}
	public void addNeighbor(Tile temp) {
		Tile left = leftTile(temp);
		Tile right = rightTile(temp);
		Tile up = upTile(temp);
		Tile down = downTile(temp);
		if(checkNotContains(left) && left.checkMovable()) {
			open.add(left);
			left.setD(temp.getD()+d);
		}
		if(checkNotContains(right) && right.checkMovable()) {
			open.add(right);
			right.setD(temp.getD()+d);
		}
		if(checkNotContains(up) && up.checkMovable()) {
			open.add(up);
			up.setD(temp.getD()+d);
		}
		if(checkNotContains(down) && down.checkMovable()) {
			open.add(down);
			down.setD(temp.getD()+d);
		}
	}
	public Tile leftTile(Tile temp) {
		if(temp.getJ()>0) return tile[temp.getI()][temp.getJ()-1];
		return null;
	}
	public Tile rightTile(Tile temp) {
		if(temp.getJ()<31) return tile[temp.getI()][temp.getJ()+1];
		return null;
	}
	public Tile upTile(Tile temp) {
		if(temp.getI()>0) return tile[temp.getI()-1][temp.getJ()];
		return null;
	}
	public Tile downTile(Tile temp) {
		if(temp.getI()<23) return tile[temp.getI()+1][temp.getJ()];
		return null;
	}
}

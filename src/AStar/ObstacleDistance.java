package AStar;

import java.util.LinkedList;

public class ObstacleDistance {
	TileMap tileMap = AStar.MainPanel.tileMap;
	Tile tile[][] = tileMap.getTile();
	LinkedList <Node> ObNode = new LinkedList<>();
	int row = tileMap.row;
	int col = tileMap.col;
	int TileHeight = tileMap.TileHeight;
	int TileWidth = tileMap.TileWidth;
	int mapHeight = row*TileHeight;
	int mapWidth = col*TileWidth;
	public ObstacleDistance() {
		for(int x = 0;x<mapWidth;x+=10) {
			ObNode.add(new Node(x,0));
			ObNode.add(new Node(x,mapHeight));
		}
		for(int y = 0;y < mapHeight;y+=10) {
			ObNode.add(new Node(0,y));
			ObNode.add(new Node(mapWidth,y));
		}
		NodeFromOb();
	}
	public void NodeFromOb() {
		for(int i=0;i<row;i++) {
			for(int j =0;j<col;j++) {
				if (!tile[i][j].checkMovable()) {
					int x = j*TileWidth;
					int y = i*TileHeight;
					ObNode.add(new Node(x,y));
					ObNode.add(new Node(x+TileWidth,y));
					ObNode.add(new Node(x,y+TileHeight));
					ObNode.add(new Node(x+TileWidth,y+TileHeight));
					
				}
			}
		}
	}
	public double getMinDistance(Node node) {
		int size = ObNode.size();
		double min = ObNode.get(0).distance(node);
		for(int i=1;i<size;i++) {
			double d = ObNode.get(i).distance(node);
			if(d<min) {
				min = d;
			}
		}
		return min;
	}
}

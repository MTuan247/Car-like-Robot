package AStar;

import java.awt.Color;
import java.awt.Graphics2D;

public class TileMap {
	//row la hang, col la cot
	int TileWidth=25;
	int TileHeight=25;
	int row = AStarPanel.height/TileHeight;
	int col = AStarPanel.width/TileWidth;
	Tile tile[][]= new Tile [row][col];
	//tao map chua cac tile 
	//thong tin map lay tu file
	public TileMap() {
		for(int i =0; i<row; i++) {
		for(int j=0;j<col;j++) {
			tile[i][j]= new Tile(0,TileHeight,TileWidth);
			tile[i][j].setJ(j);
			tile[i][j].setI(i);
			tile[i][j].setHeight(TileHeight);
			tile[i][j].setWidth(TileWidth);
		}
	}
	}
	//i la hang, i la chieu doc
	//j la cot, j la chieu ngang
	//Method ve len Panal
	public void drawBox(Graphics2D g) {
		g.setColor(Color.GRAY);
		for (int i=0;i<col;i++) {
			g.drawLine(i*TileWidth, 0, i*TileWidth, row*TileWidth);
		}
		for (int j=0;j<row;j++) {
			g.drawLine(0, j*TileHeight, col*TileHeight, j*TileHeight);
		}
	}
	public Tile[][] getTile() {
		return tile;
	}
	public void draw(Graphics2D g) {
		for(int i=0; i <row;i++) {
			for(int j =0;j<col;j++) {
				int rc = tile[i][j].getNum();
				if(rc==1) g.setColor(Color.BLACK);
				if(rc==0) g.setColor(Color.WHITE);
				g.fillRect(j*TileWidth,i*TileHeight,TileWidth,TileHeight);
			}
		}
	}
}

package AStar;

import java.awt.Color;
import java.awt.Graphics;

public class TileMap {
	int row = 24; int col = 32; //row la hang, col la cot
	Tile tile[][]= new Tile [row][col];
	int TileWidth=25;
	int TileHeight=25;
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
	public void drawBox(Graphics g) {
		g.setColor(Color.BLUE);
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
	public void draw(Graphics g) {
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

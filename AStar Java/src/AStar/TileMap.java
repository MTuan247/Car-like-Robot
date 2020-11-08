package AStar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
public class TileMap {
	int TileWidth=25;
	int TileHeight=25;
	int row = 24; int col = 32; //row la hang, col la cot
	Tile tile[][]= new Tile [row][col];
	public Tile[][] getTile() {
		return tile;
	}
	//tao map chua cac tile 
	//thong tin map lay tu file
	public TileMap(String s) {
		for(int i =0; i<row; i++) {
		for(int j=0;j<col;j++) {
			tile[i][j]= new Tile(0,TileHeight,TileWidth);
			tile[i][j].setJ(j);
			tile[i][j].setI(i);
		}
	}
	}
	//i la hang, i la chieu doc
	//j la cot, j la chieu ngang
	//Method ve len Panal
	public void drawBox(Graphics g) {
		g.setColor(Color.BLUE);
		for (int i=0;i<col;i++) {
			g.drawLine(i*25, 0, i*25, col*25);
		}
		for (int j=0;j<row;j++) {
			g.drawLine(0, j*25, col*25, j*25);
		}
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

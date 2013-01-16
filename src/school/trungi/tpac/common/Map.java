package school.trungi.tpac.common;

import java.io.FileInputStream;
import java.util.Scanner;

import android.graphics.Bitmap;

public class Map {
	
	Box[][] map;
	int m, n;
	private BoxTypes boxes = null;
	
	public Map(int m, int n) {
		map = new Box[m][n];
		this.m = m;
		this.n = n;
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				map[i][j] = new Box();
			}
		}
	}
	
	public Map(FileInputStream stream) {
			Scanner in = new Scanner(stream);
			
			m = Integer.parseInt(in.next());
			n = Integer.parseInt(in.next());
		
			map = new Box[m][n];
		
			for (int i=0; i<m; i++) {
				for (int j=0; j<n; j++) {
					map[i][j] = new Box(Integer.parseInt(in.next()));
				}
			}
	}
	

	public void setBoxes(BoxTypes _boxes) {
		boxes = _boxes;
	}
	
	public Box get(int i, int j) {
		return map[i][j];
	}
	
	public int getM() {
		return this.m;
	}
	
	public int getN() {
		return this.n;
	}

	public Bitmap getBitmap(int i, int j) {
		int index = map[i][j].item;
		
		if (index != BoxTypes.INVISIBLE_BOX) {
			return boxes.bitmaps[index];
		} else {
			return null;
		}
	}
	
	public void set(int i, int j, Box b) {
		map[i][j] = b;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(Integer.toString(m)).append("\n");
		str.append(Integer.toString(n)).append("\n");
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				str.append(map[i][j].toString()).append("\n");
			}
			str.append('\n');
		}
		
		return str.toString();
	}
}

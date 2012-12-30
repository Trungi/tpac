package common;

public class Map {
	
	Box[][] map;
	int m, n;
	
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
	
	public Box get(int i, int j) {
		return map[i][j];
	}
	
	public void set(int i, int j, Box b) {
		map[i][j] = b;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(Integer.toString(m)).append(" ");
		str.append(Integer.toString(n)).append("\n");
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				str.append(map[i][j].toString());
			}
		}
		
		return str.toString();
	}
}

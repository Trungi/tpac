package school.trungi.tpac.mooveable;

import java.util.Random;

import school.trungi.tpac.R;
import school.trungi.tpac.common.Map;
import android.content.res.Resources;

public class PinkGhost extends Ghost {
	protected Pacman pacman;
	protected Random generator;
	
	public PinkGhost(int x, int y, int m, int n, int size,
			Resources resources, Map map, Pacman pacman) {
		super(x, y, m, n, size, resources, map);
		
		this.setBitMap(R.drawable.pinkghost);
		this.pacman = pacman;
		generator = new Random(System.nanoTime());
	}
	
	int calls = 0;
	boolean nochange = false;
	@Override
	public void computeDirection() {
		int x = posX, y = posY;
		int dir = Math.abs(generator.nextInt()) % 4;
		
		countPos();
		int i = 0;
		while (x == posX && y == posY && i<5) {
			posX = x;
			posY = y;
			direction = dir;
			countPos();
			dir++;
			i++;
			if (dir > 3) dir = 0;
		}
		posX = x;
		posY = y;
	}

}

package school.trungi.tpac.mooveable;

import java.util.Random;

import school.trungi.tpac.R;
import school.trungi.tpac.common.Map;
import android.content.res.Resources;

public class GreenGhost extends Ghost {
	protected Pacman pacman;
	protected Random generator;
	
	public GreenGhost(int x, int y, int m, int n, int size,
			Resources resources, Map map, Pacman pacman) {
		super(x, y, m, n, size, resources, map);
		
		this.setBitMap(R.drawable.pacman_spawn);
		this.pacman = pacman;
		generator = new Random(System.nanoTime());
	}
	
	int calls = 0;
	boolean nochange = false;
	@Override
	public void computeDirection() {
		if (calls < 15) {
			direction = generator.nextInt() % 4;
			calls++;
		}
		
		if (pacman.getY() != posY && !nochange) {
			if (pacman.getY() > posY) {
				direction = DIRECTION_DOWN;
			} else {
				direction = DIRECTION_UP;
			}
			
			/* test if ghost moves */
			int x = posX, y = posY;
			countPos();
			if (x != posX || y != posY) {
				posX = x;
				posY = y;
				return;
			}
		}
		if (pacman.getX() != posX) {
			nochange = false;
			
			if (pacman.getX() > posX) {
				direction = DIRECTION_RIGHT;
			} else {
				direction = DIRECTION_LEFT;
			}
			
			/* test if ghost moves */
			int x = posX, y = posY;
			countPos();
			if (x != posX || y != posY) {
				posX = x;
				posY = y;
				return;
			} else {
				direction = DIRECTION_UP;
				nochange = true;
			}
		}
	}

}

package school.trungi.tpac.mooveable;

import school.trungi.tpac.R;
import school.trungi.tpac.common.Map;
import android.content.res.Resources;

public class RedGhost extends Ghost {
	protected Pacman pacman;
	
	public RedGhost(int x, int y, int m, int n, int size,
			Resources resources, Map map, Pacman pacman) {
		super(x, y, m, n, size, resources, map);
		
		this.setBitMap(R.drawable.ghost_spawn);
		this.pacman = pacman;
	}
	
	@Override
	public void computeDirection() {
		if (pacman.getY() != posY) {
			if (pacman.getY() > posY) {
				direction = DIRECTION_DOWN;
			} else {
				direction = DIRECTION_UP;
			}
			return;
		}
		if (pacman.getX() != posX) {
			if (pacman.getX() > posX) {
				direction = DIRECTION_RIGHT;
			} else {
				direction = DIRECTION_LEFT;
			}
		}
	}

}

package school.trungi.tpac.mooveable;


import school.trungi.tpac.R;
import android.content.res.Resources;

public class Pacman extends Mooveable {

	public Pacman(int x, int y, int m, int n, int size, Resources r) {
		super(x, y, m, n, size, r);
		
		this.setBitMap(R.drawable.logo);
	}

	public void computeDirection(int x, int y, int width, int height) {
		if (y < height/5) {
			direction = DIRECTION_UP;
		}
		if (y > 4*height/5) {
			direction = DIRECTION_DOWN;
		}
		if (x < width/5) {
			direction = DIRECTION_LEFT;
		}
		if (x > 4*width/5) {
			direction = DIRECTION_RIGHT;
		}
	}
	
}

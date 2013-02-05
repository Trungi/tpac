package school.trungi.tpac.mooveable;

import java.util.Random;

import school.trungi.tpac.R;
import school.trungi.tpac.common.Map;
import android.content.res.Resources;

public class Ghost extends Mooveable {
	Random generator = new Random(System.currentTimeMillis());
	
	public Ghost(int x, int y, int m, int n, int size, Resources resources, Map map) {
		super(x, y, m, n, size, resources, map);
		this.setBitMap(R.drawable.blueghost);
	}
	
	public void computeDirection() {
		if (Math.abs(System.currentTimeMillis()) % 4 == 1) {
			
			direction = Math.abs(generator.nextInt() + 
					(int)(System.nanoTime()%100)) % 4;
		}
		
	}
	
	public void move() {
		computeDirection();
		super.move();
	}
}

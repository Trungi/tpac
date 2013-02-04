package school.trungi.tpac.mooveable;


import school.trungi.tpac.R;
import school.trungi.tpac.common.Map;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Pacman extends Mooveable {

	int startX, startY;
	
	public Pacman(int x, int y, int m, int n, int size, Resources r, Map map) {
		super(x, y, m, n, size, r, map);
		
		startX = x;
		startY = y;
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
	
	protected void setBitMap(int id) {
		for (int i=0; i<4; i++) {
			images[i] = Bitmap.createBitmap(2*size, 2*size, Bitmap.Config.ARGB_8888);
        	Canvas canvas = new Canvas(images[i]);
        	canvas.rotate(90*i, images[i].getWidth()/2, images[i].getHeight()/2);
        	canvas.drawBitmap(images[i], 0, 0, null);
        	Drawable tile = resources.getDrawable(id);	
        	tile.setBounds(0, 0, 2*size, 2*size);
			tile.draw(canvas);
		}
	}

	public void reset() {
		posX = startX;
		posY = startY;
	}
	
}

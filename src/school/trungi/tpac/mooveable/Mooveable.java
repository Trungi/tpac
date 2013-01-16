package school.trungi.tpac.mooveable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public abstract class Mooveable {
	protected int posX, posY;
	protected int sizeX, sizeY;
	protected int size;
	protected int direction;
	
	protected Resources resources;
	public Bitmap image;
	
	protected final int DIRECTION_UP = 0;
	protected final int DIRECTION_DOWN = 1;
	protected final int DIRECTION_LEFT = 2;
	protected final int DIRECTION_RIGHT = 3;
	
	public Mooveable(int x, int y, int m, int n, int size, Resources resources) {
		posX = x;
		posY = y;
		sizeX = m;
		sizeY = n;
		this.size = size;
		this.resources = resources;
		direction = DIRECTION_RIGHT;
	}
	
	public void draw(Canvas canvas, Paint paint, int marginLeft, int marginTop) {
		assert(image != null);
		
		canvas.drawBitmap(image, posX*size+marginLeft, posY*size+marginTop, paint);
	}
	
	protected void setBitMap(int id) {
		image = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        Drawable tile = resources.getDrawable(id);	
        tile.setBounds(0, 0, size, size);
		tile.draw(canvas);
	}
	

	public void move() {
		switch (direction) {
			case DIRECTION_LEFT: {if (posX-1 >= 0) posX--; break;}
			case DIRECTION_RIGHT: {if (posX+1 < sizeX) posX++; break;}
			case DIRECTION_UP: {if (posY-1 >= 0) posY--; break;}
			case DIRECTION_DOWN: {if (posY+1 < sizeY) posY++; break;}
		}
	}

}

package school.trungi.tpac.mooveable;

import school.trungi.tpac.common.Map;
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
	protected Map map;
	
	protected Resources resources;
	public Bitmap images[] = new Bitmap[4];
	
	protected final int DIRECTION_UP = 3;
	protected final int DIRECTION_DOWN = 1;
	protected final int DIRECTION_LEFT = 2;
	protected final int DIRECTION_RIGHT = 0;
	
	public Mooveable(int x, int y, int m, int n, int size, Resources resources, Map map) {
		posX = x;
		posY = y;
		sizeX = m;
		sizeY = n;
		this.size = size;
		this.resources = resources;
		this.map = map;
		direction = DIRECTION_RIGHT;
	}
	
	public void draw(Canvas canvas, Paint paint, int marginLeft, int marginTop) {
		assert(images[direction] != null);
		
		canvas.drawBitmap(images[direction] , posX*size+marginLeft, posY*size+marginTop, paint);
	}
	
	protected void setBitMap(int id) {
		for (int i=0; i<4; i++) {
			images[i] = Bitmap.createBitmap(2*size, 2*size, Bitmap.Config.ARGB_8888);
        	Canvas canvas = new Canvas(images[i]);
        	Drawable tile = resources.getDrawable(id);	
        	tile.setBounds(0, 0, 2*size, 2*size);
			tile.draw(canvas);
		}
	}
	

	public void move() {
		countPos();
	}
	
	protected void countPos() {
		int oldX = posX, oldY = posY;
		
		switch (direction) {
			case DIRECTION_LEFT: {if (posX-1 >= 0) posX--; break;}
			case DIRECTION_RIGHT: {if (posX+2 < sizeX) posX++; break;}
			case DIRECTION_UP: {if (posY-1 >= 0) posY--; break;}
			case DIRECTION_DOWN: {if (posY+2 < sizeY) posY++; break;}
		}
		
		if (map.get(posX, posY).isBlock() ||
				map.get(posX+1, posY).isBlock()
				|| map.get(posX+1, posY+1).isBlock() || 
				map.get(posX, posY+1).isBlock()) {
			posX = oldX;
			posY = oldY;
		}
	}

	public int getX() {
		return this.posX;
	}
	
	public int getY() {
		return this.posY;
	}
}

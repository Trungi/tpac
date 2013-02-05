package school.trungi.tpac.mooveable;


import school.trungi.tpac.R;
import school.trungi.tpac.common.Map;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Pacman extends Mooveable {
	
	public Pacman(int x, int y, int m, int n, int size, Resources r, Map map) {
		super(x, y, m, n, size, r, map);

		this.setBitMap(R.drawable.logo);
	}

	public void computeDirection(int x, int y, int width, int height,
			int pacX, int pacY) {
		
		Point a = new Point(0,0);
		Point b = new Point(width, 0);
		Point c = new Point(0, height);
		Point d = new Point(width, height);
		
		Point pac = new Point(pacX, pacY);
		Point touch = new Point(x, y);
		
		if (PointInTriangle(touch, a, b, pac)) {
			direction = DIRECTION_UP;
		}
		if (PointInTriangle(touch, b, d, pac)) {
			direction = DIRECTION_RIGHT;
		}
		if (PointInTriangle(touch, a, c, pac)) {
			direction = DIRECTION_LEFT;
		}
		if (PointInTriangle(touch, c, d, pac)) {
			direction = DIRECTION_DOWN;
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

	float sign(Point p1, Point p2, Point p3)
	{
	  return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
	}

	boolean PointInTriangle(Point pt, Point v1, Point v2, Point v3)
	{
	  boolean b1, b2, b3;

	  b1 = sign(pt, v1, v2) < 0.0f;
	  b2 = sign(pt, v2, v3) < 0.0f;
	  b3 = sign(pt, v3, v1) < 0.0f;

	  return ((b1 == b2) && (b2 == b3));
	}

	class Point {
		public Point(int a, int b) {
			x = a;
			y = b;
		}
		public int x, y;
	}
	
}

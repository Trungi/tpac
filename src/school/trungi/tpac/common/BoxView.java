package school.trungi.tpac.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BoxView extends View {
	
	protected int m = 1, n = 1;
	protected int marginTop = 0, marginLeft = 0;
	protected int width, height, size;
	protected Map map = null;
	protected Paint paint = new Paint();
	public String TAG = "BoxView";
	
	public BoxView(Context context) {
		super(context);
	}

	public BoxView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BoxView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		this.width = w;
		this.height = h;
		
		int wsize = w / m;
		int hsize = h / n;
		
		size = (wsize < hsize) ? wsize : hsize;
		
		marginTop = (h - n*size) /2;
		marginLeft = (w - m*size) / 2;
		map.setBoxes(new BoxTypes(getResources(), size, 2*size));
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if (map == null) return;
		paint.setColor(0xff101010);
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				//canvas.drawText(map.get(i, j).toString(), getX(i), getY(j+1), paint);
				
				if (map.getBitmap(i, j) != null) {
					canvas.drawBitmap(map.getBitmap(i, j), getX(i), getY(j), paint);
				}
			}
		} 
	}
	
	protected int getX(int i) {
		return i*size + marginLeft;
	}
	
	protected int getY(int j) {
		return j*size + marginTop; 
	}
	
	public void setMapSize(int x, int y) {
		this.m = x;
		this.n = y;
		map = new Map(x, y);
	}

	public Map getMap() {
		return this.map;
	}
}

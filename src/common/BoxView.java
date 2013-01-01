package common;

import school.trungi.tpac.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class BoxView extends View {
	
	protected int m = 1, n = 1;
	protected int width, height, wsize, hsize;
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
		
		this.wsize = w / m;
		this.hsize = h / n;
		
		map.setBoxes(new BoxTypes(getResources(), wsize, 2*wsize));
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if (map == null) return;
		paint.setColor(0xff101010);
		
		for (int i=wsize; i<=width; i+= wsize) {
			canvas.drawLine(i, 0, i, height, paint);
		}
		for (int j=hsize; j<=height; j+= hsize) {
			canvas.drawLine(0, j, width, j, paint);
		}
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				canvas.drawText(map.get(i, j).toString(), (i)*(width/m), (j+1)*(height/n), paint);
				if (map.getBitmap(i, j) != null) {
					canvas.drawBitmap(map.getBitmap(i, j), i*(width/m), j*(height/n), paint);
				}
			}
		} 
	}
	
	public Bitmap get(Box a) {
        Resources r = this.getContext().getResources();

        Bitmap bitmap = Bitmap.createBitmap(wsize, hsize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        
        Drawable tile = r.getDrawable(R.drawable.e);
		
        tile.setBounds(0, 0, wsize, hsize);
        tile.draw(canvas);
        
        return bitmap;
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

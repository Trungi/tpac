package school.trungi.tpac.levelBuilder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import common.Box;
import common.BoxView;
import common.Map;

public class EditorView extends BoxView {
	
	protected Paint paint = new Paint();
	public String TAG = "EditorView";
	protected Map map = new Map(100, 100);
	public int i = 0;
	protected int width, height, wsize, hsize;
	protected int m = 1, n = 1;
	protected EditorButton button;

	public EditorView(Context context) {
		super(context);
		editorInit(m, n);
	}

	public EditorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		editorInit(m, n);
	}

	public EditorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		editorInit(m, n);
	}

	protected void editorInit(int m, int n) {
		map = new Map(m, n);
		
		this.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				setBox((int)event.getX(), (int)event.getY());
				invalidate();
				return true;
			}
		});
	}
	
	public void setBox(int clickX, int clickY) {
		 if (button != null) {
			 int i = clickX / (width/m);
			 int j = clickY / (height/n) + 1;
			 
			 if (i < m && j < n) {
			 	map.set(i, j, new Box(button.getCurrent()));
			 }
		 }
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(0xff101010);
		
		for (int i=wsize; i<=width; i+= wsize) {
			canvas.drawLine(i, 0, i, height, paint);
		}
		for (int j=hsize; j<=height; j+= hsize) {
			canvas.drawLine(0, j, width, j, paint);
		}
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				canvas.drawText(map.get(i, j).toString(), i*(width/m), j*(height/n), paint);
			}
		} 
	}
	
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		this.width = w;
		this.height = h;
		
		this.wsize = w / m;
		this.hsize = h / n;
	}
	
	public void setButton(EditorButton _button) {
		button = _button;
	}
	
	public Map getMap() {
		return this.map;
	}

	public void setMapSize(int x, int y) {
		this.m = x;
		this.n = y;
		editorInit(x, y);
	}
}

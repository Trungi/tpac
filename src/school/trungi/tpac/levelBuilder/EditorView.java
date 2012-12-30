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
	protected int m, n, width, height, wsize, hsize;
	protected EditorButton button;

	public EditorView(Context context) {
		super(context);
		editorInit();
	}

	public EditorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		editorInit();
	}

	public EditorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		editorInit();
	}

	protected void editorInit() {
		m = 50;
		n = 50;
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
			 int j = clickY / (height/n);
			 map.set(i, j, new Box(button.getCurrent()));
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
		canvas.drawText(map+ "", 100, 100, paint);
		//canvas.drawText(map[0][0].toString(), 100, 100, paint);
		
		
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
}

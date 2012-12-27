package school.trungi.tpac.levelBuilder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import common.BoxRenderer;
import common.BoxView;

public class EditorButton extends BoxView {
	
	public char[] attrs = {'a', 'b' };
	private BoxRenderer renderer = new BoxRenderer();
	public int position = -1;
	private String TAG = "EditorButton";
	private Paint paint = new Paint();

	public EditorButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public EditorButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public EditorButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public void onDraw(Canvas c) {
		super.onDraw(c);
		
		if (position >= attrs.length) {
			position = 0;
		} Log.d(TAG, "click! " + position + " " + this.getId());
		
		if (position != -1) {
			paint.setColor(0x00FF00);
			c.clipRect(0, 0, 400, 400);
			c.drawColor(0x00FFFF);
		}
	}
	
	public char getCurrent() {
		return attrs[position];
	}
}

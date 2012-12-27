package school.trungi.tpac.levelBuilder;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import common.BoxView;

public class EditorView extends BoxView {
	
	Paint mPaint = new Paint();

	public EditorView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public EditorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public EditorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub 
	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPaint.setColor(0xff101010);
		canvas.drawText("TExtik", 0, 100, mPaint);
	}
}

package school.trungi.tpac.levelBuilder;

import school.trungi.tpac.common.BoxTypes;
import school.trungi.tpac.common.BoxView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class EditorButton extends BoxView {
	
	protected BoxTypes boxes;
	protected int position = 0;
	protected String TAG = "EditorButton";
	protected Paint paint = new Paint();
	protected int width, height;
	

	public EditorButton(Context context) {
		super(context);
		initClick();
	}

	public EditorButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initClick();
	}

	public EditorButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initClick();
	}
	
	public void initClick() {
	}
	
	@Override
	public void onDraw(Canvas c) {
		super.onDraw(c);		
		if (boxes == null) return;
		
		paint.setColor(Color.BLACK);
		

		c.drawLine(0, 0, 0, height, paint);
		c.drawLine(0, 0, width, 0, paint);
		c.drawLine(width-1, 0, width-1, height, paint);
		c.drawLine(0, height-1, width, height-1, paint);
		
		if (BoxTypes.list[getCurrent()] <= 'Z' || getCurrent() == BoxTypes.EMPTY_BOX) {
			c.drawLine(0, height/2, width, height/2, paint);
			c.drawLine(width/2, 0, width/2, height, paint);
		}

		c.drawBitmap(boxes.bitmaps[position], 0, 0, paint);
		//c.drawText(Character.toString(BoxTypes.list[position]), width/2, height/2, paint);
	}
	
	public void setPosition(int diff) {
		position += diff;		
		
		if (position >= BoxTypes.list.length) {
			position = 0;
		}
		if (position < 0 ) {
			position = BoxTypes.list.length + position;
		}
	}
	
	public int getCurrent() {
		return position;		
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w;
		height = h;
		
		boxes = new BoxTypes(getResources(), w, w);
	}
}

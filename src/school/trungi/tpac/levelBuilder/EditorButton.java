package school.trungi.tpac.levelBuilder;

import school.trungi.tpac.common.BoxTypes;
import school.trungi.tpac.common.BoxView;
import android.content.Context;
import android.graphics.Canvas;
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
		this.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				setPosition(1);
				invalidate();
			}
		});
	}
	
	@Override
	public void onDraw(Canvas c) {
		super.onDraw(c);		
		if (boxes == null) return;
		
		paint.setColor(0xFFFFFFFF);
		
		//c.drawText(Character.toString(BoxTypes.list[position]), width/2, height/2, paint);
		c.drawBitmap(boxes.bitmaps[position], 0, 0, paint);
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

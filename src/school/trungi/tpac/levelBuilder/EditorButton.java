package school.trungi.tpac.levelBuilder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import common.BoxRenderer;
import common.BoxTypes;
import common.BoxView;

public class EditorButton extends BoxView {
	
	public final char[] attrs = BoxTypes.list;
	private BoxRenderer renderer = new BoxRenderer();
	private int position = 0;
	private String TAG = "EditorButton";
	private Paint paint = new Paint();
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
				position++;
				invalidate();
			}
		});
	}
	
	@Override
	public void onDraw(Canvas c) {
		super.onDraw(c);		
		if (position >= attrs.length) {
			position = 0;
		}
		if (position < 0 ) {
			position = attrs.length - position;
		}
		
		c.drawText(Character.toString(getCurrent()), width/2, height/2, paint);
	}
	
	public void setPosition(int diff) {
		position += diff;		
		
		if (position >= attrs.length) {
			position = 0;
		}
		if (position < 0 ) {
			position = attrs.length + position;
		}
	}
	
	public char getCurrent() {
		return attrs[position];		
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w;
		height = h;
	}
}

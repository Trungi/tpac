package school.trungi.tpac.levelBuilder;

import school.trungi.tpac.common.BoxTypes;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class EditorWithButtonsView extends EditorView {

	public EditorWithButtonsView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public EditorWithButtonsView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}
	
	int curX = 0;
	int curY = 0;

	protected void editorInit(int m, int n) {}
	
	public void clickUp() {
		curY--;
		if (curY < 0) curY = 0;
		invalidate();
	}
	
	public void clickLeft() {
		curX--;
		if (curX < 0) curX = 0;
		invalidate();
	}
	
	public void clickRight() {
		curX++;
		if (curX >= m) curX--;
		invalidate();
	}
	
	public void clickDown() {
		curY++;
		if (curY >= n) curY--;
		invalidate();
	}
	
	public void clickConfirm() {
		setBox(curX, curY);
		invalidate();
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if (BoxTypes.list[button.getCurrent()] > 'Z') {
			canvas.drawRect(getX(curX), getY(curY), getX(curX)+size, getY(curY)+size, paint);
		} else {
			canvas.drawRect(getX(curX), getY(curY), getX(curX)+2*size, getY(curY)+2*size, paint);
		}
		}
}

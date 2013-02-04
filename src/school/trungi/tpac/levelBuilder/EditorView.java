package school.trungi.tpac.levelBuilder;

import school.trungi.tpac.common.Box;
import school.trungi.tpac.common.BoxTypes;
import school.trungi.tpac.common.BoxView;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class EditorView extends BoxView {
	
	public String TAG = "EditorView";
	protected EditorButton button;
	private int INVISIBLE_BOX = BoxTypes.INVISIBLE_BOX;

	public EditorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		editorInit(m, n);
	}
	public EditorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		editorInit(m, n);
	}

	protected void editorInit(int m, int n) {
		this.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				setBox((int)event.getX(), (int)event.getY());
				invalidate();
				return true;
			}
		});
	}
	
	private int lastI = -1, lastJ = -1;
	
	public void setBox(int clickX, int clickY) {
		int i = (clickX - marginLeft) / size;
		int j = (clickY - marginTop) / size;
			 Log.d("setBox", i + " " + j);
		try {
			if (button.getCurrent() == BoxTypes.EMPTY_BOX) {
				if (map.get(i, j).toString().equals(".")) {
					if ((i-1 > 0) && !map.get(i-1, j).toString().equals(".")) {
						i--;
					}
					if ((j-1 > 0) && !map.get(i, j-1).toString().equals(".")) {
						j--;
					}
					if ((i-1 > 0) && (j-1 > 0) && !map.get(i-1, j).toString().equals(".")) {
						i--;
						j--;
					}
				}
				
				if (map.get(i, j).toString().charAt(0) <= 'Z') {
					map.set(i, j, new Box(BoxTypes.EMPTY_BOX));
					map.set(i+1, j, new Box(BoxTypes.EMPTY_BOX));
					map.set(i, j+1, new Box(BoxTypes.EMPTY_BOX));
					map.set(i+1, j+1, new Box(BoxTypes.EMPTY_BOX));
				} else {
					map.set(i, j, new Box(BoxTypes.EMPTY_BOX));
				}
			} else {
			
			
				if (BoxTypes.list[button.getCurrent()] > 'Z') {
					if (i < m && j < n) {
						if (!map.get(i, j).isEmpty()) throw new CanNotPutException();
					
						map.set(i, j, new Box(button.getCurrent()));
					}	
				} else {
					if (i+1 < m && j+1 < n) {
						if (!map.get(i, j).isEmpty()) throw new CanNotPutException();
						if (!map.get(i+1, j).isEmpty()) throw new CanNotPutException();
						if (!map.get(i, j+1).isEmpty()) throw new CanNotPutException();
						if (!map.get(i+1, j+1).isEmpty()) throw new CanNotPutException();
					
						map.set(i, j, new Box(button.getCurrent()));
						map.set(i+1, j, new Box(INVISIBLE_BOX));
						map.set(i, j+1, new Box(INVISIBLE_BOX));
						map.set(i+1, j+1, new Box(INVISIBLE_BOX));				 
					}
				}
			
				lastI = i;
				lastJ = j;
			}
		} catch (CanNotPutException e) {
			if (i != lastI && j != lastJ) ;
			//	Toast.makeText(this.getContext(), R.string.not_empty, Toast.LENGTH_SHORT).show();
		} catch (ArrayIndexOutOfBoundsException e) {}
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		long a = System.currentTimeMillis();
		for (int i=0; i<=m; i++) {
			canvas.drawLine(getX(i), marginTop, getX(i), n*size+marginTop, paint);
		}
		for (int j=0; j<=n; j++) {
			canvas.drawLine(marginLeft, getY(j), m*size+marginLeft, getY(j), paint);
		}
		super.onDraw(canvas);

		canvas.drawText(Long.toString(System.currentTimeMillis()-a), 100, 100, paint);
	}
	
	public void setButton(EditorButton _button) {
		button = _button;
	}
	
    class CanNotPutException extends Exception {
		private static final long serialVersionUID = 1L;};
}

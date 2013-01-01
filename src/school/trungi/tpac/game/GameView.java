package school.trungi.tpac.game;

import java.util.Scanner;

import school.trungi.tpac.common.BoxView;
import school.trungi.tpac.common.Map;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class GameView extends BoxView {

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public void loadMap(String mapStr) {
		Scanner in = new Scanner(mapStr);
		
		m = in.nextInt();
		n = in.nextInt();
		map = new Map(mapStr);
	}
	
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawLine(getX(0), marginTop, getX(0), n*size+marginTop, paint);
		canvas.drawLine(getX(m), marginTop, getX(m), n*size+marginTop, paint);
		
		canvas.drawLine(marginLeft, getY(0), m*size+marginLeft, getY(0), paint);
		canvas.drawLine(marginLeft, getY(n), m*size+marginLeft, getY(n), paint);
	}

}

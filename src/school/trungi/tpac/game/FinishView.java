package school.trungi.tpac.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class FinishView extends View {

	public FinishView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public FinishView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public FinishView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	long score, lives, seconds = 0;
	
	public void init(int lives, long score) {
		this.score = score;
		this.lives = lives;
	}
	
	Paint paint = new Paint();
	
	public void onDraw(Canvas canvas) {
		paint.setColor(0xff101010);
		paint.setTextSize(30);
		
		canvas.drawText("Score: " + score, 20, 20, paint);
		canvas.drawText("Lives: " + lives, 20, 75, paint);
		canvas.drawText("Time: " + (5-seconds), 20, 120, paint);
	}

	public void inc() {
		seconds++;
	}

	public int get() {
		return (int) seconds;
	}

}

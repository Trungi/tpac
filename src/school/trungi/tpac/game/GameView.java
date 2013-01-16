package school.trungi.tpac.game;

import java.io.FileInputStream;
import java.util.Timer;

import school.trungi.tpac.common.BoxView;
import school.trungi.tpac.common.Map;
import school.trungi.tpac.mooveable.Pacman;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends BoxView {
	
	protected int pacX, pacY;
	protected boolean running = true;
	protected Timer timer;
	protected Pacman pacman;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		timer = new Timer();
		
		this.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				pacman.computeDirection((int)event.getX(), (int)event.getY(), 
						(int)width, (int)height);
				return true;
			}
		});
	}

	public void loadMap(FileInputStream in) {
		map = new Map(in);
		
		m = map.getM();
		n = map.getN();
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		/* Draw edges */
		canvas.drawLine(getX(0), marginTop, getX(0), n*size+marginTop, paint);
		canvas.drawLine(getX(m), marginTop, getX(m), n*size+marginTop, paint);
		canvas.drawLine(marginLeft, getY(0), m*size+marginLeft, getY(0), paint);
		canvas.drawLine(marginLeft, getY(n), m*size+marginLeft, getY(n), paint);
		
		/* Draw mooveables */
		pacman.draw(canvas, paint, marginLeft, marginTop);
	}

	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		pacman = new Pacman(0, 0, m, n, size, getResources());
	}

	public void redraw() {
		try {
			pacman.move();
			this.invalidate();
		} catch (NullPointerException e) {}
	}

	public boolean isRunning() {
		return running;
	}

}
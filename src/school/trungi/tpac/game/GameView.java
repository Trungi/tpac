package school.trungi.tpac.game;

import java.io.FileInputStream;
import java.util.Timer;

import school.trungi.tpac.GameActivity;
import school.trungi.tpac.common.Box;
import school.trungi.tpac.common.BoxView;
import school.trungi.tpac.common.Map;
import school.trungi.tpac.mooveable.Ghost;
import school.trungi.tpac.mooveable.GreenGhost;
import school.trungi.tpac.mooveable.Pacman;
import school.trungi.tpac.mooveable.PinkGhost;
import school.trungi.tpac.mooveable.RedGhost;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends BoxView {
	
	protected int pacX, pacY;
	protected boolean running = true;
	protected GameActivity ga;
	protected Timer timer;
	protected Pacman pacman;
	protected Ghost[] ghosts = new Ghost[4];
	protected StatsView stats;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		timer = new Timer();
		
		this.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				pacman.computeDirection((int)event.getX(), (int)event.getY(), 
						(int)width, (int)height, getX(pacman.getX()), getY(pacman.getY()));
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
		
		for (int i=0; i<4; i++){
			ghosts[i].draw(canvas, paint, marginLeft, marginTop);
		}
	}

	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		for (int i=0; i<map.getM(); i++) {
			for (int j=0; j<map.getN(); j++){
				if (map.get(i, j).isPacman()) {
					pacman = new Pacman(i, j, m, n, size, getResources(), map);
					map.set(i, j, new Box());
				}
			}
		}

		for (int i=0; i<map.getM(); i++) {
			for (int j=0; j<map.getN(); j++){
				if (map.get(i, j).isGhost()) {
					map.set(i, j, new Box());
				
					ghosts[0] = new Ghost(i, j, m, n, size, getResources(), map);
					ghosts[1] = new PinkGhost(i, j, m, n, size, getResources(), map, pacman);
					ghosts[2] = new GreenGhost(i, j, m, n, size, getResources(), map, pacman);
					ghosts[3] = new RedGhost(i, j, m, n, size, getResources(), map, pacman);
				}
			}
		}
	}

	private boolean finished = false;
	public void redraw() {
		try {
			if (finished) {
				ga.finishGame(stats.getScore(), stats.getLives());
				return;
			}
			
			pacman.move();
			for (int i=0; i<4; i++){
				ghosts[i].move();
			}
			
			if (map.get(pacman.getX(), pacman.getY()).isFood()) {
				map.set(pacman.getX(), pacman.getY(), new Box());
				stats.eat();
			}
			
			if (map.get(pacman.getX(), pacman.getY()).isFruit()) {
				map.set(pacman.getX(), pacman.getY(), new Box());
				stats.eatFruit();
			}
			
			for (int i=0; i<4; i++) {
				int x = pacman.getX();
				int y = pacman.getY();
				int x1 = ghosts[i].getX();
				int y1 = ghosts[i].getY();
			
				if (	(x == x1 && y == y1) ||
					(x == x1+1 && y == y1) ||
					(x == x1 && y == y1+1) ||
					(x == x1-1 && y == y1) ||
					(x == x1 && y == y1-1) ||
					(x == x1+1 && y == y1+1) ||
					(x == x1-1 && y == y1-1)
						) {
					stats.kill();
					if (stats.getLives() != 0) {
						pacman.reset();
						for (int q=0; q<4; q++) {
							ghosts[q].reset();
						}
					} 
				}
			}
				
			this.invalidate();
			
			if (stats.getLives() <= 0 || stats.getFood() <= 0) {
				finished = true;
			}
		} catch (NullPointerException e) {}
	}

	public boolean isRunning() {
		return running;
	}

	public void init(StatsView s, GameActivity ga) {
		stats = s;
		this.ga = ga;
		stats.setMap(map);
	}
}
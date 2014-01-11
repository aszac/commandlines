package uk.ac.reading.tq011338.commandlines;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public abstract class GameThread extends Thread {

	private SurfaceHolder mSurfaceHolder;
	private boolean threadIsRunning = true;
	public GameView mGameView;

	protected int mCanvasWidth = 1;
	protected int mCanvasHeight = 1;
	
	public GameThread(GameView gameView, Activity activity) {
		mSurfaceHolder = gameView.getHolder();
		gameView.getContext();
		mGameView = gameView;

	}

	public GameThread(GameView gameView) {
		mSurfaceHolder = gameView.getHolder();
		gameView.getContext();
		mGameView = gameView;
	}

	public void setRunning(boolean running) {
		threadIsRunning = running;
	}

	public void run() {
		Canvas canvasRun = null;
		while (threadIsRunning) {
			try {
				canvasRun = mSurfaceHolder.lockCanvas(null);
				synchronized (mSurfaceHolder) {
					drawWorld(canvasRun);
				}
			} finally {
				if (canvasRun != null) {
					mSurfaceHolder.unlockCanvasAndPost(canvasRun);

				}
			}
		}
	}

	public void setSurfaceSize(int width, int height) {
		synchronized (mSurfaceHolder) {
			mCanvasWidth = width;
			mCanvasHeight = height;
		}
	}

	abstract protected void drawWorld(Canvas canvas);

	abstract public void selectFigure(MotionEvent event);
	abstract public ActionFigure getSelectedFigure();
	
	public void cleanup() {		
		this.mGameView = null;
		this.mSurfaceHolder = null;
	}
}

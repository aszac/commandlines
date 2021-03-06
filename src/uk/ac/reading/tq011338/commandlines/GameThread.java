package uk.ac.reading.tq011338.commandlines;

import java.util.List;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public abstract class GameThread extends Thread {

	private SurfaceHolder mSurfaceHolder;
	private boolean threadIsRunning = true;
	public GameView mGameView;

	protected int mCanvasWidth = 1;
	protected int mCanvasHeight = 1;
	
	protected WorldObject[][] worldMap;
	protected List<WorldObject> objectList;

	protected int mMode = 1;
	protected boolean isButtonClicked = false;
	
	public boolean wasHit = false;

	public GameThread(GameView gameView, Activity activity) {
		mSurfaceHolder = gameView.getHolder();
		gameView.getContext();
		mGameView = gameView;
		gameView.getmHandler();
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
					checkIfGameOver();
					checkTurn();			
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

	abstract protected boolean checkIfGameOver();
	abstract protected void drawWorld(Canvas canvas);
	abstract protected void checkTurn();
	abstract protected void removeFigure(int x, int y);

	public void cleanup() {
		this.mGameView = null;
		this.mSurfaceHolder = null;
	}

	public int getMode() {
		return mMode;
	}

	public void setMode(int mMode) {
		this.mMode = mMode;
	}

	abstract public ActionFigure getActiveFigure();
}
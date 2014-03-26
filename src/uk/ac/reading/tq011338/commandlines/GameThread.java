package uk.ac.reading.tq011338.commandlines;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

public abstract class GameThread extends Thread {

	private SurfaceHolder mSurfaceHolder;
	private boolean threadIsRunning = true;
	public GameView mGameView;

	protected int mCanvasWidth = 1;
	protected int mCanvasHeight = 1;

	protected int mMode = 1;
//	public static final int STATE_LOSE = 1;
//	public static final int STATE_RUNNING = 2;
//	public static final int STATE_WIN = 3;
	private Handler mHandler;
	private Context mContext;

	public GameThread(GameView gameView, Activity activity) {
		mSurfaceHolder = gameView.getHolder();
		mContext = gameView.getContext();
		mGameView = gameView;
		mHandler = gameView.getmHandler();
	}

	public void setRunning(boolean running) {
		threadIsRunning = running;
//		setState(STATE_RUNNING);
	}

	public void run() {
		Canvas canvasRun = null;
		while (threadIsRunning) {
			try {
				canvasRun = mSurfaceHolder.lockCanvas(null);
				synchronized (mSurfaceHolder) {
					drawWorld(canvasRun);
					
//					if (mMode == STATE_RUNNING) {
						checkTurn();
//					}
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
	abstract protected void checkTurn();

	public void cleanup() {
		this.mGameView = null;
		this.mSurfaceHolder = null;
		this.mContext = null;
		this.mHandler = null;
	}

//	public void setState(int mode) {
//		synchronized (mSurfaceHolder) {
//			mMode = mode;
//			if (mMode == STATE_RUNNING) {
//				Message msg = mHandler.obtainMessage();
//				Bundle b = new Bundle();
//				b.putString("text", "");
//				b.putInt("viz", View.INVISIBLE);
//				b.putBoolean("showAd", false);
//				msg.setData(b);
//				mHandler.sendMessage(msg);
//			} else {
//				Message msg = mHandler.obtainMessage();
//				Bundle b = new Bundle();
//				Resources res = mContext.getResources();
//				CharSequence str = "";
//				if (mMode == STATE_LOSE)
//					str = res.getText(R.string.mode_lose);
//				else if (mMode == STATE_WIN) {
//					str = res.getText(R.string.mode_win);
//				}
//				b.putString("text", str.toString());
//				b.putInt("viz", View.VISIBLE);
//				msg.setData(b);
//				mHandler.sendMessage(msg);
//			}
//		}
//	}
	
	public int getMode() {
		return mMode;
	}

	public void setMode(int mMode) {
		this.mMode = mMode;
	}
}
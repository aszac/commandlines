package uk.ac.reading.tq011338.commandlines;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private Handler mHandler;
	private TextView mStatusView;
	
	
	private volatile GameThread thread;

	@SuppressLint("HandlerLeak")
	public GameView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);

		SurfaceHolder holder = getHolder();
		holder.addCallback(this);

		//Set up a handler for messages from GameThread
		mHandler = new Handler() {
			public void handleMessage(Message m) {
				if (m != null) {
					mStatusView.setVisibility(m.getData().getInt("viz"));
				}
 			}
		};
	}

	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		if (thread != null) {
			thread.setSurfaceSize(width, height);
		}
	}

	public void surfaceCreated(SurfaceHolder holder) {
		if(thread!=null) {
			thread.setRunning(true);
			
			if(thread.getState() == Thread.State.NEW){
				thread.start();
			}
			else {
				if(thread.getState() == Thread.State.TERMINATED){
					try {
						thread = new TheSingleplayerGame(this, null, 0, null);
					} catch (JSONException e) {
						e.printStackTrace();
					} 
					thread.setRunning(true);
					thread.start();
				}
			}
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		thread.setRunning(false);
	}

	public void setThread(GameThread newThread) {
		thread = newThread;

	}
	
	public Handler getmHandler() {
		return mHandler;
	}

	public void setmHandler(Handler mHandler) {
		this.mHandler = mHandler;
	}

	public GameThread getThread() {
		return thread;
	}
	
	public void cleanup() {
		this.thread.setRunning(false);
		this.thread.cleanup();
		
		this.removeCallbacks(thread);
		thread = null;
		
		this.setOnTouchListener(null);
		
		SurfaceHolder holder = getHolder();
		holder.removeCallback(this);
	}
	
	public TextView getStatusView() {
		return mStatusView;
	}

	public void setStatusView(TextView mStatusView) {
		this.mStatusView = mStatusView;
	}

}

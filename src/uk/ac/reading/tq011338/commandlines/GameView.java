package uk.ac.reading.tq011338.commandlines;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private LayoutInflater layoutInflater;
	private Context parentContext;
	private Bitmap mFigure;

	private volatile GameThread thread;

	public GameView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);

		SurfaceHolder holder = getHolder();
		holder.addCallback(this);

		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		parentContext = context;

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

	}

	public void addNewActionFigure(Canvas canvas) {
		mFigure = BitmapFactory.decodeResource(parentContext.getResources(),
				R.drawable.face);
		if (canvas == null)
			return;
		canvas.drawBitmap(mFigure, 0, 0, null);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		if (thread != null) {
			thread.setSurfaceSize(width, height);
		}
	}

	public void surfaceCreated(SurfaceHolder holder) {
		thread = new TheGame(this);
		thread.setRunning(true);
		thread.start();

	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		thread.setRunning(false);
	}

	protected void onDraw(Canvas canvas) {
		addNewActionFigure(canvas);

	}

	public void setThread(GameThread newThread) {
		thread = newThread;

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

}

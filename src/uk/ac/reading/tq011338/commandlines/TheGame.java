package uk.ac.reading.tq011338.commandlines;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageView;

public class TheGame extends GameThread {

	private Bitmap mFigure;
	private Bitmap mFigureSelected;
	private Bitmap mGridTile;
	private Bitmap mStone;
	private Bitmap mTree;
	
	private Bitmap mRecovery;
	private Bitmap mAttack;
	private Bitmap mDefend;

	private LayoutInflater layoutInflater;
	protected final static int mapSizeX = 8;
	protected final static int mapSizeY = 8;

	protected static int mGridSize = 24;

	private int mStatusBarHight;

	private ImageView img;
	private EditText mCommandView;
	private Activity activity;

	protected static WorldObject[][] worldMap = new WorldObject[mapSizeX][mapSizeY];

	/**
	 * Constructor called from the activity call, passing the current activity
	 * 
	 * @param gameView
	 * @param activity
	 */
	public TheGame(final GameView gameView, Activity activity) {
		super(gameView, activity);
		this.activity = activity;

		setGridSize (gameView);
		mFigure = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.face);

		mFigureSelected = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.selectedface);

		mGridTile = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.square_teal);
		
		mStone = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.stone);
		
		mTree = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.tree);
		
		mRecovery = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.cross); 
		
		mAttack = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.angry); 
		
		mDefend = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.defend);

		mStatusBarHight = (int) Math.ceil(25 * gameView.getContext()
				.getResources().getDisplayMetrics().density);
		
		

		mCommandView = (EditText) this.activity.findViewById(R.id.commandView);

		// TODO
		createNewWorld();

	}

	/**
	 * Constructor called from the GameView class
	 * 
	 * @param gameView
	 */
	public TheGame(GameView gameView) {
		super(gameView);
		setGridSize (gameView);

		mFigure = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.face);

		mFigureSelected = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.selectedface);

		mGridTile = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.square_teal);
		
		mStone = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.stone);		
		
		mTree = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.tree);

		mStatusBarHight = (int) Math.ceil(25 * gameView.getContext()
				.getResources().getDisplayMetrics().density);

		// TODO
		createNewWorld();
	}

	public void createNewWorld() {
		// TODO
		worldMap[6][6] = new ActionFigure(6, 6);
		worldMap[0][1] = new ActionFigure(0, 1);
		
		worldMap[4][2] = new Obstacle(4,2);
		worldMap[6][0] = new Obstacle(6,0);

	}

	/**
	 * Function called from the GUI thread. Draws the game graphics.
	 */
	protected void drawWorld(Canvas canvas) {

		if (canvas == null)
			return;

		canvas.drawColor(Color.BLACK);
		for (int i = 0; i < mapSizeX; i++) {
			for (int j = 0; j < mapSizeY; j++) {
				canvas.drawBitmap(mGridTile, i * mGridSize, j * mGridSize, null);
				if (worldMap[i][j] instanceof ActionFigure) { // TODO recently changed
					if (!worldMap[i][j].isSelected()) { // use different bitmap
						// if selected
						canvas.drawBitmap(mFigure, i * mGridSize,
								j * mGridSize, null);
					} else {
						canvas.drawBitmap(mFigureSelected, i * mGridSize, j
								* mGridSize, null);
					}
				}
				else if (worldMap[i][j] instanceof Obstacle) {
					canvas.drawBitmap(mTree, i * mGridSize,
							j * mGridSize, null);
				}
			}
		}
	}

	/**
	 * Updates position of the object in the array and the position fields in
	 * the object.
	 * 
	 * @param figure
	 *            - the action figure object that changes position
	 * @param newX
	 *            - new x coordinate
	 * @param newY
	 *            - new y coordinate
	 */
	public static void updatePosition(ActionFigure figure, int newX, int newY) {
		worldMap[figure.getX()][figure.getY()] = null;
		worldMap[newX][newY] = figure;
		try {
			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Called when the screen is touched. Selects or unselects the action
	 * figure.
	 */
	public void selectFigure(MotionEvent event) {
		Point touchPoint = new Point((int) event.getX(), (int) event.getY());

		// find the position in the array
		int x = (int) Math.floor(touchPoint.x / mGridSize);
		int y = (int) Math.floor((touchPoint.y - mStatusBarHight) / mGridSize);

		// if selected unselect, if unselected select
		if (x < mapSizeX && y < mapSizeY) {
			if (worldMap[x][y] != null) {
				if (worldMap[x][y].isSelected()) {
					unselect(x, y);
				} else {
					select(x, y);
				}
			}
		}

	}

	/**
	 * Unselects the action figure. Called when selected figure is touched or
	 * when another figure is being selected.
	 * 
	 * @param x
	 *            - x position of the unselected object in the array
	 * @param y
	 *            - y position of the unselected object in the array
	 */
	private void unselect(int x, int y) {
		worldMap[x][y].setSelected(false); // unselect the object, save text
											// from the text field and clear the
											// field
		worldMap[x][y].setCommand(mCommandView.getText().toString());
		mCommandView.setText("");
	}

	/**
	 * Selects the touched action figure. Unselects all other objects.
	 * 
	 * @param x
	 *            - x position of the selected object in the array
	 * @param y
	 *            - y position of the selected object in the array
	 */
	private void select(int x, int y) {
		for (int i = 0; i < mapSizeX; i++) { // unselect all selected figures
			for (int j = 0; j < mapSizeY; j++) {
				if (worldMap[i][j] != null && worldMap[i][j].isSelected()) {
					unselect(i, j);
				}
			}
		}

		worldMap[x][y].setSelected(true); // select the desired object
		mCommandView.setText(worldMap[x][y].getCommand()); // set command for
															// the object
	}

	/**
	 * Returns selected action figure to execute code on. Called from the
	 * onClick function in CommandLines activity.
	 */
	public ActionFigure getSelectedFigure() {
		for (int i = 0; i < mapSizeX; i++) { // unselect all selected figures
			for (int j = 0; j < mapSizeY; j++) {
				if (worldMap[i][j] != null && worldMap[i][j].isSelected()) {
					return (ActionFigure) worldMap[i][j];
				}
			}
		}
		return null;
	}

	/**
	 * Assigns value to mGridSize variable, which controls drawing the graphics.
	 * 
	 * @param gameView
	 */
	private void setGridSize (GameView gameView) {
		int density= gameView.getResources().getDisplayMetrics().densityDpi;
		int width= gameView.getResources().getDisplayMetrics().widthPixels;
		int height= gameView.getResources().getDisplayMetrics().heightPixels;

		
		// support for tablet graphics
		if (width >= 800 && density == DisplayMetrics.DENSITY_HIGH) {
			mGridSize = 69;
			return;
		}		
		if (width >= 600 && density == DisplayMetrics.DENSITY_MEDIUM) {
			mGridSize = 51;
			return;
		}
		if (width >= 600 && density == DisplayMetrics.DENSITY_HIGH) {
			mGridSize = 69;
			return;
		}
		
		// for mobile graphics
		switch(density)
		{
		case DisplayMetrics.DENSITY_LOW:
			mGridSize = 25;
		    break;
		case DisplayMetrics.DENSITY_MEDIUM:
			mGridSize = 34;
			break;
		case DisplayMetrics.DENSITY_HIGH:
			mGridSize = 51;
		    break;
		case DisplayMetrics.DENSITY_XHIGH:
			mGridSize = 69;
		    break;
		}
	}
}

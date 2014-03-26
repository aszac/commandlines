package uk.ac.reading.tq011338.commandlines;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
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

	private Bitmap mFigure_enemy;
	private Bitmap mRecovery_enemy;
	private Bitmap mAttack_enemy;
	private Bitmap mDefend_enemy;

	private LayoutInflater layoutInflater;
	protected final static int mapSizeX = 8;
	protected final static int mapSizeY = 8;

	protected static int mGridSize = 24;

	private int mStatusBarHight;

	private ImageView img;
	private EditText mCommandView;
	private Activity activity;
	private GameView gameView;

	protected static WorldObject[][] worldMap = new WorldObject[mapSizeX][mapSizeY];
	public static boolean isButtonClicked = false;
	public static List<ActionFigure> figureListForTurns = new ArrayList<ActionFigure>();
	public static List<ActionFigure> figureList = new ArrayList<ActionFigure>();
	private boolean isDialogDisplayed;

	private static ActionFigure activeFigure;
	
	/**
	 * Constructor called from the activity call, passing the current activity
	 * 
	 * @param gameView
	 * @param activity
	 */
	public TheGame(final GameView gameView, Activity activity) {
		super(gameView, activity);
		this.activity = activity;
		this.gameView = gameView;

		setGridSize(gameView);
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

		mFigure_enemy = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.face_enemy);

		mRecovery_enemy = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.cross_enemy);

		mAttack_enemy = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.angry_enemy);

		mDefend_enemy = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.defend_enemy);

		// TODO
		createNewWorld();

	}

	public void createNewWorld() {
		// TODO
		ActionFigure figure1 = new ActionFigure(6, 6);
		figureList.add(figure1);
		worldMap[6][6] = figure1;

		EnemyActionFigure figure2 = new EnemyActionFigure(0, 1);
		figureList.add(figure2);
		worldMap[0][1] = figure2;

		worldMap[4][2] = new Obstacle(4, 2);
		worldMap[6][0] = new Obstacle(6, 0);

	}

	public void checkTurn() {
		checkIfGameOver();
		
		if (figureListForTurns.size() == 0) {
			for (ActionFigure figure : figureList) {
				figure.setAP(100);
				figureListForTurns.add(figure);
			}
		}

		setActiveFigure(figureListForTurns.get(0));
		if (activeFigure instanceof EnemyActionFigure) {
			if (activeFigure.AP > 0) {
				activeFigure.decideOnNextMove();
			} else {
				figureListForTurns.remove(activeFigure);
			}
		} else {
			if (isButtonClicked) {
				figureListForTurns.remove(activeFigure);
				isButtonClicked = false;
			}
		}

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
				if (worldMap[i][j] instanceof EnemyActionFigure) {
					uk.ac.reading.tq011338.commandlines.ActionFigure.State s = worldMap[i][j]
							.getState();
					switch (worldMap[i][j].getState()) {
					case ATTACK:
						canvas.drawBitmap(mAttack_enemy, i * mGridSize, j
								* mGridSize, null);
						break;
					case HEAL:
						canvas.drawBitmap(mRecovery_enemy, i * mGridSize, j
								* mGridSize, null);
						break;
					case DEFEND:
						canvas.drawBitmap(mDefend_enemy, i * mGridSize, j
								* mGridSize, null);
						break;
					default:
						canvas.drawBitmap(mFigure_enemy, i * mGridSize, j
								* mGridSize, null);
					}
				} else if (worldMap[i][j] instanceof ActionFigure) {

					if (worldMap[i][j] != activeFigure) { // use different
															// bitmap
						// if not selected
						uk.ac.reading.tq011338.commandlines.ActionFigure.State s = worldMap[i][j]
								.getState();
						switch (worldMap[i][j].getState()) {
						case ATTACK:
							canvas.drawBitmap(mAttack, i * mGridSize, j
									* mGridSize, null);
							break;
						case HEAL:
							canvas.drawBitmap(mRecovery, i * mGridSize, j
									* mGridSize, null);
							break;
						case DEFEND:
							canvas.drawBitmap(mDefend, i * mGridSize, j
									* mGridSize, null);
							break;
						default:
							canvas.drawBitmap(mFigure, i * mGridSize, j
									* mGridSize, null);
						}

					} else {
						canvas.drawBitmap(mFigureSelected, i * mGridSize, j
								* mGridSize, null);
					}
				} else if (worldMap[i][j] instanceof Obstacle) {
					canvas.drawBitmap(mTree, i * mGridSize, j * mGridSize, null);
				}
			}
		}

		// execute game
//		checkTurn();
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
	 * Assigns value to mGridSize variable, which controls drawing the graphics.
	 * 
	 * @param gameView
	 */
	private void setGridSize(GameView gameView) {
		int density = gameView.getResources().getDisplayMetrics().densityDpi;
		int width = gameView.getResources().getDisplayMetrics().widthPixels;
		int height = gameView.getResources().getDisplayMetrics().heightPixels;

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
		switch (density) {
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

	private boolean checkIfGameOver() {
		int numberOfFigures = 0;
		int numberOfEnemyFigures = 0;
		for (int i = 0; i < mapSizeX; i++) {
			for (int j = 0; j < mapSizeY; j++) {
				if (worldMap[i][j] instanceof ActionFigure) {
					if (worldMap[i][j] instanceof EnemyActionFigure) {
						numberOfEnemyFigures++;
					} else {
						numberOfFigures++;
					}
				}
			}
		}
		if (numberOfFigures == 0) {
			this.setRunning(false);
			showGameOverDialog(R.string.mode_lose);
//			setState(GameThread.STATE_LOSE);
			return true;
		}
		if (numberOfEnemyFigures == 0) {
			this.setRunning(false);
			showGameOverDialog(R.string.mode_win);
//			setState(GameThread.STATE_WIN);
			return true;
		}
		return false;
	}

	public void setActiveFigure(ActionFigure activeFigure) {
		if (activeFigure != getActiveFigure()) {
			this.activeFigure = activeFigure;
		}
	}

	public static ActionFigure getActiveFigure() {
		return activeFigure;
	}

	
	private void showGameOverDialog(int messageId) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(gameView.getContext());
		builder.setTitle(gameView.getResources().getString(messageId));
		builder.setCancelable(false);
		
		builder.setPositiveButton(R.string.restart_button, 
				new DialogInterface.OnClickListener() {
										
					public void onClick(DialogInterface dialog, int which) {					
						isDialogDisplayed = false;
						activity.finish();
					}
				});
		
		builder.setNegativeButton(R.string.exit_button, 
				new DialogInterface.OnClickListener() {
										
					public void onClick(DialogInterface dialog, int which) {					
						isDialogDisplayed = false;
						activity.finish();
					}
				});
		
		activity.runOnUiThread(new Runnable() {
			public void run () {
				isDialogDisplayed = true;
				builder.show();
			}
		});
		
	}
}

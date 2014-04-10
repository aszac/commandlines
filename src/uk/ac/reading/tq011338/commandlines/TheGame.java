package uk.ac.reading.tq011338.commandlines;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

public class TheGame extends GameThread {

	private Bitmap mFigure;
	private Bitmap mFigureSelected;
	private Bitmap mGridTile;
	private Bitmap mTree;

	private Bitmap mRecovery;
	private Bitmap mAttack;
	private Bitmap mDefend;

	private Bitmap mFigure_enemy;
	private Bitmap mRecovery_enemy;
	private Bitmap mAttack_enemy;
	private Bitmap mDefend_enemy;

	protected final static int mapSizeX = 8;
	protected final static int mapSizeY = 8;

	protected static int mGridSize = 24;

	private Activity activity;
	private GameView gameView;
	private boolean isDialogDisplayed;
	private ActionFigure activeFigure;
	private int selected_level;

	/**
	 * Constructor called from the activity call, passing the current activity
	 * 
	 * @param gameView
	 * @param activity
	 * @param selected_level
	 */
	public TheGame(final GameView gameView, Activity activity,
			int selected_level) {
		super(gameView, activity, selected_level);
		this.activity = activity;
		this.gameView = gameView;
		this.selected_level = selected_level;

		setGridSize(gameView);
		mFigure = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.face);

		mFigureSelected = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.selectedface);

		mGridTile = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.square_teal);

		mTree = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.tree);

		mRecovery = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.cross);

		mAttack = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.angry);

		mDefend = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.defend);

		mFigure_enemy = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.face_enemy);

		mRecovery_enemy = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.cross_enemy);

		mAttack_enemy = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.angry_enemy);

		mDefend_enemy = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.defend_enemy);

		createNewWorld();
	}

	public void createNewWorld() {
		worldMap = new WorldObject[mapSizeX][mapSizeY];
		objectList = new ArrayList<WorldObject>();
		figureListForTurns = new ArrayList<ActionFigure>();
		
		ActionFigure figure1 = new ActionFigure(6, 6, this);
		objectList.add(figure1);
		worldMap[6][6] = figure1;
		
		ActionFigure figure2 = new ActionFigure(5, 5, this);
		objectList.add(figure2);
		worldMap[5][5] = figure2;
		
		ActionFigure figure7 = new ActionFigure(4, 4, this);
		objectList.add(figure7);
		worldMap[4][4] = figure7;

		EnemyActionFigure figure3 = new EnemyActionFigure(0, 0, this);
		objectList.add(figure3);
		worldMap[2][2] = figure3;
		
		EnemyActionFigure figure4 = new EnemyActionFigure(1, 1, this);
		objectList.add(figure4);
		worldMap[1][1] = figure4;
		
		EnemyActionFigure figure5 = new EnemyActionFigure(3, 4, this);
		objectList.add(figure5);
		worldMap[3][4] = figure5;
		
		EnemyActionFigure figure6 = new EnemyActionFigure(5, 1, this);
		objectList.add(figure6);
		worldMap[5][1] = figure6;

		Obstacle obstacle1 = new Obstacle(3, 2);
		worldMap[3][2] = obstacle1;
		objectList.add(obstacle1);

		Obstacle obstacle2 = new Obstacle(6, 0);
		worldMap[6][0] = obstacle2;
		objectList.add(obstacle2);
		
		Obstacle obstacle3 = new Obstacle(5, 0);
		worldMap[5][0] = obstacle3;
		objectList.add(obstacle3);
		
		Obstacle obstacle4 = new Obstacle(3, 6);
		worldMap[3][6] = obstacle4;
		objectList.add(obstacle4);
		
		Obstacle obstacle5 = new Obstacle(3, 7);
		worldMap[3][7] = obstacle5;
		objectList.add(obstacle5);
	}

	public void checkTurn() {
		checkIfGameOver();

		if (figureListForTurns.size() == 0) {
			for (WorldObject figure : objectList) {
				if (figure instanceof ActionFigure) {
					figure.setAP(100);
					figureListForTurns.add((ActionFigure) figure);
				}
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

	}

	/**
	 * Assigns value to mGridSize variable, which controls drawing the graphics.
	 * 
	 * @param gameView
	 */
	private void setGridSize(GameView gameView) {
		int density = gameView.getResources().getDisplayMetrics().densityDpi;
		int width = gameView.getResources().getDisplayMetrics().widthPixels;

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
		for (WorldObject object : objectList) {
			if (object instanceof ActionFigure) {
				if (object instanceof EnemyActionFigure) {
					numberOfEnemyFigures++;
				} else {
					numberOfFigures++;
				}
			}
		}

		if (numberOfFigures == 0) {
			this.setRunning(false);
			showGameOverDialog(R.string.mode_lose);
			return true;
		}
		if (numberOfEnemyFigures == 0) {
			this.setRunning(false);
			String PREF_ENABLED_LEVEL = "enabled_level";
			String enabled_level = PreferenceManager
					.getDefaultSharedPreferences(activity).getString(
							PREF_ENABLED_LEVEL, null);
			if (Integer.parseInt(enabled_level) == selected_level) {
				PreferenceManager
						.getDefaultSharedPreferences(activity)
						.edit()
						.putString(PREF_ENABLED_LEVEL,
								Integer.toString(selected_level + 1)).commit();
			}
			showGameOverDialog(R.string.mode_win);
			return true;
		}
		return false;
	}

	public void setActiveFigure(ActionFigure activeFigure) {
		if (activeFigure != getActiveFigure()) {
			this.activeFigure = activeFigure;
		}
	}

	public ActionFigure getActiveFigure() {
		return activeFigure;
	}

	private void showGameOverDialog(int messageId) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				gameView.getContext());
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
			public void run() {
				isDialogDisplayed = true;
				builder.show();
			}
		});

	}
}

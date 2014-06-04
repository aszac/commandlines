package uk.ac.reading.tq011338.commandlines;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;

public class TheMultiplayerGame extends GameThread {

	private Bitmap mFigure_player1;
	private Bitmap mFigureSelected;
	private Bitmap mFigure2Selected;
	private Bitmap mGridTile;
	private Bitmap mTree;

	private Bitmap mRecovery_player1;
	private Bitmap mAttack_player1;
	private Bitmap mDefend_player1;

	private Bitmap mFigure_player2;
	private Bitmap mRecovery_player2;
	private Bitmap mAttack_player2;
	private Bitmap mDefend_player2;

	protected final static int mapSizeX = 8;
	protected final static int mapSizeY = 8;

	protected static int mGridSize = 24;

	private Activity activity;
	private GameView gameView;
	private ActionFigure activeFigure;
	private JSONArray levelObjects;

	private List<ActionFigure> figureListForPlayer1;
	private List<ActionFigure> figureListForPlayer2;

	private boolean isPlayer1Turn = true;

	/**
	 * Constructor called from the activity call, passing the current activity
	 * 
	 * @param gameView
	 * @param activity
	 * @param levelObjects
	 * @throws JSONException
	 */
	public TheMultiplayerGame(final GameView gameView, Activity activity,
			JSONArray levelObjects) throws JSONException {
		super(gameView, activity);
		this.activity = activity;
		this.gameView = gameView;
		this.levelObjects = levelObjects;

		setGridSize(gameView);
		mFigure_player1 = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.face);

		mFigureSelected = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.selectedface);
		
		mFigure2Selected = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.selected_enemy);

		mGridTile = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.square_teal);

		mTree = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.tree);

		mRecovery_player1 = BitmapFactory.decodeResource(
				gameView.getResources(), R.drawable.cross);

		mAttack_player1 = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.angry);

		mDefend_player1 = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.defend);

		mFigure_player2 = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.face_enemy);

		mRecovery_player2 = BitmapFactory.decodeResource(
				gameView.getResources(), R.drawable.cross_enemy);

		mAttack_player2 = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.angry_enemy);

		mDefend_player2 = BitmapFactory.decodeResource(gameView.getResources(),
				R.drawable.defend_enemy);

		createNewWorld();
	}

	/**
	 * Reads a file with the data for the current level
	 * 
	 * @throws JSONException
	 */
	public void createNewWorld() throws JSONException {
		worldMap = new WorldObject[mapSizeX][mapSizeY];
		figureListForPlayer1 = new ArrayList<ActionFigure>();
		figureListForPlayer2 = new ArrayList<ActionFigure>();
		objectList = new ArrayList<WorldObject>();

		for (int i = 0; i < levelObjects.length(); i++) {
			JSONObject JSONobject = levelObjects.getJSONObject(i);

			int hp = JSONobject.getInt("hp");
			int x = JSONobject.getInt("x");
			int y = JSONobject.getInt("y");
			String type = JSONobject.getString("type");
			WorldObject object;

			if (type.contains("EnemyActionFigure")) {
				object = new ActionFigure(x, y, hp, this, false);
				figureListForPlayer2.add((ActionFigure) object);
			} else if (type.contains("Obstacle")) {
				object = new Obstacle(x, y);
			} else {
				object = new ActionFigure(x, y, hp, this, true);
				figureListForPlayer1.add((ActionFigure) object);
			}
			objectList.add(object);
			worldMap[x][y] = object;
		}

	}

	/**
	 * Sets the turn and initiates action for enemy figures
	 */
	public void checkTurn() {
		checkIfGameOver();

		// on start of the new turn
		if (figureListForPlayer1.size() == 0
				&& figureListForPlayer2.size() == 0) {
			// change the turn to player1
			isPlayer1Turn = true;

			for (WorldObject figure : objectList) {
				if (figure instanceof ActionFigure) {
					figure.setAP(100);

					if (!figure.isPlayer1()) {
						figureListForPlayer2.add((ActionFigure) figure);
					} else {
						figureListForPlayer1.add((ActionFigure) figure);
					}
				}
			}
		}

		// perform actions for the current turn
		if (isPlayer1Turn) {
			setActiveFigure(figureListForPlayer1.get(0));
			if (isButtonClicked) {
				figureListForPlayer1.remove(activeFigure);
				isButtonClicked = false;
			}
		} else {
			setActiveFigure(figureListForPlayer2.get(0));
			if (isButtonClicked) {
				figureListForPlayer2.remove(activeFigure);
				isButtonClicked = false;
			}
		}

		// change the turn to player2
		if (figureListForPlayer1.size() == 0) {
			isPlayer1Turn = false;
		}
	}

	/**
	 * Function called from the GUI thread. Draws the game graphics.
	 */
	protected void drawWorld(Canvas canvas) {

		if (canvas == null)
			return;

		if (!wasHit) {
		canvas.drawColor(Color.BLACK);
		}
		else {
			canvas.drawColor(Color.RED);
			wasHit = false;
		}
		for (int i = 0; i < mapSizeX; i++) {
			for (int j = 0; j < mapSizeY; j++) {
				canvas.drawBitmap(mGridTile, i * mGridSize, j * mGridSize, null);
			}
		}

		for (WorldObject object : objectList) {
			int i = object.x;
			int j = object.y;
			if (object instanceof Obstacle) {
				canvas.drawBitmap(mTree, i * mGridSize, j * mGridSize, null);
			} else if (!object.isPlayer1()) {
				if (object != activeFigure) { // use different
					// bitmap
					// if not selected
					switch (object.getState()) {
					case ATTACK:
						canvas.drawBitmap(mAttack_player2, i * mGridSize, j
								* mGridSize, null);
						break;
					case HEAL:
						canvas.drawBitmap(mRecovery_player2, i * mGridSize, j
								* mGridSize, null);
						break;
					case DEFEND:
						canvas.drawBitmap(mDefend_player2, i * mGridSize, j
								* mGridSize, null);
						break;
					default:
						canvas.drawBitmap(mFigure_player2, i * mGridSize, j
								* mGridSize, null);
					}
				} else {
					canvas.drawBitmap(mFigure2Selected, i * mGridSize, j
							* mGridSize, null);
				}

			} else if (object.isPlayer1()) {
				if (object != activeFigure) { // use different
													// bitmap
					// if not selected
					switch (object.getState()) {
					case ATTACK:
						canvas.drawBitmap(mAttack_player1, i * mGridSize, j
								* mGridSize, null);
						break;
					case HEAL:
						canvas.drawBitmap(mRecovery_player1, i * mGridSize, j
								* mGridSize, null);
						break;
					case DEFEND:
						canvas.drawBitmap(mDefend_player1, i * mGridSize, j
								* mGridSize, null);
						break;
					default:
						canvas.drawBitmap(mFigure_player1, i * mGridSize, j
								* mGridSize, null);
					}

				} else {
					canvas.drawBitmap(mFigureSelected, i * mGridSize, j
							* mGridSize, null);
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

	/**
	 * Verifies if the game has been completed
	 * 
	 * @return game over - true / false
	 */
	protected boolean checkIfGameOver() {
		int numberOfPlayer1Figures = 0;
		int numberOfPlayer2Figures = 0;
		for (WorldObject object : objectList) {
			if (object instanceof ActionFigure) {
				if (object.isPlayer1()) {
					numberOfPlayer1Figures++;
				} else {
					numberOfPlayer2Figures++;
				}
			}
		}

		// check if any player 1 figures exist
		if (numberOfPlayer1Figures == 0) {
			this.setRunning(false);
			showGameOverDialog(R.string.player2_win);
			return true;
		}

		// check if any player 2 figures exist
		if (numberOfPlayer2Figures == 0) {
			this.setRunning(false);
			showGameOverDialog(R.string.player1_win);
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

	/**
	 * Displays a dialog with game won / lost message
	 * 
	 * @param messageId
	 *            - game won / lost
	 */
	private void showGameOverDialog(int messageId) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				gameView.getContext());
		builder.setTitle(gameView.getResources().getString(messageId));
		builder.setCancelable(false);

		builder.setPositiveButton(R.string.restart_button,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						activity.finish();
					}
				});

		builder.setNegativeButton(R.string.exit_button,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						activity.finish();
					}
				});

		activity.runOnUiThread(new Runnable() {
			public void run() {
				builder.show();
			}
		});

	}

	/**
	 * Remove killed figure
	 */
	protected void removeFigure(int x, int y) {
		if (isPlayer1Turn) {
			figureListForPlayer2.remove(worldMap[x][y]);
		} else {
			figureListForPlayer1.remove(worldMap[x][y]);
		}

	}
}

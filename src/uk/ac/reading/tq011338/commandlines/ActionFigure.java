package uk.ac.reading.tq011338.commandlines;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ActionFigure extends WorldObject {

	protected int AP;
	protected GameThread mGameThread;
	
	protected boolean isPlayer1 = true;

	/**
	 * Used for direction of motion for an action figure
	 * 
	 */
	public enum Direction { // movement direction
		UP, DOWN, RIGHT, LEFT
	}

	public enum State {
		ATTACK, MOVE, DEFEND, HEAL
	}

	/**
	 * Constructor for new action figure
	 * 
	 * @param x
	 *            - initial x location on the screen
	 * @param y
	 *            - initial y location on the screen
	 */
	public ActionFigure(int x, int y, int hp, GameThread mGameThread2) {
		this.x = x;
		this.y = y;
		this.mGameThread = mGameThread2;
		hitPoints = hp;
		this.state = State.MOVE;
		AP = 100;
	}
	
	public ActionFigure(int x, int y, int hp, GameThread mGameThread2, boolean isPlayer1) {
		this(x, y, hp, mGameThread2);
		this.isPlayer1 = isPlayer1;
	}

	/**
	 * Function that changes the position of the object on the grid, providing
	 * it is not outside of the grid
	 * 
	 * @param direction
	 *            - direction of movement
	 * @param numberOfSteps
	 *            - number of steps to take
	 */
	public void move(Direction direction, int numberOfSteps) {

		state = State.MOVE;
		AP = AP - (10 * numberOfSteps);
		while (numberOfSteps > 0) { // performing one step at a time
			// check if action figure stepping out of the grid
			if (checkIfOutOfBounds(direction))
				break;

			// prevent from moving if another object in the cell
			if (checkIfOtherObjectInCell(direction))
				break;

			// update the position in the world map and update coordinates
			switch (direction) {
			case UP:
				updatePosition(this, x, y - 1);
				y--;
				break;
			case DOWN:
				updatePosition(this, x, y + 1);
				y++;
				break;
			case RIGHT:
				updatePosition(this, x + 1, y);
				x++;
				break;
			case LEFT:
				updatePosition(this, x - 1, y);
				x--;
				break;
			default:
				System.out.println("The direction is incorrect.");
				break;
			}
			numberOfSteps--;
		}

	}

	/**
	 * One step of an action figure in the specified direction
	 * 
	 * @param direction
	 * @return - boolean, is out of bounds
	 */
	public boolean checkIfOutOfBounds(Direction direction) {
		switch (direction) {
		case UP:
			return y - 1 < 0;
		case DOWN:
			return y + 1 >= TheSingleplayerGame.mapSizeY;
		case RIGHT:
			return x + 1 >= TheSingleplayerGame.mapSizeX;
		case LEFT:
			return x - 1 < 0;
		default:
			return true;
		}
	}

	/**
	 * Check if another object in cell.
	 * 
	 * @param direction
	 *            - direction of movement
	 * @return - true if another object in cell
	 */
	public boolean checkIfOtherObjectInCell(Direction direction) {
		switch (direction) {
		case UP:
			return mGameThread.worldMap[x][y - 1] != null;
		case DOWN:
			return mGameThread.worldMap[x][y + 1] != null;
		case RIGHT:
			return mGameThread.worldMap[x + 1][y] != null;
		case LEFT:
			return mGameThread.worldMap[x - 1][y] != null;
		default:
			return true;
		}

	}

	/**
	 * Check if enemy present in the field
	 * 
	 * @param x
	 *            - enemy position x
	 * @param y
	 *            - enemy position y
	 * @return enemy in grid - yes / no
	 */
	public boolean checkIfEnemyInGrid(int x, int y) {
		if (mGameThread.worldMap[x][y] instanceof ActionFigure) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Perform attack action
	 * 
	 * @param direction
	 * @param force
	 */
	public void attack(Direction direction, int force) {
		state = State.ATTACK;
		AP = AP - (10 * force);
		if (!checkIfOutOfBounds(direction)) {
			// check if enemy present in the field && hit
			mGameThread.wasHit = true;
			switch (direction) {
			case UP:
				if (checkIfEnemyInGrid(x, y - 1)) {
					mGameThread.worldMap[x][y - 1].reduceHitPoints(10 * force);
					checkIfKilled(x, y - 1);
				}
				break;
			case DOWN:
				if (checkIfEnemyInGrid(x, y + 1)) {
					mGameThread.worldMap[x][y + 1].reduceHitPoints(10 * force);
					checkIfKilled(x, y + 1);
				}
				break;
			case RIGHT:
				if (checkIfEnemyInGrid(x + 1, y)) {
					mGameThread.worldMap[x + 1][y].reduceHitPoints(10 * force);
					checkIfKilled(x + 1, y);
				}
				break;
			case LEFT:
				if (checkIfEnemyInGrid(x - 1, y)) {
					mGameThread.worldMap[x - 1][y].reduceHitPoints(10 * force);
					checkIfKilled(x - 1, y);
				}
				break;
			default:
				System.out.println("The direction is incorrect.");
				break;
			}
		}

	}

	/**
	 * Perform defend action
	 */
	public void defend() {
		if (AP > 10) {
			state = State.DEFEND;
		}
	}

	/**
	 * Perform heal action
	 * 
	 * @param times
	 */
	public void heal(int times) {
		if (AP > 10) {
			state = State.HEAL;
			AP = AP - (10 * times);
			// increase figures hitpoints
			if (hitPoints < 100) {
				hitPoints = hitPoints + times * 10;
			}
		}
	}

	/**
	 * Check if enemy killed
	 * 
	 * @param x
	 * @param y
	 */
	public void checkIfKilled(int x, int y) {
		// if killed - remove from the map
		if (mGameThread.worldMap[x][y].getHitPoints() <= 0) {
			mGameThread.objectList.remove(mGameThread.worldMap[x][y]);
			try {
				mGameThread.removeFigure(x, y);
			} catch (Exception exception) {

			}
			mGameThread.worldMap[x][y] = null;
		}
	}

	/**
	 * The the enum Direction value given a String
	 */
	public Direction getDirection(String directionString) {
		return Direction.valueOf(directionString);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void reduceHitPoints(int hitPoints) {
		this.hitPoints = this.hitPoints - hitPoints;
	}

	public int getAP() {
		return AP;
	}

	public void setAP(int aP) {
		AP = aP;
	}

	@Override
	public void decideOnNextMove() {
		// TODO Auto-generated method stub

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
	@SuppressWarnings("static-access")
	public void updatePosition(ActionFigure figure, int newX, int newY) {
		mGameThread.worldMap[figure.getX()][figure.getY()] = null;
		mGameThread.worldMap[newX][newY] = figure;
		try {
			mGameThread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Serialize object to json
	 */
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_X, x);
		json.put(JSON_Y, y);
		json.put(JSON_TYPE, this.getClass().getName());
		json.put(JSON_HP, hitPoints);

		Log.d("f", json.toString());

		return json;
	}
	
	public boolean isPlayer1() {
		return isPlayer1;
	}
}

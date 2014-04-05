package uk.ac.reading.tq011338.commandlines;

import org.json.JSONException;
import org.json.JSONObject;

public class ActionFigure implements WorldObject {
	
	protected int x; // x coordinate
	protected int y; // y coordinate

	protected int hitPoints;
	protected int AP;
	protected State state;

	/**
	 * Used for direction of motion for an action figure
	 * 
	 */
	public enum Direction { // movement direction
		// TODO change to private
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
	public ActionFigure(int x, int y) {
		this.x = x;
		this.y = y;
		hitPoints = 100;
		this.state = State.MOVE;
		AP = 100;
	}
	
	public ActionFigure(JSONObject json) throws JSONException {
		x = json.getInt(JSON_X);
		y = json.getInt(JSON_Y);
		hitPoints = json.getInt(JSON_HP);
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
				TheGame.updatePosition(this, x, y - 1);
				y--;
				break;
			case DOWN:
				TheGame.updatePosition(this, x, y + 1);
				y++;
				break;
			case RIGHT:
				TheGame.updatePosition(this, x + 1, y);
				x++;
				break;
			case LEFT:
				TheGame.updatePosition(this, x - 1, y);
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
			return y + 1 >= TheGame.mapSizeY;
		case RIGHT:
			return x + 1 >= TheGame.mapSizeX;
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
			return TheGame.worldMap[x][y - 1] != null;
		case DOWN:
			return TheGame.worldMap[x][y + 1] != null;
		case RIGHT:
			return TheGame.worldMap[x + 1][y] != null;
		case LEFT:
			return TheGame.worldMap[x - 1][y] != null;
		default:
			return true;
		}

	}

	public boolean checkIfEnemyInGrid(int x, int y) {
		if (TheGame.worldMap[x][y] instanceof ActionFigure) {
			return true;
		} else {
			return false;
		}
	}

	public void attack(Direction direction, int force) {
		state = State.ATTACK;
		AP = AP - (10 * force);
		if (!checkIfOutOfBounds(direction)) {
			// check if enemy present in the field && hit
			switch (direction) {
			case UP:
				if (checkIfEnemyInGrid(x, y - 1)) {
					TheGame.worldMap[x][y - 1].reduceHitPoints(10 * force);
					checkIfKilled(x, y - 1);
				}
				break;
			case DOWN:
				if (checkIfEnemyInGrid(x, y + 1)) {
					TheGame.worldMap[x][y + 1].reduceHitPoints(10 * force);
					checkIfKilled(x, y + 1);
				}
				break;
			case RIGHT:
				if (checkIfEnemyInGrid(x + 1, y)) {
					TheGame.worldMap[x + 1][y].reduceHitPoints(10 * force);
					checkIfKilled(x + 1, y);
				}
				break;
			case LEFT:
				if (checkIfEnemyInGrid(x - 1, y)) {
					TheGame.worldMap[x - 1][y].reduceHitPoints(10 * force);
					checkIfKilled(x - 1, y);
				}
				break;
			default:
				System.out.println("The direction is incorrect.");
				break;
			}
		}

	}

	public void defend() {
		// TODO if enough action points defend
		AP = 10;
		state = State.DEFEND;
	}

	public void heal(int times) {
		state = State.HEAL;
		AP = AP - (10 * times);
		// TODO if enough action points
		if (hitPoints < 100) {
			hitPoints = hitPoints + times * 10;
		}
	}

	public void checkIfKilled(int x, int y) {
		if (TheGame.worldMap[x][y].getHitPoints() <= 0) {
			TheGame.figureList.remove(TheGame.worldMap[x][y]);
			try {
				TheGame.figureListForTurns.remove(TheGame.worldMap[x][y]);
			} catch (Exception exception) {

			}
			TheGame.worldMap[x][y] = null;
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
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_TYPE, this.getClass().getName());
		json.put(JSON_HP, hitPoints);
		json.put(JSON_X, x);
		json.put(JSON_Y, y);
		
		return json;
	}
}

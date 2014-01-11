package uk.ac.reading.tq011338.commandlines;

import java.util.ArrayList;
import java.util.List;

import uk.ac.reading.tq011338.state.*;

public class ActionFigure implements WorldObject {
	private int x; // x coordinate
	private int y; // y coordinate
	private boolean selected = false;

	private String command = new String();

	private int hitPoints;

	private FigureState state;

	/**
	 * Used for direction of motion for an action figure
	 * 
	 */
	public enum Direction { // movement direction
		// TODO change to private
		UP, DOWN, RIGHT, LEFT
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
		command = "";
		hitPoints = 100;
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
		
		state = new MoveState();

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

	/**
	 * Find the closest enemy to attack
	 * 
	 * @return
	 */
	private ActionFigure findClosestEnemy() {
		List<ActionFigure> enemyList = new ArrayList<ActionFigure>();
		ActionFigure closestEnemy = null;
		double closestDistance = 100;

		// create enemy list
		for (int i = 0; i < TheGame.worldMap.length; i++) {
			for (int j = 0; j < TheGame.worldMap.length; j++) {
				if (TheGame.worldMap[i][j] instanceof ActionFigure) {
					enemyList.add((ActionFigure) TheGame.worldMap[i][j]);
				}
			}
		}

		// find the Manhattan distance of the target
		for (ActionFigure figure : enemyList) {
			double distance = Math.sqrt(Math.pow(figure.x - x, 2)
					+ Math.pow(figure.y - y, 2));
			if (distance < closestDistance) {
				closestDistance = distance;
				closestEnemy = figure;
			}
		}

		return closestEnemy;
	}

	public void attack(Direction direction, int force) {
		
		// TODO reduce turns
		
		state = new AttackState();

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
		
		state = new DefendState();
	}
	
	public void heal (int times) {
		state = new RecoverState();
		
		// TODO if enough action points
		if (hitPoints < 100) {
			hitPoints = hitPoints + times * 10;
		}
		
	}

	public void checkIfKilled(int x, int y) {
		if (TheGame.worldMap[x][y].getHitPoints() <= 0) {
			TheGame.worldMap[x][y] = null;
		}
	}

	public boolean checkIfEnemyInGrid(int x, int y) {
		if (TheGame.worldMap[x][y] instanceof ActionFigure) {
			return true;
		} else {
			return false;
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

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public FigureState getState() {
		return state;
	}

	public void setState(FigureState state) {
		this.state = state;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void reduceHitPoints(int hitPoints) {
		this.hitPoints = this.hitPoints - hitPoints;
	}

}

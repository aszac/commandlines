package uk.ac.reading.tq011338.commandlines;

import java.util.ArrayList;
import java.util.List;

public class EnemyActionFigure extends ActionFigure {

	protected int x; // x coordinate
	protected int y; // y coordinate

	private int hitPoints;
	private ActionFigure targetEnemy = null;

	public EnemyActionFigure(int x, int y) {
		super(x, y);

	}

	public void decideOnNextMove() {
		targetEnemy = findClosestEnemy();
		if (targetEnemy.getHitPoints() >= (this.hitPoints + TheGame.AP)) {
			if (getDistanceToClosestEnemy() <= 1) {
				this.attack(getDirectionOfEnemy(), 1);
			} else {
				Dijkstra dijkstra = new Dijkstra();
				dijkstra.pathfinding(TheGame.getIndex(this.getX(), this.getY()), TheGame.getIndex(targetEnemy.getX(), targetEnemy.getY()));
				
//				for (Vertex v : dijkstra.getPath()) {
//					v.getX();
//					v.getY();
//				}
//				Vertex nextMove = dijkstra.getPath().get(0);
//				nextMove.getX();
//				nextMove.getY();
			}
		} else if (TheGame.AP < 10) {
			this.defend();
		} else if (this.hitPoints < 40) { // run away
			this.move(getOppositeDirection(), 10);
		} else {
			this.heal(10);
		}
	}

	public int getDistanceToClosestEnemy() {
		int x = this.getX() - targetEnemy.getX();
		int y = this.getY() - targetEnemy.getY();

		return (int) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public Direction getOppositeDirection() {
		Direction enemyDirection = getDirectionOfEnemy();
		switch (enemyDirection) {
		case UP:
			return Direction.DOWN;
		case DOWN:
			return Direction.UP;
		case RIGHT:
			return Direction.LEFT;
		case LEFT:
			return Direction.RIGHT;
		}
		return null;
	}

	public Direction getDirectionOfEnemy() {
		if (targetEnemy.getY() > this.getY()) {
			return Direction.UP;
		} else if (targetEnemy.getY() < this.getY()) {
			return Direction.DOWN;
		} else {
			if (targetEnemy.getX() > this.getX()) {
				return Direction.RIGHT;
			} else {
				return Direction.LEFT;
			}
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
					if (!(TheGame.worldMap[i][j] instanceof EnemyActionFigure)) {
						enemyList.add((ActionFigure) TheGame.worldMap[i][j]);
					}
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

}

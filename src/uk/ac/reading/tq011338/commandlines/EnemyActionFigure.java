package uk.ac.reading.tq011338.commandlines;

import java.util.ArrayList;
import java.util.List;

public class EnemyActionFigure extends ActionFigure {

	private ActionFigure targetEnemy = null;

	public EnemyActionFigure(int x, int y, int hp, GameThread mGameThread) {
		super(x, y, hp, mGameThread);
		hitPoints = 100;
	}

	/**
	 * AI decision for the next move
	 */
	public void decideOnNextMove() {
		targetEnemy = findClosestEnemy();
		if (targetEnemy == null) {
			return;
		}
		
		Dijkstra dijkstra = new Dijkstra(mGameThread.worldMap);
		
		dijkstra.pathfinding(this.getX(), this.getY(), targetEnemy.getX(),
				targetEnemy.getY());
		int counter = 0;

		if (targetEnemy.getHitPoints() <= (this.hitPoints + this.AP)) {
			if (isDistanceToEnemyOne()) {
				this.attack(getDirectionOfEnemy(), 1);
			} else if (dijkstra.getPath().size() >= counter) {
				Vertex nextMove = dijkstra.getPath().get(counter);
				int moveY = nextMove.getX();
				int moveX = nextMove.getY();
				Direction d = getMoveDirection(moveY, moveX);
				this.move(d, 1);
				counter++;
			}
		} else if (this.AP < 10) {
			this.defend();
		} else if (this.hitPoints < 40) { // run away
			this.move(getOppositeDirection(), 10);
		} else {
			this.heal(10);
		}
	}

	/**
	 * Find the direction for the next move
	 * 
	 * @param moveX
	 * @param moveY
	 * @return
	 */
	private Direction getMoveDirection(int moveX, int moveY) {
		if (moveY == this.getY()) {
			if (moveX < this.getX()) {
				return Direction.LEFT;
			} else if (moveX > this.getX()) {
				return Direction.RIGHT;
			}
		} else if (moveX == this.getX()) {
			if (moveY > this.getY()) {
				return Direction.DOWN;
			} else {
				return Direction.UP;
			}
		}
		return null;
	}

	/**
	 * Check if enemy located next to the current figure
	 * 
	 * @return 
	 */
	public boolean isDistanceToEnemyOne() {
		double x = this.getX() - targetEnemy.getX();
		double y = this.getY() - targetEnemy.getY();

		if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) == 1.00) {
			return true;
		} else
			return false;

	}

	/**
	 * Get a direction opposite to enemy direction
	 * 
	 * @return opposite direction
	 */
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

	/**
	 * Get direction of the enemy based on current position
	 * 
	 * @return direction of enemy
	 */
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
	 * @return closest enemy
	 */
	private ActionFigure findClosestEnemy() {
		List<ActionFigure> enemyList = new ArrayList<ActionFigure>();
		ActionFigure closestEnemy = null;
		double closestDistance = 100;

		// create enemy list
		for (int i = 0; i < mGameThread.worldMap.length; i++) {
			for (int j = 0; j < mGameThread.worldMap.length; j++) {
				if (mGameThread.worldMap[i][j] instanceof ActionFigure) {
					if (!(mGameThread.worldMap[i][j] instanceof EnemyActionFigure)) {
						enemyList.add((ActionFigure) mGameThread.worldMap[i][j]);
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

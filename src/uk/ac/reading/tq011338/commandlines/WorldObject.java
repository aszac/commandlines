package uk.ac.reading.tq011338.commandlines;

import uk.ac.reading.tq011338.commandlines.ActionFigure.State;

public interface WorldObject {
	int x = 0;
	int y = 0;
	int hitPoints = 100;
	State state = State.MOVE;

	public int getHitPoints();
	public void reduceHitPoints(int hitPoints);
	public State getState();
	public void decideOnNextMove();
}

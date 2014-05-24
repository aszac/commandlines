package uk.ac.reading.tq011338.commandlines;

import org.json.JSONException;
import org.json.JSONObject;

import uk.ac.reading.tq011338.commandlines.ActionFigure.State;

public abstract class WorldObject {
	int x;
	int y;
	int hitPoints = 100;
	State state = State.MOVE;
	
	static final String JSON_TYPE = "type";
	static final String JSON_HP = "hp";
	static final String JSON_X = "x";
	static final String JSON_Y = "y";
	
	public abstract int getHitPoints();
	public abstract void reduceHitPoints(int hitPoints);
	public abstract State getState();
	public abstract void decideOnNextMove();
	
	public abstract JSONObject toJSON() throws JSONException;
	public abstract void setAP(int i);
	public abstract boolean isPlayer1();
}

package uk.ac.reading.tq011338.state;

import uk.ac.reading.tq011338.commandlines.ActionFigure;

public abstract class FigureState {
	protected ActionFigure figure;
	
	public abstract void move();
	public abstract void attack();
	public abstract void defend();
	public abstract void recover();

	public ActionFigure getFigure() {
		return figure;
	}

	public void setFigure(ActionFigure figure) {
		this.figure = figure;
	}
	
}

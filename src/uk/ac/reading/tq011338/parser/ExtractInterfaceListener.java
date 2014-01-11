package uk.ac.reading.tq011338.parser;

import uk.ac.reading.tq011338.commandlines.ActionFigure;

public class ExtractInterfaceListener extends CommandLinesBaseListener{

	private ActionFigure figure;
	public ExtractInterfaceListener (CommandLinesParser parser, ActionFigure figure) {
		this.figure = figure;
	}
	
	public void enterMove(CommandLinesParser.MoveContext ctx) {


		String noOfSteps = ctx.STEPS().getText();
		String direction = ctx.DIRECTION().getText();
		
		figure.move(figure.getDirection(direction), Integer.parseInt(noOfSteps));
		System.out.println(noOfSteps);
		System.out.println(direction);
		
	}
}

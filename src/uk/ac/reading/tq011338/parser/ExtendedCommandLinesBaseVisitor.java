package uk.ac.reading.tq011338.parser;

import java.util.List;

import uk.ac.reading.tq011338.commandlines.ActionFigure;
import uk.ac.reading.tq011338.commandlines.ActionFigure.Direction;
import uk.ac.reading.tq011338.commandlines.TheGame;
import uk.ac.reading.tq011338.parser.CommandLinesParser.AtomContext;
import uk.ac.reading.tq011338.parser.CommandLinesParser.AttackContext;
import uk.ac.reading.tq011338.parser.CommandLinesParser.DefendContext;
import uk.ac.reading.tq011338.parser.CommandLinesParser.HealContext;
import uk.ac.reading.tq011338.parser.CommandLinesParser.If_statContext;
import uk.ac.reading.tq011338.parser.CommandLinesParser.MoveContext;
import uk.ac.reading.tq011338.parser.CommandLinesParser.While_statContext;

public class ExtendedCommandLinesBaseVisitor extends
		CommandLinesBaseVisitor<Value> {

	private ActionFigure figure;

	public ExtendedCommandLinesBaseVisitor(ActionFigure figure) {
		this.figure = figure;
	}

	public Value visitIf_stat(If_statContext ctx) {

		List<CommandLinesParser.Condition_blockContext> conditions = ctx
				.condition_block();
		boolean evaluatedBlock = false;

		for (CommandLinesParser.Condition_blockContext condition : conditions) {
			Value value = this.visit(condition.expr());

			if (value.asBoolean()) {

				evaluatedBlock = true;
				this.visit(condition.stat_block());
				break;
			}
		}

		if (!evaluatedBlock && ctx.stat_block() != null) {
			// evaluate the else-stat_block (if present == not null)
			this.visit(ctx.stat_block());
		}

		return Value.VOID;
	}

	public Value visitAtom(AtomContext ctx) {
		int size = 2;
		String s = ctx.getText();
		s = s.replace("(", "");
		s = s.replace(")", "");
		String[] atoms = new String[size];
		String operatorPresent = ""; 
		String[] operators = {"<=", ">=", "!=", "==", ">", "<"};
		for (String operator : operators) {
			if (s.contains(operator)) {
			atoms = s.split(operator);
			operatorPresent = operator;
			break;
			}
		}
		
		boolean hasAPHP = false;		
		int[] atomsInt = new int[size];
		if (atoms.length != 0) {
			for (int i = 0; i < size; i++) {
				if (atoms[i].equalsIgnoreCase("AP")) {
					atomsInt[i] = figure.getAP();
					hasAPHP = true;
				}
				else if (atoms[i].equalsIgnoreCase("HP")) {
					atomsInt[i] = figure.getHitPoints();
					hasAPHP = true;
				}
				else {
					atomsInt[i] = Integer.parseInt(atoms[i]);
				}
			}
			
			// make sure that there is at least one AP or HP
			if (!hasAPHP) {
				return new Value(false);
			}
			
			// check which operand is it and evaluate expression
			return getValueOfCondition(operatorPresent, atomsInt);
			

		}
		else {
			return new Value(false);
		}
	}

	private Value getValueOfCondition(String operatorPresent, int[] atomsInt) {
		if (operatorPresent.equals("<=")) {
			if (atomsInt[0] <= atomsInt[1]) {
				return new Value(true);
			}
		}
		else if (operatorPresent.equals(">=")) {
			if (atomsInt[0] >= atomsInt[1]) {
				return new Value(true);
			}
		}
		else if (operatorPresent.equals("!=")) {
			if (atomsInt[0] != atomsInt[1]) {
				return new Value(true);
			}
		}
		else if (operatorPresent.equals("==")) {
			if (atomsInt[0] == atomsInt[1]) {
				return new Value(true);
			}
		}
		else if (operatorPresent.equals(">")) {
			if (atomsInt[0] > atomsInt[1]) {
				return new Value(true);
			}
		}
		else if (operatorPresent.equals("<")) {
			if (atomsInt[0] < atomsInt[1]) {
				return new Value(true);
			}
		}
		return new Value(false);

	}

	public Value visitHeal(HealContext ctx) {
		figure.heal(Integer.parseInt(ctx.INT().getText()));

		return Value.VOID;
	}

	public Value visitAttack(AttackContext ctx) {
		Direction direction = figure.getDirection(ctx.DIRECTION().getText());
		int times = Integer.parseInt(ctx.INT().getText());

		figure.attack(direction, times);

		return Value.VOID;
	}

	public Value visitDefend(DefendContext ctx) {
		figure.defend();
		return Value.VOID;
	}

	public Value visitWhile_stat(While_statContext ctx) {
		Value value = this.visit(ctx.expr());
		int loopCount = 0;

		while (value.asBoolean()) {

			// evaluate the code block
			this.visit(ctx.stat_block());

			// evaluate the expression
			value = this.visit(ctx.expr());
			
			// avoid infinate loops
			if (loopCount < 10) {
			loopCount++;
			}
			else {
				break;
			}
		}

		return Value.VOID;
	}

	public Value visitMove(MoveContext ctx) {
		Direction direction = figure.getDirection(ctx.DIRECTION().getText());
		int times = Integer.parseInt(ctx.INT().getText());

		figure.move(direction, times);

		return Value.VOID;
	}

}

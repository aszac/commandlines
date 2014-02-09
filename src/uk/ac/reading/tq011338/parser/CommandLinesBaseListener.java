package uk.ac.reading.tq011338.parser;

// Generated from CommandLines.g4 by ANTLR 4.0

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;

public class CommandLinesBaseListener implements CommandLinesListener {
	@Override public void enterAtom(CommandLinesParser.AtomContext ctx) { }
	@Override public void exitAtom(CommandLinesParser.AtomContext ctx) { }

	@Override public void enterStat_block(CommandLinesParser.Stat_blockContext ctx) { }
	@Override public void exitStat_block(CommandLinesParser.Stat_blockContext ctx) { }

	@Override public void enterHeal(CommandLinesParser.HealContext ctx) { }
	@Override public void exitHeal(CommandLinesParser.HealContext ctx) { }

	@Override public void enterCondition_block(CommandLinesParser.Condition_blockContext ctx) { }
	@Override public void exitCondition_block(CommandLinesParser.Condition_blockContext ctx) { }

	@Override public void enterBlock(CommandLinesParser.BlockContext ctx) { }
	@Override public void exitBlock(CommandLinesParser.BlockContext ctx) { }

	@Override public void enterExpr(CommandLinesParser.ExprContext ctx) { }
	@Override public void exitExpr(CommandLinesParser.ExprContext ctx) { }

	@Override public void enterAttack(CommandLinesParser.AttackContext ctx) { }
	@Override public void exitAttack(CommandLinesParser.AttackContext ctx) { }

	@Override public void enterDefend(CommandLinesParser.DefendContext ctx) { }
	@Override public void exitDefend(CommandLinesParser.DefendContext ctx) { }

	@Override public void enterParse(CommandLinesParser.ParseContext ctx) { }
	@Override public void exitParse(CommandLinesParser.ParseContext ctx) { }

	@Override public void enterWhile_stat(CommandLinesParser.While_statContext ctx) { }
	@Override public void exitWhile_stat(CommandLinesParser.While_statContext ctx) { }

	@Override public void enterMove(CommandLinesParser.MoveContext ctx) { }
	@Override public void exitMove(CommandLinesParser.MoveContext ctx) { }

	@Override public void enterStat(CommandLinesParser.StatContext ctx) { }
	@Override public void exitStat(CommandLinesParser.StatContext ctx) { }

	@Override public void enterIf_stat(CommandLinesParser.If_statContext ctx) { }
	@Override public void exitIf_stat(CommandLinesParser.If_statContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	@Override public void visitTerminal(TerminalNode node) { }
	@Override public void visitErrorNode(ErrorNode node) { }
}
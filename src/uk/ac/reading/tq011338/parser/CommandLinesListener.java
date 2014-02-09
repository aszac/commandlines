package uk.ac.reading.tq011338.parser;

// Generated from CommandLines.g4 by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface CommandLinesListener extends ParseTreeListener {
	void enterAtom(CommandLinesParser.AtomContext ctx);
	void exitAtom(CommandLinesParser.AtomContext ctx);

	void enterStat_block(CommandLinesParser.Stat_blockContext ctx);
	void exitStat_block(CommandLinesParser.Stat_blockContext ctx);

	void enterHeal(CommandLinesParser.HealContext ctx);
	void exitHeal(CommandLinesParser.HealContext ctx);

	void enterCondition_block(CommandLinesParser.Condition_blockContext ctx);
	void exitCondition_block(CommandLinesParser.Condition_blockContext ctx);

	void enterBlock(CommandLinesParser.BlockContext ctx);
	void exitBlock(CommandLinesParser.BlockContext ctx);

	void enterExpr(CommandLinesParser.ExprContext ctx);
	void exitExpr(CommandLinesParser.ExprContext ctx);

	void enterAttack(CommandLinesParser.AttackContext ctx);
	void exitAttack(CommandLinesParser.AttackContext ctx);

	void enterDefend(CommandLinesParser.DefendContext ctx);
	void exitDefend(CommandLinesParser.DefendContext ctx);

	void enterParse(CommandLinesParser.ParseContext ctx);
	void exitParse(CommandLinesParser.ParseContext ctx);

	void enterWhile_stat(CommandLinesParser.While_statContext ctx);
	void exitWhile_stat(CommandLinesParser.While_statContext ctx);

	void enterMove(CommandLinesParser.MoveContext ctx);
	void exitMove(CommandLinesParser.MoveContext ctx);

	void enterStat(CommandLinesParser.StatContext ctx);
	void exitStat(CommandLinesParser.StatContext ctx);

	void enterIf_stat(CommandLinesParser.If_statContext ctx);
	void exitIf_stat(CommandLinesParser.If_statContext ctx);
}
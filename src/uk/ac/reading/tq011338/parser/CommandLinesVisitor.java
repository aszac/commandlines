package uk.ac.reading.tq011338.parser;

// Generated from CommandLines.g4 by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;

public interface CommandLinesVisitor<T> extends ParseTreeVisitor<T> {
	T visitAtom(CommandLinesParser.AtomContext ctx);

	T visitStat_block(CommandLinesParser.Stat_blockContext ctx);

	T visitHeal(CommandLinesParser.HealContext ctx);

	T visitCondition_block(CommandLinesParser.Condition_blockContext ctx);

	T visitBlock(CommandLinesParser.BlockContext ctx);

	T visitExpr(CommandLinesParser.ExprContext ctx);

	T visitAttack(CommandLinesParser.AttackContext ctx);

	T visitDefend(CommandLinesParser.DefendContext ctx);

	T visitParse(CommandLinesParser.ParseContext ctx);

	T visitWhile_stat(CommandLinesParser.While_statContext ctx);

	T visitMove(CommandLinesParser.MoveContext ctx);

	T visitStat(CommandLinesParser.StatContext ctx);

	T visitIf_stat(CommandLinesParser.If_statContext ctx);
}
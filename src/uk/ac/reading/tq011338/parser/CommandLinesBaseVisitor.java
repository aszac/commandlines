package uk.ac.reading.tq011338.parser;

// Generated from CommandLines.g4 by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;

public class CommandLinesBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements CommandLinesVisitor<T> {
	@Override public T visitAtom(CommandLinesParser.AtomContext ctx) { return visitChildren(ctx); }

	@Override public T visitStat_block(CommandLinesParser.Stat_blockContext ctx) { return visitChildren(ctx); }

	@Override public T visitHeal(CommandLinesParser.HealContext ctx) { return visitChildren(ctx); }

	@Override public T visitCondition_block(CommandLinesParser.Condition_blockContext ctx) { return visitChildren(ctx); }

	@Override public T visitBlock(CommandLinesParser.BlockContext ctx) { return visitChildren(ctx); }

	@Override public T visitExpr(CommandLinesParser.ExprContext ctx) { return visitChildren(ctx); }

	@Override public T visitAttack(CommandLinesParser.AttackContext ctx) { return visitChildren(ctx); }

	@Override public T visitDefend(CommandLinesParser.DefendContext ctx) { return visitChildren(ctx); }

	@Override public T visitParse(CommandLinesParser.ParseContext ctx) { return visitChildren(ctx); }

	@Override public T visitWhile_stat(CommandLinesParser.While_statContext ctx) { return visitChildren(ctx); }

	@Override public T visitMove(CommandLinesParser.MoveContext ctx) { return visitChildren(ctx); }

	@Override public T visitStat(CommandLinesParser.StatContext ctx) { return visitChildren(ctx); }

	@Override public T visitIf_stat(CommandLinesParser.If_statContext ctx) { return visitChildren(ctx); }
}
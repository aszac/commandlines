package uk.ac.reading.tq011338.parser;

// Generated from CommandLines.g4 by ANTLR 4.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CommandLinesParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DIRECTION=1, EQ=2, NEQ=3, GT=4, LT=5, GTEQ=6, LTEQ=7, UP=8, DOWN=9, LEFT=10, 
		RIGHT=11, COMMA=12, HP=13, AP=14, SCOL=15, OPAR=16, CPAR=17, OBRACE=18, 
		CBRACE=19, IF=20, ELSE=21, WHILE=22, MOVE=23, DEFEND=24, HEAL=25, ATTACK=26, 
		INT=27, WS=28;
	public static final String[] tokenNames = {
		"<INVALID>", "DIRECTION", "'=='", "'!='", "'>'", "'<'", "'>='", "'<='", 
		"'UP'", "'DOWN'", "'LEFT'", "'RIGHT'", "','", "'HP'", "'AP'", "';'", "'('", 
		"')'", "'{'", "'}'", "'if'", "'else'", "'while'", "'move'", "'defend'", 
		"'heal'", "'attack'", "INT", "WS"
	};
	public static final int
		RULE_parse = 0, RULE_block = 1, RULE_stat = 2, RULE_defend = 3, RULE_heal = 4, 
		RULE_attack = 5, RULE_move = 6, RULE_if_stat = 7, RULE_condition_block = 8, 
		RULE_stat_block = 9, RULE_while_stat = 10, RULE_expr = 11, RULE_atom = 12;
	public static final String[] ruleNames = {
		"parse", "block", "stat", "defend", "heal", "attack", "move", "if_stat", 
		"condition_block", "stat_block", "while_stat", "expr", "atom"
	};

	@Override
	public String getGrammarFileName() { return "CommandLines.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public CommandLinesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CommandLinesParser.EOF, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLinesVisitor ) return ((CommandLinesVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26); block();
			setState(27); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLinesVisitor ) return ((CommandLinesVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << MOVE) | (1L << DEFEND) | (1L << HEAL) | (1L << ATTACK))) != 0)) {
				{
				{
				setState(29); stat();
				}
				}
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public HealContext heal() {
			return getRuleContext(HealContext.class,0);
		}
		public DefendContext defend() {
			return getRuleContext(DefendContext.class,0);
		}
		public AttackContext attack() {
			return getRuleContext(AttackContext.class,0);
		}
		public MoveContext move() {
			return getRuleContext(MoveContext.class,0);
		}
		public While_statContext while_stat() {
			return getRuleContext(While_statContext.class,0);
		}
		public If_statContext if_stat() {
			return getRuleContext(If_statContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLinesVisitor ) return ((CommandLinesVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_stat);
		try {
			setState(41);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(35); if_stat();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(36); while_stat();
				}
				break;
			case MOVE:
				enterOuterAlt(_localctx, 3);
				{
				setState(37); move();
				}
				break;
			case ATTACK:
				enterOuterAlt(_localctx, 4);
				{
				setState(38); attack();
				}
				break;
			case DEFEND:
				enterOuterAlt(_localctx, 5);
				{
				setState(39); defend();
				}
				break;
			case HEAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(40); heal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefendContext extends ParserRuleContext {
		public TerminalNode SCOL() { return getToken(CommandLinesParser.SCOL, 0); }
		public TerminalNode DEFEND() { return getToken(CommandLinesParser.DEFEND, 0); }
		public TerminalNode OPAR() { return getToken(CommandLinesParser.OPAR, 0); }
		public TerminalNode CPAR() { return getToken(CommandLinesParser.CPAR, 0); }
		public DefendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).enterDefend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).exitDefend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLinesVisitor ) return ((CommandLinesVisitor<? extends T>)visitor).visitDefend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefendContext defend() throws RecognitionException {
		DefendContext _localctx = new DefendContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_defend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43); match(DEFEND);
			setState(44); match(OPAR);
			setState(45); match(CPAR);
			setState(46); match(SCOL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HealContext extends ParserRuleContext {
		public TerminalNode SCOL() { return getToken(CommandLinesParser.SCOL, 0); }
		public TerminalNode INT() { return getToken(CommandLinesParser.INT, 0); }
		public TerminalNode OPAR() { return getToken(CommandLinesParser.OPAR, 0); }
		public TerminalNode HEAL() { return getToken(CommandLinesParser.HEAL, 0); }
		public TerminalNode CPAR() { return getToken(CommandLinesParser.CPAR, 0); }
		public HealContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).enterHeal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).exitHeal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLinesVisitor ) return ((CommandLinesVisitor<? extends T>)visitor).visitHeal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HealContext heal() throws RecognitionException {
		HealContext _localctx = new HealContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_heal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); match(HEAL);
			setState(49); match(OPAR);
			setState(50); match(INT);
			setState(51); match(CPAR);
			setState(52); match(SCOL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttackContext extends ParserRuleContext {
		public TerminalNode DIRECTION() { return getToken(CommandLinesParser.DIRECTION, 0); }
		public TerminalNode SCOL() { return getToken(CommandLinesParser.SCOL, 0); }
		public TerminalNode INT() { return getToken(CommandLinesParser.INT, 0); }
		public TerminalNode OPAR() { return getToken(CommandLinesParser.OPAR, 0); }
		public TerminalNode ATTACK() { return getToken(CommandLinesParser.ATTACK, 0); }
		public TerminalNode COMMA() { return getToken(CommandLinesParser.COMMA, 0); }
		public TerminalNode CPAR() { return getToken(CommandLinesParser.CPAR, 0); }
		public AttackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attack; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).enterAttack(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).exitAttack(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLinesVisitor ) return ((CommandLinesVisitor<? extends T>)visitor).visitAttack(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttackContext attack() throws RecognitionException {
		AttackContext _localctx = new AttackContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_attack);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54); match(ATTACK);
			setState(55); match(OPAR);
			setState(56); match(DIRECTION);
			setState(57); match(COMMA);
			setState(58); match(INT);
			setState(59); match(CPAR);
			setState(60); match(SCOL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MoveContext extends ParserRuleContext {
		public TerminalNode DIRECTION() { return getToken(CommandLinesParser.DIRECTION, 0); }
		public TerminalNode SCOL() { return getToken(CommandLinesParser.SCOL, 0); }
		public TerminalNode INT() { return getToken(CommandLinesParser.INT, 0); }
		public TerminalNode OPAR() { return getToken(CommandLinesParser.OPAR, 0); }
		public TerminalNode COMMA() { return getToken(CommandLinesParser.COMMA, 0); }
		public TerminalNode MOVE() { return getToken(CommandLinesParser.MOVE, 0); }
		public TerminalNode CPAR() { return getToken(CommandLinesParser.CPAR, 0); }
		public MoveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_move; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).enterMove(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).exitMove(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLinesVisitor ) return ((CommandLinesVisitor<? extends T>)visitor).visitMove(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoveContext move() throws RecognitionException {
		MoveContext _localctx = new MoveContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_move);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); match(MOVE);
			setState(63); match(OPAR);
			setState(64); match(DIRECTION);
			setState(65); match(COMMA);
			setState(66); match(INT);
			setState(67); match(CPAR);
			setState(68); match(SCOL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_statContext extends ParserRuleContext {
		public Condition_blockContext condition_block(int i) {
			return getRuleContext(Condition_blockContext.class,i);
		}
		public Stat_blockContext stat_block() {
			return getRuleContext(Stat_blockContext.class,0);
		}
		public TerminalNode ELSE(int i) {
			return getToken(CommandLinesParser.ELSE, i);
		}
		public TerminalNode IF(int i) {
			return getToken(CommandLinesParser.IF, i);
		}
		public List<Condition_blockContext> condition_block() {
			return getRuleContexts(Condition_blockContext.class);
		}
		public List<TerminalNode> ELSE() { return getTokens(CommandLinesParser.ELSE); }
		public List<TerminalNode> IF() { return getTokens(CommandLinesParser.IF); }
		public If_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).enterIf_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).exitIf_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLinesVisitor ) return ((CommandLinesVisitor<? extends T>)visitor).visitIf_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_statContext if_stat() throws RecognitionException {
		If_statContext _localctx = new If_statContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_if_stat);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(70); match(IF);
			setState(71); condition_block();
			setState(77);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(72); match(ELSE);
					setState(73); match(IF);
					setState(74); condition_block();
					}
					} 
				}
				setState(79);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(82);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(80); match(ELSE);
				setState(81); stat_block();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Condition_blockContext extends ParserRuleContext {
		public Stat_blockContext stat_block() {
			return getRuleContext(Stat_blockContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Condition_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).enterCondition_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).exitCondition_block(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLinesVisitor ) return ((CommandLinesVisitor<? extends T>)visitor).visitCondition_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Condition_blockContext condition_block() throws RecognitionException {
		Condition_blockContext _localctx = new Condition_blockContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_condition_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); expr(0);
			setState(85); stat_block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Stat_blockContext extends ParserRuleContext {
		public TerminalNode OBRACE() { return getToken(CommandLinesParser.OBRACE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode CBRACE() { return getToken(CommandLinesParser.CBRACE, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public Stat_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).enterStat_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).exitStat_block(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLinesVisitor ) return ((CommandLinesVisitor<? extends T>)visitor).visitStat_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Stat_blockContext stat_block() throws RecognitionException {
		Stat_blockContext _localctx = new Stat_blockContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_stat_block);
		try {
			setState(92);
			switch (_input.LA(1)) {
			case OBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(87); match(OBRACE);
				setState(88); block();
				setState(89); match(CBRACE);
				}
				break;
			case IF:
			case WHILE:
			case MOVE:
			case DEFEND:
			case HEAL:
			case ATTACK:
				enterOuterAlt(_localctx, 2);
				{
				setState(91); stat();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_statContext extends ParserRuleContext {
		public Stat_blockContext stat_block() {
			return getRuleContext(Stat_blockContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(CommandLinesParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public While_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).enterWhile_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).exitWhile_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLinesVisitor ) return ((CommandLinesVisitor<? extends T>)visitor).visitWhile_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_statContext while_stat() throws RecognitionException {
		While_statContext _localctx = new While_statContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_while_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94); match(WHILE);
			setState(95); expr(0);
			setState(96); stat_block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public int _p;
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode GT() { return getToken(CommandLinesParser.GT, 0); }
		public TerminalNode LT() { return getToken(CommandLinesParser.LT, 0); }
		public TerminalNode NEQ() { return getToken(CommandLinesParser.NEQ, 0); }
		public TerminalNode EQ() { return getToken(CommandLinesParser.EQ, 0); }
		public TerminalNode GTEQ() { return getToken(CommandLinesParser.GTEQ, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode LTEQ() { return getToken(CommandLinesParser.LTEQ, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExprContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLinesVisitor ) return ((CommandLinesVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState, _p);
		ExprContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, RULE_expr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(99); atom();
			}
			_ctx.stop = _input.LT(-1);
			setState(121);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(119);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(101);
						if (!(7 >= _localctx._p)) throw new FailedPredicateException(this, "7 >= $_p");
						setState(102); match(LTEQ);
						setState(103); expr(8);
						}
						break;

					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(104);
						if (!(6 >= _localctx._p)) throw new FailedPredicateException(this, "6 >= $_p");
						setState(105); match(GTEQ);
						setState(106); expr(7);
						}
						break;

					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(107);
						if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
						setState(108); match(LT);
						setState(109); expr(6);
						}
						break;

					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(110);
						if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "4 >= $_p");
						setState(111); match(GT);
						setState(112); expr(5);
						}
						break;

					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(113);
						if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
						setState(114); match(NEQ);
						setState(115); expr(4);
						}
						break;

					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(116);
						if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
						setState(117); match(EQ);
						setState(118); expr(3);
						}
						break;
					}
					} 
				}
				setState(123);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(CommandLinesParser.INT, 0); }
		public TerminalNode OPAR() { return getToken(CommandLinesParser.OPAR, 0); }
		public TerminalNode HP() { return getToken(CommandLinesParser.HP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode AP() { return getToken(CommandLinesParser.AP, 0); }
		public TerminalNode CPAR() { return getToken(CommandLinesParser.CPAR, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLinesListener ) ((CommandLinesListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLinesVisitor ) return ((CommandLinesVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_atom);
		try {
			setState(131);
			switch (_input.LA(1)) {
			case OPAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(124); match(OPAR);
				setState(125); expr(0);
				setState(126); match(CPAR);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(128); match(INT);
				}
				break;
			case HP:
				enterOuterAlt(_localctx, 3);
				{
				setState(129); match(HP);
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 4);
				{
				setState(130); match(AP);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 7 >= _localctx._p;

		case 1: return 6 >= _localctx._p;

		case 2: return 5 >= _localctx._p;

		case 3: return 4 >= _localctx._p;

		case 4: return 3 >= _localctx._p;

		case 5: return 2 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\2\3\36\u0088\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\7"+
		"\3!\n\3\f\3\16\3$\13\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4,\n\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\7\tN\n\t\f\t\16\tQ\13\t\3"+
		"\t\3\t\5\tU\n\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\5\13_\n\13\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\7\rz\n\r\f\r\16\r}\13\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\5\16\u0086\n\16\3\16\2\17\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\2\2\u008c\2\34\3\2\2\2\4\"\3\2\2\2\6+\3\2\2\2\b-\3\2\2\2\n\62\3\2"+
		"\2\2\f8\3\2\2\2\16@\3\2\2\2\20H\3\2\2\2\22V\3\2\2\2\24^\3\2\2\2\26`\3"+
		"\2\2\2\30d\3\2\2\2\32\u0085\3\2\2\2\34\35\5\4\3\2\35\36\7\1\2\2\36\3\3"+
		"\2\2\2\37!\5\6\4\2 \37\3\2\2\2!$\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#\5\3\2"+
		"\2\2$\"\3\2\2\2%,\5\20\t\2&,\5\26\f\2\',\5\16\b\2(,\5\f\7\2),\5\b\5\2"+
		"*,\5\n\6\2+%\3\2\2\2+&\3\2\2\2+\'\3\2\2\2+(\3\2\2\2+)\3\2\2\2+*\3\2\2"+
		"\2,\7\3\2\2\2-.\7\32\2\2./\7\22\2\2/\60\7\23\2\2\60\61\7\21\2\2\61\t\3"+
		"\2\2\2\62\63\7\33\2\2\63\64\7\22\2\2\64\65\7\35\2\2\65\66\7\23\2\2\66"+
		"\67\7\21\2\2\67\13\3\2\2\289\7\34\2\29:\7\22\2\2:;\7\3\2\2;<\7\16\2\2"+
		"<=\7\35\2\2=>\7\23\2\2>?\7\21\2\2?\r\3\2\2\2@A\7\31\2\2AB\7\22\2\2BC\7"+
		"\3\2\2CD\7\16\2\2DE\7\35\2\2EF\7\23\2\2FG\7\21\2\2G\17\3\2\2\2HI\7\26"+
		"\2\2IO\5\22\n\2JK\7\27\2\2KL\7\26\2\2LN\5\22\n\2MJ\3\2\2\2NQ\3\2\2\2O"+
		"M\3\2\2\2OP\3\2\2\2PT\3\2\2\2QO\3\2\2\2RS\7\27\2\2SU\5\24\13\2TR\3\2\2"+
		"\2TU\3\2\2\2U\21\3\2\2\2VW\5\30\r\2WX\5\24\13\2X\23\3\2\2\2YZ\7\24\2\2"+
		"Z[\5\4\3\2[\\\7\25\2\2\\_\3\2\2\2]_\5\6\4\2^Y\3\2\2\2^]\3\2\2\2_\25\3"+
		"\2\2\2`a\7\30\2\2ab\5\30\r\2bc\5\24\13\2c\27\3\2\2\2de\b\r\1\2ef\5\32"+
		"\16\2f{\3\2\2\2gh\6\r\2\3hi\7\t\2\2iz\5\30\r\2jk\6\r\3\3kl\7\b\2\2lz\5"+
		"\30\r\2mn\6\r\4\3no\7\7\2\2oz\5\30\r\2pq\6\r\5\3qr\7\6\2\2rz\5\30\r\2"+
		"st\6\r\6\3tu\7\5\2\2uz\5\30\r\2vw\6\r\7\3wx\7\4\2\2xz\5\30\r\2yg\3\2\2"+
		"\2yj\3\2\2\2ym\3\2\2\2yp\3\2\2\2ys\3\2\2\2yv\3\2\2\2z}\3\2\2\2{y\3\2\2"+
		"\2{|\3\2\2\2|\31\3\2\2\2}{\3\2\2\2~\177\7\22\2\2\177\u0080\5\30\r\2\u0080"+
		"\u0081\7\23\2\2\u0081\u0086\3\2\2\2\u0082\u0086\7\35\2\2\u0083\u0086\7"+
		"\17\2\2\u0084\u0086\7\20\2\2\u0085~\3\2\2\2\u0085\u0082\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0085\u0084\3\2\2\2\u0086\33\3\2\2\2\n\"+OT^y{\u0085";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}
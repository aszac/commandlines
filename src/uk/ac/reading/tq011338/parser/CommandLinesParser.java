// Generated from CommandLines.g4 by ANTLR 4.x
package uk.ac.reading.tq011338.parser;
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
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, DIRECTION=6, STEPS=7, WS=8;
	public static final String[] tokenNames = {
		"<INVALID>", "'figure.move'", "')'", "','", "'('", "';'", "DIRECTION", 
		"STEPS", "WS"
	};
	public static final int
		RULE_move = 0;
	public static final String[] ruleNames = {
		"move"
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
	public static class MoveContext extends ParserRuleContext {
		public TerminalNode DIRECTION() { return getToken(CommandLinesParser.DIRECTION, 0); }
		public TerminalNode STEPS() { return getToken(CommandLinesParser.STEPS, 0); }
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
	}

	public final MoveContext move() throws RecognitionException {
		MoveContext _localctx = new MoveContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_move);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2); match(1);
			setState(3); match(4);
			setState(4); match(DIRECTION);
			setState(5); match(3);
			setState(6); match(STEPS);
			setState(7); match(2);
			setState(8); match(5);
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

	public static final String _serializedATN =
		"\2\3\n\r\4\2\t\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\2\3\2\2\2\13\2\4"+
		"\3\2\2\2\4\5\7\3\2\2\5\6\7\6\2\2\6\7\7\b\2\2\7\b\7\5\2\2\b\t\7\t\2\2\t"+
		"\n\7\4\2\2\n\13\7\7\2\2\13\3\3\2\2\2\2";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}
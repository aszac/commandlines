// Generated from CommandLines.g4 by ANTLR 4.x
package uk.ac.reading.tq011338.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CommandLinesLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, DIRECTION=6, STEPS=7, WS=8;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'figure.move'", "')'", "','", "'('", "';'", "DIRECTION", "STEPS", "WS"
	};
	public static final String[] ruleNames = {
		"T__4", "T__3", "T__2", "T__1", "T__0", "DIRECTION", "STEPS", "WS"
	};


	public CommandLinesLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CommandLines.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 7: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\nA\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7\67\n\7\3\b\3\b\3\t\6\t<\n\t\r\t\16\t=\3\t\3\t\2\n\3\3\1\5\4"+
		"\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\2\3\2\3\5\13\f\17\17\"\"D\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\3\23\3\2\2\2\5\37\3\2\2\2\7!\3\2\2\2\t#\3\2"+
		"\2\2\13%\3\2\2\2\r\66\3\2\2\2\178\3\2\2\2\21;\3\2\2\2\23\24\7h\2\2\24"+
		"\25\7k\2\2\25\26\7i\2\2\26\27\7w\2\2\27\30\7t\2\2\30\31\7g\2\2\31\32\7"+
		"\60\2\2\32\33\7o\2\2\33\34\7q\2\2\34\35\7x\2\2\35\36\7g\2\2\36\4\3\2\2"+
		"\2\37 \7+\2\2 \6\3\2\2\2!\"\7.\2\2\"\b\3\2\2\2#$\7*\2\2$\n\3\2\2\2%&\7"+
		"=\2\2&\f\3\2\2\2\'(\7W\2\2(\67\7R\2\2)*\7F\2\2*+\7Q\2\2+,\7Y\2\2,\67\7"+
		"P\2\2-.\7T\2\2./\7K\2\2/\60\7I\2\2\60\61\7J\2\2\61\67\7V\2\2\62\63\7N"+
		"\2\2\63\64\7G\2\2\64\65\7H\2\2\65\67\7V\2\2\66\'\3\2\2\2\66)\3\2\2\2\66"+
		"-\3\2\2\2\66\62\3\2\2\2\67\16\3\2\2\289\4\63;\29\20\3\2\2\2:<\t\2\2\2"+
		";:\3\2\2\2<=\3\2\2\2=;\3\2\2\2=>\3\2\2\2>?\3\2\2\2?@\b\t\2\2@\22\3\2\2"+
		"\2\5\2\66=";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}
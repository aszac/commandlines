# grammar based on a tutorial http://stackoverflow.com/questions/15610183/if-else-statements-in-antlr-using-listeners by Bart Kiers

grammar CommandLines;

parse
 : block EOF;

block
 : stat*;

stat
 : if_stat
 | while_stat
 | move
 | attack
 | defend
 | heal;

defend 
    : DEFEND OPAR  CPAR SCOL;
	
heal 
    : HEAL OPAR INT CPAR SCOL;

attack 
    : ATTACK OPAR DIRECTION COMMA INT CPAR SCOL;

move 
    : MOVE OPAR DIRECTION COMMA INT CPAR SCOL;

DIRECTION 
    : UP 
    | DOWN
    | LEFT 
    | RIGHT;

if_stat
 : IF condition_block (ELSE IF condition_block)* (ELSE stat_block)?;

condition_block
 : expr stat_block;

stat_block
 : OBRACE block CBRACE
 | stat;

while_stat
 : WHILE expr stat_block;

expr
 : expr LTEQ expr
 | expr GTEQ expr
 | expr LT expr
 | expr GT expr
 | expr NEQ expr
 | expr EQ expr
 | atom;

atom
 : OPAR expr CPAR
 | INT
 | HP
 | AP;

EQ              : '==';
NEQ             : '!=';
GT              : '>';
LT              : '<';
GTEQ            : '>=';
LTEQ            : '<=';
UP              : 'UP';
DOWN            : 'DOWN';
LEFT            : 'LEFT';
RIGHT           : 'RIGHT';
COMMA           : ',';
HP              : 'HP';
AP              : 'AP';
SCOL            : ';';
OPAR            : '(';
CPAR            : ')';
OBRACE          : '{';
CBRACE          : '}';
IF              : 'if';
ELSE            : 'else';
WHILE           : 'while';
MOVE		: 'move';
DEFEND		: 'defend';
HEAL		: 'heal';
ATTACK		: 'attack';

INT
 : [0-9]+;

WS  :  [ \t\r\n\u000C]+ -> skip;
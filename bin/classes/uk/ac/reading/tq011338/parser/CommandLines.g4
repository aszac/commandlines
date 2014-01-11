grammar CommandLines;

move : 'figure.move' '(' DIRECTION ',' STEPS ')' ';';

DIRECTION : 'UP' | 'DOWN' | 'RIGHT' | 'LEFT';

STEPS : '1'..'9';

WS : [ \t\r\n]+ -> skip ;
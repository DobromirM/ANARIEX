lexer grammar AnariexLexer;

// General
PRINT               : 'print' ;
ASSIGN              : '=' ;
LPAREN              : '(' ;
RPAREN              : ')' ;
fragment POINT               : '.' ;
fragment DIGIT               : ('0' .. '9') ;
fragment LETTER              : ('a' .. 'z') | ('A' .. 'Z') ;

// Comments
COMMENT             : '#' .*? '#' -> skip;

// Operators
PLUS                : '+' ;
MINUS               : '-' ;
MULT                : '*' ;
DIV                 : '/' ;
POW                 : '^' ;

// Functions
COS                 : 'cos' ;
SIN                 : 'sin' ;
TAN                 : 'tan' ;
LOG                 : 'log' ;

// Numbers
NUMBER              : DIGIT+ (POINT DIGIT+)? ;

// Variables
DEF                 : 'def' ;
ID                  : LETTER (LETTER | DIGIT)* ;

//Command Terminator
TERM                : ';' ;

//White Space
WS                  : [ \t\r\n]+ -> skip ;

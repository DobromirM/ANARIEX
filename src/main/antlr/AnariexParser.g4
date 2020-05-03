parser grammar AnariexParser;

options { tokenVocab = AnariexLexer; }

file                : lines = line* EOF;
line                : statement TERM ;

statement           : declaration                                                       # declarationStatement
                    | assignment                                                        # assignmentStatement
                    | evaluation                                                        # evaluationStatement ;

expression          : left = expression operator = POW            right = expression    # binaryOperation
                    | left = expression operator = (DIV | MULT)   right = expression    # binaryOperation
                    | left = expression operator = (PLUS | MINUS) right = expression    # binaryOperation
                    | operator = function  argument = grouping                          # unaryOperation
                    | grouping                                                          # groupingOperation
                    | ID                                                                # variable
                    | NUMBER                                                            # number ;

function            : (COS | SIN | TAN | LOG | MINUS) ;
grouping            : LPAREN expression RPAREN ;
declaration         : DEF name = ID;
assignment          : name = ID op = ASSIGN value = expression ;
evaluation          : op = PRINT LPAREN expression RPAREN;
grammar RouteSpecification;

options {
    backtrack=true;
    output=AST; 	
    ASTLabelType=CommonTree;
    k=1; 
}

@header {
    package com.khubla.pragmatach.framework.uri.antlr;
} 

@lexer::header {
    package com.khubla.pragmatach.framework.uri.antlr;
} 

routespecification 
	: ('/'! segment)+;
segment	
	: pathsegment | idsegment;	
idsegment
	: '@'^ id;	
pathsegment
	: ALPHANUM^;
id	
	: ALPHANUM;
ALPHANUM
    	: ('a'..'z'|'A'..'Z'|'0'..'9')+ ;
    
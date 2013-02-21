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
	: ('/' (staticsegment | dynamicsegment))+;
	
staticsegment	
	: STRING;	
dynamicsegment
	: '{' expression '}' ('@' id)?;	
expression
	: STRING;
id	
	: STRING;	
STRING
    	:   
    	('a'..'z'|'A'..'Z'|'0'..'9' | '-' )* ;
	    
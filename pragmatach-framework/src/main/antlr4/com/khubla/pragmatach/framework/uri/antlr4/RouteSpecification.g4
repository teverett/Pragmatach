grammar RouteSpecification;

routespecification 
	: ('/' segment?)+;
segment	
	: pathsegment | idsegment;	
idsegment
	: AMPER id;	
pathsegment
	: ALPHANUM;
id	
	: ALPHANUM;
ALPHANUM
    : ('a'..'z'|'A'..'Z'|'0'..'9' | '*')+ ;
AMPER	
	: '@';
	
    
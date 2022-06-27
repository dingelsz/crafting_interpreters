default: lox/*.java
	#*******************************************************************************
	# MAKING....
	#*******************************************************************************
	# Build expressions
	rm -f lox/Expr.java lox/Stmt.java
	javac lox/tool/*.java
	java lox/tool/GenerateAst lox/
	# build library
	javac lox/*.java

run: default
	java lox/Lox

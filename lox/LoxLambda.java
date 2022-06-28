package lox;

import java.util.List;

class LoxLambda implements LoxCallable {
    private final Expr.Lambda lambda;
    private final Environment closure;
    
    LoxLambda(Expr.Lambda lambda, Environment environment) {
	this.closure = environment;
	this.lambda  = lambda;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
	Environment environment = new Environment(closure);
	for (int i = 0; i < arguments.size(); i++) {
	    environment.define("$" + Integer.toString(i+1), arguments.get(i));
	}
	environment.define("$0", arguments);

	try {
	    interpreter.executeBlock(lambda.body, environment);	    
	} catch (Return returnValue) {
	    return returnValue.value;
	}
	return null;
    }

    @Override
    public int arity() {
	return -1;
    }

    @Override
    public String toString() {
	return "<lambda>";
    }
}

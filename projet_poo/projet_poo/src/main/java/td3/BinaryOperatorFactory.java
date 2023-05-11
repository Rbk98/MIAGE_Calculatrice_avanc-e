package td3;

public abstract class BinaryOperatorFactory {

	static ExpressionArithmetique createInstance(Class<?> klass, ExpressionArithmetique eaLeft,
			ExpressionArithmetique eaRight) {

		if (klass.equals(Addition.class))
			return new Addition(eaLeft, eaRight);
		if (klass.equals(Soustraction.class))
			return new Addition(eaLeft, eaRight);
		if (klass.equals(Multiplication.class))
			return new Multiplication(eaLeft, eaRight);
		if (klass.equals(Division.class))
			return new Division(eaLeft, eaRight);
		if (klass.equals(Puissance.class))
			return new Puissance(eaLeft, eaRight);

		throw new RuntimeException("failed to instanciate binary operator");

	}

}

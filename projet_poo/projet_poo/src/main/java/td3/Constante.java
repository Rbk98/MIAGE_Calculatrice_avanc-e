package td3;

public abstract class Constante implements ExpressionArithmetique {
	 public static final Constante pi = new ConstSymbolique("pi", Math.PI); 
	 public static final Constante e = new ConstSymbolique("e", Math.E);

}

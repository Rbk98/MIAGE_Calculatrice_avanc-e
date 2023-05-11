package td3;

public final class ExpressionArithmetiques {
	public static ExpressionArithmetique c(int c) {
		return new ConstEntiere(c);
	}
	
	public static ExpressionArithmetique c(String s) {
		return new VariableSymbolique(s);
	}
	
	public static ExpressionArithmetique c(int n,int m) {
		return new ConstRationnelle(n, m);
	}
	
	public static ExpressionArithmetique plus(ExpressionArithmetique l,ExpressionArithmetique r) {
		return new Addition(l, r);
	}
	
	public static ExpressionArithmetique minus(ExpressionArithmetique l,ExpressionArithmetique r) {
		return new Soustraction(l, r);
	}
	
	public static ExpressionArithmetique times(ExpressionArithmetique l,ExpressionArithmetique r) {
		return new Multiplication(l, r);
	}
	
	public static ExpressionArithmetique slash(ExpressionArithmetique l,ExpressionArithmetique r) {
		return new Division(l, r);
	}
	
	public static ExpressionArithmetique pow(ExpressionArithmetique l,ExpressionArithmetique r) {
		return new Puissance(l, r);
	}

}

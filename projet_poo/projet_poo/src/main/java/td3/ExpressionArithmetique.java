package td3;

import java.util.Map;

public interface ExpressionArithmetique {
	public ExpressionArithmetique simplifier();

	public double calculer();

	public String toString();

	public int hashCode();

	public boolean equals(Object obj);

	public double calculer(Map<VariableSymbolique, Constante> mapValue); // demander si map marche

	default int gcd(int a, int b) {
		if (b == 0)
			return a;
		else
			return gcd(b, a % b);
	}

}
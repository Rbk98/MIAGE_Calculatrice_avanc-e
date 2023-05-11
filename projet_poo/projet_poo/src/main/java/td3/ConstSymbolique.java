package td3;

import java.util.Map;

public class ConstSymbolique extends Constante {

	private final String symbole;
	private final double value;

	public ConstSymbolique(String symbole, double valeur) {
		this.symbole = symbole.toLowerCase();
		this.value = valeur;
	}

	public String getSymbole() {
		return this.symbole;
	}

	public double getValue() {
		return this.value;
	}

	@Override
	public ExpressionArithmetique simplifier() {
		return this;
	}

	@Override
	public double calculer() {
		return this.value;
	}

	@Override
	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		return this.value;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbole == null) ? 0 : symbole.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConstSymbolique other = (ConstSymbolique) obj;
		if (symbole == null) {
			if (other.symbole != null)
				return false;
		} else if (!symbole.equals(other.symbole))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}
 
	@Override
	public String toString() {
		return this.symbole;
	}
}

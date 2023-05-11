package td3;

import java.util.Map;

public final class ConstEntiere extends Constante{

	public static final ConstEntiere zero = new ConstEntiere(0);
	public static final ConstEntiere un = new ConstEntiere(1);
	private int entier;

	public ConstEntiere(int value) {
		this.entier = value;
	}

	public int getEntier() {
		return entier;
	}

	public void setEntier(int entier) {
		this.entier = entier;
	}

	@Override
	public ExpressionArithmetique simplifier() {
		return this;
	}

	@Override
	public double calculer() {
		return this.getEntier();
	}

	@Override
	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		return this.getEntier();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + entier;
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
		ConstEntiere other = (ConstEntiere) obj;
		if (entier != other.entier)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + this.entier + "";
	}
}

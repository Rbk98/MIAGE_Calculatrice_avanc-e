package td3;

import java.util.Collections;
import java.util.Map;

public class VariableSymbolique implements ExpressionArithmetique {

	protected final String variable;

	public VariableSymbolique(String variable) {
		this.variable = variable.toLowerCase();
	}
	
	public String getVariable() {
		return this.variable;
	}

	@Override
	public ExpressionArithmetique simplifier() {
		return this;
	}

	
	public ExpressionArithmetique simplifier(Map<VariableSymbolique, Constante> mapValue) {
		return mapValue.get(this);
	}

	@Override
	public double calculer() {
		return calculer(Collections.<VariableSymbolique, Constante>emptyMap());
	}

	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		if (!mapValue.containsKey(this)){
			throw new NullPointerException("Pas de constante correspondante trouvée");
		} //peut on faire un catch?
		return mapValue.get(this).calculer();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((variable == null) ? 0 : variable.hashCode());
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
		VariableSymbolique other = (VariableSymbolique) obj;
		if (variable == null) {
			if (other.variable != null)
				return false;
		} else if (!variable.equals(other.variable))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.variable;
	}

}

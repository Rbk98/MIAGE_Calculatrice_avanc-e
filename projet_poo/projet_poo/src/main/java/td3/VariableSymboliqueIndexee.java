package td3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class VariableSymboliqueIndexee extends VariableSymbolique {

	private final VariableSymbolique index;
	private Map<VariableSymbolique, ArrayList<Constante>> mapValues = new HashMap<VariableSymbolique, ArrayList<Constante>>();

	public VariableSymboliqueIndexee(String variable, VariableSymbolique index) {
		this(variable, index, Collections.<VariableSymbolique, ArrayList<Constante>>emptyMap() );
	}

	public VariableSymboliqueIndexee(String variable, VariableSymbolique index, Map<VariableSymbolique, ArrayList<Constante>> test){
		super(variable);
		this.index = index;
		this.mapValues = test;
	}
	
	@Override
	public String toString() {
		return this.variable + "_" + (index.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		VariableSymboliqueIndexee other = (VariableSymboliqueIndexee) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		return true;
	}
}
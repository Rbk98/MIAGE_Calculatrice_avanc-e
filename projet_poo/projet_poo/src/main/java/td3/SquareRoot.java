package td3;

import java.util.Map;

public class SquareRoot extends OperationUnaire {

	public SquareRoot(ExpressionArithmetique eaMain) {
		super(eaMain);
	}
 
	@Override
	public double calculer() {
		return Math.sqrt(this.eaMain.calculer());
	}
	
	@Override
	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		return Math.sqrt(this.eaMain.calculer(mapValue));
	}

	@Override
	ExpressionArithmetique createNewInstance(ExpressionArithmetique EA) {
		if(EA instanceof ConstEntiere) {
			double sqrtEA = Math.sqrt(EA.calculer());
			int testInt = (int)sqrtEA;
			if(testInt == sqrtEA ) {
				return new ConstEntiere(testInt);
			}
		}
		return new SquareRoot(EA); 
	}

	@Override
	public String toString() {
		return "(("+this.eaMain.toString()+")^1/2)";
	}
	
	
	//String.format("%1.4f",pi)
	
}

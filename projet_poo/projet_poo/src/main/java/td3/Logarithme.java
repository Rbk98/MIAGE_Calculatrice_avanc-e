package td3;

import java.util.Map;

public class Logarithme extends OperationUnaire {
	
	public Logarithme(ExpressionArithmetique eaMain) {
		super(eaMain);
	}
 
	@Override
	public double calculer() {
		return Math.log(this.eaMain.calculer());
	}
	
	@Override 
	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		return Math.log(this.eaMain.calculer());
	}

	@Override
	ExpressionArithmetique createNewInstance(ExpressionArithmetique EA) {
		if (EA instanceof ConstEntiere) {
			if (EA.calculer() == 1) {
				return new ConstEntiere(0);
			}
		}
		if (EA instanceof ConstSymbolique) {
			if (EA.toString().equals("e")) { 
				return new ConstEntiere(1);
			}
		}
		return new Logarithme(EA);
	}


	@Override
	public String toString() {
		return "ln("+this.eaMain.toString()+")";
	}
	
	//String.format("%1.4f",pi)

}

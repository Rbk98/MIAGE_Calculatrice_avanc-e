package td3;

import java.util.Map;

public class Cosinus extends Trigo {

	public Cosinus(ExpressionArithmetique eaMain) {
		super(eaMain);
	}

	@Override
	public double calculer() {
		return Math.cos(this.eaMain.calculer());
	}
	
	@Override
	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		return Math.cos(this.eaMain.calculer(mapValue));
	}
	 
	@Override
	ExpressionArithmetique createNewInstance(ExpressionArithmetique EA) {
		
		if (EA instanceof Multiplication) {
			EA = simplifModuloMult(EA);
		}
		if (EA instanceof ConstEntiere) {
			if (((ConstEntiere) EA).getEntier() == 0) {
				return new ConstEntiere(1);
			}
		}
		if (EA instanceof Division) {
			EA = simplifModuloDiv(EA);
			if ((EA.toString().equals("(pi/2)") || EA.toString().equals("((3*pi)/2)"))){
				return new ConstEntiere(0);
			}

			else if ((EA.toString().equals("(pi/3)") || EA.toString().equals("((5*pi)/3)"))){
				return new ConstRationnelle(1, 2);
			}

			else if ((EA.toString().equals("((2*pi)/3)") || EA.toString().equals("((4*pi)/3)"))){
				return new ConstRationnelle(1, 2);
			}
			
			else if ((EA.toString().equals("(pi/4)") || EA.toString().equals("((7*pi)/4)"))) {
				return new Division(new SquareRoot(new ConstEntiere(2)), new ConstEntiere(2));
			}

			else if ((EA.toString().equals("(pi/6)") || EA.toString().equals("((11*pi)/6)"))) {
				return new Division(new SquareRoot(new ConstEntiere(3)), new ConstEntiere(2));
			}
		}
		if (EA instanceof ConstSymbolique) {
			if (EA.toString().equals("pi")) {
				return new ConstEntiere(-1); 
			}
		}
		return new Cosinus(EA);
	}

	@Override
	public String toString() {
		return "cos("+this.eaMain.toString()+")";
	}
	
	

	//String.format("%1.4f",pi)
}

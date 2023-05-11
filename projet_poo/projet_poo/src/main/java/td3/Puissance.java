package td3;

import java.util.Map;

public class Puissance extends OperationBinaire {

	public Puissance(ExpressionArithmetique eaLeft, ExpressionArithmetique eaRight) {
		super(eaLeft, eaRight);

	}
	
	@Override
	public double calculer() {
		return Math.pow(this.eaLeft.calculer(), this.eaRight.calculer());
	}

	@Override
	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		return Math.pow(this.eaLeft.calculer(mapValue), this.eaRight.calculer(mapValue));
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstEntiere droite) {
		return new ConstRationnelle((int) Math.pow(gauche.getNumerateur(), droite.getEntier()),
				(int) Math.pow(gauche.getDenominateur(), droite.getEntier())).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstEntiere droite) {
		return new ConstEntiere((int) Math.pow(gauche.getEntier(), droite.getEntier())).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ConstEntiere droite) {
		if (droite.getEntier() == 0) {
				return new ConstEntiere(1);
		}
		return this;
	}

	@Override
	public String toString() {
		return "(" + this.eaLeft.toString() + ")^" + this.eaRight.toString();
	}
	
	public ConstEntiere getElementNeutre() {
        return new ConstEntiere(1);
	}

}

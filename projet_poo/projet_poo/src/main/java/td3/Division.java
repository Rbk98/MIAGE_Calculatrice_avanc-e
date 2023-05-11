package td3;

import java.util.Map;

public class Division extends OperationBinaire {

	public Division(ExpressionArithmetique eaLeft, ExpressionArithmetique eaRight) {
		super(eaLeft, eaRight);

	}

	@Override
	public double calculer() {
		if (this.eaRight.calculer()==0.0) {
			throw new DenominateurException("Denominateur nul"); //ArithmeticException ?
		}
		return this.eaLeft.calculer() / this.eaRight.calculer();
	}
	 
	@Override
	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		if (this.eaRight.calculer(mapValue)==0.0) {
				throw new DenominateurException("Denominateur nul"); //ArithmeticException ?
		}
		return this.eaLeft.calculer(mapValue) / this.eaRight.calculer(mapValue);
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstEntiere droite) {
		return new ConstRationnelle(gauche.getNumerateur(), gauche.getDenominateur() * droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstRationnelle droite) {
		return new ConstRationnelle(gauche.getEntier() * droite.getNumerateur(), droite.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstRationnelle droite) {
		return new ConstRationnelle(gauche.getNumerateur() * droite.getDenominateur(),
				gauche.getDenominateur() * droite.getNumerateur()).simplifier();
	}
 
	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstEntiere droite) {
		return new ConstRationnelle(gauche.getEntier(), droite.getEntier()).simplifier();
	}

	

	@Override
	public String toString() {
		return "("+this.eaLeft.toString()+"/"+this.eaRight.toString()+")";
	}
	
	public ConstEntiere getElementNeutre() {
        return new ConstEntiere(1);
}
	
}

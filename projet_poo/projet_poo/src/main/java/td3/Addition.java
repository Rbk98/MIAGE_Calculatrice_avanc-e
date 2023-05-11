
package td3;

import java.util.Map;

public class Addition extends OperationBinaire {

	public Addition(ExpressionArithmetique eaLeft, ExpressionArithmetique eaRight) {
		super(eaLeft, eaRight);

	}
	
	@Override
	public double calculer() {
		return this.eaLeft.calculer() + this.eaRight.calculer();
	}

	@Override
	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		return this.eaLeft.calculer(mapValue) + this.eaRight.calculer(mapValue);
	}

	public ExpressionArithmetique associativite() {
		ExpressionArithmetique gauche = this.getEaLeft();
		ExpressionArithmetique droite = this.getEaRight();
		if ((gauche instanceof Constante) && (droite instanceof Addition)) {
			if (!(((Addition) droite).eaLeft instanceof VariableSymbolique)) {
				if (((Addition) droite).eaLeft instanceof Addition) {
					return new Addition(new Addition(gauche, ((Addition) droite).eaLeft).associativite(),
							((Addition) droite).eaRight).simplifier();
				} else {
					return new Addition(new Addition(gauche, ((Addition) droite).eaLeft), ((Addition) droite).eaRight)
							.simplifier();
				}
			}
		} else if ((droite instanceof Constante) && (gauche instanceof Addition)) {
			if (!(((Addition) gauche).eaRight instanceof VariableSymbolique)) {
				if (((Addition) gauche).eaRight instanceof Addition) {
					return new Addition(((Addition) gauche).eaLeft,
							new Addition(((Addition) gauche).eaRight, droite).associativite()).simplifier();
				} else {
					return new Addition(((Addition) gauche).eaLeft, new Addition(((Addition) gauche).eaRight, droite))
							.simplifier();
				}
			}
		}
		return this;
	}


	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstEntiere droite) {
		return new ConstRationnelle(gauche.getNumerateur() * droite.getEntier() + gauche.getDenominateur() * 1,
				1 * gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstRationnelle droite) {
		return new ConstRationnelle(
				gauche.getNumerateur() * droite.getDenominateur() + gauche.getDenominateur() * droite.getNumerateur(),
				droite.getDenominateur() * gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstEntiere droite) {
		return new ConstEntiere(gauche.getEntier() + droite.getEntier()).simplifier();
	}

	@Override
	public String toString() {
		return "(" + this.eaLeft.toString() + "+" + this.eaRight.toString() + ")";
	}

	@Override
	public ConstEntiere getElementNeutre() {
	        return new ConstEntiere(0);
	}

}
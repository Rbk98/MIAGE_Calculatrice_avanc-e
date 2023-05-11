package td3;

import java.util.Map;

public class Soustraction extends OperationBinaire {

	public Soustraction(ExpressionArithmetique eaLeft, ExpressionArithmetique eaRight) {
		super(eaLeft, eaRight);

	}

	@Override
	public double calculer() {
		return this.eaLeft.calculer() - this.eaRight.calculer();
	}

	@Override
	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		return this.eaLeft.calculer(mapValue) - this.eaRight.calculer(mapValue);
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstEntiere droite) {
		return new ConstRationnelle(gauche.getNumerateur() * droite.getEntier() - gauche.getDenominateur() * 1,
				1 * gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstRationnelle droite) {
		return new ConstRationnelle(
				gauche.getNumerateur() * droite.getDenominateur() - gauche.getDenominateur() * droite.getNumerateur(),
				droite.getDenominateur() * gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstEntiere droite) {
		return new ConstEntiere(gauche.getEntier() - droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstRationnelle droite) {
		return new ConstRationnelle(droite.getDenominateur() * gauche.getEntier() - droite.getNumerateur() * 1,
				1 * droite.getDenominateur()).simplifier();
	}

	@Override
	public String toString() {
		return "(" + this.eaLeft.toString() + "-" + this.eaRight.toString() + ")";
	}

	public ConstEntiere getElementNeutre() {
		return new ConstEntiere(0);
	}

}

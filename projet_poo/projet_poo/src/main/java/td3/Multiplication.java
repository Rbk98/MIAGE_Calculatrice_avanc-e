package td3;

import java.util.Map;

public class Multiplication extends OperationBinaire {

	public Multiplication(ExpressionArithmetique eaLeft, ExpressionArithmetique eaRight) {
		super(eaLeft, eaRight);

	}

	public ExpressionArithmetique associativite() {
		ExpressionArithmetique gauche = this.getEaLeft();
		ExpressionArithmetique droite = this.getEaRight();
		if ((gauche instanceof Constante) && (droite instanceof Multiplication)) {
			if (!(((Multiplication) droite).eaLeft instanceof VariableSymbolique)) {
				if (((Multiplication) droite).eaLeft instanceof Multiplication) {
					return new Multiplication(
							new Multiplication(gauche, ((Multiplication) droite).eaLeft).associativite(),
							((Multiplication) droite).eaRight).simplifier();
				} else {
					return new Multiplication(new Multiplication(gauche, ((Multiplication) droite).eaLeft),
							((Multiplication) droite).eaRight).simplifier();
				}
			}
		} else if ((droite instanceof Constante) && (gauche instanceof Multiplication)) {
			if (!(((Multiplication) gauche).eaRight instanceof VariableSymbolique)) {
				if (((Multiplication) gauche).eaLeft instanceof Multiplication) {
					return new Multiplication(((Multiplication) gauche).eaLeft,
							new Multiplication(((Multiplication) gauche).eaRight, droite).associativite()).simplifier();
				} else {
					return new Multiplication(((Multiplication) gauche).eaLeft,
							new Multiplication(((Multiplication) gauche).eaRight, droite)).simplifier();
				}
			}
		}
		return this.simplifier();
	}

	public ExpressionArithmetique distributivite() {
		ExpressionArithmetique droite = this.getEaRight();
		ExpressionArithmetique gauche = this.getEaLeft();
		if ((droite instanceof Constante) || (droite instanceof VariableSymbolique)) {
			if (gauche instanceof Addition) {
				return new Addition(new Multiplication(((Addition) gauche).eaLeft, droite),
						new Multiplication(((Addition) gauche).eaRight, droite)).simplifier();
			} else if (gauche instanceof Soustraction) {
				return new Soustraction(new Multiplication(((Soustraction) gauche).eaLeft, droite),
						new Multiplication(((Soustraction) gauche).eaRight, droite)).simplifier();
			}
		} else if ((gauche instanceof Constante) || (gauche instanceof VariableSymbolique)) {
			if (droite instanceof Addition) {
				return new Addition(new Multiplication(((Addition) droite).eaLeft, gauche),
						new Multiplication(((Addition) droite).eaRight, gauche)).simplifier();
			} else if (droite instanceof Soustraction) {
				return new Soustraction(new Multiplication(((Soustraction) droite).eaLeft, gauche),
						new Multiplication(((Soustraction) droite).eaRight, gauche)).simplifier();
			}
		}
		return this;
	}
	
	
	@Override 
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ExpressionArithmetique droite) {
		if(gauche.getEntier() == 0) {
            return new ConstEntiere(0);
        }
        return this;
	}
	
	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ConstEntiere droite) {
		return simplifie(droite, gauche);
	}
	
	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstEntiere droite) {
		return new ConstRationnelle(droite.getEntier() * gauche.getNumerateur(), gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstRationnelle droite) {
		return new ConstRationnelle(gauche.getNumerateur() * droite.getNumerateur(),
				droite.getDenominateur() * gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstEntiere droite) {
		return new ConstEntiere(gauche.getEntier() * droite.getEntier()).simplifier();
	}

	@Override
	public double calculer() {
		return this.eaLeft.calculer() * this.eaRight.calculer();
	}

	@Override
	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		return this.eaLeft.calculer(mapValue) * this.eaRight.calculer(mapValue);
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstRationnelle droite) {
		return this.simplifie(droite, gauche).simplifier();
	}

	@Override
	public String toString() {
		return "(" + this.eaLeft.toString() + "*" + this.eaRight.toString() + ")";
	}

	public ConstEntiere getElementNeutre() {
		return new ConstEntiere(1);
	}

}

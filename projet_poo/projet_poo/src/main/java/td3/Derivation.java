package td3;

import java.util.Collections;
import java.util.Map;

public class Derivation implements ExpressionArithmetique {

	private int ordre;
	private ExpressionArithmetique ea;

	public Derivation(ExpressionArithmetique ea) {
		this(ea,1);
	}

	public Derivation(ExpressionArithmetique ea, int ordre) {
		this.ordre = ordre;
		this.ea = ea;
	}

	public ExpressionArithmetique expressionDerivee(ExpressionArithmetique ea) {
		ExpressionArithmetique resultDerivee;
		if ((ea instanceof Addition) || (ea instanceof Soustraction)) {
			ExpressionArithmetique expressionGauche = ((OperationBinaire) ea).eaLeft;
			ExpressionArithmetique expressionDroite = ((OperationBinaire) ea).eaRight;
			ExpressionArithmetique deriveegauche = derivee(expressionGauche);

			if (ea instanceof Addition) {
				resultDerivee = new Addition(deriveegauche, expressionDerivee(expressionDroite));
			}else {
				resultDerivee = new Soustraction(deriveegauche, expressionDerivee(expressionDroite));
			}
		}
		else {
			resultDerivee = derivee(ea);
		}
		return resultDerivee.simplifier();
	}
	
	public ExpressionArithmetique ordreN() {
		ExpressionArithmetique derivationN = this.ea;
		for(int i =0;i<this.ordre;i++) {
			derivationN = expressionDerivee(derivationN);
		}
		return derivationN;
	}

	public ExpressionArithmetique derivee(ExpressionArithmetique ea) {
		ExpressionArithmetique derivee=null;
		if (ea instanceof Multiplication) {
			ExpressionArithmetique multipgauche = ((Multiplication) ea).eaLeft;
			ExpressionArithmetique multipdroite = ((Multiplication) ea).eaRight;

			if (multipgauche instanceof ConstEntiere && multipdroite instanceof Puissance) {
				int exposant = ((ConstEntiere) ((Puissance) multipdroite).eaRight).getEntier();
				int valeur = (((ConstEntiere) multipgauche).getEntier()) * exposant;
				derivee = new Multiplication(new ConstEntiere(valeur),
						new Puissance(new VariableSymbolique(((Puissance) multipdroite).eaLeft.toString()),
								new ConstEntiere(exposant - 1)));
			}
			if (multipgauche instanceof ConstEntiere && multipdroite instanceof VariableSymbolique) {
				derivee = new ConstEntiere(((ConstEntiere) multipgauche).getEntier());
			}
		} else if (ea instanceof ConstEntiere) {
			derivee = new ConstEntiere(0);
		} else if (ea instanceof VariableSymbolique) {
			derivee = new ConstEntiere(1);
		} else if (ea instanceof Puissance) {
			ExpressionArithmetique multipdroite = ((Puissance) ea).eaRight;
			int exposant = ((ConstEntiere) ((Puissance) multipdroite).eaRight).getEntier();
			derivee = new Multiplication(new ConstEntiere(exposant),
					new Puissance(new VariableSymbolique(((Puissance) multipdroite).eaLeft.toString()),
							new ConstEntiere(exposant - 1)));

		}
		return derivee;
	}

	@Override
	public double calculer() {
		return calculer(Collections.<VariableSymbolique, Constante>emptyMap());
	}

	@Override
	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		return this.ordreN().calculer(mapValue);
	}

	@Override
	public String toString() {
		return this.ordreN().toString();
	}

	@Override
	public ExpressionArithmetique simplifier() {
		return this.ordreN().simplifier();
	}

}

package td3;

public abstract class Trigo extends OperationUnaire {

	public Trigo(ExpressionArithmetique eaMain) {
		super(eaMain);
	}

	protected ExpressionArithmetique simplifModuloMult(ExpressionArithmetique ea) {

		if ((((Multiplication) ea).eaRight instanceof ConstRationnelle)
				&& (((Multiplication) ea).eaLeft instanceof ConstSymbolique)) {
			int num = ((ConstRationnelle) ((Multiplication) ea).eaRight).getNumerateur();
			int denum = ((ConstRationnelle) ((Multiplication) ea).eaRight).getDenominateur();
			ConstSymbolique left = (ConstSymbolique) ((Multiplication) ea).eaLeft;
			if (left.toString().equals("pi")) {
				return new Division(new Multiplication(new ConstEntiere(num), Constante.pi), new ConstEntiere(denum))
						.simplifier();
			}
		}
		if ((((Multiplication) ea).eaLeft instanceof ConstRationnelle)
				&& (((Multiplication) ea).eaRight instanceof ConstSymbolique)) {
			return simplifModuloMult(new Multiplication((((Multiplication) ea).eaRight),
					 (((Multiplication) ea).eaLeft)));

		}

		if ((((Multiplication) ea).eaRight instanceof ConstEntiere)
				&& (((Multiplication) ea).eaLeft instanceof ConstSymbolique)) {
			ConstEntiere right = (ConstEntiere) ((Multiplication) ea).eaRight;
			ConstSymbolique left = (ConstSymbolique) ((Multiplication) ea).eaLeft;
			int count = right.getEntier();
			if (left.toString().equals("pi")) {
				while (count >= 2) {
					count -= 2;
				}
				while (count < 0) {
					count += 2;
				}
				return new Multiplication(Constante.pi, new ConstEntiere(count)).simplifier();
			}
		}
		if ((((Multiplication) ea).eaLeft instanceof ConstEntiere)
				&& (((Multiplication) ea).eaRight instanceof ConstSymbolique)) {
			return simplifModuloMult(new Multiplication((((Multiplication) ea).eaRight),
					 (((Multiplication) ea).eaLeft)));
		}
		return ea;
	}

	protected ExpressionArithmetique simplifModuloDiv(ExpressionArithmetique ea) {
		if ((((Division) ea).eaRight instanceof ConstEntiere) && (((Division) ea).eaLeft instanceof Multiplication)) {
			int denominateur = ((ConstEntiere) ((Division) ea).eaRight).getEntier();
			if ((((Multiplication) ((Division) ea).eaLeft).eaLeft) instanceof ConstSymbolique
					&& (((Multiplication) ((Division) ea).eaLeft).eaRight) instanceof ConstEntiere) {
				int numerateur = ((ConstEntiere) ((Multiplication) ((Division) ea).eaLeft).eaRight).getEntier();
				ConstSymbolique pi = (ConstSymbolique) ((Multiplication) ((Division) ea).eaLeft).eaLeft;
				if (pi.toString().equals("pi")) {
					ConstRationnelle cr = new ConstRationnelle(numerateur, denominateur);
					int pgcd = cr.gcd(numerateur, denominateur);
					numerateur = numerateur / pgcd;
					denominateur = denominateur / pgcd;
					if (denominateur == 1) {
						return new Multiplication(new ConstEntiere(numerateur), Constante.pi).simplifier();
					} else if (numerateur == 1) {
						return new Division(pi, new ConstEntiere(denominateur)).simplifier();
					}
					return new Division(new Multiplication(new ConstEntiere(numerateur), Constante.pi),
							new ConstEntiere(denominateur)).simplifier();
				}
			} else if ((((Multiplication) ((Division) ea).eaLeft).eaLeft) instanceof ConstEntiere
					&& (((Multiplication) ((Division) ea).eaLeft).eaRight) instanceof ConstSymbolique) {
				ConstSymbolique pi = (ConstSymbolique) ((Multiplication) ((Division) ea).eaLeft).eaRight;
				if (pi.toString().equals("pi")) {
					return simplifModuloDiv(new Division(
							new Multiplication(Constante.pi, new ConstEntiere(
									((ConstEntiere) ((Multiplication) ((Division) ea).eaLeft).eaLeft).getEntier())),
							new ConstEntiere(denominateur))).simplifier();
				}
			}

		}
		return ea;
	}
}
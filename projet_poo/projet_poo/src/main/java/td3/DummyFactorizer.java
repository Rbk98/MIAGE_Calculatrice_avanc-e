package td3;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DummyFactorizer {

	static class FactorizationVisitor {
		Deque<ConstEntiere> constantes = new LinkedList<ConstEntiere>();

		boolean visitSuceed = true;

		public boolean visitCollectGCD(ExpressionArithmetique ea) {
			if (ea instanceof Addition || ea instanceof Soustraction) {
				OperationBinaire eaAdd = (OperationBinaire) ea;
				if (eaAdd.eaLeft instanceof Multiplication) {
					Multiplication esAddLeftMult = (Multiplication) eaAdd.eaLeft;
					if (esAddLeftMult.eaLeft instanceof ConstEntiere) {
						ConstEntiere esAddLeftMulteaLeft = (ConstEntiere) esAddLeftMult.eaLeft;
						constantes.push(esAddLeftMulteaLeft);

						return visitCollectGCD(eaAdd.eaRight);
					}
				}

			} else if (ea instanceof Multiplication) {
				Multiplication eaMult = (Multiplication) ea;
				if (eaMult.eaLeft instanceof ConstEntiere) {
					constantes.add((ConstEntiere) eaMult.eaLeft);
					return true;
				}

			}
			return false;
		}

	}

	public static ExpressionArithmetique factorize(ExpressionArithmetique ea) {
		FactorizationVisitor visitor = new FactorizationVisitor();
		if (visitor.visitCollectGCD(ea)) {

			int gcd = visitor.constantes.getFirst().getEntier();
			for (ConstEntiere constante : visitor.constantes) {
				gcd = ea.gcd(gcd, constante.getEntier());
			}

			if (gcd != 1) {
				for (ConstEntiere constante : visitor.constantes) {
					constante.setEntier(constante.getEntier() / gcd);
				}
				return new Multiplication(new ConstEntiere(gcd), ea.simplifier());
			}
		}
		return ea;

	}

}

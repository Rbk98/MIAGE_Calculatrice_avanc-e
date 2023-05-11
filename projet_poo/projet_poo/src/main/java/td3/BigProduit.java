package td3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class BigProduit implements ExpressionArithmetique{

	private VariableSymbolique vs;
	private ConstEntiere debut;
	private ConstEntiere fin;
	private ExpressionArithmetique ea;
	
	
	public BigProduit(VariableSymbolique vs, ConstEntiere debut, ConstEntiere fin, ExpressionArithmetique ea) {
		this.vs = vs;
		this.debut = debut;
		this.fin = fin;
		this.ea = ea;
	}
	
	public double calculer(Map<VariableSymbolique, ArrayList<Constante>> mapValueCoeff,
			Map<VariableSymbolique, Constante> mapValueVariable) {
		double result = 1;
		double calculCoeff = 0;
		if (ea instanceof Multiplication && ((OperationBinaire) ea).eaLeft instanceof VariableSymboliqueIndexee && ((OperationBinaire) ea).eaRight instanceof Puissance) {
			ExpressionArithmetique variableSymb = (((Puissance) ((OperationBinaire) ea).eaRight).eaLeft);
			
			VariableSymbolique coeff = new VariableSymbolique(((VariableSymboliqueIndexee) ((OperationBinaire) ea).eaLeft).getVariable());
			
			if (!mapValueCoeff.containsKey(coeff)) {
				throw new NullPointerException("Pas de constante correspondante trouvée");
			}
			ArrayList<Constante> valuesCoeff = mapValueCoeff.get(coeff);
			int exposant= 0;
			for (int i = debut.getEntier(); i <= fin.getEntier(); i++) {
				exposant+=i; 
				calculCoeff = (valuesCoeff.get(i)).calculer();
				result *=calculCoeff;
			}
			result*= (new Puissance(variableSymb, new ConstEntiere(exposant))).calculer(mapValueVariable);
			return result;
		}
		throw new ArithmeticException("Produit incorrect");
	}
	
	
	public ExpressionArithmetique prodExpression() {
		ExpressionArithmetique result = null;
		ExpressionArithmetique coeff = ((OperationBinaire) ea).eaLeft; // multiplication ?
		String variableGauche = ((VariableSymboliqueIndexee) coeff).getVariable();
		ExpressionArithmetique variableSymb = ((OperationBinaire) ea).eaRight; // multiplication ?
		String variableDroite = ((VariableSymbolique)((Puissance) variableSymb).eaLeft).getVariable();
		ExpressionArithmetique deb = new VariableSymboliqueIndexee(variableGauche, new VariableSymbolique(""+ fin.getEntier()));
		int variableExposant= fin.getEntier();
		for (int i = (fin.getEntier()-1); i >= (debut.getEntier()); i--) {
			
			variableExposant+=i;
			ExpressionArithmetique nouv;
			nouv = new VariableSymboliqueIndexee(variableGauche, new VariableSymbolique("" + i));
			result = new Multiplication (nouv, deb);
			deb = result;
		}
		return new Multiplication(result, new Puissance(new VariableSymbolique(variableDroite), new ConstEntiere(variableExposant)));
	}
	
	@Override
	public String toString() {
		return this.prodExpression().toString();
	}
	
	public ExpressionArithmetique simplifier() {
		return this.prodExpression().simplifier();
	}
	
	@Override
	public double calculer() {
		return calculer(Collections.<VariableSymbolique, ArrayList<Constante>>emptyMap(),Collections.<VariableSymbolique, Constante>emptyMap());
	}
	@Override
	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		return calculer(Collections.<VariableSymbolique, ArrayList<Constante>>emptyMap(),Collections.<VariableSymbolique, Constante>emptyMap());
	}
	
}

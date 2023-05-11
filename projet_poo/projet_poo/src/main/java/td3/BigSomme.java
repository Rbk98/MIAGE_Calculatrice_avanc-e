package td3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
public class BigSomme implements ExpressionArithmetique{
	private VariableSymbolique vs;
	private ConstEntiere debut;
	private ConstEntiere fin;
	private ExpressionArithmetique ea;
	
	public BigSomme(VariableSymbolique vs, ConstEntiere debut, ConstEntiere fin, ExpressionArithmetique ea) {
		this.vs = vs;
		this.debut = debut;
		this.fin = fin;
		this.ea = ea;
	} 
	
	public double calculer(Map<VariableSymbolique, ArrayList<Constante>> mapValueCoeff,
			Map<VariableSymbolique, Constante> mapValueVariable) {
		double res = 0;
		double calculVariable = 0;
		double calculCoeff = 0;
		if (ea instanceof Multiplication && ((OperationBinaire) ea).eaLeft instanceof VariableSymboliqueIndexee
				&& ((OperationBinaire) ea).eaRight instanceof Puissance) {
			ExpressionArithmetique variableSymb = (((Puissance) ((OperationBinaire) ea).eaRight).eaLeft);
			VariableSymbolique coeff = new VariableSymbolique(
					((VariableSymboliqueIndexee) ((OperationBinaire) ea).eaLeft).getVariable());
			if (!mapValueCoeff.containsKey(coeff)) {
				throw new NullPointerException("Pas de constante correspondante trouvée");
			}
			ArrayList<Constante> valuesCoeff = mapValueCoeff.get(coeff);
			for (int i = debut.getEntier(); i <= fin.getEntier(); i++) {
				calculVariable = new Puissance(variableSymb, new ConstEntiere(i)).calculer(mapValueVariable);
				calculCoeff = (valuesCoeff.get(i)).calculer();
				res += calculVariable * calculCoeff;
			}
			return res;
		}
		throw new ArithmeticException("Somme incorrect");
	}
	
	//FAIRE LE CAS INVERSE ?
	
	public ExpressionArithmetique sumExpression() {
		ExpressionArithmetique res = null;
		ExpressionArithmetique coeff = ((OperationBinaire) ea).eaLeft; // multiplication ?
		String variableGauche = ((VariableSymboliqueIndexee) coeff).getVariable();
		ExpressionArithmetique variableSymb = ((OperationBinaire) ea).eaRight; // multiplication ?
		String variableDroite = ((VariableSymbolique)((Puissance) variableSymb).eaLeft).getVariable();
		ExpressionArithmetique deb = new Multiplication(new VariableSymboliqueIndexee(variableGauche, new VariableSymbolique(""+ fin.getEntier())),
				new Puissance(new VariableSymbolique(variableDroite), (new ConstEntiere(fin.getEntier()))));
		
		for (int i = (fin.getEntier()-1); i >= (debut.getEntier()); i--) {
			ExpressionArithmetique nouv;
			nouv = new Multiplication(new VariableSymboliqueIndexee(variableGauche, new VariableSymbolique("" + i)),
					new Puissance(new VariableSymbolique(variableDroite), (new ConstEntiere(i))));
			res = new Addition (nouv, deb);
			deb = res;
		}
		return res;
	}
	
	public ExpressionArithmetique simplifier() {
		return this.sumExpression().simplifier();
	}
	
	public String toString() {
		return this.sumExpression().toString();
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
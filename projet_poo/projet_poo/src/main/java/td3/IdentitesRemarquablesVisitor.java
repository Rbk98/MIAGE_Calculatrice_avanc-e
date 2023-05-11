package td3;


import java.util.ArrayList;

public class IdentitesRemarquablesVisitor {
	protected static final ExpressionArithmetique identiteRadd = new Addition(new Puissance(new VariableSymbolique("a"),new ConstEntiere(2)),new Addition(new Multiplication(new ConstEntiere(2),new Multiplication(new VariableSymbolique("a"), new VariableSymbolique("b"))),new Puissance(new VariableSymbolique("b"),new ConstEntiere(2))));
	protected static final ExpressionArithmetique identiteRsous = new Soustraction(new Puissance(new VariableSymbolique("a"),new ConstEntiere(2)),new Addition(new Multiplication(new ConstEntiere(2),new Multiplication(new VariableSymbolique("a"), new VariableSymbolique("b"))),new Puissance(new VariableSymbolique("b"),new ConstEntiere(2))));
	protected static final ExpressionArithmetique identiteDiffCarreV1 = new Multiplication(new Soustraction(new VariableSymbolique("a"),new VariableSymbolique("b")),new Addition(new VariableSymbolique("a"),new VariableSymbolique("b")));
	protected static final ExpressionArithmetique identiteDiffCarreV2 = new Multiplication(new Addition(new VariableSymbolique("a"),new VariableSymbolique("b")),new Soustraction(new VariableSymbolique("a"),new VariableSymbolique("b")));
	
	protected ExpressionArithmetique identiteUtilisateur;
	protected static final ExpressionArithmetique identiteRfactoriseAdd = new Puissance(new Addition(new VariableSymbolique("a"),new VariableSymbolique("b")),new ConstEntiere(2));
	protected static final ExpressionArithmetique identiteRfactoriseSous = new Puissance(new Soustraction(new VariableSymbolique("a"),new VariableSymbolique("b")),new ConstEntiere(2));
	protected static final ExpressionArithmetique identiteDiffCarreFactorise = new Soustraction(new Puissance(new VariableSymbolique("a"),new ConstEntiere(2)),new Puissance(new VariableSymbolique("b"),new ConstEntiere(2)));

}
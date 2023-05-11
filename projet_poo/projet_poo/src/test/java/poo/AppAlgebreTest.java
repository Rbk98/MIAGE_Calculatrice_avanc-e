package poo;

import static org.junit.Assert.assertEquals;
import static td3.ExpressionArithmetiques.c;
import static td3.ExpressionArithmetiques.minus;
import static td3.ExpressionArithmetiques.plus;
import static td3.ExpressionArithmetiques.pow;
import static td3.ExpressionArithmetiques.times;

import org.junit.Test;

import td3.Addition;
import td3.BigSomme;
import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.ConstSymbolique;
import td3.Constante;
import td3.Division;
import td3.DummyFactorizer;
import td3.ExpressionArithmetique;
import td3.IdentitesRemarquables;
import td3.Multiplication;
import td3.Puissance;
import td3.Soustraction; 
import td3.VariableSymbolique;
import td3.VariableSymboliqueIndexee;

public class AppAlgebreTest {

	@Test
	public void exempleMultiplicationNeutre() {
		ExpressionArithmetique test = new Multiplication(new VariableSymbolique("x"), new ConstEntiere(1));
		ExpressionArithmetique test1 = new Multiplication(new ConstEntiere(1), new VariableSymbolique("x"));
		ExpressionArithmetique test2 = new Multiplication(Constante.e, new ConstEntiere(1));
		ExpressionArithmetique test2bis = new Multiplication(new ConstEntiere(1), Constante.e);
		ExpressionArithmetique test3 = new Multiplication(new Addition(new VariableSymbolique("x"), new ConstEntiere(1)),new ConstEntiere(1));
		ExpressionArithmetique aux1 = new Addition(new Addition(new ConstEntiere(1),new ConstEntiere(1)),new VariableSymbolique("x"));
		ExpressionArithmetique test4 = new Multiplication(new ConstEntiere(1),aux1);
		assertEquals("x", test.simplifier().toString());
		assertEquals("x", test1.simplifier().toString());
		assertEquals("e", test2.simplifier().toString());
		assertEquals("e", test2bis.simplifier().toString());
		assertEquals("(2+x)",test4.simplifier().toString());
		
		
		System.out.println(test3.simplifier().toString());
		assertEquals("(x+1)", test3.simplifier().toString());
		
	}

	@Test 
	public void exempleAdditionNeutre() {
		ExpressionArithmetique test = new Addition(new VariableSymbolique("x"), new ConstEntiere(0));
		ExpressionArithmetique test2 = new Addition(Constante.e, new ConstEntiere(0));
		ExpressionArithmetique test3 = new Addition(new Addition(new VariableSymbolique("x"), new ConstEntiere(1)),new ConstEntiere(0));

		assertEquals("x", test.simplifier().toString());
		assertEquals("e", test2.simplifier().toString());
		
		System.out.println(test3.simplifier().toString());
		assertEquals("(x+1)", test3.simplifier().toString());
	}

	@Test
	public void exempleSoustractionNeutre() {
		ExpressionArithmetique test = new Soustraction(new VariableSymbolique("x"), new ConstEntiere(0));
		ExpressionArithmetique test2 = new Soustraction(Constante.e, new ConstEntiere(0));
		
		ExpressionArithmetique test3 = new Soustraction(new Addition(new VariableSymbolique("x"), new ConstEntiere(1)),new ConstEntiere(0));


		assertEquals("x", test.simplifier().toString());
		assertEquals("e", test2.simplifier().toString());
		
		System.out.println(test3.simplifier().toString());
		assertEquals("(x+1)", test3.simplifier().toString());
	}

	@Test
	public void exempleDivionNeutre() {
		ExpressionArithmetique test = new Division(new VariableSymbolique("x"), new ConstEntiere(1));
		ExpressionArithmetique test1 = new Division(new ConstEntiere(1), new VariableSymbolique("x"));
		ExpressionArithmetique test2 = new Division(Constante.e, new ConstEntiere(1));
		ExpressionArithmetique test2bis = new Division(new ConstEntiere(1), Constante.e);
		ExpressionArithmetique test3 = new Division(new Addition(new VariableSymbolique("x"), new ConstEntiere(1)),new ConstEntiere(1));

		assertEquals("x", test.simplifier().toString());
		assertEquals("(1/x)", test1.simplifier().toString());
		assertEquals("e", test2.simplifier().toString());
		assertEquals("(1/e)", test2bis.simplifier().toString());
		
		System.out.println(test3.simplifier().toString());
		assertEquals("(x+1)", test3.simplifier().toString());
	}
	
	@Test
    public void exemplePuissanceNeutre() {
        ExpressionArithmetique test = new Puissance(new VariableSymbolique("x"), new ConstEntiere(1));
        ExpressionArithmetique test1 = new Puissance(new ConstEntiere(1), new VariableSymbolique("x"));

        ExpressionArithmetique test2 = new Puissance(Constante.e, new ConstEntiere(1));

        assertEquals("x", test.simplifier().toString());
        assertEquals("(1)^x", test1.simplifier().toString());

        assertEquals("e", test2.simplifier().toString());
    }
	
	
	@Test
	public void exempleDistributivite() {
		ExpressionArithmetique test1 = new Addition(new VariableSymbolique("x"), new ConstRationnelle(1,2));
		ExpressionArithmetique aux1 = new ConstEntiere(2);
		ExpressionArithmetique aux2 = new ConstSymbolique("pi",Math.PI);
		ExpressionArithmetique aux3 = new ConstRationnelle(2,3);
		ExpressionArithmetique distributivite1 = new Multiplication(aux1,test1).distributivite();
		ExpressionArithmetique distributivite3 = new Multiplication(aux2,test1).distributivite();
		ExpressionArithmetique distributivite4 = new Multiplication(aux3,test1).distributivite();
		ExpressionArithmetique distributivite1bis = new Multiplication(test1,aux1).distributivite();
		distributivite1bis.simplifier();
		System.out.println(distributivite1.toString());
		System.out.println(distributivite4.toString());
		System.out.println(distributivite4.simplifier().toString());
		System.out.println(distributivite1.simplifier().toString());
		assertEquals("((x*2)+1)",distributivite1.toString());
		assertEquals("((x*2)+1)",distributivite1bis.simplifier().toString());
		assertEquals("((x*pi)+((1/2)*pi))",distributivite3.simplifier().toString());
		assertEquals("((x*(2/3))+(1/3))",distributivite4.simplifier().toString());
		
		ExpressionArithmetique test2 = new Soustraction(new VariableSymbolique("x"), new ConstRationnelle(1,2));
		ExpressionArithmetique distributivite2 = new Multiplication(aux1,test2).distributivite();
		ExpressionArithmetique distributivite2bis = new Multiplication(test2,aux2).distributivite();
		System.out.println(distributivite2.toString());
		System.out.println(distributivite2.simplifier().toString());
		assertEquals("((x*2)-1)",distributivite2.simplifier().toString());
		assertEquals("((x*pi)-((1/2)*pi))",distributivite2bis.simplifier().toString());
	
		ExpressionArithmetique test3 = new Addition(new ConstEntiere(1), new VariableSymbolique("x"));
		ExpressionArithmetique distributivite5 = new Multiplication(new VariableSymbolique("x"),test3).distributivite();
		assertEquals("(x+(x*x))", distributivite5.simplifier().toString());
		
		
	}
	
	@Test
	public void exempleAssociativite() {
		ExpressionArithmetique test1 = new Multiplication(new ConstRationnelle(1,2), new VariableSymbolique("x"));
		ExpressionArithmetique test2 = new Multiplication( new VariableSymbolique("x"),new ConstRationnelle(1,2));
		ExpressionArithmetique aux1 = new ConstEntiere(2);
		ExpressionArithmetique aux2 = new ConstEntiere(1);
		ExpressionArithmetique aux3 = new ConstRationnelle(1,2);
		ExpressionArithmetique associativite1 = new Multiplication(aux1, test1).associativite();
		ExpressionArithmetique associativite2 = new Multiplication(test1,aux1).associativite();
		ExpressionArithmetique associativite3 = new Multiplication(test1,aux2).associativite();
		ExpressionArithmetique associativite4 = new Multiplication(test2,aux1).associativite();
		ExpressionArithmetique associativite5 = new Multiplication(new Multiplication(new VariableSymbolique("x"),new ConstEntiere(2)),aux3).associativite();
		ExpressionArithmetique multiplicationTest1 = new Multiplication(aux1, new Multiplication(test1,aux1)).associativite();
		System.out.println(multiplicationTest1.toString());
		System.out.println(multiplicationTest1.simplifier().toString());
		
		test1.simplifier();
		assertEquals("x",associativite1.toString());
		assertEquals("(((1/2)*x)*2)",associativite2.toString());
		assertEquals("((1/2)*x)",associativite3.toString());
		assertEquals("x",associativite4.simplifier().toString());
		assertEquals("x", associativite5.simplifier().toString());
		assertEquals("(x*2)", multiplicationTest1.toString());
		
		ExpressionArithmetique test3 = new Addition(new ConstEntiere(1),new VariableSymbolique("x")).associativite();
		ExpressionArithmetique addTest1 = new Addition(test3, new ConstEntiere(1)).associativite();
		ExpressionArithmetique addTest2 = new Addition(new ConstEntiere(1), test3).associativite();
		ExpressionArithmetique addTest3 = new Addition(new ConstRationnelle(1,2), test3).associativite();
		ExpressionArithmetique addTest4 = new Addition(aux2, new Addition(test3,aux2)).associativite();

		assertEquals("((1+x)+1)",addTest1.toString());
		assertEquals("(2+x)", addTest2.toString());
		assertEquals("((3/2)+x)", addTest3.toString());
		assertEquals("((2+x)+1)",addTest4.toString());
	} 
	
	@Test
	public void testBigSum() {
		ExpressionArithmetique ea = new Multiplication( new VariableSymboliqueIndexee("a", new VariableSymbolique("i")), new Puissance(new VariableSymbolique("x"), new VariableSymbolique("i")));
		BigSomme bs = new BigSomme(new VariableSymbolique("i"),new ConstEntiere(0), new ConstEntiere(4), ea);
		System.out.println(bs.toString());
		assertEquals(1,1);
	}
	
	@Test
	public void exempleIdentite() {
		ExpressionArithmetique identite1 = new Addition(new Puissance(new VariableSymbolique("a"),new ConstEntiere(2)),new Addition(new Multiplication(new ConstEntiere(2),new Multiplication(new VariableSymbolique("a"), new VariableSymbolique("b"))),new Puissance(new VariableSymbolique("b"),new ConstEntiere(2))));
		ExpressionArithmetique identite2 = new Addition(new Multiplication(new ConstEntiere(2),new Puissance(new VariableSymbolique("a"),new ConstEntiere(2))),new Addition(new Multiplication(new ConstEntiere(4),new Multiplication(new VariableSymbolique("a"), new VariableSymbolique("b"))),new Multiplication(new ConstEntiere(2),new Puissance(new VariableSymbolique("b"),new ConstEntiere(2)))));
		ExpressionArithmetique identite3 = new Addition(new Multiplication(new ConstEntiere(3),new Puissance(new VariableSymbolique("a"),new ConstEntiere(2))),new Addition(new Multiplication(new ConstEntiere(6),new Multiplication(new VariableSymbolique("a"), new VariableSymbolique("b"))),new Multiplication(new ConstEntiere(3),new Puissance(new VariableSymbolique("b"),new ConstEntiere(2)))));
		
		ExpressionArithmetique identite4 = new Soustraction(new Multiplication(new ConstEntiere(2),new Puissance(new VariableSymbolique("a"),new ConstEntiere(2))),new Addition(new Multiplication(new ConstEntiere(4),new Multiplication(new VariableSymbolique("a"), new VariableSymbolique("b"))),new Multiplication(new ConstEntiere(2),new Puissance(new VariableSymbolique("b"),new ConstEntiere(2)))));
		ExpressionArithmetique identite5 = new Soustraction(new Multiplication(new ConstEntiere(3),new Puissance(new VariableSymbolique("a"),new ConstEntiere(2))),new Addition(new Multiplication(new ConstEntiere(6),new Multiplication(new VariableSymbolique("a"), new VariableSymbolique("b"))),new Multiplication(new ConstEntiere(3),new Puissance(new VariableSymbolique("b"),new ConstEntiere(2)))));
		
		ExpressionArithmetique identite6 = new Multiplication(new Multiplication(new ConstEntiere(3),new Puissance(new VariableSymbolique("a"),new ConstEntiere(2))),new Addition(new Multiplication(new ConstEntiere(6),new Multiplication(new VariableSymbolique("a"), new VariableSymbolique("b"))),new Multiplication(new ConstEntiere(3),new Puissance(new VariableSymbolique("b"),new ConstEntiere(2)))));
		System.out.println(identite3.toString());
		assertEquals("((a+b))^2", new IdentitesRemarquables(identite1).simplifier().toString());
		assertEquals("(2*((a+b))^2)", new IdentitesRemarquables(identite2).simplifier().toString());
		
		ExpressionArithmetique identite4Test= new IdentitesRemarquables(identite4).simplifier();
		assertEquals("(2*((a-b))^2)",identite4Test.toString());
		
		ExpressionArithmetique ea = plus(times(c(3), pow(c("a"), c(2))),plus(times(c(6), times(c("a"), c("b"))), times(c(3), pow(c("b"), c(2)))));
		assertEquals("((3*(a)^2)+((6*(a*b))+(3*(b)^2)))", ea.toString());
		ExpressionArithmetique ea2 = DummyFactorizer.factorize(ea);
		System.out.println(ea2.toString());
		System.out.println(ea.toString());
		assertEquals("(3*((a)^2+((2*(a*b))+(b)^2)))", ea2.simplifier().toString());
		ExpressionArithmetique identite3Test = new IdentitesRemarquables(ea2).simplifier();
		assertEquals("(3*((a+b))^2)", identite3Test.toString());
		
		ExpressionArithmetique eaBis = times(plus(c("a"),c("b")),minus(c("a"),c("b")));
		ExpressionArithmetique eaBis2 = DummyFactorizer.factorize(eaBis);
		ExpressionArithmetique identite = new IdentitesRemarquables(eaBis2).simplifier();
		assertEquals("((a)^2-(b)^2)",identite.toString());
		
		ExpressionArithmetique eaQuatre = times(c(2),times(plus(c("a"),c("b")),minus(c("a"),c("b"))));
		ExpressionArithmetique eaQuatre2 = DummyFactorizer.factorize(eaQuatre);
		ExpressionArithmetique identite8 = new IdentitesRemarquables(eaQuatre2).simplifier();
		assertEquals("(2*((a)^2-(b)^2))",identite8.toString());
		
 	minus(pow(c("a"),c(2)),plus(times(c(2),times(c("a"),c("b"))),pow(c("b"),c(2)))); //(a^2)-(2*a*b+b^2)
 	minus(pow(c("a"),c(2)),minus(times(c(2),times(c("a"),c("b"))),pow(c("b"),c(2)))); //(a^2)-(2*a*b-b^2)
 		assertEquals("(3*((a+b))^2)", identite3Test.toString());
 		assertEquals("(3*((a-b))^2)", new IdentitesRemarquables(identite5).simplifier().toString());
 		assertEquals("((3*(a)^2)*((6*(a*b))+(3*(b)^2)))", new IdentitesRemarquables(identite6).simplifier().toString());
 		
	}
	
/**	@Test
	public void testEquationDegre2() {
		ExpressionArithmetique ea = minus(times(c(3),pow(c("x"),c(2))),plus(times(c(30),c("x")),c(75)));
		assertEquals("((3*(x)^2)-((30*x)+75))", ea.toString());
		ExpressionArithmetique eaBis = DummyFactorizer.factorize(ea);
		System.out.println(eaBis);
		assertEquals("(3*(((x)^2)+((10*x)+25)))", eaBis.toString());
	}
 **/
}
package poo;
import static td3.ExpressionArithmetiques.c;
import static td3.ExpressionArithmetiques.minus;
import static td3.ExpressionArithmetiques.plus;
import static td3.ExpressionArithmetiques.pow;
import static td3.ExpressionArithmetiques.times;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import td3.Addition;
import td3.BigProduit;
import td3.BigSomme;
import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.ConstSymbolique;
import td3.Constante;
import td3.Cosinus;
import td3.Derivation;
import td3.Division;
import td3.ExpressionArithmetique;
import td3.Logarithme;
import td3.Multiplication;
import td3.Puissance;
import td3.Sinus;
import td3.Soustraction;
import td3.SquareRoot;
import td3.VariableSymbolique;
import td3.VariableSymboliqueIndexee;
import static org.junit.Assert.assertEquals;
public class AppAnalyseTest {
	@Test
	public void testLogRea() {
		ExpressionArithmetique a = new Addition(new Soustraction(Constante.pi, Constante.pi), new ConstEntiere(1));
		ExpressionArithmetique test1 = new Logarithme(a);
		assertEquals(0, test1.calculer(), 0.1);

		ExpressionArithmetique test2 = new Addition(new Logarithme(new ConstEntiere(1)), new ConstEntiere(1));
		assertEquals(1, test2.calculer(), 0.1);

		ExpressionArithmetique test3 = new Addition(new Logarithme(Constante.e), new ConstEntiere(1));
		assertEquals(2, test3.calculer(), 0.1);
	}

	@Test
	public void testCos() {
		ExpressionArithmetique test2 = new Addition(new Cosinus(new ConstEntiere(0)), new ConstEntiere(10));
		assertEquals("11", test2.simplifier().toString());

		ExpressionArithmetique test3 = new Cosinus(new Division(Constante.pi, new ConstEntiere(2)));
		assertEquals("0", test3.simplifier().toString());

		ExpressionArithmetique test4 = new Cosinus(new Cosinus(new Division(Constante.pi, new ConstEntiere(2))));
		assertEquals("1", test4.simplifier().toString());

		// CHANGEMENT 1/2 => (1/2)
		ExpressionArithmetique test5 = new Cosinus(new Division(Constante.pi, new ConstEntiere(3)));
		assertEquals("(1/2)", test5.simplifier().toString());

		ExpressionArithmetique test6 = new Cosinus(new Division(Constante.pi, new ConstEntiere(4)));
		assertEquals("(((2)^1/2)/2)", test6.simplifier().toString());

		ExpressionArithmetique test7 = new Cosinus(new Division(Constante.pi, new ConstEntiere(6)));
		assertEquals("(((3)^1/2)/2)", test7.simplifier().toString());

		ExpressionArithmetique test8 = new Cosinus(new Division(Constante.pi,
				new Addition(new Addition(new ConstEntiere(3), new ConstEntiere(2)), new ConstEntiere(1))));
		assertEquals("(((3)^1/2)/2)", test8.simplifier().toString());
	}

	@Test
	public void testSin() {
		ExpressionArithmetique test2 = new Addition(new Sinus(new ConstEntiere(0)), new ConstEntiere(10));
		assertEquals("10", test2.simplifier().toString());
		ExpressionArithmetique test3 = new Sinus(new Division(Constante.pi, new ConstEntiere(2)));
		assertEquals("1", test3.simplifier().toString());

		ExpressionArithmetique test4 = new Sinus(new Cosinus(new Division(Constante.pi, new ConstEntiere(2))));
		assertEquals("0", test4.simplifier().toString());
		ExpressionArithmetique test5 = new Sinus(new Division(Constante.pi, new ConstEntiere(3)));
		assertEquals("(((3)^1/2)/2)", test5.simplifier().toString());
		ExpressionArithmetique test6 = new Sinus(new Division(Constante.pi, new ConstEntiere(4)));
		assertEquals("(((2)^1/2)/2)", test6.simplifier().toString());
		// CHANGEMENT 1/2 => (1/2)
		ExpressionArithmetique test7 = new Sinus(new Division(Constante.pi, new ConstEntiere(6)));
		assertEquals("(1/2)", test7.simplifier().toString());

	}

	@Test
	public void testExp() {
		ExpressionArithmetique test = new Puissance(Constante.e, new ConstEntiere(0));
		assertEquals("1", test.simplifier().toString());

		ExpressionArithmetique test2 = new SquareRoot(new ConstEntiere(16));
		assertEquals("4", test2.simplifier().toString());

		ExpressionArithmetique test3 = new Puissance(new ConstEntiere(0), Constante.e);
		assertEquals("(0)^e", test3.simplifier().toString());
	}

	@Test
	public void testTrigo() {
		ExpressionArithmetique test = new Sinus(new Multiplication(Constante.pi, new ConstEntiere(4)));
		assertEquals("0", test.simplifier().toString());

		ExpressionArithmetique test2 = new Cosinus(new Multiplication(Constante.pi, new ConstEntiere(4)));
		assertEquals("1", test2.simplifier().toString());

		ExpressionArithmetique test3 = new Sinus(new Multiplication(new ConstEntiere(4), Constante.pi));
		assertEquals("0", test3.simplifier().toString());

		ExpressionArithmetique test4 = new Cosinus(new Multiplication(new ConstEntiere(4), Constante.pi));
		assertEquals("1", test4.simplifier().toString());

		ExpressionArithmetique test5 = new Cosinus(new Multiplication(Constante.pi, new ConstEntiere(-4)));
		assertEquals("1", test5.simplifier().toString());

		ExpressionArithmetique test6 = new Sinus(new Multiplication(Constante.pi, new ConstEntiere(-4)));
		assertEquals("0", test6.simplifier().toString());

		ExpressionArithmetique test7 = new Cosinus(new Division(
				new Multiplication(new ConstRationnelle(13, 8), new ConstEntiere(6)), new ConstEntiere(3)));
		assertEquals("cos((13/4))", test7.simplifier().toString());

		ExpressionArithmetique test8 = new Sinus(
				new Multiplication(new Division(new ConstEntiere(6), new ConstEntiere(2)), Constante.pi));
		assertEquals("0", test8.simplifier().toString());

		ExpressionArithmetique test10 = new Sinus(new Multiplication(
				new Division(new ConstEntiere(12), new ConstEntiere(2)), new VariableSymbolique("x")));
		assertEquals("sin((6*x))", test10.simplifier().toString());

		ExpressionArithmetique test11 = new Sinus(
				new Division(new Multiplication(Constante.pi, new ConstEntiere(20)), new ConstEntiere(6)));
		assertEquals("sin(((10*pi)/3))", test11.simplifier().toString());

		ExpressionArithmetique test12 = new Cosinus(
				new Division(new Multiplication(new ConstEntiere(3), Constante.pi), new ConstEntiere(9)));
		assertEquals("(1/2)", test12.simplifier().toString());

		ExpressionArithmetique test13 = new Sinus(
				new Multiplication(new Division(new ConstEntiere(6), new ConstEntiere(12)), Constante.pi));
		assertEquals("1", test13.simplifier().toString());

		ExpressionArithmetique test14 = new Sinus(
				new Multiplication(Constante.pi, new Division(new ConstEntiere(6), new ConstEntiere(12))));
		assertEquals("1", test14.simplifier().toString());

		ExpressionArithmetique test15 = new Sinus(
				new Division(new Multiplication(new ConstEntiere(6), Constante.pi), new ConstEntiere(12)));
		assertEquals("1", test15.simplifier().toString());

		ExpressionArithmetique test16 = new Sinus(
				new Division(new Multiplication(Constante.pi, new ConstEntiere(12)), new ConstEntiere(24)));
		assertEquals("1", test16.simplifier().toString());

	}

	@Test
	public void testBigSum() {
		ExpressionArithmetique ea = new Multiplication(new VariableSymboliqueIndexee("a", new VariableSymbolique("i")),
				new Puissance(new VariableSymbolique("x"), new VariableSymbolique("i")));
		BigSomme bs = new BigSomme(new VariableSymbolique("i"), new ConstEntiere(0), new ConstEntiere(4), ea);
		assertEquals("(a_0+((a_1*x)+((a_2*(x)^2)+((a_3*(x)^3)+(a_4*(x)^4)))))", bs.simplifier().toString());
		BigSomme bs2 = new BigSomme(new VariableSymbolique("i"), new ConstEntiere(5), new ConstEntiere(10), ea);
		assertEquals("((a_5*(x)^5)+((a_6*(x)^6)+((a_7*(x)^7)+((a_8*(x)^8)+((a_9*(x)^9)+(a_10*(x)^10))))))",
				bs2.simplifier().toString());

		ExpressionArithmetique a = new VariableSymbolique("a");
		ArrayList<Constante> array = new ArrayList<>();
		array.add(new ConstEntiere(1));
		array.add(new ConstEntiere(9));
		array.add(new ConstEntiere(4));
		array.add(new ConstEntiere(2));
		Map<VariableSymbolique, ArrayList<Constante>> map = new LinkedHashMap<>();
		map.put((VariableSymbolique) a, (ArrayList<Constante>) array);

		ExpressionArithmetique x = new VariableSymbolique("x");
		ExpressionArithmetique z = new ConstEntiere(3);
		Map<VariableSymbolique, Constante> map2 = new LinkedHashMap<VariableSymbolique, Constante>();
		map2.put((VariableSymbolique) x, (Constante) z);

		ExpressionArithmetique ea2 = new Multiplication(new VariableSymboliqueIndexee("a", new VariableSymbolique("i")),
				new Puissance(new VariableSymbolique("x"), new VariableSymbolique("i")));
		BigSomme bs3 = new BigSomme(new VariableSymbolique("i"), new ConstEntiere(0), new ConstEntiere(3), ea2);
		assertEquals(118, bs3.calculer(map, map2), 0.01);
	}

	@Test
	public void testBigProduit() {
		ExpressionArithmetique ea = new Multiplication(new VariableSymboliqueIndexee("a", new VariableSymbolique("i")),
				new Puissance(new VariableSymbolique("x"), new VariableSymbolique("i")));
		BigProduit bs = new BigProduit(new VariableSymbolique("i"), new ConstEntiere(0), new ConstEntiere(4), ea);

		ExpressionArithmetique ea2 = new Multiplication(new VariableSymboliqueIndexee("a", new VariableSymbolique("i")),
				new Puissance(new VariableSymbolique("x"), new VariableSymbolique("i")));
		BigProduit bs2 = new BigProduit(new VariableSymbolique("i"), new ConstEntiere(0), new ConstEntiere(1), ea2);

		ExpressionArithmetique a = new VariableSymbolique("a");
		ArrayList<Constante> array = new ArrayList<>();
		array.add(new ConstEntiere(1));
		array.add(new ConstEntiere(9));
		array.add(new ConstEntiere(4));
		array.add(new ConstEntiere(2));
		Map<VariableSymbolique, ArrayList<Constante>> map = new LinkedHashMap<>();
		map.put((VariableSymbolique) a, (ArrayList<Constante>) array);

		ExpressionArithmetique x = new VariableSymbolique("x");
		ExpressionArithmetique z = new ConstEntiere(2);
		Map<VariableSymbolique, Constante> map2 = new LinkedHashMap<VariableSymbolique, Constante>();
		map2.put((VariableSymbolique) x, (Constante) z);

		ExpressionArithmetique ea3 = new Multiplication(new VariableSymboliqueIndexee("a", new VariableSymbolique("i")),
				new Puissance(new VariableSymbolique("x"), new VariableSymbolique("i")));
		BigProduit bs3 = new BigProduit(new VariableSymbolique("i"), new ConstEntiere(0), new ConstEntiere(3), ea3);
		assertEquals(4608, bs3.calculer(map, map2), 0.01);

	}

	@Test
	public void testDerivation() {
		Derivation e1 = new Derivation(new Addition(
				new Multiplication(new ConstEntiere(3),
						new Puissance(new VariableSymbolique("x"), new ConstEntiere(2))),
				new Addition(new Multiplication(new ConstEntiere(5), new VariableSymbolique("x")),
						new ConstEntiere(10))));
		assertEquals("((6*x)+5)",e1.simplifier().toString()); // 3x^2+5x+10
		
		ExpressionArithmetique a = new ConstEntiere(2);
		ExpressionArithmetique b = new VariableSymbolique("x");
		Map<VariableSymbolique,Constante> map = new LinkedHashMap<VariableSymbolique,Constante>();
		map.put((VariableSymbolique) b, (Constante) a);
		assertEquals(17,e1.calculer(map),0.01);
		
		Derivation e5 = new Derivation(new Addition(
				new Multiplication(new ConstEntiere(3),
						new Puissance(new VariableSymbolique("x"), new ConstEntiere(2))),
				new Addition(new Multiplication(new ConstEntiere(5), new VariableSymbolique("x")),
						new ConstEntiere(10))));
		
		ExpressionArithmetique a2 = new ConstRationnelle(3,2);
		ExpressionArithmetique b2 = new VariableSymbolique("x");
		Map<VariableSymbolique,Constante> map2 = new LinkedHashMap<VariableSymbolique,Constante>();
		map2.put((VariableSymbolique) b2, (Constante) a2);
		assertEquals(14,e5.calculer(map2),0.01);
		

		Derivation e2 = new Derivation(new Addition(
				new Multiplication(new ConstEntiere(4),
						new Puissance(new VariableSymbolique("x"), new ConstEntiere(3))),
				new Addition(
						new Multiplication(new ConstEntiere(3),
								new Puissance(new VariableSymbolique("x"), new ConstEntiere(2))),
						new Addition(new Multiplication(new ConstEntiere(5), new VariableSymbolique("x")),
								new ConstEntiere(10))))); // 4x^3+3x^2+5x+10
		assertEquals("((12*(x)^2)+((6*x)+5))",e2.simplifier().toString());

		Derivation e3 = new Derivation(new Addition(
				new Multiplication(new ConstEntiere(5),
						new Puissance(new VariableSymbolique("x"), new ConstEntiere(4))),
				new Addition(
						new Multiplication(new ConstEntiere(4),
								new Puissance(new VariableSymbolique("x"), new ConstEntiere(3))),
						new Addition(
								new Multiplication(new ConstEntiere(3),
										new Puissance(new VariableSymbolique("x"), new ConstEntiere(2))),
								new Addition(new Multiplication(new ConstEntiere(5), new VariableSymbolique("x")),
										new ConstEntiere(10))))),3); // 5x^4+4x^3+3x^2+5x+10
		assertEquals("((120*x)+24)",e3.simplifier().toString());
		
		Derivation e4 = new Derivation(new Addition(
				new Multiplication(new ConstEntiere(3),
						new Puissance(new VariableSymbolique("x"), new ConstEntiere(2))),
				new Addition(new ConstEntiere(10),new Multiplication(new ConstEntiere(5), new VariableSymbolique("x")))));
		assertEquals("((6*x)+5)",e4.simplifier().toString());

		Derivation ea = new Derivation(new Addition(new Multiplication(new
		ConstEntiere(3), new Puissance(new VariableSymbolique("x"), new
		ConstEntiere(2))),new VariableSymbolique("x")));  //3x^2+x
		assertEquals("((6*x)+1)",ea.simplifier().toString());
		
		Derivation ea2 = new Derivation(plus(times(c(10),c("x")), (times(c(10),c("x")))));
		assertEquals("20", ea2.toString());
		
		Derivation e8 = new Derivation(new Addition(
                new Multiplication(new ConstEntiere(12),
                        new Puissance(new VariableSymbolique("x"), new ConstEntiere(2))),
                new Soustraction(new Multiplication(new ConstEntiere(12), new VariableSymbolique("x")),
                        new ConstEntiere(10)))); // 12x^2+12x-10
        assertEquals("((24*x)+12)",e8.toString());
        
		
		
		Derivation e9 = new Derivation(new Soustraction(
				new Multiplication(new ConstEntiere(6),
						new Puissance(new VariableSymbolique("x"), new ConstEntiere(2))),
				new Soustraction(new Multiplication(new ConstEntiere(12), new VariableSymbolique("x")),
						new ConstEntiere(10)))); // 6x^2-12x-10
		assertEquals("((12*x)-12)",e9.simplifier().toString());
		
		Derivation e10 = new Derivation(new Soustraction(
				new Multiplication(new ConstEntiere(4),
						new Puissance(new VariableSymbolique("x"), new ConstEntiere(3))),
				new Addition(
						new Multiplication(new ConstEntiere(3),
								new Puissance(new VariableSymbolique("x"), new ConstEntiere(2))),
						new Soustraction(new Multiplication(new ConstEntiere(5), new VariableSymbolique("x")),
								new ConstEntiere(10))))); // 4x^3-3x^2+5x-10
		assertEquals("((12*(x)^2)-((6*x)+5))",e10.simplifier().toString());
	} 
}
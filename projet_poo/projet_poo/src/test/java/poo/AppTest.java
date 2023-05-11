package poo;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import td3.Addition;
import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.ConstSymbolique;
import td3.Constante;
import td3.Cosinus;
import td3.Division;
import td3.ExpressionArithmetique;
import td3.Logarithme;
import td3.Multiplication;
import td3.Puissance;
import td3.Sinus;
import td3.Soustraction;
import td3.SquareRoot;
import td3.VariableSymbolique;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void simpleSum() {

		ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);
		Addition racine = new Addition(neuf, deux);

		assertEquals(11, ((ConstEntiere) racine.simplifier()).getEntier());

	}

	@Test
	public void classExample() {

		ExpressionArithmetique neuf = new ConstEntiere(9);
		ExpressionArithmetique deux = new ConstEntiere(2);
		ExpressionArithmetique trois = new ConstEntiere(3);
		ExpressionArithmetique cr = new ConstRationnelle(1, 17);

		ExpressionArithmetique plus = new Addition(neuf, deux);
		ExpressionArithmetique minus = new Soustraction(trois, cr);
		ExpressionArithmetique times = new Multiplication(plus, minus);

		

		assertEquals(550, ((ConstRationnelle) times.simplifier()).getNumerateur());
		assertEquals(17, ((ConstRationnelle) times.simplifier()).getDenominateur());

	}
	
	@Test
	public void exempleCalculer() {

		ExpressionArithmetique neuf = new ConstEntiere(9);
		ExpressionArithmetique deux = new ConstEntiere(2);
		ExpressionArithmetique trois = new ConstEntiere(3);
		ExpressionArithmetique cr = new ConstRationnelle(1, 17);

		ExpressionArithmetique plus = new Addition(neuf, deux);
		ExpressionArithmetique minus = new Soustraction(trois, cr);
		ExpressionArithmetique times = new Multiplication(plus, minus);

		ExpressionArithmetique results = new ConstRationnelle(550, 17);
		

		
		assertEquals(550/17.0, times.calculer(),0.00001);

	}
	
	@ Test 
	public void exemplePuissance() {
		ExpressionArithmetique neuf = new ConstRationnelle(9,2);
		ExpressionArithmetique deuxbis = new ConstEntiere(2);
		ExpressionArithmetique puiss = new Puissance(neuf,deuxbis);
		assertEquals(81.0/4, (puiss.calculer()), 0.0001);
		assertEquals(81,( (ConstRationnelle)(puiss.simplifier())).getNumerateur());
		assertEquals(4,( (ConstRationnelle)(puiss.simplifier())).getDenominateur());
		
		ExpressionArithmetique trois = new ConstEntiere(3);
		ExpressionArithmetique deux = new ConstEntiere(2);		
		ExpressionArithmetique puissbis = new Puissance(trois, deux);	
		ExpressionArithmetique puissTest = new Puissance(new ConstSymbolique("e",Math.E),deux);
		System.out.println(puissTest.simplifier().toString());
		
		assertEquals(9, ((ConstEntiere) puissbis.simplifier()).getEntier());	

		
	}
	
    @Test
	public void exempleFormeStandard() {

		ExpressionArithmetique a = new  Addition(new ConstEntiere(1), new VariableSymbolique("x"));
		System.out.println(a);
		ExpressionArithmetique c = new  Addition(new ConstEntiere(3), new VariableSymbolique("x"));
		ExpressionArithmetique test1 = new Addition(new Multiplication(new ConstEntiere(4),new ConstEntiere(2)), new VariableSymbolique("x"));
		ExpressionArithmetique test3 = new Addition(new ConstEntiere(8), new VariableSymbolique("x"));
		ExpressionArithmetique test4 = new Multiplication(new ConstEntiere(2), new VariableSymbolique("x"));
		ExpressionArithmetique test5 = new Multiplication( new VariableSymbolique("x"),new ConstEntiere(2));
		ExpressionArithmetique test2 = new Addition(new ConstEntiere(4),new Multiplication(new ConstEntiere(2), new VariableSymbolique("x")));
		ExpressionArithmetique b = new Addition(new Addition(new ConstRationnelle(1,4),new ConstRationnelle(3,4)),new VariableSymbolique("x"));
		System.out.println(a);
		System.out.println(c);
		System.out.println(new ConstEntiere(1).equals(new ConstEntiere(3)));
		ExpressionArithmetique lnTest = new Logarithme(new Addition(new Addition(new ConstEntiere(1),new ConstEntiere(2)), new VariableSymbolique("x")));
		ExpressionArithmetique lnTest2 = new Logarithme(new Addition(new ConstEntiere(3), new VariableSymbolique("x")));
		assertEquals(false, a.equals(test3));	
		assertEquals(false, new ConstEntiere(1).equals(new ConstEntiere(3)));
		assertEquals(false, test4.equals(test5));
		assertEquals("ln(((1+2)+x))", lnTest.toString());
		assertEquals("ln((3+x))", lnTest2.toString()); 
		assertEquals(true,test1.simplifier().equals(test3));
		assertEquals(true,lnTest.simplifier().equals(lnTest2));
		
	} 
	 
	@Test
	public void exempleCalcul() {
		ExpressionArithmetique a = new  Addition(new ConstEntiere(1), new ConstRationnelle(3,4));
		assertEquals(1.7500, a.calculer(), 0.0001);
		ExpressionArithmetique b = new Addition(new ConstEntiere(1), new ConstSymbolique("pi",Math.PI));
		assertEquals(4.1416, b.calculer(), 0.0001);
		ExpressionArithmetique cos1 = new Cosinus(new Division(new ConstSymbolique("pi",Math.PI),new ConstEntiere(6)));
		ExpressionArithmetique costest = new  Division(new SquareRoot(new ConstEntiere(3)), new ConstEntiere(2));
		assertEquals(0.8660, cos1.calculer(), 0.0001); 
		assertEquals(costest.calculer(), cos1.calculer(), 0.0001);
	}
	
	@Test
	public void exempleEgalite() {
		ExpressionArithmetique a = new  Addition(new ConstEntiere(3), new VariableSymbolique("x"));
		ExpressionArithmetique c = new  Addition(new ConstEntiere(1), new VariableSymbolique("x"));
		ExpressionArithmetique test = new Addition(a,c);
		ExpressionArithmetique b = new ConstRationnelle(3,4);
		ExpressionArithmetique d = new ConstRationnelle(3,5);
		ExpressionArithmetique sin1 = new Sinus(b);
		ExpressionArithmetique sin2 = new Sinus(d);
		ExpressionArithmetique ln1 = new Logarithme(a);
		ExpressionArithmetique ln2 = new Logarithme(a);
		ExpressionArithmetique ln3 = new Logarithme(c);
		assertEquals(true, new Addition(a,c).equals(test));
		assertEquals(false, (a).equals(c));
		assertEquals(false, sin1.equals(sin2));
		assertEquals(true, ln1.equals(ln2));
		assertEquals(false, ln1.equals(ln3));
	}
	
	@Test
	public void exempleVariableSymbolique() {
		ExpressionArithmetique a = new ConstEntiere(1);
		ExpressionArithmetique z = new ConstEntiere(2);
		ExpressionArithmetique x = new ConstRationnelle(3,2);
		ExpressionArithmetique b = new VariableSymbolique("x");
		ExpressionArithmetique d = new VariableSymbolique("y");
		ExpressionArithmetique c = new Addition(new VariableSymbolique("y"),a);
		ExpressionArithmetique cbis = new Soustraction(a,new VariableSymbolique("y"));
		ExpressionArithmetique division = new Division(b,c);
		ExpressionArithmetique divisionbis = new Division(b,cbis);
		ExpressionArithmetique calcul = new Addition(a, division);
		ExpressionArithmetique calculbis = new Addition(a, divisionbis);
		ExpressionArithmetique test2 = new Addition(new Multiplication(new ConstEntiere(3), new ConstSymbolique("pi",Math.PI)),new VariableSymbolique("x"));
		Map<VariableSymbolique,Constante> map = new LinkedHashMap<VariableSymbolique,Constante>();
		Map<VariableSymbolique,Constante> map2 = new LinkedHashMap<VariableSymbolique,Constante>();
		Map<VariableSymbolique,Constante> map3 = new LinkedHashMap<VariableSymbolique,Constante>();
		map.put((VariableSymbolique) b, (Constante) a);
		map.put((VariableSymbolique) d, (Constante) a);
		
		map2.put((VariableSymbolique) b, (Constante) z);
		map2.put((VariableSymbolique) d, (Constante) x);
		
		map3.put((VariableSymbolique) b, (Constante) a);
		map3.put((VariableSymbolique) d, (Constante) a);
		
		assertEquals(1.5,calcul.calculer(map), 0.01);
		System.out.println(calcul.toString()); 
		assertEquals(10.4248,test2.calculer(map), 0.0001);
		assertEquals(1.8,calcul.calculer(map2), 0.01);
		 
		System.out.println(cbis.calculer(map3));
		System.out.println(calculbis.toString()); 
		//System.out.println(new Division(a,new ConstEntiere(0)).calculer());
		//System.out.println(calculbis.calculer(map3));
		
		
	}
	
	@Test
	public void exempleAffichageExpressions() {
		ExpressionArithmetique a = new  Addition(new ConstEntiere(3), new VariableSymbolique("x"));
		assertEquals("(3+x)", a.toString());
		ExpressionArithmetique test1 = new Addition(new Multiplication(new ConstEntiere(4),new ConstEntiere(2)), new VariableSymbolique("x"));
		assertEquals("((4*2)+x)", test1.toString());
		assertEquals("(8+x)", test1.simplifier().toString());
		System.out.println(test1.toString());
		ExpressionArithmetique test2 = new Addition(new Multiplication(new ConstEntiere(3), new ConstSymbolique("pi",Math.PI)),new VariableSymbolique("x"));
		assertEquals("((3*pi)+x)",test2.toString());
		System.out.println(test2.toString());
	}
}

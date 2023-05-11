package poo;

import td3.ExpressionArithmetique;
import td3.DummyFactorizer;
import td3.IdentitesRemarquables;
import td3.Multiplication;

import static org.junit.Assert.assertEquals;
import static td3.ExpressionArithmetiques.*;

import org.junit.Test;

public class AppNHETest {

	@Test
	public void testFactorizerWithIdentiteRemarquables() {

		ExpressionArithmetique ea = plus(times(c(3), pow(c("a"), c(2))),
				plus(times(c(6), times(c("a"), c("b"))), times(c(3), pow(c("b"), c(2)))));
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
		ExpressionArithmetique identite4 = new IdentitesRemarquables(eaQuatre2).simplifier();
		assertEquals("(2*((a)^2-(b)^2))",identite4.toString());
		
		ExpressionArithmetique eaCinq = times(plus(times(c(2),c("a")),times(c(2),c("b"))),minus(c("a"),c("b")));
		ExpressionArithmetique eaCinq2 = DummyFactorizer.factorize(eaCinq);
		ExpressionArithmetique identite5 = new IdentitesRemarquables(eaCinq).simplifier();
		//assertEquals("(2*((a)^2-(b)^2))",identite5.toString()); 
		
		/**ExpressionArithmetique eaTrois = times(plus(times(c(2),c("a")),times(c(2),c("b"))),minus(times(c(2),c("a")),times(c(2),c("b"))));
		
		ExpressionArithmetique identite2 = new IdentitesRemarquables(eaTrois).simplifier();
		assertEquals("(4*((a)^2-(b)^2))",identite2.toString());  **/
		
	} 

}

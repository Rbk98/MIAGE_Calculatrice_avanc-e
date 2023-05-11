package td3;

import java.util.Map;

public final class ConstRationnelle extends Constante{

	public ConstRationnelle(int num, int denom) {
		this.numerateur = num;
		this.denominateur = denom; 
	}

	public int getNumerateur() {
		return numerateur;
	}

	public int getDenominateur() {
		return denominateur;
	}

	private final int numerateur;
	private final int denominateur;

	@Override
	public ExpressionArithmetique simplifier() {
		int pgcd = gcd(this.numerateur, this.denominateur);
		if((this.denominateur/pgcd) == 1) {
			return new ConstEntiere(this.numerateur/pgcd);
		}
		return new ConstRationnelle(this.numerateur / pgcd, this.denominateur / pgcd);
	}



	@Override
	public double calculer() {
		return (double) this.numerateur/this.denominateur;
	} 
	
	@Override
	public double calculer(Map<VariableSymbolique, Constante> mapValue) {
		return (double) this.numerateur/this.denominateur;
	}
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + denominateur;
		result = prime * result + numerateur;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConstRationnelle other = (ConstRationnelle) obj;
		if (denominateur != other.denominateur)
			return false;
		if (numerateur != other.numerateur)
			return false;
		return true;
	}
 
	@Override
	public String toString() {
		return "("+this.numerateur+"/"+this.denominateur+")";
	}

}

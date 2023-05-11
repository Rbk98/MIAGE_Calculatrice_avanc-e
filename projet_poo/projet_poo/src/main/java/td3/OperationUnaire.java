package td3;

public abstract class OperationUnaire implements ExpressionArithmetique {

	protected ExpressionArithmetique eaMain;

	public OperationUnaire(ExpressionArithmetique eaMain) {
		this.eaMain = eaMain;
	}
	
	abstract ExpressionArithmetique createNewInstance(ExpressionArithmetique EA);
	
	public ExpressionArithmetique simplifier() {
		this.eaMain = this.eaMain.simplifier();
		return createNewInstance(this.eaMain);
	}
	 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eaMain == null) ? 0 : eaMain.hashCode());
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
		OperationUnaire other = (OperationUnaire) obj;
		if (eaMain == null) {
			if (other.eaMain != null)
				return false;
		} else if (!eaMain.equals(other.eaMain))
			return false;
		return true;
	} 
	
	

}

package td3;



public abstract class OperationBinaire implements ExpressionArithmetique {
	protected ExpressionArithmetique eaLeft;
	public ExpressionArithmetique getEaLeft() {
		return eaLeft;
	}

	public ExpressionArithmetique getEaRight() {
		return eaRight;
	}

	protected ExpressionArithmetique eaRight;

	public OperationBinaire(ExpressionArithmetique eaLeft, ExpressionArithmetique eaRight) {
		this.eaLeft = eaLeft;
		this.eaRight = eaRight;
	}
	
	public abstract ConstEntiere getElementNeutre(); 

	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstEntiere droite) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstRationnelle droite) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstEntiere droite) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstRationnelle droite) {
		return this;
	}

	protected ExpressionArithmetique simplifie(VariableSymbolique gauche, ConstEntiere droite) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstSymbolique gauche, ConstEntiere droite) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ConstEntiere droite) {
		return this;
	}
	
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ExpressionArithmetique droite) {
		return this;
	}
	
	

@Override									
	public ExpressionArithmetique simplifier() {

		ExpressionArithmetique res;
		this.eaLeft = this.eaLeft.simplifier();
		this.eaRight = this.eaRight.simplifier();

		if (this.eaLeft instanceof ConstEntiere && this.eaRight instanceof ConstEntiere) {
			ConstEntiere gauche = (ConstEntiere) this.eaLeft;
			ConstEntiere droite = (ConstEntiere) this.eaRight;

			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof ConstRationnelle && this.eaRight instanceof ConstRationnelle) {
			ConstRationnelle gauche = (ConstRationnelle) this.eaLeft;
			ConstRationnelle droite = (ConstRationnelle) this.eaRight;

			res = simplifie(gauche, droite);

		} else if (this.eaLeft instanceof ConstRationnelle && this.eaRight instanceof ConstEntiere) {
			ConstRationnelle gauche = (ConstRationnelle) this.eaLeft;
			ConstEntiere droite = (ConstEntiere) this.eaRight;
			res = simplifie(gauche, droite);
		

		} else if (this.eaLeft instanceof ConstEntiere && this.eaRight instanceof ConstRationnelle) {
			ConstEntiere gauche = (ConstEntiere) this.eaLeft;
			ConstRationnelle droite = (ConstRationnelle) this.eaRight;
				res = simplifie(gauche, droite);
			

		} else if (this.eaLeft instanceof ExpressionArithmetique && this.eaRight instanceof ConstEntiere) {
			ExpressionArithmetique gauche = this.eaLeft;
			ConstEntiere droite = (ConstEntiere) this.eaRight;
			if (droite.equals(this.getElementNeutre())) {
				res = gauche;
			} else {
				res = simplifie(gauche, droite);
			}
		} else if (this.eaLeft instanceof ConstEntiere && this.eaRight instanceof ExpressionArithmetique ) {
			ConstEntiere  gauche = (ConstEntiere) this.eaLeft;
			ExpressionArithmetique droite = this.eaRight;
			if (((this instanceof Multiplication) || (this instanceof Addition)) && (gauche.equals(this.getElementNeutre()))) {
				res = droite;
			}else {
				res = simplifie(gauche, droite);
			}
		} else {
			res = this;
		}

		return res;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eaLeft == null) ? 0 : eaLeft.hashCode());
		result = prime * result + ((eaRight == null) ? 0 : eaRight.hashCode());
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
		OperationBinaire other = (OperationBinaire) obj;
		if (eaLeft == null) {
			if (other.eaLeft != null)
				return false;
		} else if (!eaLeft.equals(other.eaLeft))
			return false;
		if (eaRight == null) {
			if (other.eaRight != null)
				return false;
		} else if (!eaRight.equals(other.eaRight))
			return false;
		return true;
	}

	/**
	 * public boolean equals(Object obj) { if (this == obj) return true; if (obj ==
	 * null) return false; if (getClass() != obj.getClass()) return false;
	 * OperationBinaire other = (OperationBinaire) obj; if
	 * ((this.eaLeft.getClass()!=other.eaLeft.getClass()) ||
	 * (this.eaRight.getClass()!=other.eaRight.getClass())) { return false; } else
	 * if (this.eaLeft instanceof Constante) { if (this.eaLeft instanceof
	 * ConstEntiere) { ConstEntiere e1 = (ConstEntiere) other.eaLeft; ConstEntiere
	 * e2 = (ConstEntiere) other.eaRight; return ((this.eaLeft.equals(e1)) &&
	 * (this.eaRight.equals(e2))) ; } else if (this.eaLeft instanceof
	 * ConstRationnelle) { ConstRationnelle e1 = (ConstRationnelle) other.eaLeft;
	 * ConstRationnelle e2 = (ConstRationnelle) other.eaRight; return
	 * ((this.eaLeft.equals(e1)) && (this.eaRight.equals(e2))) ; } else {
	 * ConstSymbolique e1 = (ConstSymbolique) other.eaLeft; ConstSymbolique e2 =
	 * (ConstSymbolique) other.eaRight; return ((this.eaLeft.equals(e1)) &&
	 * (this.eaRight.equals(e2))) ; } } else { return
	 * ((this.eaLeft.equals(other.eaLeft)) && (this.eaRight.equals(other.eaRight)));
	 * } } if (eaLeft == null) { if (other.eaLeft != null) return false; } else if
	 * (!eaLeft.equals(other.eaLeft)) return false; if (eaRight == null) { if
	 * (other.eaRight != null) return false; } else if
	 * (!eaRight.equals(other.eaRight)) return false; return true; }
	 **/

}


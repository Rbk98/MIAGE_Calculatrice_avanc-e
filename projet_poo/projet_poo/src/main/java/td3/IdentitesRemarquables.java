package td3;

public class IdentitesRemarquables extends IdentitesRemarquablesVisitor {

	public IdentitesRemarquables(ExpressionArithmetique iR) {
		this.identiteUtilisateur = iR;
	}

	public ExpressionArithmetique simplifier() {

		
		ExpressionArithmetique factorized = DummyFactorizer.factorize(this.identiteUtilisateur);

		if (factorized instanceof Multiplication) {
			Multiplication factorizedMult = (Multiplication) factorized;
			if ((factorizedMult.eaLeft instanceof OperationBinaire) && (factorizedMult.eaRight instanceof OperationBinaire)){
				factorized = tryIRIdentification(factorized);
				
			}
			factorizedMult.eaRight = tryIRIdentification(factorizedMult.eaRight);
			return factorized;
		} else if (factorized instanceof OperationBinaire) {
			this.identiteUtilisateur = tryIRIdentification(this.identiteUtilisateur);

		}

		return this.identiteUtilisateur;

	}

	private ExpressionArithmetique tryIRIdentification(ExpressionArithmetique candidate) {
		if (identiteRadd.equals(candidate)) {
			return identiteRfactoriseAdd;

		} else if (identiteRsous.equals(candidate)) {
			return identiteRfactoriseSous;

		} else if (identiteDiffCarreV1.equals(candidate) || identiteDiffCarreV2.equals(candidate)) {
			return identiteDiffCarreFactorise;

		} else {
			return candidate;
		}
	}

}
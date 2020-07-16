package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class StaticVariableReferenceTest {

	@Test
	public void detected() {
		
		JavaCheckVerifier.verify("src/test/files/StaticVariableReferenceCheck.java", new StaticVariableReferenceRule());
	}
}

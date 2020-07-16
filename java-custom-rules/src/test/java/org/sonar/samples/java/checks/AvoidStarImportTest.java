package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidStarImportTest {

	@Test
	public void detected() {
		
		JavaCheckVerifier.verify("src/test/files/AvoidStarImportCheck.java", new AvoidStarImportRule());
	}
}

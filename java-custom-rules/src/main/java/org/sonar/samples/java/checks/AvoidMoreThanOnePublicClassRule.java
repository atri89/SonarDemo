package org.sonar.samples.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;

@Rule(key = "AvoidMoreThanOnePublicClass", 
	  description = "", 
	  priority = Priority.MAJOR, 
	  tags = { "bug" })
public class AvoidMoreThanOnePublicClassRule  {
	
}



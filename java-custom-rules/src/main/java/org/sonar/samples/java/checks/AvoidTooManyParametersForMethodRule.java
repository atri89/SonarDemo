package org.sonar.samples.java.checks;

import java.util.List;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import com.google.common.collect.ImmutableList;

@Rule(key = "AvoidTooManyParametersForMethod", 
	  description = "Method sould not have morethan 5 parameters", 
	  priority = Priority.MAJOR, 
	  tags = {"bug"})
public class AvoidTooManyParametersForMethodRule extends IssuableSubscriptionVisitor{

	@Override
	public List<Kind> nodesToVisit() {
		return ImmutableList.of(Tree.Kind.METHOD);
	}
	
	public void visitNode(Tree tree) {
		MethodTree methodtree = (MethodTree)tree;
		MethodSymbol methodSymbol = methodtree.symbol();
		if(methodSymbol.parameterTypes().size()>5) {
			reportIssue(tree, "Avoid Too Many Parameters");
		}
	}

	
}

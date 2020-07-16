package org.sonar.samples.java.checks;

import java.util.List;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.ImportTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import com.google.common.collect.ImmutableList;

@Rule(key = "AvoidStarImport", 
	  description = "Explicitly import the specific classes needed.", 
	  priority = Priority.MAJOR, 
	  tags = { "bug" })
public class AvoidStarImportRule extends IssuableSubscriptionVisitor {
	@Override
	public List<Kind> nodesToVisit() {
		return ImmutableList.of(Tree.Kind.IMPORT);
	}

	@Override
	public void visitNode(Tree tree) {
		ImportTree importTree = (ImportTree) tree;

		if (fullQualifiedName(importTree.qualifiedIdentifier()).endsWith(".*") && !importTree.isStatic()) {
			reportIssue(importTree.qualifiedIdentifier(), "Explicitly import the specific classes needed.");
		}
	}

	private static String fullQualifiedName(Tree tree) {
		if (tree.is(Tree.Kind.IDENTIFIER)) {
			return ((IdentifierTree) tree).name();
		} else if (tree.is(Tree.Kind.MEMBER_SELECT)) {
			MemberSelectExpressionTree m = (MemberSelectExpressionTree) tree;
			return fullQualifiedName(m.expression()) + "." + m.identifier().name();
		}
		throw new UnsupportedOperationException(String.format("Kind/Class '%s' not supported", tree.getClass()));
	}
}

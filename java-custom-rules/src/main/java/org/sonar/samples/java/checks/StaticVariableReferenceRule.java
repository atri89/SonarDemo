package org.sonar.samples.java.checks;

import java.util.Collections;
import java.util.List;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.Tree;


@Rule(key = "StaticVariableReference", 
description = "static variable should be accessed by class name", 
priority = Priority.MAJOR, 
tags = { "bug" })
public class StaticVariableReferenceRule extends IssuableSubscriptionVisitor {

	@Override
	  public List<Tree.Kind> nodesToVisit() {
	    return Collections.singletonList(Tree.Kind.MEMBER_SELECT);
	  }

	  @Override
	  public void visitNode(Tree tree) {
	    MemberSelectExpressionTree memberSelect = (MemberSelectExpressionTree) tree;
	    if (memberSelect.identifier().symbol().isStatic()) {
	      ExpressionTree memberSelectExpression = memberSelect.expression();
	      if (memberSelectExpression.is(Tree.Kind.MEMBER_SELECT)) {
	        memberSelectExpression = ((MemberSelectExpressionTree) memberSelectExpression).identifier();
	      }
	      if (!memberSelectExpression.is(Tree.Kind.IDENTIFIER) || ((IdentifierTree) memberSelectExpression).symbol().isVariableSymbol()) {
	        context.reportIssue(this, memberSelect, "static variable should be accessed by class name");
	        //reportIssue(memberSelect, "static variable should be accessed by class name");
	      }
	    }
	  }

}

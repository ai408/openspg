package com.antgroup.openspg.reasoner.thinker.logic.rule.exact;

import com.antgroup.openspg.reasoner.thinker.logic.rule.Node;
import com.antgroup.openspg.reasoner.thinker.logic.rule.TreeLogger;
import com.antgroup.openspg.reasoner.thinker.logic.rule.visitor.RuleNodeVisitor;
import com.antgroup.openspg.reasoner.warehouse.common.VertexSubGraph;
import java.util.Map;

public abstract class Condition implements Node {
  @Override
  public <R> R accept(
      VertexSubGraph vertexGraph, Map<String, Object> context, RuleNodeVisitor<R> visitor) {
    return visitor.visit(this, vertexGraph, context);
  }

  public abstract boolean execute(
      VertexSubGraph vertexGraph, Map<String, Object> context, TreeLogger logger);

  public abstract String getExpress();
}
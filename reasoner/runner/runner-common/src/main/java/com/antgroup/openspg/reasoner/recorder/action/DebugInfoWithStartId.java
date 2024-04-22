/*
 * Copyright 2023 OpenSPG Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 */
package com.antgroup.openspg.reasoner.recorder.action;

import com.antgroup.openspg.reasoner.common.graph.vertex.IVertexId;
import com.antgroup.openspg.reasoner.lube.common.rule.Rule;
import com.antgroup.openspg.reasoner.warehouse.utils.WareHouseUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * @author peilong.zpl
 * @version $Id: DebugInfoWithStartId.java, v 0.1 2024-04-17 16:12 peilong.zpl Exp $$
 */
public class DebugInfoWithStartId {
  private IVertexId vertexId;
  private List<Rule> hitRules = new ArrayList<>();
  private List<Rule> failedRules = new ArrayList<>();

  public IVertexId getVertexId() {
    return vertexId;
  }

  public Map<String, Object> toJsonObj() {
    Map<String, Object> result = new HashMap<>();
    Map<String, Object> hitRuleInfo = new HashMap<>();
    for (Rule r : hitRules) {
      hitRuleInfo.put(r.getName(), StringUtils.join(WareHouseUtils.getRuleList(r), ","));
    }
    result.put("hit_rule", hitRuleInfo);
    Map<String, Object> failedRuleInfo = new HashMap<>();
    for (Rule r : failedRules) {
      failedRuleInfo.put(r.getName(), StringUtils.join(WareHouseUtils.getRuleList(r), ","));
    }
    result.put("failed_rule", failedRuleInfo);
    return result;
  }

  public void mergeDebugInfo(DebugInfoWithStartId other) {
    if (this.vertexId != other.vertexId) {
      return;
    }
    hitRules.addAll(other.getHitRules());
    hitRules.addAll(other.getFailedRules());
  }

  public void setVertexId(IVertexId vertexId) {
    this.vertexId = vertexId;
  }

  public List<Rule> getHitRules() {
    return hitRules;
  }

  public void setHitRules(List<Rule> hitRules) {
    this.hitRules = hitRules;
  }

  public List<Rule> getFailedRules() {
    return failedRules;
  }

  public void setFailedRules(List<Rule> failedRules) {
    this.failedRules = failedRules;
  }
}

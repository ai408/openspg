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
package com.antgroup.openspg.cloudext.interfaces.graphstore.cmd;

import com.antgroup.openspg.cloudext.interfaces.graphstore.model.lpg.record.VertexRecord;
import java.util.List;
import lombok.Data;

@Data
public class PageRankCompete {

  private List<VertexRecord> startVertices;
  private String targetVertexType;
  private Float dampingFactor = 0.85f;
  private Integer maxIterations = 20;

  public PageRankCompete(List<VertexRecord> startVertices, String targetVertexType) {
    this.startVertices = startVertices;
    this.targetVertexType = targetVertexType;
  }
}

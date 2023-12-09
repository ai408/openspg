package com.antgroup.openspg.builder.core.physical.process;

import com.antgroup.openspg.builder.core.runtime.BuilderContext;
import com.antgroup.openspg.builder.model.exception.BuilderException;
import com.antgroup.openspg.builder.model.exception.BuilderRecordException;
import com.antgroup.openspg.builder.model.pipeline.config.RelationMappingNodeConfig;
import com.antgroup.openspg.builder.model.record.BaseRecord;
import com.antgroup.openspg.builder.model.record.BuilderRecord;
import com.antgroup.openspg.builder.model.record.RelationRecord;
import com.antgroup.openspg.cloudext.interfaces.graphstore.adapter.record.impl.convertor.EdgeRecordConvertor;
import com.antgroup.openspg.common.util.StringUtils;
import com.antgroup.openspg.core.schema.model.identifier.RelationIdentifier;
import com.antgroup.openspg.core.schema.model.predicate.Relation;
import java.util.ArrayList;
import java.util.List;

public class RelationMappingProcessor extends BaseMappingProcessor<RelationMappingNodeConfig> {

  private Relation relation;

  public RelationMappingProcessor(String id, String name, RelationMappingNodeConfig config) {
    super(id, name, config);
  }

  @Override
  public void doInit(BuilderContext context) throws BuilderException {
    super.doInit(context);

    RelationIdentifier identifier = RelationIdentifier.parse(config.getRelation());
    this.relation = (Relation) loadSchema(identifier, context.getProjectSchema());
  }

  @Override
  public List<BaseRecord> process(List<BaseRecord> inputs) {
    List<BaseRecord> spgRecords = new ArrayList<>(inputs.size());
    for (BaseRecord baseRecord : inputs) {
      BuilderRecord record = (BuilderRecord) baseRecord;
      RelationRecord relationRecord = relationRecordMapping(record, relation, config);
      if (relationRecord != null) {
        spgRecords.add(relationRecord);
      }
    }
    return spgRecords;
  }

  public static RelationRecord relationRecordMapping(
      BuilderRecord record, Relation relation, RelationMappingNodeConfig mappingConfig) {
    if (isFiltered(record, mappingConfig.getMappingFilters())) {
      return null;
    }

    BuilderRecord mappedRecord = mapping(record, mappingConfig.getMappingConfigs());
    return toSPGRecord(mappedRecord, relation);
  }

  private static RelationRecord toSPGRecord(BuilderRecord record, Relation relation) {
    String srcId = record.getPropValue("srcId");
    String dstId = record.getPropValue("dstId");
    if (StringUtils.isBlank(srcId) || StringUtils.isBlank(dstId)) {
      throw new BuilderRecordException("");
    }
    return EdgeRecordConvertor.toRelationRecord(relation, srcId, dstId, record.getProps());
  }

  @Override
  public void close() throws Exception {}
}
import json
from abc import ABC
from typing import Union, List, Dict

from knext.client.model.base import BaseSpgType
from knext.client.schema import SchemaClient
from knext.common.schema_helper import SPGTypeHelper, PropertyHelper
from knext.operator.op import PromptOp
from knext.operator.spg_record import SPGRecord


class AutoPrompt(PromptOp, ABC):

    def build_prompt(self, record: Dict[str, str]) -> str:
        pass

    def parse_response(self, response: str) -> Union[List[Dict[str, str]], List[SPGRecord]]:
        pass

    def eval(self, *args):
        pass


class SPOPrompt(PromptOp):

    template = """
已知SPO关系包括:[{schema}]。从下列句子中提取定义的这些关系。最终抽取结果以json格式输出。
input:{input}
输出格式为:{"spo":[{"subject":,"predicate":,"object":},]}
"output":
    """

    def __init__(self, spg_type_name: Union[str, SPGTypeHelper], property_names: List[Union[str, PropertyHelper]]):
        super().__init__()
        schema_client = SchemaClient()
        spg_type = schema_client.query_spg_type(spg_type_name=spg_type_name)
        self.predicate_zh_to_en_name = {}
        self.predicate_type_zh_to_en_name = {}
        for k, v in spg_type.properties.items():
            self.predicate_zh_to_en_name[v.name_zh] = k
            self.predicate_type_zh_to_en_name[v.name_zh] = v.object_type_name
        self._render(spg_type, property_names)

    def build_prompt(self, record: Dict[str, str]) -> str:
        return self.template.format(input=record.get("input"))

    def parse_response(self, response: str) -> Union[List[Dict[str, str]], List[SPGRecord]]:
        result = []
        subject = {}
        re_obj = json.loads(response)
        if "spo" not in re_obj.keys():
            raise ValueError("SPO format error.")
        for spo_item in re_obj.get("spo", []):
            if spo_item["predicate"] not in self.predicate_zh_to_en_name:
                continue
            subject_properties = {}
            if spo_item["subject"] not in subject:
                subject[spo_item["subject"]] = subject_properties
            else:
                subject_properties = subject[spo_item["subject"]]

            # 获取属性类型
            spo_en_name = self.predicate_zh_to_en_name[spo_item["predicate"]]
            spo_type = self.predicate_type_zh_to_en_name[spo_item["predicate"]]

            if spo_en_name in subject_properties and len(
                    subject_properties[spo_en_name]
            ):
                subject_properties[spo_en_name] = (
                        subject_properties[spo_en_name] + "," + spo_item["object"]
                )
            else:
                subject_properties[spo_en_name] = spo_item["object"]

            for k, val in subject.items():
                subject_entity = SPGRecord(spg_type_name=spo_type, properties=val)
                result.append(subject_entity)
        return result

    def _render(self, spg_type: BaseSpgType, property_names: List[str]):
        spos = []
        for property_name in property_names:
            if property_name in ["id", "name", "description"]:
                continue
            prop = spg_type.properties.get(property_name)
            spos.append(f'{spg_type.name_zh}({spg_type.desc})-f{prop.name_zh}-f{prop.object_type_name_zh}')
        schema_text = ','.join(spos)
        self.template.format(schema=schema_text)
# -*- coding: utf-8 -*-
# Copyright 2023 Ant Group CO., Ltd.
#
# Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
# in compliance with the License. You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software distributed under the License
# is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
# or implied.

# ATTENTION!
# This file is generated by Schema automatically, it will be refreshed after schema has been committed
# PLEASE DO NOT MODIFY THIS FILE!!!
#

from knext.common.schema_helper import SPGTypeHelper, PropertyHelper


class Financial:
    
    class Indicator(SPGTypeHelper):
        description = PropertyHelper("description")
        id = PropertyHelper("id")
        name = PropertyHelper("name")
        alias = PropertyHelper("alias")
        stdId = PropertyHelper("stdId")
    
    class Organization(SPGTypeHelper):
        description = PropertyHelper("description")
        id = PropertyHelper("id")
        name = PropertyHelper("name")
    
    class RegionalEconomicIndicatorEvent(SPGTypeHelper):
        description = PropertyHelper("description")
        id = PropertyHelper("id")
        name = PropertyHelper("name")
        source = PropertyHelper("source")
        eventTime = PropertyHelper("eventTime")
        date = PropertyHelper("date")
        region = PropertyHelper("region")
        subject = PropertyHelper("subject")
    
    class State(SPGTypeHelper):
        description = PropertyHelper("description")
        id = PropertyHelper("id")
        name = PropertyHelper("name")
        causeOf = PropertyHelper("causeOf")
        alias = PropertyHelper("alias")
        stdId = PropertyHelper("stdId")
        derivedFrom = PropertyHelper("derivedFrom")
    
    Indicator = Indicator("Financial.Indicator")
    Organization = Organization("Financial.Organization")
    RegionalEconomicIndicatorEvent = RegionalEconomicIndicatorEvent("Financial.RegionalEconomicIndicatorEvent")
    State = State("Financial.State")
    
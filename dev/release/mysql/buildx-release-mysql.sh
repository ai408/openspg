# Copyright 2023 OpenSPG Authors
#
# Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
# in compliance with the License. You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software distributed under the License
# is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
# or implied.

docker buildx build -f Dockerfile --platform linux/arm64/v8,linux/amd64 --push \
  -t spg-registry.cn-hangzhou.cr.aliyuncs.com/spg/openspg-mysql:0.5.1 \
  -t spg-registry.cn-hangzhou.cr.aliyuncs.com/spg/openspg-mysql:latest \
  -t openspg/openspg-mysql:0.5.1 \
  -t openspg/openspg-mysql:latest \
  .

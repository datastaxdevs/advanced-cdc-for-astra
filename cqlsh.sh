#!/bin/bash
set -a
source /workspace/workshop-spring-reactive/.env
set +a
/workspace/workshop-spring-reactive/tools/cqlsh-astra/bin/cqlsh -u token -p ${ASTRA_DB_ADMIN_TOKEN} -b /home/gitpod/.astra/scb_${ASTRA_DB_ID}_${ASTRA_DB_REGION}.zip
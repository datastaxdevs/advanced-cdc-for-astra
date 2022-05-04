#!/bin/bash
set -a
source /workspace/advanced-cdc-for-astra/.env
set +a
/workspace/advanced-cdc-for-astra/tools/cqlsh-astra/bin/cqlsh -u token -p ${ASTRA_DB_ADMIN_TOKEN} -b /home/gitpod/.astra/scb_${ASTRA_DB_ID}_${ASTRA_DB_REGION}.zip
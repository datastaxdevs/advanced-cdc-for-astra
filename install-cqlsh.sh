#!/bin/bash

GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m'

clear
echo -e "${BLUE}------------------------------------------------------------${NC}"
echo -e "${BLUE}--            Installation of Cqlsh                      ---${NC}"
echo -e "${BLUE}------------------------------------------------------------${NC}"
echo -e " "
mkdir /workspace/advanced-cdc-for-astra/tools 2>/dev/null
echo -e "${GREEN}[OK]${NC} - Tools folder has been created"
cd /workspace/advanced-cdc-for-astra/tools
wget -q https://downloads.datastax.com/enterprise/cqlsh-astra.tar.gz >> install-cqlsh.log
tar xvzf cqlsh-astra.tar.gz  >> install-cqlsh.log
rm -f cqlsh-astra.tar.gz >> install-cqlsh.log
echo -e "${GREEN}[OK]${NC} - Package has been downloaded"
cd /workspace/advanced-cdc-for-astra >> install-cqlsh.log

cd /workspace/advanced-cdc-for-astra
npm install --silent astra-setup
npm exec -y astra-setup camp-constellation crud_data
set -a
source /workspace/advanced-cdc-for-astra/.env
set +a
echo -e "${GREEN}[OK]${NC} - Database ID is ${BLUE}${ASTRA_DB_ID}${NC}"
echo -e "${GREEN}[OK]${NC} - Database REGION is ${BLUE}${ASTRA_DB_REGION}${NC}"
echo -e "${GREEN}[OK]${NC} - Database TOKEN is ${BLUE}${ASTRA_DB_ADMIN_TOKEN}${NC}"
cd /workspace/advanced-cdc-for-astra/java
mvn test -Dcom.datastax.workshop.petclinic.Test01_Connectivity >> install.log
cd /workspace/advanced-cdc-for-astra
echo -e "${GREEN}[OK]${NC} - Secure Connect Bundle downloaded"
echo -e "${GREEN}[OK]${NC} - Launching CQLSH...."
/workspace/advanced-cdc-for-astra/cqlsh.sh
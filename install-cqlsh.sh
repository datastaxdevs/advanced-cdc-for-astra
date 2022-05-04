#!/bin/bash

GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m'

clear
echo -e "${BLUE}------------------------------------------------------------${NC}"
echo -e "${BLUE}--            Installation of Cqlsh                      ---${NC}"
echo -e "${BLUE}------------------------------------------------------------${NC}"
echo -e " "
mkdir /workspace/workshop-spring-reactive/tools 2>/dev/null
echo -e "${GREEN}[OK]${NC} - Tools folder has been created"
cd /workspace/workshop-spring-reactive/tools
wget -q https://downloads.datastax.com/enterprise/cqlsh-astra.tar.gz >> install-cqlsh.log
tar xvzf cqlsh-astra.tar.gz  >> install-cqlsh.log
rm -f cqlsh-astra.tar.gz >> install-cqlsh.log
echo -e "${GREEN}[OK]${NC} - Package has been downloaded"
cd /workspace/workshop-spring-reactive >> install-cqlsh.log
echo -e "${GREEN}[OK]${NC} - We will now as you about your ASTRA TOKEN (AstraCS....)"

cd /workspace/workshop-spring-reactive
npm install --silent astra-setup
npm exec -y astra-setup workshops spring_petclinic
set -a
source /workspace/workshop-spring-reactive/.env
set +a
echo -e "${GREEN}[OK]${NC} - Database ID is ${BLUE}${ASTRA_DB_ID}${NC}"
echo -e "${GREEN}[OK]${NC} - Database REGION is ${BLUE}${ASTRA_DB_REGION}${NC}"
echo -e "${GREEN}[OK]${NC} - Database TOKEN is ${BLUE}${ASTRA_DB_ADMIN_TOKEN}${NC}"
mvn test -Dtest=com.datastax.workshop.petclinic.Test01_Connectivity >> instal.log
echo -e "${GREEN}[OK]${NC} - Secure Connect Bundle downloaded"
echo -e "${GREEN}[OK]${NC} - Launching CQLSH...."
./cqlsh
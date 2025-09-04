#!/bin/bash
./mvnw clean package
cp ~/keycloak-custom-auth/target/keycloak-custom-auth-0.0.1-SNAPSHOT.jar ~/keycloak-26.1.4/providers/
cd ~/keycloak-26.1.4
bin/kc.sh build
bin/kc.sh start-dev
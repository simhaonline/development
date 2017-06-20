#!/bin/sh
#move image name to jenkins job variable
docker login --username=tech-oscm --email=tech-oscm@est.fujitsu.com artifactory.intern.est.fujitsu.com:5003 && docker pull artifactory.intern.est.fujitsu.com:5003/ctmg:$1
docker stop default || true
docker rm default || true
docker run --add-host=oscm.org:127.0.0.1 --name=default -h default -d -i -p 8080:8080 -p 8081:8081 -p 8048:8048 -p 8037:8037 artifactory.intern.est.fujitsu.com:5003/ctmg:$1
sleep 180
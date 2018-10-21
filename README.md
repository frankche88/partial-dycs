# process-order-message
#ejecutar en  local

./gradlew build shadowJar

export JDBC_URL=jdbc:mysql://us-cdbr-iron-east-01.cleardb.net/ad_c2f6e2b9d66a1a0?user=ba78368ad1fc13&password=deee0775
export JDBC_DRIVER=com.mysql.jdbc.Driver

export DB_USERNAME=ba78368ad1fc13
export DB_PASSWORD=deee0775

export AMQ_URL=amqp://bzizimsp:LKVNczGhhTNTsk-CxeLgdE7MAFt_8Nbq@chimpanzee.rmq.cloudamqp.com/bzizimsp
export AMQ_QUEUE=order.procesed

java -jar build/libs/service.jar migrate src/main/resources/config.yaml

java -jar build/libs/service.jar server src/main/resources/config.yaml 
 
# subir a pivotal
cf login -a https://api.run.pivotal.io

cf push --no-start

cf set-env partial-dycs JDBC_URL jdbc:mysql://us-cdbr-iron-east-01.cleardb.net/ad_c2f6e2b9d66a1a0?user=ba78368ad1fc13&password=deee0775
cf set-env partial-dycs JDBC_DRIVER com.mysql.jdbc.Driver

cf set-env partial-dycs DB_USERNAME ba78368ad1fc13
cf set-env partial-dycs DB_PASSWORD deee0775

cf set-env partial-dycs AMQ_URL amqp://bzizimsp:LKVNczGhhTNTsk-CxeLgdE7MAFt_8Nbq@chimpanzee.rmq.cloudamqp.com/bzizimsp
cf set-env partial-dycs AMQ_QUEUE order.procesed


###Variable optenida de pivotal

cf start partial-dycs


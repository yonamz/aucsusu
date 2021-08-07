#!/bin/bash

REPOSITORY=~/spring
PROJECT_NAME=aucsusu

cd $REPOSITORY/$PROJECT_NAME

echo ">git pull"

git pull

echo "> Build 시작"

./gradlew build

cd $REPOSITORY

echo ">build 복사"

cp $REPOSITORY/$PROJECT_NAME/build/libs/aucsusu-1.0-SNAPSHOT.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"


CURRENT_PID=$(pgrep -f *.jar)

echo "현재 구동중인 어플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/ | grep .jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"


nohup java -jar \
    -Dspring.config.location=classpath:/application.properties \
    $REPOSITORY/$PROJECT_NAME/build/libs/$JAR_NAME > $REPOSITORY/$PROJECT_NAME/nohup.out 2>&1 &



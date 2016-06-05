#!/usr/bin/env bash
SERVICE_NAME=ZOOKEEPER
PID_PATH_NAME=/tmp/Zookeeper-pid
case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            nohup /var/uber/kafka/bin/zookeeper-server-start.sh config/zookeeper.properties 2>&1 &
                     echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            nohup nohup /var/uber/kafka/bin/kafka-server-start.sh /var/uber/kafka/config/server.properties > /var/uber/kafka/kafka.log 2>&1 &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac

nohup /var/uber/kafka/bin/kafka-server-start.sh /var/uber/kafka/config/server.properties > /var/uber/kafka/kafka.log 2>&1 &


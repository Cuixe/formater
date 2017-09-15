#!/bin/bash

CLASS_PATH='./files'
CLASS_PATH=$CLASS_PATH:formater-1.0-SNAPSHOT-all.jar

java -cp $CLASS_PATH org.cuixe.formater.Main > ./resultado.txt

RETVAL=$?

exit $RETVAL

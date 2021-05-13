# multi-stage Dockerfile

###
### builder: used to build the jars from the java source code
FROM openjdk:8 AS builder

# copy java src code to $APP_HOME
ARG APP_HOME=/tmp/app
COPY src $APP_HOME/src

#copy prowidecore swift libraries and dependencies to $APP_HOME/jgw
COPY lib/pw-swift-core-SRU2020-9.1.4.jar $APP_HOME/jgw/
COPY lib/gson-2.8.5.jar $APP_HOME/jgw/

# copy iris pex libraries to $APP_HOME/jgw
COPY --from=intersystemsdc/jgw:latest /jgw/*.jar $APP_HOME/jgw/

# create $APP_HOME/jar
WORKDIR $APP_HOME/jar/

# compile java source code and create a jar file in $APP_HOME/jar
# you could also use maven, gradle, ant
WORKDIR $APP_HOME/src/
RUN javac -classpath $APP_HOME/jar/*:$APP_HOME/jgw/* swift/pex/*.java && \
    jar -cvf $APP_HOME/jar/FirstDemo.jar swift/pex/*.class

###
### jgw: java gateway server
FROM intersystemsdc/jgw:latest

# copy jars built in builder image into java gateway server 
COPY --from=builder /tmp/app/jar/*.jar $GWDIR/

# copy prowidecore jars into java gateway server
COPY lib/pw-swift-core-SRU2020-9.1.4.jar $GWDIR/
COPY lib/gson-2.8.5.jar $GWDIR/
COPY lib/commons-lang3-3.8.1.jar $GWDIR/
COPY lib/commons-text-1.6.jar $GWDIR/

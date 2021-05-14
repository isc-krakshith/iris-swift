# IRIS SWIFT example

# What do you need to install? 
* [Git](https://git-scm.com/downloads) 
* [Docker](https://www.docker.com/products/docker-desktop) (if you are using Windows, make sure you set your Docker installation to use "Linux containers").
* [Docker Compose](https://docs.docker.com/compose/install/)
* [Visual Studio Code](https://code.visualstudio.com/download) + [InterSystems ObjectScript VSCode Extension](https://marketplace.visualstudio.com/items?itemName=daimor.vscode-objectscript)

# Setup
Build the image:

```console
$ git clone https://github.com/isc-krakshith/iris-swift
$ cd iris-swift
$ docker-compose build
```
# Examples

## (a). FirstDemo interoperability Production
* Run the containers:
```
docker-compose up -d
```

* Open the [Management Portal](http://localhost:52773/csp/sys/UtilHome.csp).
* Login using the default `superuser`/ `SYS` account.
* Click on [Swift.PEX.FirstDemo.Production](http://localhost:52773/csp/user/EnsPortal.ProductionConfig.zen?PRODUCTION=Swift.PEX.FirstDemo.Production&$NAMESPACE=USER) to access the sample interoperability production we will use. You can access also through *Interoperability > User > Configure > Production*.

## (b). Enable Business Service and Business Process
* Check message log to see what happens.
In VS Code, open [java/src/swift/pex/FirstService.java](java/src/swift/pex/FirstService.java) and [java/src/swift/pex/FirstProcess.java](java/src/swift/pex/FirstProcess.java) 

## (c). Making changes
* Make some changes on [FirstService.java](java/src/swift/pex/FirstService.java), [FirstProcess.java](java/src/swift/pex/FirstProcess.java) or [FirstOperation.java](java/src/swift/pex/FirstOperation.java) (e.g. add some extra `LOGINFO`).
* Stop the containers: `docker-compose down`
* Re-compile: `docker-compose build`
* Run: `docker-compose up -d`
* Test your changes.


# Smallworld5 Magik wrapper for Sentry.io

## Development

Maven is used for project management. Although Smallworld uses Ant itself for its demos, Maven works fine as well.

Build using:

```
$ mvn clean bundle:bundle
```

### Dependencies

To build this, you need to manually install the Smallworld5 Commons and Interop jars in maven. These jars are found in your Smallworld installation.

You can install the Commons and Interop jars by running this command:

```
$ echo ${SMALLWORLD_GIS}                 # This points to your Smallworld/core installation, for example: /opt/Smallworld/core
$ export SMALLWORLD_VERSION=5.2.8.0-261  # Set Smallworld version.
$ mvn install:install-file -Dfile=${SMALLWORLD_GIS}/libs/com.gesmallworld.magik.interop-${SMALLWORLD_VERSION}.jar -DgroupId=com.gesmallworld -DartifactId=magik.interop -Dversion=${SMALLWORLD_VERSION} -Dpackaging=jar
$ mvn install:install-file -Dfile=${SMALLWORLD_GIS}/libs/com.gesmallworld.magik.commons-${SMALLWORLD_VERSION}.jar -DgroupId=com.gesmallworld -DartifactId=magik.commons -Dversion=${SMALLWORLD_VERSION} -Dpackaging=jar
```

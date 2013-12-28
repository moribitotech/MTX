This maven project structure was generated using official libgdx maven archetype - https://github.com/libgdx/libgdx-maven-archetype

## Running your project

### Build and run the Desktop backend

You can build and run your game using the Desktop backend like this:

```
% mvn integration-test -Pdesktop
```

This is pretty simple. It builds the Java code, and then unpacks some native libraries into the
right place and then runs the code.

You can also build a single jar file version of your game that you can send to friends or do
whatever you like with:

```
% mvn package -Pdesktop
% java -jar desktop/target/mtx-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### Build and run the Android backend

You can build your game using the Android backend and install it to a device like this:

```
% mvn install -Pandroid
```

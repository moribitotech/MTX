MTX (MoribitoTechX)
===

- Compact-MTX framework over LibGDX Scene2D 
- Tutorial Blog: http://moribitotechx.blogspot.co.uk/
- Twitter: https://twitter.com/MoribitoTech
- Exclusive Facebook Group: https://www.facebook.com/groups/MoribitoTechAndroid/

Info:
---------
- It is a Open Source Project Under Apache Licence, Version 2.0
- %100 Free and Awsome 
- Designed for Apps, Games & Live Wallpapers (Extremely easy and fast)
- Designed for far less code writing
- Built over LibGDX Scene2D
- Cross device resolution (Same quality in every device wiht Mtx v2.0 version, no more stretching)
- Designed for easier Scene / Stage / Group / Actor integration
- Designed for easier Menu Creations
- Designed for easier Animation Integration
- Designed for easier Settings Management
- Ready effects and smart models
- Designed for game specific purposes. For example, creating level buttons, setting special animations, getting screen time, integrating momentarily animations, multi-textured buttons, advanced actors, special setting management (volume, vibration, sfx and others.).
- Also usable for game logics, but it is heavily depends on Scene2D, it means that it is not %100 MVC supportive. However for games like puzzles, brain teasers, low-level platformers, low-level defence games, other low-level causal games, it may be suitable. Again, I warn you, it is not %100 MVC supportive.

What you need to know:
---------
- Basic knowledge and experience with Java, Android SDK, Eclipse and LibGDX
- Understanding the fundamentals of LibGDX Scene2D
- TexturePacking and TextureAtlas management (Not essential)

How can it be used:
---------
- First of all it is pure LibGDX, so you can develop your game in your way (2D / 3D) with LibGDX and add MTX over it without any problems
- Main menu and game menus with animations, easy to use buttons, no stretching problems (only Mtx v2.0)
- Game background environments, bubbles, snows, parallax effects, clouds, animated object anything about game environment can be done in seconds
- Live wallpapers (I made a small fortune with them), Top-notch, very detailed and high quality live wallpapers can be done in days
- Simple touch, touch drag, swipe intentions and collision detections for your needs
- Games, you can develop a full game without any problems, if you like abilities of LibGDX Scene2D


## Installation

Project structure was generated using official libgdx maven archetype - https://github.com/libgdx/libgdx-maven-archetype
In order to build it, please follow the following steps:
- Download latest version of Apache Maven tool from http://maven.apache.org/download.cgi and unpack it to <MAVEN_DIR> folder on your PC.
- Create M2_HOME environment variable and set it to <MAVEN_DIR>
- Update PATH environment variable and append <M2_HOME>/bin folder to it. For Windows users it is sometimes required to restart cmd after setting your environment variables.
- Verify that you install Maven correctly by typing "mvn -version" in your shell. As a result you should see installed maven version.

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

## Run MTX samples

There are 3 samples which come together with MTX framework:
- Core samples (tutorials 1-3 from http://moribitotechx.blogspot.co.uk/p/tutorial-series-libgdx-mtx.html)
- Game World (tutorial 5)
- Jungle Menu (tutorial 7)

### Desktop
In order to run MTX samples you need to change "mainClass" property inside desktop/pom.xml 
- com.moribitotech.samples.core.Main (Core samples)
- com.moribitotech.samples.gameworld.Main (Game World)
- com.moribitotech.samples.jungle.Main (Jungle Menu)

### Android
In order to run MTX samples you need to change android:name property for main activity inside AndroidManifest.xml:
- com.moribitotech.samples.core.MainActivity (Core samples)
- com.moribitotech.samples.gameworld.MainActivity (Game World)
- com.moribitotech.samples.jungle.MainActivity (Jungle Menu)
Also please check the the package name is equal to "com.moribitotech.mtx.samples".


### Import project into IDE.

All modern IDEs has build-in Maven plugin, so in order to create project you need to select "Import project" option in your IDE and select root pom.xml file. IDE should automatically set up all required information.
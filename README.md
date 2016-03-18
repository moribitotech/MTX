Thank moribitotech for useful source code about libgdx platform.

I am very interesting about libgdx. I found out this source code in github, but it was out-of-date.
I want to reuse this source code and maintain to support newest version of libgdx.
The heart of platform stay in core project. That's why I only keep core and desktop project.

Installation

  - git clone https://github.com/luyentm/MTX.git
  - import to eclipse by gradle
  - compile and run

Desktop

In order to run MTX samples you need to change "mainClass" property inside desktop/pom.xml

    com.moribitotech.samples.core.Main (Core samples)
    com.moribitotech.samples.gameworld.Main (Game World)
    com.moribitotech.samples.jungle.Main (Jungle Menu)

Mail: tranminhluyen.1993@gmail.com

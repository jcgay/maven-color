#Maven Color

A multi-OS colorized Maven console.

[![asciicast](https://asciinema.org/a/20053.png)](https://asciinema.org/a/20053?autoplay=1)

#What's new ?

See [CHANGELOG](https://github.com/jcgay/maven-color/blob/master/CHANGELOG.md) for latest changes.

#Notifications ?

If you would like to be notified when a `mvn` build ends, you should give [maven-notifier](https://github.com/jcgay/maven-notifier) a try!

#Documentation

Visit the [wiki](https://github.com/jcgay/maven-color/wiki).  
You'll find known problem solutions and how to customize colors.

#Installation

`$M2_HOME` refers to maven installation folder.

```
maven
├── bin
├── boot
├── conf
└── lib
``` 

## OS X ?

You can install a pre-packaged maven named [maven-deluxe](https://github.com/jcgay/homebrew-jcgay#maven-deluxe) using `brew`.  
It comes with [maven-color](https://github.com/jcgay/maven-color), [maven-notifier](https://github.com/jcgay/maven-notifier) and [maven-profiler](https://github.com/jcgay/maven-profiler).  
It is based on latest maven release.

    brew tap jcgay/jcgay
    brew install maven-deluxe

## Maven >= 3.1.x

Maven relies on [SLF4J](http://www.slf4j.org/) internally, meaning you can choose what logger implementation you want to use. By default it comes with `slf4j-simple`.

Read [United colors of Maven](http://aheritier.net/united-colors-of-maven/) to learn more about it !

You can have colored console using `Logback` or `Log4j 2`.

### Logback

Get maven-color-logback-bundle: [tar.gz](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-logback/1.2/maven-color-logback-1.2-bundle.tar.gz) or [zip](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-logback/1.2/maven-color-logback-1.2-bundle.zip).  

Extract it in your `$M2_HOME` folder. It contains logback dependencies and logging configuration.  

    tar xvfz maven-color-logback-1.2-bundle.tar.gz -C $M2_HOME

Delete `$M2_HOME/lib/slf4j-simple-1.7.x.jar`.

    rm $M2_HOME/lib/slf4j-simple-1.7.*.jar

### Log4j 2

Get maven-color-log4j2-bundle: [tar.gz](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-log4j2/1.2/maven-color-log4j2-1.2-bundle.tar.gz) or [zip](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-log4j2/1.2/maven-color-log4j2-1.2-bundle.zip).  

Extract it in your `$M2_HOME` folder. It contains logback dependencies and logging configuration.  

    tar xvfz maven-color-log4j2-1.2-bundle.tar.gz -C $M2_HOME

Delete `$M2_HOME/lib/slf4j-simple-1.7.x.jar`.

    rm $M2_HOME/lib/slf4j-simple-1.7.*.jar

## Update from previous installation

Cleanup your previous installation before following the guides.  
In `$M2_HOME/lib/ext`, delete `maven-color*`, `logback*`, `log4j*`, `jansi*`, `slf4j*`, `groovy*` and `cal10n*` files. (May vary between `Logback` or `Log4j 2` installation)

## Maven 3.0.x

Use [maven-color 1.1](https://github.com/jcgay/maven-color/tree/v1.1#maven-30x) which is the last release compatible with Maven 3.0.x.

# Build status
[![Build Status](https://travis-ci.org/jcgay/maven-color.svg?branch=master)](https://travis-ci.org/jcgay/maven-color)
[![Build status](https://ci.appveyor.com/api/projects/status/y8rn0pew98jbr9j8/branch/master?svg=true)](https://ci.appveyor.com/project/jcgay/maven-color/branch/master)
[![Coverage Status](https://coveralls.io/repos/jcgay/maven-color/badge.svg?branch=master)](https://coveralls.io/r/jcgay/maven-color?branch=master)

# Release

    mvn -B release:prepare release:perform

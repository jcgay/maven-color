#Maven Color

A multi-OS colorized Maven console.

[![asciicast](https://asciinema.org/a/20053.png)](https://asciinema.org/a/20053?autoplay=1)

#What's new ?

See [CHANGELOG](https://github.com/jcgay/maven-color/blob/master/CHANGELOG.md) for latest changes.

#Notifications ?

If you would like to be notified when a `mvn` build ends, you should give a try to [maven-notifier](https://github.com/jcgay/maven-notifier) !

#Installation

`$M2_HOME` refers to maven installation folder.

```
.
├── bin
├── boot
├── conf
└── lib
``` 

## Maven >= 3.1.x

Maven now relies on [SLF4J](http://www.slf4j.org/) internally, meaning you can choose what logger implementation you want to use. By default it comes with `slf4j-simple`.

Read [United colors of Maven](http://aheritier.net/united-colors-of-maven/) to learn more about it !

Surefire colorization is not implemented, the plugin doesn't use SLF4J.

### Logback

Get maven-color-logback-bundle: [tar.gz](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-logback/1.1/maven-color-logback-1.1-bundle.tar.gz) or [zip](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-logback/1.1/maven-color-logback-1.1-bundle.zip).  

Extract it in your `$M2_HOME` folder. It contains logback dependencies and logging configuration.  

    tar xvfz maven-color-logback-1.1-bundle.tar.gz -C $M2_HOME

Delete `$M2_HOME/lib/slf4j-simple-1.7.x.jar`.

    rm $M2_HOME/lib/slf4j-simple-1.7.*.jar

### Log4j 2

Get maven-color-log4j2-bundle: [tar.gz](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-log4j2/1.1/maven-color-log4j2-1.1-bundle.tar.gz) or [zip](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-log4j2/1.1/maven-color-log4j2-1.1-bundle.zip).  

Extract it in your `$M2_HOME` folder. It contains logback dependencies and logging configuration.  

    tar xvfz maven-color-log4j2-1.1-bundle.tar.gz -C $M2_HOME

Delete `$M2_HOME/lib/slf4j-simple-1.7.x.jar`.

    rm $M2_HOME/lib/slf4j-simple-1.7.*.jar

## Update from previous installation

Cleanup your previous installation before following the guides.  
In `$M2_HOME/lib/ext`, delete `maven-color*`, `logback*`, `log4j*`, `jansi*`, `slf4j*` and `cal10n*` files. (May vary between `Logback` or `Log4j 2` installation)

## Maven 3.0.x

### Installation

Get [maven-color-agent](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-agent/1.1/maven-color-agent-1.1.jar). Copy it wherever you want (`$HOME/.m2` for example).  
Append `-javaagent:/Users/jcgay/.m2/maven-color-agent-1.1.jar` to your `$MAVEN_OPTS` environment variable where `/Users/jcgay/` is your `$HOME` value.  
Get [maven-color-logger](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-logger/1.1/maven-color-logger-1.1.jar) and copy it in `$M2_HOME/lib/ext` folder.

### How it works
This is a hacky java agent that replaces some logger implementation used internally by Maven and Surefire plugin.  
It has been tested with Maven 3.0.4/5 and maven-surefire-plugin 2.6/2.9/2.14.  

It uses [Jansi](https://github.com/fusesource/jansi) under the hood, which makes this monster working on Windows :)

Colorization can be turned off by setting environment variable `$MAVEN_COLOR` to `false`: 
`export MAVEN_COLOR=false`

#Known problems

Visit [wiki](https://github.com/jcgay/maven-color/wiki/Problems) to solve known problems.

# Build status
[![Build Status](https://travis-ci.org/jcgay/maven-color.svg?branch=master)](https://travis-ci.org/jcgay/maven-color)
[![Coverage Status](https://coveralls.io/repos/jcgay/maven-color/badge.svg?branch=master)](https://coveralls.io/r/jcgay/maven-color?branch=master)

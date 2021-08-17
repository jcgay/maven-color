# Maven Color

A multi-OS colorized Maven console.  
Maven has built-in color since version 3.5.0, you should stick with it now 😍

[![asciicast](https://asciinema.org/a/51007.png)](https://asciinema.org/a/51007?autoplay=1)

# What's new ?

See [CHANGELOG](https://github.com/jcgay/maven-color/blob/master/CHANGELOG.md) for latest changes.

# Installation

Follow instructions [here](https://github.com/jcgay/maven-color/wiki/Installation)

# Documentation

Visit the [wiki](https://github.com/jcgay/maven-color/wiki).  
You'll find known problem solutions and how to customize colors.

# Build

## Status

[![Build Status](https://github.com/jcgay/maven-color/actions/workflows/maven.yml/badge.svg)](https://github.com/jcgay/maven-color/actions/workflows/maven.yml)
[![Build status](https://ci.appveyor.com/api/projects/status/y8rn0pew98jbr9j8/branch/master?svg=true)](https://ci.appveyor.com/project/jcgay/maven-color/branch/master)
[![Coverage Status](https://coveralls.io/repos/jcgay/maven-color/badge.svg?branch=master)](https://coveralls.io/r/jcgay/maven-color?branch=master)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.github.jcgay.maven.color%3Amaven-color&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.github.jcgay.maven.color%3Amaven-color)

## Release

    mvn -B release:prepare release:perform

## Testing with Docker

### Latest Maven version

    docker build . -t jcgay/maven-color
    docker run -it --rm -v /Users/jcgay/.m2:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven jcgay/maven-color mvn clean verify
    docker run -e MAVEN_OPTS="$MAVEN_OPTS -Dmaven.color=false" -it --rm -v /Users/jcgay/.m2:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven jcgay/maven-color mvn clean verify

### Maven 3.3.x

    docker build . -f Dockerfile-3.3 -t jcgay/maven-color:3.3
    docker run -it --rm -v /Users/jcgay/.m2:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven jcgay/maven-color:3.3 mvn clean verify
    docker run -e MAVEN_OPTS="$MAVEN_OPTS -Dmaven.color=false" -it --rm -v /Users/jcgay/.m2:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven jcgay/maven-color:3.3 mvn clean verify
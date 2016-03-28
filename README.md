#Maven Color

A multi-OS colorized Maven console.

[![asciicast](https://asciinema.org/a/20053.png)](https://asciinema.org/a/20053?autoplay=1)

#What's new ?

See [CHANGELOG](https://github.com/jcgay/maven-color/blob/master/CHANGELOG.md) for latest changes.

#Installation

Follow instructions [here](https://github.com/jcgay/maven-color/wiki/Installation)

#Documentation

Visit the [wiki](https://github.com/jcgay/maven-color/wiki).  
You'll find known problem solutions and how to customize colors.

# Build

## Status

[![Build Status](https://travis-ci.org/jcgay/maven-color.svg?branch=master)](https://travis-ci.org/jcgay/maven-color)
[![Build status](https://ci.appveyor.com/api/projects/status/y8rn0pew98jbr9j8/branch/master?svg=true)](https://ci.appveyor.com/project/jcgay/maven-color/branch/master)
[![Coverage Status](https://coveralls.io/repos/jcgay/maven-color/badge.svg?branch=master)](https://coveralls.io/r/jcgay/maven-color?branch=master)

## Release

    mvn -B release:prepare release:perform

## Test with Docker

### Log4j2

    docker build -f Dockerfile-log4j2 -t jcgay/maven-color-log4j2 .
    docker run -it --rm -v /Users/jcgay/.m2:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven jcgay/maven-color-log4j2 mvn clean verify
    docker run -e MAVEN_OPTS="$MAVEN_OPTS -Dmaven.color=false" -it --rm -v /Users/jcgay/.m2:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven jcgay/maven-color-log4j2 mvn clean verify

### Logback
    
    docker build -f Dockerfile-logback -t jcgay/maven-color-logback .
    docker run -it --rm -v /Users/jcgay/.m2:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven jcgay/maven-color-logback mvn clean verify
    docker run -e MAVEN_OPTS="$MAVEN_OPTS -Dmaven.color=false" -it --rm -v /Users/jcgay/.m2:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven jcgay/maven-color-logback mvn clean verify
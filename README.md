#Maven Color

A colorized Maven console.

[![maven-color](http://jeanchristophegay.com/wp-content/uploads/2013/10/maven-color-resize.png)](http://jeanchristophegay.com/wp-content/uploads/2013/10/maven-color.png)

#What's new ?

See [CHANGELOG](https://github.com/jcgay/maven-color/blob/master/CHANGELOG.md) for latest changes.

#Installation

## Maven 3.1.x

Maven now relies on [SLF4J](http://www.slf4j.org/) internally, meaning you can choose what logger implementation you want to use. By default it comes with `slf4j-simple`.

Read [United colors of Maven](http://aheritier.net/united-colors-of-maven/) to learn more about it !

Surefire colorization is not implemented, the plugin doesn't use SLF4J.

### Logback

Get maven-color-logback-bundle: [tar.gz](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-logback/0.5/maven-color-logback-0.5-bundle.tar.gz) or [zip](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-logback/0.5/maven-color-logback-0.5-bundle.zip).  
Extract it in your `$M2_HOME` folder. It contains logback dependencies and logging configuration.  
Delete `$M2_HOME/lib/slf4j-simple-1.7.x.jar`.

### Log4j 2

Get maven-color-log4j2-bundle: [tar.gz](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-log4j2/0.5/maven-color-log4j2-0.5-bundle.tar.gz) or [zip](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-log4j2/0.5/maven-color-log4j2-0.5-bundle.zip).  
Extract it in your `$M2_HOME` folder. It contains log4j 2 dependencies and logging configuration.  
Delete `$M2_HOME/lib/slf4j-simple-1.7.x.jar`.

## Update from previous installation

Cleanup your previous installation before following the guides.  
In `$M2_HOME/lib/ext`, delete `maven-color*`, `logback*`, `log4j*`, `jansi*`, `slf4j*` and `cal10n*` files. (May vary between `Logback` or `Log4j 2` installation)

## Maven 3.0.x

### Installation

Get [maven-color-agent](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-agent/0.5/maven-color-agent-0.5.jar). Copy it wherever you want (`$HOME/.m2` for example).  
Append `-javaagent:/Users/jcgay/.m2/maven-color-agent-0.5.jar` to your `$MAVEN_OPTS` environment variable where `/Users/jcgay/` is your `$HOME` value.  
Get [maven-color-logger](http://dl.bintray.com/jcgay/maven/com/github/jcgay/maven/color/maven-color-logger/0.5/maven-color-logger-0.5.jar) and copy it in `$M2_HOME/lib/ext` folder.

### How it works
This is a hacky java agent that replaces some logger implementation used internally by Maven and Surefire plugin.  
It has been tested with Maven 3.0.4/5 and maven-surefire-plugin 2.6/2.9/2.14.  

It uses [Jansi](http://jansi.fusesource.org/) under the hood, which makes this monster working on Windows :)

Colorization can be turned off by setting environment variable `$MAVEN_COLOR` to `false`: 
`export MAVEN_COLOR=false`

### Known issues
I'm using ASM 4.x to manipulate bytecode, if your build depends on a maven plugin which is using a different version of ASM, it will poorly fail…
For example, you can't run `mvn sonar:sonar` with `maven-color` configured.  
In that case, deactivate the Java agent in `$MAVEN_OPTS` environment variable to not instrument the code.

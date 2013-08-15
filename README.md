#Maven Color

A colorized Maven console.  
Error messages are displayed in red, Warning in yellow.  
Tests results are also in color, failures/errors in red, skipped in yellow.

## Maven 3.0.x

### Installation

Get [maven-color-agent](https://repository-jcgay.forge.cloudbees.com/release/com/github/jcgay/maven/color/maven-color-agent/0.1/maven-color-agent-0.1.jar). Copy it wherever you want (`$HOME/.m2` for example).  
Append `-javaagent:$HOME/.m2/maven-color-agent-[version].jar` to your `$MAVEN_OPTS` environment variable.  
Get [maven-color-logger](https://repository-jcgay.forge.cloudbees.com/release/com/github/jcgay/maven/color/maven-color-logger/0.1/maven-color-logger-0.1.jar) and copy it in `%M2_HOME%/lib/ext` folder.

### How it works
This is a hacky java agent that replaces some logger implementation used internally by Maven and Surefire plugin.  
It has been tested with Maven 3.0.4/5 and maven-surefire-plugin 2.6/2.9/2.14.

## Maven 3.1.x

Maven now use SLF4J internally, meaning you can choose what logger implementation you want to use. By default it comes with `slf4j-simple`.

Surefire colorization is not implemented, the plugin doesn't use SLF4J yet.

### Logback

Get maven-color-logback-bundle: [tar.gz](https://repository-jcgay.forge.cloudbees.com/snapshot/com/github/jcgay/maven/color/maven-color-logback/0.2-SNAPSHOT/maven-color-logback-0.2-20130814.191948-2-bundle.tar.gz) or [zip](https://repository-jcgay.forge.cloudbees.com/snapshot/com/github/jcgay/maven/color/maven-color-logback/0.2-SNAPSHOT/maven-color-logback-0.2-20130814.191948-2-bundle.zip).  
Extract it in your `%M2_HOME%` folder. It contains logback dependencies and logging configuration.  
Delete `%M2_HOME%\lib\slf4j-simple-1.7.4.jar`.


#Maven Color

A colorized Maven console.  
Error messages are displayed in red, Warning in yellow.  
Tests results are also in color, failures/errors in red, skipped in yellow.

## Installation

Get [maven-color-agent](https://repository-jcgay.forge.cloudbees.com/release/com/github/jcgay/maven/color/maven-color-agent/0.1/maven-color-agent-0.1.jar). Copy it wherever you want (`$HOME/.m2` for example).
Append `-javaagent:$HOME/.m2/maven-color-agent-[version].jar` to you `$MAVEN_OPTS` environment variable.  
Get [maven-color-logger](https://repository-jcgay.forge.cloudbees.com/snapshot/com/github/jcgay/maven/color/maven-color-logger/0.1-SNAPSHOT/maven-color-logger-0.1.jar) and copy it in `%M2_HOME%/lib/ext` folder.

## How it works
This is a hacky java agent that replaces some logger implementation used internally by Maven and surefire plugin.  
It has been tested with Maven 3.0.4/5 and maven-surefire-plugin 2.6/2.9/2.14.
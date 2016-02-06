#1.3
***

- Remove log level for colored output ([8702b0e](http://github.com/jcgay/maven-color/commit/8702b0eccd8c04f77ffc7f0269b25c19cccd9c7a))
- Can disable coloring using property maven.color ([481b9c2](http://github.com/jcgay/maven-color/commit/481b9c2f53000ddee3b2296cad210580718739b8))

#1.2
***

- Can customize colors ([b4630d7](http://github.com/jcgay/maven-color/commit/b4630d74f29ee31230fc0eb2c64edcc4c7907e94))
- Remove Maven 3.0.x support ([1e04624](http://github.com/jcgay/maven-color/commit/1e0462444fcb9032ff82d8709c4e26e287ca6f27))

#1.1
***

- Prevent warning when Jansi fails to create WindowsAnsiOutputStream ([a377797](http://github.com/jcgay/maven-color/commit/a3777977f493a9f9ce85501ab957a3812ebc5b17))
- Use Jansi with Logback when windows terminal is msys ([f763ab5](http://github.com/jcgay/maven-color/commit/f763ab52ecdb6fb8882769a1a5106c89b4dcbe2e))
- Do not create a top directory in archives ([88c8ec2](http://github.com/jcgay/maven-color/commit/88c8ec2e54303d58d7895b0b8394545ebbdab3f7))
- Upgrade Log4j 2 to version 2.0-beta8 ([91a2c67](http://github.com/jcgay/maven-color/commit/91a2c673a19356ad3daf5529de969f81a7022b13))

#1.0
***

- Relocate ASM classes [view](http://github.com/jcgay/maven-color/commit/781157375e84fb8994a477a955c55854408c8a61)
- Remove build warning about cross platform compatibility [view](http://github.com/jcgay/maven-color/commit/6159f0cdc10bca11da36607038502db194fd3e06)

#0.5
***

- Remove errors under Windows with unix terminal using Logback [view](http://github.com/jcgay/maven-color/commit/db91a163d7eb1ec6b9232bbf0913c9e21dc96934)  
- Build a Windows box with Vagrant for testing [view](http://github.com/jcgay/maven-color/commit/36b75763617504b7e38ce9522802a9e0d3e549ef)  
- Move the 3.1.x section on top to fix issue #4 [view](http://github.com/jcgay/maven-color/commit/be1770826543e07afa5d4b05786814d0bb066695)  
- Clarify javaagent configuration. [view](http://github.com/jcgay/maven-color/commit/1ba3db7ecd9cc9f033301160acd8cac4bc393fcd)  

#0.4
***

- Write messages in one statement. [view](http://github.com/jcgay/maven-color/commit/246b891d62b6dd9e6dbd7b2a6f6c83cc0bb60920)  

#0.3
***

- Make tests run on Windows. [view](http://github.com/jcgay/maven-color/commit/32157ed4163dba74b1e99af2744e037796f1b4d1)  
- Do not use bright colors on Windows. [view](http://github.com/jcgay/maven-color/commit/2b2cec9150fae0f8a5344bca1466ebb6e542040b)  
- Can turn-off colorization. [view](http://github.com/jcgay/maven-color/commit/bf8dd607670ad4f6914ffd78939e7d20cc7fa080)  

#0.2
***

- Display module header in Cyan. [view](http://github.com/jcgay/maven-color/commit/d5d2258726ce5267c19dd19a6eb7bae56162a880)  
- Set bold style to plugin execution start message. [view](http://github.com/jcgay/maven-color/commit/2bffa403a591933d5ebd12360fd3726e8c78c834)  
- Add a log4j2 module for Maven 3.1.0. [view](http://github.com/jcgay/maven-color/commit/62694e32c0f3a9bf46c0a0483e787c9b73495359)  
- Add a logback module for Maven 3.1.0. [view](http://github.com/jcgay/maven-color/commit/72ed124cf9d384cfc9fba433d96c8722fd005b61)  

#0.1
***

- Print 'SKIPPED' module in yellow in reactor summary. [view](http://github.com/jcgay/maven-color/commit/b1a487167a1c90e1f9b413b10a97473a77210eb9)  
- Can disable maven color using property 'maven.color' (-Dmaven.color=false). [view](http://github.com/jcgay/maven-color/commit/b45e5a2b654858f6b180a97f61e3b45dc58184ed)  
- Replace OutputConsumer for maven-surefire 2.3. [view](http://github.com/jcgay/maven-color/commit/4ae3fc815b0ab015436aeec8147a07811ddf0cb0)  
- Create a maven-surefire OutputConsumer implementation using Jansi. [view](http://github.com/jcgay/maven-color/commit/deac04573a6106c6ff8aeaf0b140bbc8770a0e3e)  
- Enable maven-surefire-plugin logger replacement for version 2.9. [view](http://github.com/jcgay/maven-color/commit/f967e821030ca3ea802c11e019e6de865a02efc9)  
- Replace maven-surefire logger. [view](http://github.com/jcgay/maven-color/commit/32ee47d9e7b948b1e807f813b355f8e283bf11a2)  
- Create a maven-surefire logger implementation using Jansi. [view](http://github.com/jcgay/maven-color/commit/7c98bb52cc605fa8c3e43057df9d4a48804e046a)  
- Colorize build status messages. [view](http://github.com/jcgay/maven-color/commit/6e9656350f5fe5e2d101f3ebf9cae8b7f5ec0cb2)  
- Create a javaagent to change Maven logger implementation. [view](http://github.com/jcgay/maven-color/commit/269effdf63e9bfe2d9d58da71b96e3c9b2eae7bf)    
- Create a Logger implementation using Jansi. [view](http://github.com/jcgay/maven-color/commit/ae2fe10d03f571d2599c9fef227fe34cec95b641)  
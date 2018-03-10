FROM maven:alpine

WORKDIR /home
RUN echo '<toolchains><toolchain><type>jdk</type><provides><version>1.6</version></provides><configuration><jdkHome>/usr/lib/jvm/java-1.8-openjdk</jdkHome></configuration></toolchain></toolchains>' > toolchains.xml
ADD . /home
RUN mvn -t toolchains.xml package
RUN tar xvfz maven-color-gossip/target/maven-color-gossip-*-bundle-without-jansi.tar.gz -C $MAVEN_HOME
RUN rm $MAVEN_HOME/lib/maven-slf4j-provider*.jar

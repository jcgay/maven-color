FROM maven:3.3

WORKDIR /home
RUN echo '<toolchains><toolchain><type>jdk</type><provides><version>1.6</version></provides><configuration><jdkHome>/usr/lib/jvm/java-8-openjdk-amd64/</jdkHome></configuration></toolchain></toolchains>' > toolchains.xml
ADD . /home
RUN mvn -t toolchains.xml package
RUN tar xvfz maven-color-gossip/target/maven-color-gossip-*-bundle.tar.gz -C $MAVEN_HOME
RUN rm $MAVEN_HOME/lib/slf4j-simple-1.7.*.jar

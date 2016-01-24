FROM java:openjdk-8-jdk

# Install Scala.
RUN wget -q http://www.scala-lang.org/files/archive/scala-2.11.7.deb && \
    dpkg -i scala-2.11.7.deb && \
    rm -f scala-2.11.7.deb

# cleanup
RUN apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

WORKDIR /tmp/scala_exercises
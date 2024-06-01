JAVAC = javac
JAVA = java
JAVADOC = javadoc
MAIN = Main

SOURCES = Person.java SocialNetworkGraph.java Main.java

CLASSES = $(SOURCES:.java=.class)


JAVADOC_DIR = javadoc


all: $(CLASSES) javadoc


%.class: %.java
	$(JAVAC) $<


javadoc:
	$(JAVADOC) -d $(JAVADOC_DIR) $(SOURCES)


run: all
	$(JAVA) $(MAIN)


clean:
	rm -f *.class
	rm -rf $(JAVADOC_DIR)


.PHONY: all run clean

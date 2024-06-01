JC = javac
JFLAGS = -g
SOURCES = Main.java CLI.java Cluster.java Hobby.java Person.java SocialNetwork.java Suggestion.java
CLASSES = $(SOURCES:.java=.class)


default: doc compile run

# Compile all .java files to .class files
%.class: %.java
	$(JC) $(JFLAGS) $<


# Compile all the classes
compile: $(CLASSES)

doc: $(SOURCES)
	javadoc -d doc $(SOURCES)
# Run the main program
run: $(CLASSES)
	java Main

# Clean up .class files and the generated input file
clean:
	rm -f *.class

.PHONY: clean default
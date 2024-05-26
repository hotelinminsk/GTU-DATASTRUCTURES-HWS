JC = javac
JFLAGS = -g
SOURCES = Main.java Stock.java AVLTree.java StockDataManager.java RandomStringGenerator.java GUIVisualization.java randomInputGenerator.java
CLASSES = $(SOURCES:.java=.class)
INPUT_FILE = randominput.txt

default: run clean

# Compile all .java files to .class files
%.class: %.java
	$(JC) $(JFLAGS) $<

# Generate the random input file
$(INPUT_FILE): randomInputGenerator.class
	java randomInputGenerator

# Compile all the classes
compile: $(CLASSES)

# Run the main program
run: $(CLASSES) $(INPUT_FILE)
	java -Xint Main $(INPUT_FILE)

# Clean up .class files and the generated input file
clean:
	rm -f *.class $(INPUT_FILE)

.PHONY: clean default

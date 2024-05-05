JFLAGS = -g -cp src
JC = javac
JVM= java -cp src
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	src/Main.java \
	src/Directory.java \
	src/File.java \
	src/FileSystemElement.java \
	src/FileSystem.java

MAIN = Main

default: classes run

classes: $(CLASSES:.java=.class)

run: classes
	$(JVM) $(MAIN)

doc:
	javadoc -d doc -sourcepath src src/*.java

clean:
	$(RM) *.class

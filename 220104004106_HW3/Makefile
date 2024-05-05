JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = del
JR = java

CLASSES = \
		libraries/Device.java \
		libraries/AbstractDevice.java \
		libraries/Inventory.java \
		libraries/Smartphone.java \
		libraries/Laptop.java \
		libraries/SmartWatch.java \
		libraries/Headphones.java \
		libraries/TV.java \
		libraries/SingleLinkedList.java \
		srclibraries/Inventory.java \
		srclibraries/menu.java \
		Main.java

all : Main.class run clean

run : 
	$(JR) Main

classes : $(CLASSES:.java=.class)

%.class : %.java
	$(JC) $(JFLAGS) $<

doc:
	$(JD) $(JDFLAGS) *.java 

clean:
	$(RM) libraries/*.class
	$(RM) srclibraries/*.class
	$(RM) /*.class 



cleandoc:
	$(RM) -r ./javadoc
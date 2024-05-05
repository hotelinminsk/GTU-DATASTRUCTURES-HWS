JC = javac
JFLAGS = -g
TARGET = tester
SOURCES = tester.java alphabet.java encryptor.java decryptor.java preprocessor.java


default: $(TARGET) clean

$(TARGET): $(SOURCES)
	$(JC) $(JFLAGS) $^
	java $(TARGET)


clean:
	rm -f *.class

.PHONY: clean default

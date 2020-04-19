all: build
	java Jagger ./test.txt

clean:
	rm Jagger.java JaggerConstants.java JaggerTokenManager.java ParseException.java SimpleCharStream.java Token.java TokenMgrError.java
	rm *.class

build:
	javacc Jagger.jj
	javac *.java

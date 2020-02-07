all:
	javacc Jagger.jj
	javac *.java
	java Jagger

clean:
	rm Jagger.java JaggerConstants.java JaggerTokenManager.java ParseException.java SimpleCharStream.java Token.java TokenMgrError.java
	rm *.class


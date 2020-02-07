all:
	javacc Jagger.jj
	javac *.java
	java Jagger

clean:
	rm *.class


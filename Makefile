all: build
	java Jagger ./test.txt

clean:
	rm Jagger.java JaggerConstants.java JaggerTokenManager.java ParseException.java SimpleCharStream.java Token.java TokenMgrError.java
	rm *.class

build:
	javacc Jagger.jj
	javac *.java

check: build
	@echo "================================="
	java Jagger ./tests/pos_1.txt
	@echo "================================="
	java Jagger ./tests/pos_scopes.txt
	@echo "================================="
	java Jagger ./tests/pos_aff.txt
	@echo "================================="
	java Jagger ./tests/pos_loops.txt
	@echo "================================="
	java Jagger ./tests/neg_if.txt
	@echo "================================="
	java Jagger ./tests/neg_binop.txt
	@echo "================================="
	java Jagger ./tests/neg_binop_type.txt
	@echo "================================="
	java Jagger ./tests/neg_vardecl.txt
	@echo "================================="
	java Jagger ./tests/neg_var.txt
	@echo "================================="
	java Jagger ./tests/neg_binder.txt
	@echo "================================="
	java Jagger ./tests/neg_assign.txt
	@echo "================================="

include config.mk
OBJ = $(addprefix ${PKG}/,${SRC:.java=.class})
JC = javac
J = java
PKG = out

compile: ${SRC}
	${JC} -d . ${SRC}

run: ${OBJ}
	${J} ${PKG}/Main

clean:
	rm ${PKG}/*.class

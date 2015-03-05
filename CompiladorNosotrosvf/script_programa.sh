clear
./byaccj -J -v -Jstack=999 Proyectic.y 
jflex-1.6.0/bin/jflex lexico.flex
javac objetos/*.java
javac *.java
java Parser programa.c
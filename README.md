# Adapter-Map-2st-exams

Giacomo Calabria           	Matricola n°2007964

Secondo appello dell' esame del corso di Elementi di Ingegneria del Software, canale 1

Supponendo di essere in ambiente Java Micro Edition CLDC 1.1

* [Vector (CLDC 1.1) (oracle.com)](https://docs.oracle.com/javame/config/cldc/ref-impl/cldc1.1/jsr139/java/util/Vector.html)
* [Enumeration (CLDC 1.1) (oracle.com)](https://docs.oracle.com/javame/config/cldc/ref-impl/cldc1.1/jsr139/java/util/Enumeration.html)
* [Hashtable (CLDC 1.1) (oracle.com)](https://docs.oracle.com/javame/config/cldc/ref-impl/cldc1.1/jsr139/java/util/Hashtable.html)

Usiamo in questo ambiente una libreria di classi in ambiente J2SE 1.4.2

* [J2SE 1.4.2 (geas.dei.unipd.it)](http://geas.dei.unipd.it/jdk1.4.2/docs/api/)
* [Map (J2SE 1.4.2) (geas.dei.unipd.it)](http://geas.dei.unipd.it/jdk1.4.2/docs/api/java/util/Map.html)
* [Collection (J2SE 1.4.2) (geas.dei.unipd.it)](https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/java/util/Collection.html)
* [Set (J2SE 1.4.2) (geas.dei.unipd.it)](http://geas.dei.unipd.it/jdk1.4.2/docs/api/java/util/Set.html)

Il secondo appello consiste nel sviluppare un ***adapter*** di Map utilizzando come ***adaptee*** per Map la classe Hashtable CLDC 1.1

La classe adapter si chiama MapAdapter e appartiene al package myAdapter

Le classi di test sono contenute nel package myTest. Il package myTest contiene una classe TestRunner che può essere invocata a linea di comando ed esegue tutti i test definiti, fornendo il risultato dei test. 


Compilazione del progetto:

    javac -cp "./JUnit/*;./Matcher/*" myAdapter/*.java myTest/*.java

Esecuzione di TestRunner:

    java -cp "./;./JUnit/*;./Matcher/*" myTest.TestRunner"


La documentazione è contenuta in una cartella denominata Documentazione. 
La documentazione delle classi, la test suite e i test case sono stati documentati nel formato javadoc.

# Laboratorio 1 ARSW

## Integrantes

*   Juan Sebastián Frásica
*   Andrés Guillermo Rocha

## BBP Formula
*   This exercise contains an introduction to programming with threads in Java, in addition to the application to a specific case.

### Part I - Introduction to Java Threads
1. As reviewed in the readings, complete the CountThread classes, so that they define the life cycle of a thread that prints the numbers between A and B. 
2. Complete the main method of the CountMainThreads class so that: 
    *   Create 3 threads of type CountThread, assigning the first one the interval [0..99], the second one [99..199], and the third one [200..299]. 
    *   Start all three threads with start(). 
    *   Run and check the output on screen. 
    *   Change the beginning with start() to run(). How does the output change? Why?
	
*   Para compilar:
```mvn package```

*   Para ejecutar:

```mvn exec:java -Dexec.mainClass="edu.eci.arsw.primefinder.Main"```

Cuando llamamos al método run() del thread, se ejecuta todo el fragmento de código que hay en este. Como lo llamamos con diferentes intervalos, se 
observa que el imprime los números entre A y B secuencialmente, es decir, de menor a mayor empezando con el intervalo A.

Cuando llamamos al método start() del thread, creamos una nueva instancia de Hilo que va a llamar al método run() desde un intervalo A hasta B. Por eso, el 
resultado que se ve en pantalla es diferentes intervalos de números, pero no en un orden secuencial. Esto ocurre porque cada hilo creado empieza a mostrar sus 
propios resultados al mismo tiempo.


### Part II - BBP Formula Exercise

*   The BBP formula (Bailey – Borwein – Plouffe formula) is an algorithm that allows you to calculate the nth digit of PI in base 16, with the particularity of not needing to calculate us n-1 previous digits. This feature makes it possible to convert the problem of calculating a massive number of PI digits (in base 16) to a shamefully parallel one. In this repository you will find the implementation, along with a set of tests.
For this exercise you want to calculate, in the shortest possible time, and in a single machine (taking advantage of the multi-core characteristics of the same) at least the first million digits of PI (in base 16).

1.  Create a Thread type class that represents the life cycle of a thread that calculates a portion of the required digits. 
2.  Have the PiDigits.getDigits() function receive as an additional parameter an N value, corresponding to the number of threads between which the solution is to be parallelized. Have that function wait until the N threads finish solving the problem to combine the answers and then return the result. For this, review the join method of the Java concurrency API. 
3.  Adjust the JUnit tests, considering the cases of using 1, 2 or 3 threads (the last one to consider an odd number of threads!)


### Part III - Performance Evaluation

*   From the above, implement the following sequence of experiments to calculate the million digits (hex) of PI, taking their execution times (be sure to do them on the same machine):
1.  Single thread. 
2.  As many threads as processing cores (have the program determine this using the Runtime API). 
3.  So many threads as double processing cores. 
4.  200 threads.
5.  500 threads    

When starting the program, run the jVisualVM monitor, and as the tests run, check and record the CPU and memory consumption in each case.     

* Con un hilo – 100000 digitos

![pa1.1](https://github.com/sebastianfrasic/Lab1-ARSW/blob/master/BBP_FORMULA/PARALLELISM-JAVA_THREADS_MAVEN-INTRODUCTION_BBP_FORMULA/img/unHilo.png)

* Con número de hilos igual a número de procesadores(4) – 100000 digitos

![pa1.2](https://github.com/sebastianfrasic/Lab1-ARSW/blob/master/BBP_FORMULA/PARALLELISM-JAVA_THREADS_MAVEN-INTRODUCTION_BBP_FORMULA/img/cuatroHilos.png)

* Con número de hilos igual al doble  número de procesadores(8) – 100000 digitos

![pa1.3](https://github.com/sebastianfrasic/Lab1-ARSW/blob/master/BBP_FORMULA/PARALLELISM-JAVA_THREADS_MAVEN-INTRODUCTION_BBP_FORMULA/img/ochoHilos.png)

* Con Numero de hilos igual a 200 – 100000 dígitos

![pa1.4](https://github.com/sebastianfrasic/Lab1-ARSW/blob/master/BBP_FORMULA/PARALLELISM-JAVA_THREADS_MAVEN-INTRODUCTION_BBP_FORMULA/img/200Hilos.png)

* Con Numero de hilos igual a 500 – 100000 dígitos

![pa1.5](https://github.com/sebastianfrasic/Lab1-ARSW/blob/master/BBP_FORMULA/PARALLELISM-JAVA_THREADS_MAVEN-INTRODUCTION_BBP_FORMULA/img/500Hilos.png)


With the above, and with the execution times given, graph solution time vs. Number of threads. Analyze and propose hypotheses with your partner for the following questions (you can take into account what is reported by jVisualVM):

![pa1.6](https://github.com/sebastianfrasic/Lab1-ARSW/blob/master/BBP_FORMULA/PARALLELISM-JAVA_THREADS_MAVEN-INTRODUCTION_BBP_FORMULA/img/grafica.PNG)

1. According to Amdahls law, where S (n) is the theoretical performance improvement, P the parallel fraction of the algorithm, and n the number of threads, the greater n, the greater the improvement should be. Why is the best performance not achieved with the 500 threads? How does this performance compare when 200 are used?.  

La ley de Amdahls dice que podemos mejorar el rendimiento de un sistema si mejoramos el rendimiento de una de sus procesos. Cuando usamos hilos en el programa BBP, estamos 
mejorando el tiempo de ejecución de cada uno de los subprocesos que tendrá nuestra solución, es decir, el tiempo de los hilos. Por lo cual, entre mayor sea el número de 
hilos que usemos, habrá un mejor rendimiento en nuestro programa.

Al observar los resultados, el rendimiento obtenido al usar 500 hilos, podemos ver que es el más demorado de todos. Además, es más lento en comparación a cuando usamos
200 hilos. Esto pasa porque en la ley de Amdahls, se llega a un número de subprocesos en donde se vuelve casi constante la mejora de rendimiento que hay en el programa, dependiendo
de la cantidad de hilos usados, es decir, aunque aumentemos el número de hilos la mejora de rendimiento var ser casi igual que las anteriores.

Pero la ley de Amdahls es un concepto teórico y no tiene en cuenta factores físicos que influyen en el rendimiento del sistema como el intercambio de bloqueos o las variables 
compartidas entre los diferentes hilos. Todas estas operaciones, relentizan el tiempo de ejecución del programa. Y a mayor número de subprocesos creados, mayor será esta 
relentización.  

Por eso, podemos ver  que al usar 200 y 500 hilos en el progama BBP estamos un timepo mayor en comparación a resolver el programa con un solo hilo.
 
2. How does the solution behave using as many processing threads as cores compared to the result of using twice as much?

Cuando usamos una cantidad de hilos igual a la de procesadores(4), obtenemos una mejora de rendimiento muy notable. Pero cuando usamos una cantidad de hilos igual
al doble de procesadores, relentizamos el rendimiento del programa. Esta relentización tiene la misma explicación a cuando usamos los 200 o 500 hilos. 

3. According to the above, if for this problem instead of 500 threads on a single CPU, 1 wire could be used on each of 500 hypothetical machines, would Amdahls's law be better applied? If, instead, c threads were used in 500 / c distributed machines (where c is the number of cores of said machines), would it be improved? Explain your answer.

No habría una mejora en el rendimiento del sistema porque al momento de unir el resultado de cada una de las diferentes máquinas estaríamos consumiendo recursos 
físicos que relentizarían nuestro programa. Además, al analizar los casos anteriores, nos damos cuenta que en la gráfica de Número de Threads vs Tiempo después de usar 
4 o 5 hilos; la mejora del rendimiento de nuestro programa es casi constante.


## Dogs Race case

*   This exercise is intended for the student to know and apply concepts of concurrent programming.

### Part I

*   Para compilar:
```mvn package```

*   Para ejecutar:

```mvn exec:java -Dexec.mainClass="edu.eci.arsw.primefinder.Main"```

*   Creation, commissioning and coordination of threads.
1.  Review the "concurrent cousins" program (in the folder part1), provided in the package edu.eci.arsw.primefinder. This is a program that calculates the prime numbers between two intervals, distributing their search among independent threads. For now, it has a single thread that seeks cousins ​​between 0 and 30,000,000. Run it, open the operating system process manager, and verify how many cores are used by it.


2.  Modify the program so that, instead of solving the problem with a single thread, do it with three, where each of these will make up the first part of the original problem. Check the operation again, and again check the use of the equipment cores.


3.  What you have been asked for is: you must modify the application so that when 5 seconds have elapsed since the execution started, all the threads are stopped and the number of primes ​​found so far is displayed. Then, you must wait for the user to press ENTER to resume their execution.

### Part II

*   For this exercise, you will work with a greyhound racing simulator (folder part2), whose graphic representation corresponds to the following figure:

![pa2.1](https://github.com/sebastianfrasic/Lab1-ARSW/blob/master/DOGS_RACE/CONCURRENT_PROGRAMMING-JAVA_MAVEN-DOGS_RACE/img/media/image1.png)

*   In the simulation, all the greyhounds have the same speed (at the programming level), so the winning greyhound will be the one that (for reasons of chance) has been most benefited by the scheduling of the processor (that is, the one with the most cycles CPU has been granted during the race). The application model is as follows:

![pa2.2](https://github.com/sebastianfrasic/Lab1-ARSW/blob/master/DOGS_RACE/CONCURRENT_PROGRAMMING-JAVA_MAVEN-DOGS_RACE/img/media/image2.png)


*   As you can see, greyhounds are thread objects, and their progress is displayed in the Canodromo class, which is basically a Swing form. All greyhounds (by default, there are 17 greyhounds running on a 100-meter track) share access to an object of type RegistrationLlegada. When a greyhound reaches the goal, it accesses the counter located on said object (whose initial value is 1), and takes that value as its arrival position, and then increases it by 1. The greyhound that manages to take the '1' will be the winner.
When starting the application, there is a first obvious error: the results (total run and number of the winning greyhound) are shown before the end of the race as such. However, once this is corrected, there may be more inconsistencies caused by the presence of race conditions.

### Part III

*   Para compilar:
```mvn package```

*   Para ejecutar:
```mvn exec:java -Dexec.mainClass="edu.eci.arsw.threads.MainCanodromo"```

1.  Fix the application so that the results notice is shown only when the execution of all the ‘greyhound’ threads is finished. For this keep in mind:
    *   The action of starting the race and showing the results is carried out from line 38 of MainCanodromo.
    *   The join() method of the Thread class can be used to synchronize the thread that starts the race, with the completion of the greyhound threads.
2.  Once the initial problem has been corrected, run the application several times, and identify the inconsistencies in the results of the same by seeing the ‘ranking’ shown on the console (sometimes valid results could appear, but in other cases such inconsistencies may occur). From this, identify the critical regions of the program.
    ```The critical region of the program is RegistroLlegada.ultimaPosicionAlcanzada ```

3. Use a synchronization mechanism to ensure that these critical regions only access one thread at a time. Verify the results.

4.  Implement the pause and continue functionalities. With these, when "Stop" is clicked, all the threads of the greyhounds should fall asleep, and when "Continue" is clicked they should wake up and continue with the race. Design a solution that allows you to do this using the synchronization mechanisms with the Locks primitives provided by the language (wait and notifyAll).

    ```These methods are in class Galgo.java and they are called pausar() and reanudar()  ```


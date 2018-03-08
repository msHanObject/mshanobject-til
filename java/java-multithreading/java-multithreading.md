Java-Multithreading
===

By definition, multitasking is when multiple processes share common processing resources such as a CPU. Multi-threading extends the idea of multitasking into applications where you can subdivide specific operations within a single application into individual threads. Each of the threads can run in parallel.

Life Cycle of a Thread
---
A thread goes through various stages in its life cycle. For example, a thread is born, started, runs, and then dies. The following diagram shows the complete life cycle of a thread.

![Life Cycle of a Thread](https://www.tutorialspoint.com/java/images/Thread_Life_Cycle.jpg)

* **New:** A new thread begins its life cycle in the new state. It remains in this state until the program starts the thread. It is also referred to as a **born thread**.
* **Runnable:** After a newly born thread is started, the thread becomes runnable. A thread in this state is considered to be executing its task.
* **Waiting:** Sometimes, a thread transitions to the watiing state while the thread waits for anothor thread to perform a task. A thread transitions back to the runnable state only when another thread signals the waiting thread to continue executing.
* **Timed Waiting:** A runnable thread can enter the timed waiting state for a specified interval of time. A thread in this state transitions back to the runnable state when that time interval expires or when the event it is waiting for occurs.
* **Terminated (Dead):** A runnable thread enters the terminated state when it completes its task or otherwise terminates.

Thread Priorities
---
Every Java thread has a priority that helps the operating system determine the order in which threads are scheduled.

Java thread priorities are in the range between MIN_PRIORITY(a constant of 1) and MAX_PRIORITY( a constant of 10). By default, every thread is given priority NORM_PRIORITY(a constant of 5).

Threads with higher priorites are in more important to a program and should be allocated processor time before lower-priority threads. However, thread priorities cannot guarantee the order in which threads execute and are very much platform dependent.

Create a Thread by Implementing a Runnable Interface
----
If your class is intended to be executed as a thread then you can achieve this by implementing a **Runnable** interface. You will need to follow three basic steps:

**Step 1**
As a first step, you need to implement a run() method provided by a **Runnable** interfcae. This method provides an entry point for the thread and you will put your complete business logic inside this method. Following is a simple syntax of the run() method:
```
public void run()
```

**Step 2**
As a second step, you will instantiate a **Thread** object using the following constructor:
```
Thread(Runnable threadObj, String threadName);
```
Where, *threadObj* is an instance of a class that implements the **Runnable** interface and **threadName** is the name given to the new thread.

**Step 3**
Once a Thread object is created, you can stat it by calling **start()** method, which executes a call to run() method. Following is a simple syntax of start() method:
```
void start();
```
**Example**
RunnableDemo.java

Create a Thread by Extending a Thread Class
---
The second way to create a thread is to create a new class that extends **Thread** class using the following two simple steps. This approach provides more flexibility in handing multiple threads created using available methods in Thread class.

**Step 1**
Yout will need to override **run()** method available in Thread class. ...
```
public void run()
```

**Step 2**
Once Thread object is created, you can start it by calling **start()** method, which executes a call to run() method.
```
void start();
```
**Example**
TestThread2.java

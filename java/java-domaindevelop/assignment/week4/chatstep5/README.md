ChatStep5
===

This is a chatting program that use client & server model.

You can imagine game chatting system.

![game chatting image](http://cdn.gamemeca.com/gmdata/0000/222/023/2011_0407_heba_guide_chat_ss1_fix.jpg)

Usage
---

* javac server\*.java
* java server.Server
* open other cmd
* javac client\*.java
* java client.Client
* open another cmd
* java client.Client
* enjoy chatting

Business Logic
---

1. Make a object of ServerSocket and HashMap hava a argument String, PrintWriter.
2. In loop, make a object of ServerThread class and start the object.
3. If the object of ServerThread don't exist, close the object.



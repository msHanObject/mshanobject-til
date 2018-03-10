Defining an Interface
===
An interface can extend other interfaces, just as a class subclass or extend another class.
However, whereas a class can extend only one other class, an interface can extend any number of interfaces.

The InterfaceBody
===
The inteface body can contain *abstract methods, default methods*, and *static methods*.
All abstract, default, and static methods in an interface are implicity *public*, so you can omit the *public* modifier.

Implementing an Interface
===
By convention, the *implements* clause follows the *extends* clause, if there is one.

```java
public interface Relatable{
	public int isLargerThan(Relatable other);
}
public int isLargerThan(Relatable other){
	RectanglePlus otherRect = (RectanglePlus) other;
	if (this.getArea() < otherRect.getArea())
		return -1;
	else if (this.getArea() > otherRect.getArea())
		retrun 1;
	else
		return 0;
	}
}
```
Using an interface as a Type
===
You can use interface names anywhere you can use any other data type name.
If you define a reference variable whose type is an interface, any object you assign to it must be an instance of a class that implements the interface.
```java
public Object findLargest(Object object1, Object object2){
	Relatable obj1 = (Relatable)object1;
	Relatable obj2 = (Relatabel)object2;
	if((obj1).isLargerThan(obj2) > 0)
		return object1;
	else
		return objet2;
}
```
By casting object1 to a Relatable type, it can invoke the isLargerThan method.

Evolving Interfaces
===
If there was old interface that changed, classes implemented old interface will break beacause they no longer implement the old interface.
So if you want to halde with this problem, make new interface that extends old interface. And used that new one.
Also you can insert default methods in the old interface.

Default Methods
===
If you modified interface, you have to modified class implemented the interface.
But you can defining default methods in interface. Not just adding methods.(Remember that an *abstract method* is a method delcared without an implementation.)

Extending Interface That Contain Default Methods
===
When you extends an interface that contains a default method, you can do the following:
* Not mention the default method at all, which lets your extended interface inherit the default method.
* Redeclare the default method, which makes it *abstract*.
* Redefine the default method, which overrides it.

Static Methods
===
The following example defines a static method that retrieves a ZoneId object coresponding to a time zone identifier; it uses the system default time zone if there is no ZoneId object corresponding to the given identifier.(As a reulst, you can simplify the method getZonedDateTime):
```java
public interface TimeClient {
//...
	static public ZoneId getZoneId (String zoneString) {
		try {
			return ZoneId.of(zoneString);
		} catch(DateTimeException e) {
			System.err.println("Invalid time zone: " + zoneString +
			"; using default time zone instead.");
		}
	}
	default public ZonedDateTime getZonedDateTime(String zoneString){
		return ZonedDateTime.of(getLocalDateTime(), getZoneId(zoneString));
	}
}
```
All method declarations in an interface, including static methods, are implicitly *public*, so you can omit the *public* modifier.

Integrating Default Methods into Existing Libraries
===


















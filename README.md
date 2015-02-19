# JPA Hello, world!

This is a minimal JPA example, which inserts an entry into a table.

## Build

I have tested this on Ubuntu 14.04, with OpenJDK 1.7,
EclipseLink 2.5.1 and a MySQL database.

Since this is just a single source, you compile it with 

    javac -cp /usr/share/java/eclipselink-jpa-2.1-spec.jar HelloWorld.java

and run it, e.g. 

    java -cp .:/usr/share/java/eclipselink.jar:/usr/share/java/eclipselink-jpa-2.1-spec.jar:/usr/share/java/mysql.jar HelloWorld

If you use another database engine, you must adjust accordingly.

## License

BSD-3

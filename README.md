# complaint-app-java-backend
Interesting back-end application for a complainant and an admin. In which a complainant, sends what bothers him to the admin by creating and submitting a complaint, and then track the life cycle of that complaint (resolved, pending-resolution, dismissed). On the other hand, the app opens the door for the admin to view all the complaints and change their status.

Run: 
1. Download Intellij IDE https://www.jetbrains.com/idea/download/#section=windows 
2. Clone the repository, and open the pom.xml file with Intellij IDE. 
3. Edit config.yml file with your database config, and change the password of the admin, and you may want to change the admin name which is by default 'aya'.
4. Download MySQL Workbench https://dev.mysql.com/downloads/workbench/ 
5. Copy the sql statments from the 'complaint-database.sql' file, and past  them into your MySQL Workbench to create the database.
6. On the left side of the Intellij IDE, do the following on Maven lifecycle: Clean, compile, and package.
7. In your project directory, run this: 

    java -jar target/complaint-1.0-SNAPSHOT.jar 

then run this: 

    java -jar target/complaint-1.0-SNAPSHOT.jar server config.yml

Check how to run dropwizard application for more information : https://www.dropwizard.io/en/latest/getting-started.html

8. Download Postman to run to do the http requests https://app.getpostman.com/app/download/win64?
9. You can test the API's in the resoure directory. Note that you have to provide 'basic auth' with each request. Also, by default the admin credentials are (username:aya, pass: 123)

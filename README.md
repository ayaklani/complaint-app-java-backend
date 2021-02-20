# complaint-app-java-backend
Interesting back-end application for a complainant and an admin. In which a complainant, sends what bothers him to the admin by creating and submitting a complaint, and then track the life cycle of that complaint (resolved, pending-resolution, dismissed). On the other hand, the app opens the door for the admin to view all the complaints and change their status.

Run: 
1. Download Intellij IDE https://www.jetbrains.com/idea/download/#section=windows 
2. Clone the repository, and open the pom.xml file with Intellij IDE. 
3. Edit config.yml file with your database config, and change the name of the admin, which is by default (username : aya, password: 123).
4. On the left side of the IDE, do the following on Maven lifecycle: Clean, compile, and package.
3. In your project directory, run this: 

    java -jar target/complaint-1.0-SNAPSHOT.jar 

then run this: 

    java -jar target/complaint-1.0-SNAPSHOT.jar server config.yml

Check how to run dropwizard application for more information : https://www.dropwizard.io/en/latest/getting-started.html

5. Download Postman to run to do the http requests https://app.getpostman.com/app/download/win64?
6. You can test the API's in the resoure directory.Note that you have to provide 'basic auth' with each request. Also, by default the admin credentials are (username:aya, pass: 123)

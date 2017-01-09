# BuggyTheApp
It is a bug-full app that build with one goal in mind, coding in the worst way. 
to do that we implemented dummy functions and added the needful files to trigger the vulnerabilities we wanted.
Here is a list of the files in the app:
1. Blistner.java //listener class to display broadcasted messages
2. broadcaster.java // Broadcasting class sends messages to this app each 3 seconds
3. CPclass.java // content provider class provide a ccess to the users info
4. DBclass.java // SQLite Database class to store users info 
5. HRtest.java // dummy login for local webserver. not used in the current run of the course
6. HttpsSearch.java // Dummy search class for searching google images, it used to demonstrate over trusted SSL certificate
7. MainActivity.java // main activity of the app present the login screen.
8. signup.java // where the new user sign up to the app and the data stored in the user information database.
9. welcome.java // interface showen for the loged in users only to start and stop some functions.

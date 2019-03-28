# java-getting-started

A barebones Java app, which can easily be deployed to Heroku.

This application supports the [Getting Started with Java on Heroku](https://devcenter.heroku.com/articles/getting-started-with-java) article - check it out.

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## Running Locally

Make sure you have Java and Maven installed.  Also, install the [Heroku CLI](https://cli.heroku.com/).

```sh
$ git clone https://github.com/heroku/java-getting-started.git
$ cd java-getting-started
$ mvn install
$ heroku local:start
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

## Deploying to Heroku

```sh
$ heroku create
$ git push heroku master
$ heroku open
```

## Postman Testing

Use the following postman collection to test the REST API.

[Postman Collection](https://www.getpostman.com/collections/316dc4d4e748fe9810e9) 

## Documentation

For more information about using Java on Heroku, see these Dev Center articles:

- [Java on Heroku](https://devcenter.heroku.com/categories/java)

## The program versions that we need to execute this project.

![](https://github.com/jcamilovelandiab/Heroku-Spring-Lab-master/tree/masterimg/java.JPG)
![](https://github.com/jcamilovelandiab/Heroku-Spring-Lab-master/tree/masterimg/maven.JPG)
![](https://github.com/jcamilovelandiab/Heroku-Spring-Lab-master/tree/masterimg/heroku.JPG)

## Prepare the app
### Execute the app in localhost. (Add screenshot)

  execute the following command. mvn spring-boot:run
  ![](https://github.com/jcamilovelandiab/Heroku-Spring-Lab-master/img/mvn_springbootRUN.PNG)
  ![](https://github.com/jcamilovelandiab/Heroku-Spring-Lab-master/img/herokuLocalhost5000.PNG)
  
### What the application do?
  The application offers the API Rest service in which we can perform operations such as get, post, put, and delete on the entities, car    and user.
  
### Describe the REST services exposed by the application.

  The REST services that offers the application:
- With the car controller we can do:
    - view information for all cars from the application.
    - create a car.
    - update car information.
    - delete a car from the application with the license plate.
- With the user controller we can do:
    - view information for all users from the application.
    - create an user.
    - update user information.
    - delete a user from the application with the id.

### For what purpose is the Procfile plaintext file? 

 Heroku apps include a Procfile that specifies the commands that are executed by the app on startup.
 The Procfile is always a simple text file that is named Procfile without a file extension. For example, Procfile.txt is not valid.  The Procfile must live in your app’s root directory. It does not function if placed anywhere else.
 
 the following line is on our procfile:
 ```sh
web: java -jar target/heroku-lab-1.0.jar.
```
  

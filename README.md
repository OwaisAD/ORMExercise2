# ORMExercise2
Another ORM Exercise with JPA

Basically the steps to follow:
- Create a schema in MySql workbench on your remote sql server through docker
- Go to model -> Click on "Create EER model from Database" -> choose your database
- Create the tables and relationships between them for your system
- Click Database in the menu -> Synchronise model
- Now jump in to IntelliJ create a project
- Make sure you have JPABuddy installed
- Jump into it in the view and create a mysql connection to the database
- Now right click on your connection and click new -> JPA Entities from DB
- REMEMBER TO CLICK ON THE DIFFERENT REFERENCES!! Make a package entities as well
- Jump into each entity and make sure to have empty constructors in all of them, if not create them
- Add each entities in <class></class> and properties from the link below in the persistence.xml file
- Now create a facade (hence facade pattern) and use singleton pattern to make it only possible to initiate once


Based on the following exercise:
https://docs.google.com/document/d/1HOGhs-xQsjy_-Kf2frI-GkF5Xw8ADpHqZpgW-KC9ghI/edit

Link to properties that needs to be copied into persistence.xml so that the db can be updated for each change happening with our entities:
https://gist.githubusercontent.com/Thomas-Hartmann/fca053a4c620818622d745da0efe18c7/raw/f20e8254a3194af8f0b903ca178bb69a349f7839/persistence.xml
(remember to change the persistence-unit name in the persistence.xml to pu + change db name, user and password)
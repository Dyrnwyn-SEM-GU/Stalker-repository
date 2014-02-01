This is the project Stalker.

It is a travel logbook system written in Java for our school assignement.

The jcalendarbutton project that is used in this project to get a date selector was downloaded from http://jcalendarbutton.sourceforge.net/



/*----------------------------------------------------------------*/
		DIT215 - Project programing
			STALKER v.1.1
/*----------------------------------------------------------------*/
a digital travel logbook
by Mahsa, Jani, Sally, Gabriele, Danielle, Mina, Aurélien


how to run it:

	- download STALKER
	- run the stalker.jar
	- to login to the system use the username dyrnwyn@dyrnwyn.com 
	  and the password dyrnwyn.
	CAREFULL! THE CONNECTION TO THE DATABASE IS NOT ENCRYPTED

	(not required!)
	if you want to install the database on your computer
	- install mysql
	- in the command line type in mysql and log in as root
	- type in "source" and the filepath of the StalkerDB.sql file
	- in the DatabaseConnector.java file in the src folder change
	the connection to "jdbc:mysql://localhost/StalkerDB" (at line 28)

	you now have a local Stalker database!
--------------------------------------------------------------------
2013-12-17
recent changes:

- it is now possible to create a travel log, and save it 
  on the database.
- it is possible to upload pictures to the database.
- the dropdown menus display values from the database

2014-01-03

- user now has to login with a valid username and 
  password combination to access the program.
- It is possible to export the data displayed in the report tab
  and with a dialogbox select where to store the exported
  file and give it a name.
- the dropdown menus display only the values that are related 
  to the logged in user.

2014-01-09
- code was totaly restructured, not needed methods where deleted,
  variables where given meaningfull names. Minor bugs where fixed,
  the look of some windows has been changed. 

2014-01-16
- final changes, many bugfixes, input checking has been added


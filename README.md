This is the project Stalker.

It is a travel logbook system written in Java for our school assignement.

The jcalendarbutton project that is used in this project to get a date selector was downloaded from http://jcalendarbutton.sourceforge.net/


Terms & Conditions for Stalker - A Travel logbook system


Copyright (c) 2014 Sally Masry, Jani Pasanen, Aurélien Hontabat,
Elisa Danielle Santos, Mahsa Abassian, Gabriele Kasparaviciute

This software is licensed under GPLv3

Permission is hereby granted, free of charge to any person obtaining a
copy of this software, this travel log application that we call Stalker, 
and associated documentation files (the "Software"), to deal in the Software 
without restriction, including without limitation the rights to use, copy, 
modify, merge, publish, distribute, sublicense, and/or sell copies of the 
Software, and to permit persons to whom the Software is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in 
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR 
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE 
OR OTHER DEALINGS IN THE SOFTWARE.


/*----------------------------------------------------------------*/
		DIT215 - Project programing
			STALKER v.1.1
/*----------------------------------------------------------------*/
a digital travel logbook
by Mahsa, Jani, Sally, Gabriele, Danielle, Mina, Aurélien


how to run it:

	- install mysql
	- in the command line type in mysql and log in as root
	- type in "source" and the filepath of the StalkerDB.sql file
	- in the DatabaseConnector.java file in the src folder make sure
	the connection is to "jdbc:mysql://localhost/StalkerDB" (at line 28)

	you now have a local Stalker database!
	
	- download STALKER
	- run the sourcefiles or export as a jar file and run it (java -jar stalker.jar)
	- to login to the system use the username dyrnwyn@dyrnwyn.com 
	  and the password dyrnwyn too test the functions with sample data
	  or create your own account from within the software.

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


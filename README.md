This is the project Stalker.

It is a digital travel logbook system written in Java for our school project.

The jcalendarbutton project that is used in this project to get a date 
selector was downloaded from http://jcalendarbutton.sourceforge.net/


Terms & Conditions for
Stalker - A Travel logbook system

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
		DIT215 - Project programming
			STALKER v.05
/*----------------------------------------------------------------*/
a digital travel logbook
by Mahsa, Jani, Sally, Gabriele, Danielle, Mina, Aurélien


how to install it:
	- pre-requisite, install MYSQL and java 1.7 JRE
	- download STALKER
	- in the MYSQL Command Line Client, type in "source" 
	  and the filepath of the "StalkerDB.sql" file 
	  included, this creates the stalker database (StalkerDB)
	- run stalker.jar
	- to login to the system us username dyrnwyn@dyrnwyn.com 
	  and username dyrnwyn.

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

2014-01-07


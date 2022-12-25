# Personal Project:
## ***Password Manager*** 


### **What will the application do?**
This application will allow a user to save multiple passwords for the different accounts they have to a place when it 
gets difficult to remember all passwords by memory. This application will be able to store the name of the application 
of what the account is for along with the username of it. An application like this is useful as all your passwords will 
be kept in one spot. The user will have abilities to view all the different account information they have saved.

### **Who will use it?**
This Password Managing application can be used by anyone. If an individual has too many passwords to keep track of, 
they can store them in this application. It can be useful to many ages as sometimes it can get difficult remembering 
your username and account for a new account you create for an application, or even when trying to remember a password 
of an account you made 10+ years ago. In cases like that, an application like this can come to your service.

### **Why is this project of interest to you?**
This idea of creating an application that will help someone keep track of their passwords and usernames is 
interesting to me as I want to create something useful but simple at the same time. For my first ever computer science
project, I want to create something that can be useful to many individuals which is why this was one of the first few
ideas that came to my mind. 


## ***USER STORIES***
- As a user, I want to be able to add an account to my Password Manager with its password, user & application name
- As a user, I want to be able to remove an account from my Password Manager
- As a user, I want to be able to view all the accounts saved in my Password Manager
- As a user, I want to be able to see the total number of accounts saved
- As a user, I want to be able to save the accounts I added to my password manager to file
- As a user, I want to be able to load my accounts from my password manager saved from file


## **About the application:**
- You can generate the first required event related to adding Accounts (Xs) to the Password Manager (Y) by entering an 
application name in the first text field, the username in the second one, and the password in the third text field. 
Then click 'ADD' below it to add this account to the password manager.

- You can generate the second required event related to removing an Account (X) from the Password Manager (Y) by 
searching an account just by its application name in the 'Application to remove' text field and then the specific 
username you saved in the 'Username to remove' text field. Then click 'REMOVE' to remove this account from the
password manager.

- You can see the total accounts present in the password manager by looking at the label at the bottom left side of 
the frame which updates when you add/remove or load accounts from file.

- You can locate my visual component by seeing it visible on the left side of the frame when the GUI is run,
behind the panel for adding and removing an account. It is saved in the ./data folder of my project.

- You can save the state of my application by going to 'File' on the top left side of the frame then clicking *'save'*
in the drop-down menu.

- You can load the state of my application by going to 'File' on the top left side of the frame then clicking *'load'*
in the drop-down menu.

  
## **Phase 4: Task 2**
- When the Password Manager GUI is run, the following is an example of how the events of adding and removing a password
are logged:

Sat Nov 26 14:28:47 PST 2022

Account for Instagram has been added!

Sat Nov 26 14:29:09 PST 2022

Account for Snapchat has been added!

Sat Nov 26 14:29:21 PST 2022

Account for SSC has been added!

Sat Nov 26 14:29:38 PST 2022

Account for Canvas has been added!

Sat Nov 26 14:35:38 PST 2022

Account for Snapchat has been removed!

Sat Nov 26 14:35:49 PST 2022

Account for SSC has been removed!

- The log also prints in the same way when a file is loaded with accounts already saved in the Password Manager. 
This occurs as even though those accounts were already added, they get logged as being newly added accounts because 
eventually the method, addAccount(Account account), is being called from gui through json reader again.


## **Phase 4: Task 3**
- If I had more time, I would definitely refactor my project, specifically the PasswordManagerGui class, so that it 
follows the single responsibility principle and to increase cohesion in it.

For example, I would:
- make separate classes for each idea such as the table that I add to my frame
- make separate class which creates the Adding/Removing passwords Label and Text Fields
- make a class for the Count label
- make a class for creating the menu bar with load and save options


By doing so, this will increase cohesion as there will be more classes and each for a single responsibility.
This will improve the readability of my code as everything will be separated into smaller classes and not what 
I currently have which is one class holding everything. Increasing cohesion will make it easier to locate code
related to something if a problem occurs in my program. In addition, some of my functionality was similar to my 
original console application, so making separate classes for that would have helped to re-use code.


Looking at my UML Design Diagram, in order to achieve low coupling, I would remove the association in the
PasswordManagerGui class with the Account class that I added because there is no point of having a field for it that is
accessible to other methods too when it does not need to. It does not get instantiated in the constructor and is 
only used in the addPassword() method. What I would do instead now is to just create a local variable in the method.
This way, no other method can access it. It also was not a correct way to have a field since I also added a field for
Password Manager, thus, even through the local variable, an account can be added to it. 

# BANKING APP FINAL PROJECT

Welcome to your online bank. Next, we will explain all the functionalities that you have at your fingertips.

- If you are not registered, you will need to **register first**. To do this, you must enter a username, email and password. ***Remember that if you are an administrator, your email must contain @admin.***

# ***USER***

- To access your bank, once registered you must **log in** with your username and password. Once inside, you will be able to access your user space. There you can see in **TOTAL BALANCE** the total balance of all your accounts. In addition, you will be able to **access your different types of accounts: Savings, Checkings or CreditCards**.

- As a user, you can also **carry out transactions**. ***Remember that the account must be in your name***

-You can access your profile on the page **profile**

# ***ADMINISTRATOR***

- Once you are registered as an administrator, you will be able to access your space. To do this, you will need to **log in with** your username and password.

- You can create accounts for registered bank users. In this way, you will be able to **create accounts of the type Saving, Checking and Credit Card**. Each account has some particularities that it must comply with. Our application will remind you what they are.

- You can access a **list of all bank accounts and delete** the account you deem necessary.

- You will be able to **access to all the transactions** that have been carried out.

- You can **update the balance of an account** by providing its id.

-You can access your profile on the page **profile**.

# ***ACCOUNT SPECIFICATIONS***

-**Savings**: 
 
    - Required: **secretKey**
    
    -  **Minimum Balance** of the account: it must be greater than 100 and less than 1000. Otherwise, the default value will be 1000
    
    -  Default Status will be **ACTIVE**
    
    -  Interest Rate will always be **0.1**

-**Checkings**: 

    - Required: **secretKey**
    
    - **Minimum Balance** of the account will always be 250
    
    - **Monthly Maintenance Fee** will always be 12
    
    - Default Status will be **ACTIVE**
    
-**CreditCards**:
 
    - **Credit Limit**: it must be greater than 100000 and less than 100. Otherwise, the default value will be 100
    
    - **Interest Rate**: it must be greater than 0.2 and less than 0.1. Otherwise, the default value will be 0.2

# HOW TO RUN THIS PROJECT:

  1) Download this project

  2) Run the **eureka-server** project first with the command **mvn spring-boot: run**

  3) **Create your database** from **Micro and Edge project**. To do this, you must enter your username and password in the properties of both projects. With the command mvn spring-boot: run, run the micro project. The last project to run must be Edge project. Now the two services are in Eureka.
  
  4) Please, remember to introduce first of all in the role table the following;

![p1](https://github.com/julialaria/Banking-App-Project/blob/main/role.JPG?raw=true)
  
  

  5) Run the **angular project with the ng serve command**.

  6) Once the 4 projects are running, you will be able to access your online bank, probably at your localhost: 4200

 ***A robust security system through tokens has been used for this project. Thanks to this, not only can we register and log in to the database but we can also keep our session started and access our data (accounts, profile ...)***
 

    

 

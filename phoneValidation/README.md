##Spring boot Phone Validation application

Spring boot application using SQLite 3 to list and categorize country phone numbers based on Country and phone number (valid or not valid).

---------- 
###Requirements 
- Java 8

###Guidance

####Application url :
    http://localhost:8080/jumia
#### Requests
- **GET** `/customer`
  - Get all customers
- **GET** `/customer?countryName=`
  - Get All customer with country name 
``ex: countryName=Morocco``
- **GET** `/customer?countryName=&state=`  
  - Get all customer with country name and phone validation state``ex: countryName=Morocco&state=valid``
  

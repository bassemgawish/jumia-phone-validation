# jumia-phone-validation

A single page application in Java using SQLite 3 to list and categorize country phone numbers based on Country and phone number (valid or not valid).

----------

### Framework used:

- Spring boot
- Angular

### Development Requirement :

- docker
- java 1.8+
- Npm

### Guidance

#####Step-1:  build the spring boot  docker images using command
`docker build -t spring-phone-validation  ./phoneValidation/`
#####Step-2:  build angular application docker image using command
`docker build -t angular-phone-validation ./phoneValidation-angular/`

#####Step-3:  create docker container
`docker compose -f docker-compose.yaml up`

#####Step-4: open the application:
open the browser and type the following url `http://localhose:4200/`

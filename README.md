# currency-cloud-test

INSTRUCTIONS ON HOW TO RUN THESE TESTS

***This is a test framework created using Java & Cucumber ***

***Dependancies are added to the project via maven ***

***Java is required to run these tests ***

1) Clone project
2) To run the tests via the command line, cd to the root of the project and run:

-- mvn test

***Notes***

When asserting a non 20* response, I am just checking for an expected response code and not a specific error message in the response.

In the entire project I am only retrieving the value of auth_token / currency_pair / client_buy_amount / client_rate / core_rate.... Therefore, instead of creating extra classes for getter & setters, I decided to retrieve the values I needed from the jSON within the class for the relevant service in order to cut down on the number of classes created for this project.

If I needed to have retrieved any values of nested keys then I would have added jackson to my pom and used that for retrieving values from the jSON.

I used Pico dependancy injection to access classes within my step defs for ease of use.

If this was a bigger project then id probably use more classes for seperation of certain variables such as base_endpoint etc. I just wanted to 

I wasnt sure how many negative test scenarios to add so I added 3... 

1) Calling get_rate enpoint without an auth_token
2) Calling get_rate enpoint with an ivalid amount format
3) Calling get_rate enpoint with both currency types set to GBP

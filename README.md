# TestVagrant
Temperature comparison tests

This Project contains two tests
1. Compare the current temperature displayed on UI with the one received via service
2. Variance check - if the difference between the temperature is not between 0-2 then a custom exception will be thrown.

#Please make sure we are running the test on JDK 8
#Step1 : Checkout
#Step2 : mvn clean install -DskioTests=true
#Step3 : mvn test

#Results : Open emailable report in browser from location target\surefire-reports\emailable-report

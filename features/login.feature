Feature: User Login
    Scenario: Successful login with valid credentials        Given the user is on the QAFox login page        When the user enters valid credentials (username: "anikettraining@gmail.com", password: "test@1234")        And the user clicks the login button        Then the user should be redirected to the dashboard page
        
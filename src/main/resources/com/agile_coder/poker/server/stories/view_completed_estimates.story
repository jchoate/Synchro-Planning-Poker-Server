Viewing the completed estimates
Given I have a user id and a session id for an ongoing session
And all of the estimates have been submitted
And the admin has unlocked the estimates
When I request the estimate list
Then I receive the list of everyone's estimates
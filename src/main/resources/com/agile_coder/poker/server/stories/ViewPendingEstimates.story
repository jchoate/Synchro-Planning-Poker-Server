Viewing the pending estimates
Given I have a user id and a session id for an ongoing session
And not all of the estimates have been submitted
When I request the estimate list
Then I receive the list of everyone and whether they have submitted or not
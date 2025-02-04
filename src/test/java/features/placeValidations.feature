Feature: Validating place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using Add place API
Given Add place payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples:
	|name   | language | address 					 |
	|AAHouse| English  | World Cross Center|
#	|BBHouse| Spanish  | Sea cross center  |


@DeletePlace @Regression
Scenario: Verify if delete place functionality is working or not.

Given deletePlace payload
When user calls "DeletePlaceAPI" with "post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
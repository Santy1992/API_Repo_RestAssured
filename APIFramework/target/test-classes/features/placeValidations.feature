Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "Post" Http request
	Then the API call is success with Status Code 200
	And "status" in response body is "OK"
	And "Scope" in response body is "App"
	And verify placeID created maps to "<name>" using "getPlaceAPI"
	
Examples: 
	| name    | language | address |
	| AAHouse | English  | World Cross Center |
#	| BBHouse | Spanish  | Sea World          |
@DeletePlace
Scenario: Verify if Delete Place functionality is working
	Given DeletePlace Payload
	When user calls "DeletePlaceAPI" with "Post" Http request
	Then the API call is success with Status Code 200
	And "status" in response body is "OK"
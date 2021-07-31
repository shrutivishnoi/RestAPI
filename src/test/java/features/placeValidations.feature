Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if place is successfully being added using addPlaceAPI
Given All request Payload with <accuracy> "<address>" "<language>" "<name>" "<phone>" "<website>"
When user calls "addPlaceAPI"  with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"
Examples:
| accuracy | address | language | name | phone | website |
| 50 | 29, side layout, cohen 09 | French-IN | shruti house | (+91) 983 893 3937 | http://google.com |


@DeletePlace
Scenario: Verify if delete place API is working fine
Given Delete place payload
When user calls "deletePlaceAPI"  with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"



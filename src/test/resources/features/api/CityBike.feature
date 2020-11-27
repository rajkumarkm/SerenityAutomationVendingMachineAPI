@CityBike @Regression
Feature: City Bike
  Scenario: As a user I want to verify that the city, country and return their corresponded latitude and longitude are displayed correctly
    Given I query the citybike network endpoint
    Then I can verify the below details in
      | City      | Country | Latitude | Longitude |
      | Frankfurt | Germany | 50.1072  | 8.66375   |

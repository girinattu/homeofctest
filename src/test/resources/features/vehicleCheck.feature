Feature: Vehicle check with the DVLA and assert on the make and colour of the vehicle
  CSV and XLSX files are read from the /src/main/resources/testData folder

  Scenario: Check the vehicle make and colour
    Given I navigate to the dvla page
    And start the vehicle check
    And verify all the vehicles in 'xlsx,csv' files from the list of files
    Then print out the results to a file

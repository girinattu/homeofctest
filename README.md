HomeOffice Test
To run the test:
mvn clean test -Dtest=testRunner test -Dbrowser=CHROME

Test Data:
All the various types of files are under 
src/main/resources/testData
There are 2 xlsx and 2 csv files with valid and invalid Regs.
Also some do not have the right colour or make for the Regs.

service:
FileInfoService:

Scans the testData (hardcoded for now - but can be pushed into the config or as a env variable) folder
Returns a map of extensions with a list of FileInfo (Model)
FileInfo is a model with the mimeType, Extension, FileSize & FileName
viz. we get csv with a list of 2 FileInfo Objects
same for xlsx, png etc.

VehicleService:
Got 2 methods - One to read CSV & other for Excel
readFile returns the list of vehicles for the list of extensions we supply
At the moment it will work only for csv & xlsx types.
Other types are unsupported and appropriate errors are written into the logs [slf4j is used]
If we supply a list of extensions as csv & xlsx, we get 12 Vehicle objects in a list

BrowserFactory:
Webdriver is initiated here using io.github.bonigarcia - Webdriver manager.

Pages - Elements are in appropriate page classes - startdvla, VehicleCheck and VehicleConfirm
The elements are objects of By type.

Actions:
All the actions on the pages are under the appropriate action classes
BaseActions - > All the pages,  Webdriver and webdriverwait are initialised here.
startdvla page -> DVLAStart - to click on the start button
CheckVehicle -> vehiclecheck and confirm
checkRegNumber - takes in list of file extensions and returns the result of running for all the regs.
For a Pass - the screenshots -> Pass_Screenshots - FileName - RegNumber
For a Fail - the screenshots -> Fail_Screenshots ->FileName - RegNumber
A final file is written under results.txt

Results of the run:
results.txt
Pass_screenshots & Fail_screenshots are attached.

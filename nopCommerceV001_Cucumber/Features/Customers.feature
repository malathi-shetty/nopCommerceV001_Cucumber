Feature: Cutomers

	Background: Below are the common steps for each scenario
			Given User Launch Chrome Browser
			When User opens URL "https://admin-demo.nopcommerce.com/login"
			And User enters Email as "admin@yourstore.com" and Password as "admin"
			And Click on Login
			Then User can view Dashboard
			When User click on customers Menu
			And click on customers Menu Item

@smoke
Scenario: Add a new Customer
	And click on Add new button
	Then User can view Add new customer page
	When User enter customer info
	And click on Save button
	Then User can view confirmation message "The new customer has been added successfully."
	And Close Browser
	
	@regression
Scenario: Search Customers by EmailID
	And Enter customer Email
	When Click on Search button
	Then User should find Email in the Search table
	And Close Browser
	
	@regression	
Scenario: Search Customers by Name
	And Enter customer FirstName
	And Enter customer LastName
	When Click on Search button
	Then User should find Name in the Search table
	And Close Browser
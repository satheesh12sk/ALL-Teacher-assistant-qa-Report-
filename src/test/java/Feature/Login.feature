Feature: Validate the login functionality

Scenario Outline: Validate login with valid & Invalid credentials

Given Open the brower
When Go to url 
And Enter the username as '<username>'
And Enter the password as '<password>'
And Click login Button 
Then Verify the login message



Examples: 
|username|password|
|demoteacher1@gmail.com|zxcASD123@!$|
|demoteacher1@gmail.co|zxcASD123@!$|
|demoteacher1@gmail.com|zxcASD123@!|
|demoteacher1@g|zxcASD123@!$|
||zxcASD123@!$|
|demoteacher1@gmail.com||
|demoteacher1@gmail.com|zxc|
|||
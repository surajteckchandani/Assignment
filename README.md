This is spring boot application 

Technologies implemented 
1) Spring boot application version=v1.4.7.RELEASE
2) JSP 
3) Jquery
4) CSS
5) Mysql
6) Hibernate for business objects
7) REST API

To start project follow the following steps
1) add database username and password in application.properties
2) Import project to STS IDE
3) create Database with Schema name "assignment" 
4) Execute INSERT INTO `authority` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN'); statement before you register a user.
5) Database table will be auto generated, but for your convinince execute sql dump file in folder src/main/resources/application.sql 
6) To run the application you need to run application as "Spring Boot App"
7) Go to http://localhost:8080/

For REST API 
1) First you will need api_key, to generate key you will need to login to application and go to "Generate Api" tab and click on "Generate API Key" and you will get key that is your api_key used to authenticate.

2) To get Long URl 
URL : http://localhost:8080/rest/getLongUrl
Header: 
	api_key : Provide your api key
Body :
	{
	  "shortUrl": "SHORT URL"
	}	
Sample Response : 
		{
		    "longUrl": "LONG URL",
		    "shortUrl": "SHORT URL"
		}	
3) To get Short URl 
URL : http://localhost:8080/rest/getShortUrl
Header: 
	api_key : Provide your api key
Body :
	{
	  "longUrl": "Long URL"
	}
Sample Response : 
		{
		    "longUrl": "LONG URL",
		    "shortUrl": "SHORT URL"
		}
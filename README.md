# inventory-application
Code Challenge from Contineo

JDK: OpenJdk 11
----------------

Limitation:
------------
- No permission check, anybody can create, update the inventory record
- Not too much validation between category and sub-category
- Cannot make application with docker-compose, need to start separately
- No swagger document to make the restful call easier 

Guide to Run
-------------
- Run `docker-compose up` to start the postgres database with 5432 port
- Run `App.java`
- Sample request curl
```
curl --location --request GET 'http://localhost:8080/categories' \
--header 'content-type: application/json' \
--data-raw '{"id": 12, "name": "Food"}'
```
# What's it about?
A basic library management platform backend, using Java's Springboot framework.  


## Features exposed:

* Create Borrower
* Create Library Manager
* Login & Logout with session storage for both Borrower and Library Manager
* Add/Update/Delete Book Genre
* Add/Update/Delete Book with Author, Genres and Publisher details
* Fetch Books
* Fetch Genres
* Create/Renew/Cancel Library Membership
* Issue Books
* Request/Approve/Reject Book Issuance

## SpringBoot Components used:

* REST Controllers
* Interceptors
* Global Exceptions handlers
* Swagger notations
* Request Payload validators
* SpringBoot Convertors
* JPA with annotations for table indices, constraints and relationships.

#### TODOs
* Unit test cases
* Integration test cases
* End-to-End test cases
* Use MD5 Hash while storing Password and Session Token into DB

# Flow diagram for Services with tables used:

![](https://github.com/Atypical3991/library-management-platform/blob/main/libary%20maangement%20platform.png)

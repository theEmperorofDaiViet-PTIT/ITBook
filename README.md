<a name="readme-top"></a>
<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#key-features">Key Features</li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<p align="center">
    <img src="/src/main/resources/static/images/icon.png" width="128" height="128">
</p>

# About The Project
An simple bookstore web application selling programming books.

## Built With
* [![Java][Java-shield]][Java-url]
* [![Spring][Spring-shield]][Spring-url]
* [![Apache Maven][Apache Maven-shield]][Apache Maven-url]
* [![Apache Tomcat][Apache Tomcat-shield]][Apache Tomcat-url]
* [![HTML5][HTML5-shield]][HTML5-url]
* [![CSS3][CSS3-shield]][CSS3-url]
* [![Thymeleaf][Thymeleaf-shield]][Thymeleaf-url]
* [![MySQL][MySQL-shield]][MySQL-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

# Getting Started

## Prerequisites
Before cloning and using this application, you'll need to install these things on your computer:
* [Java SE 8](https://www.oracle.com/java/technologies/javase/javase8u211-later-archive-downloads.html): Of course you need to have Java installed to run a Java application. I used the widely-used Java SE 8 (JDK 1.8), but I think it works fine with other versions, as well.
* [Spring Tool Suite 4](https://spring.io/tools): an Eclipse-based IDE to develop Spring applications. 
It provides a ready-to-use environment to implement, run, deploy, and debug the application. 
It validates your application and provides quick fixes for the applications.
* [MySQL 8.0](https://dev.mysql.com/downloads/installer/): an open source relational database management system that was originally released in 1995. MySQL is popular among all databases, and is ranked as the 2nd most popular database, only slightly trailing Oracle Database. Among open source databases, MySQL is the most popular database in use today and known as one of the most reliable and performative databases out there.

## Installation
You can install this application by cloning this repository into your current working directory:
```sh
git clone https://github.com/theEmperorofDaiViet-PTIT/ITBook.git
```
After cloning the repository, you can open the project by Spring Tool Suite.

Open the application.properties file in [/src/main/resources](/src/main/resources) to change the information about the datasource to fit your own settings.

Open MySQL and create a database according to the script in finalDatabase.sql file in [/src/main/resources](/src/main/resources).

Finally, back to Spring Tool Suite and run the application.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

# Key Features
- Product page
* Log in/ Log out
- Cart (in all case: Unregistered User, Registered User but added items to Cart before logging in, Registered User logged in)
* Place an Order using Cart

- Roles & their functionality:
  - Unregistered User: can place an Order without registering an account, but must provide information
  - ROLE_CUSTOMER: can see all its Orders
  - ROLE_EMPLOYEE: can see all Orders of all Users
  - ROLE_MANAGER: can see all Orders of all Users; create, update, delete Products
  
* Register (CUSTOMER only)
- Validate input
* Online support (by Facebook Messenger's Chat Plugin)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

# Usage

<p align="right">(<a href="#readme-top">back to top</a>)</p>

# Contact

You can contact me via:
* [![GitHub][GitHub-shield]][GitHub-url]
* [![LinkedIn][LinkedIn-shield]][LinkedIn-url]
* ![Gmail][Gmail-shield]:&nbsp;<i>Khiet.To.05012001@gmail.com</i>
* [![Facebook][Facebook-shield]][Facebook-url]
* [![Twitter][Twitter-shield]][Twitter-url]

<br/>
<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- Tech stack -->
[Java-shield]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white
[Java-url]: https://www.java.com/
[Spring-shield]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/
[Apache Maven-shield]: https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white
[Apache Maven-url]: https://maven.apache.org/
[Apache Tomcat-shield]: https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black
[Apache Tomcat-url]: https://tomcat.apache.org/
[HTML5-shield]: https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white
[HTML5-url]: https://www.w3.org/html/
[CSS3-shield]: https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white
[CSS3-url]: https://www.w3.org/Style/CSS/
[Thymeleaf-shield]: https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white
[Thymeleaf-url]: https://www.thymeleaf.org/
[MySQL-shield]: https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white
[MySQL-url]: https://www.mysql.com/

<!-- Contact -->
[GitHub-shield]: https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white
[GitHub-url]: https://github.com/theEmperorofDaiViet
[LinkedIn-shield]: https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white
[LinkedIn-url]: https://www.linkedin.com/in/khiet-to/
[Gmail-shield]: https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white
[Facebook-shield]: https://img.shields.io/badge/Facebook-%231877F2.svg?style=for-the-badge&logo=Facebook&logoColor=white
[Facebook-url]: https://www.facebook.com/Khiet.To.Official/
[Twitter-shield]: https://img.shields.io/badge/Twitter-%231DA1F2.svg?style=for-the-badge&logo=Twitter&logoColor=white
[Twitter-url]: https://twitter.com/KhietTo
create database itbookbeta;
use itbookbeta;

create table ACCOUNTS
(
    USERNAME varchar(16) not null,
    PASSWORD varchar(128) not null,
    USER_ROLE varchar(16) not null,
    ACTIVE bit not null,
    NAME varchar(255) not null,
    EMAIL varchar(128) not null,
    PHONE varchar(128) not null,
    ADDRESS varchar(255) not null,
    CREATED_AT datetime not null,
    primary key (USERNAME)
);

create table PRODUCTS
(
	ID varchar(20) not null,
    TITLE varchar(255),
    IMAGE longblob,
    CATEGORY_ID varchar(20) not null,
    DESCRIPTION text not null,
    LANGUAGE varchar(255) not null,
    NUM_PAGES int not null,
    PRICE double precision not null,
    DISCOUNT_ID int,
    CREATED_AT datetime not null,
    primary key (ID)
);

create table PRODUCT_INVENTORIES
(
	ID varchar(50) not null,
    PRODUCT_ID varchar(50) not null,
    QUANTITY int not null,
    primary key (ID)
);

create table CATEGORIES
(
	ID varchar(50) not null,
    NAME varchar(255) not null,
    DESCRIPTION text not null,
	primary key (ID)
);

create table AUTHORS
(
	ID varchar(50) not null,
    NAME varchar(255) not null,
    primary key (ID)
);

create table PRODUCT_AUTHOR
(	
	ID varchar(50) not null,
	PRODUCT_ID varchar(50) not null,
    AUTHOR_ID varchar(50) not null,
    primary key (ID)
);

create table DISCOUNTS
(
	ID varchar(50) not null,
    NAME varchar(255) not null,
    DESCRIPTION text not null,
    DISCOUNT_PERCENT int not null,
    primary key (ID)
);
-- _________________________________________________________________
create table ORDERS
(
  ID varchar(50) not null,
  ORDER_NUM int not null,
--  PAYMENT_ID varchar(50) not null,
  TOTAL double precision not null,
  CUSTOMER_NAME varchar(255) not null,
  CUSTOMER_EMAIL varchar(128) not null,
  CUSTOMER_PHONE varchar(128) not null,
  CUSTOMER_ADDRESS varchar(255) not null,
  CREATED_AT datetime not null,
  primary key (ID)
) ;
alter table ORDERS
  add constraint ORDER_UK unique (ORDER_NUM) ;

create table ORDER_DETAILS
(
  ID varchar(50) not null,
  ORDER_ID varchar(50) not null,
  PRODUCT_ID varchar(20) not null,
  PRICE double precision not null,
  QUANTITY int not null,
  SUBTOTAL double precision not null,
  primary key (ID)
) ;
alter table ORDER_DETAILS
  add constraint ORDER_DETAIL_ORD_FK foreign key (ORDER_ID)
  references ORDERS (ID);
alter table ORDER_DETAILS
  add constraint ORDER_DETAIL_PROD_FK foreign key (PRODUCT_ID)
  references PRODUCTS (ID);
  
create table REGISTEREDORDERS
(
  ID varchar(50) not null,
  ORDER_NUM int not null,
--  PAYMENT_ID varchar(50) not null,
  TOTAL double precision not null,
  ACCOUNT_USERNAME varchar(50) not null,
  CREATED_AT datetime not null,
  primary key (ID)
) ;
alter table REGISTEREDORDERS
  add constraint REGISTEREDORDER_UK unique (ORDER_NUM) ;
alter table REGISTEREDORDERS
  add constraint REGISTERED_ORDER_ACC_FK foreign key (ACCOUNT_USERNAME)
  references ACCOUNTS (USERNAME);

create table REGISTEREDORDER_DETAILS
(
  ID varchar(50) not null,
  REGISTEREDORDER_ID varchar(50) not null,
  PRODUCT_ID varchar(20) not null,
  PRICE double precision not null,
  QUANTITY int not null,
  SUBTOTAL double precision not null,
  primary key (ID)
) ;
alter table REGISTEREDORDER_DETAILS
  add constraint REGISTEREDORDER_DETAIL_RORD_FK foreign key (REGISTEREDORDER_ID)
  references REGISTEREDORDERS (ID);
alter table REGISTEREDORDER_DETAILS
  add constraint REGISTEREDORDER_DETAIL_PROD_FK foreign key (PRODUCT_ID)
  references PRODUCTS (ID);

create table PAYMENTS
(
	ID varchar(50) not null,
    TYPE varchar(128) not null,
    PROVIDER varchar(255),
    ACCOUNT_NO int,
    TOTAL double precision not null,
    CREATED_AT datetime not null,
    primary key (ID)
);
-- ____________________________________________________________________________________

-- ____________________________________________________________________________________

insert into Accounts (USERNAME, ACTIVE, PASSWORD, USER_ROLE, NAME, EMAIL, PHONE, ADDRESS, CREATED_AT)
values ('employee1', 1,
'$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 'ROLE_EMPLOYEE', 'employee1', 'employee1@outlook.com','0123456789','Ho Chi Minh City',sysdate());

insert into Accounts (USERNAME, ACTIVE, PASSWORD, USER_ROLE, NAME, EMAIL, PHONE, ADDRESS, CREATED_AT)
values ('manager1', 1,
'$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 'ROLE_MANAGER', 'manager1', 'manager1@outlook.com','0123456789','Ha Noi',sysdate());

-- _____________________________________________________________________________________

insert into products (ID, TITLE, PRICE,  CATEGORY_ID, DESCRIPTION, LANGUAGE, NUM_PAGES, CREATED_AT)
values ('JAV001', 'Introduction to Java Programming and Data Structures,', 79.66, 'JAV', 'Introduction to Java Programming and Data Structures helps you build a strong understanding of basic programming concepts and techniques before tackling advanced Java skills. Learn programming from a problem solving (versus syntax) perspective by completing practice exercises with varying levels of difficulty.', 'English', 359, sysdate());

insert into products (ID, TITLE, PRICE,  CATEGORY_ID, DESCRIPTION, LANGUAGE, NUM_PAGES, CREATED_AT)
values ('JAV002', 'Murach’s Java Programming', 52.50, 'JAV', 'This is the 4th Edition of our best-selling core Java book. Since 2001, it has been used by thousands of beginning and experienced programmers to master the core language skills that are needed to create console, web, and mobile applications. Now, to make training even easier, this book shows you how to develop Java programs using NetBeans, a popular IDE that will boost your productivity at every step.', 'English', 802, sysdate());

insert into products (ID, TITLE, PRICE,  CATEGORY_ID, DESCRIPTION, LANGUAGE, NUM_PAGES, CREATED_AT)
values ('JAV003', 'Murach’s Java Servlets and JSP', 57.50, 'JAV', 'From the start, servlets and JSPs have been a training problem because web programming with them requires so many different skills and so much conceptual background. But the first edition of this book solved that training problem: It presented the critical skills in the right order with plenty of examples, allowing developers to master all the complexities in a manageable way. Now, this improved and updated 3rd Edition makes it even easier and faster for you to learn.', 'English', 744, sysdate());

insert into products (ID, TITLE, PRICE,  CATEGORY_ID, DESCRIPTION, LANGUAGE, NUM_PAGES, CREATED_AT)
values ('JAV004', 'Java Database Best Practices', 39.95, 'JAV', 'When creating complex Java enterprise applications, do you spend a lot of time thumbing through a myriad of books and other resources searching for what you hope will be the API that is right for the project at hand? Java Database Best Practices rescues you from having to wade through books on each of the various APIs before figuring out which method to use! This comprehensive guide introduces each of the dominant APIs (Enterprise JavaBeans, Java Data Objects, the Java Database Connectivity API (JDBC) as well as other, lesser-known options), explores the methodology and design components that use those APIs, and then offers practices most appropriate for different types and makes of databases, as well as different types of applications.', 'English', 264, sysdate());

insert into products (ID, TITLE, PRICE,  CATEGORY_ID, DESCRIPTION, LANGUAGE, NUM_PAGES, CREATED_AT)
values ('CPP001', 'Learn C the Hard Way', 37.49, 'CPP', 'Zed Shaw has crafted the perfect course for the beginning C programmer eager to advance their skills in any language. Follow it and you will learn the many skills early and junior programmers need to succeed–just like the hundreds of thousands of programmers Zed has taught to date! You bring discipline, commitment, persistence, and experience with any programming language; the author supplies everything else.', 'English', 696, sysdate());

insert into products (ID, TITLE, PRICE,  CATEGORY_ID, DESCRIPTION, LANGUAGE, NUM_PAGES, CREATED_AT)
values ('CPP002', 'C++ Programming Language', 63.99, 'CPP', 'The new C++11 standard allows programmers to express ideas more clearly, simply, and directly, and to write faster, more efficient code. Bjarne Stroustrup, the designer and original implementer of C++, has reorganized, extended, and completely rewritten his definitive reference and tutorial for programmers who want to use C++ most effectively.', 'English', 720, sysdate());

insert into products (ID, TITLE, PRICE,  CATEGORY_ID, DESCRIPTION, LANGUAGE, NUM_PAGES, CREATED_AT)
values ('CPP003', 'Murach’s C++ Programming', 54.50, 'CPP', 'In the beginning, C++ was a hard language to learn because it required programmers to master low-level techniques to work with memory. Over the years, C++ has evolved to provide higher-level techniques that make it much easier to write effective code. But most C++ books haven’t evolved with the language. Until now. Now, this book uses modern C++ to get you off to a fast start, and then builds out your coding and OOP skills to the professional level. At that point, it also covers older techniques so you’ll be able to maintain the vast amount of legacy code that’s out there, as well as work with embedded systems that don’t support the newer techniques.', 'English', 774, sysdate());

insert into products (ID, TITLE, PRICE,  CATEGORY_ID, DESCRIPTION, LANGUAGE, NUM_PAGES, CREATED_AT)
values ('CPP004', 'Effective Modern C++', 47.62, 'CPP', 'Coming to grips with C++11 and C++14 is more than a matter of familiarizing yourself with the features they introduce (e.g., auto type declarations, move semantics, lambda expressions, and concurrency support). The challenge is learning to use those features effectively—so that your software is correct, efficient, maintainable, and portable. That’s where this practical book comes in. It describes how to write truly great software using C++11 and C++14—i.e. using modern C++.', 'English', 420, sysdate());

insert into products (ID, TITLE, PRICE,  CATEGORY_ID, DESCRIPTION, LANGUAGE, NUM_PAGES, CREATED_AT)
values ('PYT001', 'Learn Python the Hard Way', 42.99, 'PYT', 'Zed Shaw has perfected the world is best system for learning Python. Follow it and you will succeed-just like the hundreds of thousands of beginners Zed has taught to date! You bring the discipline, commitment, and persistence; the author supplies everything else.', 'English', 457, sysdate());

insert into products (ID, TITLE, PRICE,  CATEGORY_ID, DESCRIPTION, LANGUAGE, NUM_PAGES, CREATED_AT)
values ('PYT002', 'Python Crash Course', 29.99, 'PYT', 'The best-selling Python book in the world, with over 1 million copies sold! A fast-paced, no-nonsense, updated guide to programming in Python.', 'English', 330, sysdate());

insert into products (ID, TITLE, PRICE,  CATEGORY_ID, DESCRIPTION, LANGUAGE, NUM_PAGES, CREATED_AT)
values ('PYT003', 'Murach’s Python Programming', 45.99, 'PYT', 'Python is one of today’s fastest-growing languages. Its simple syntax makes it relatively easy to learn, and its diverse uses…from web and game programming to data analysis and data mining to scientific computing, artificial intelligence, and more!...are fueling its popularity. So we had to answer the clamor for a Murach book on Python. Now, we believe it provides the fastest, easiest, yet most professional way to learn Python that you can find, whether you’re a beginning programmer or have years of experience.', 'English', 576, sysdate());

insert into products (ID, TITLE, PRICE,  CATEGORY_ID, DESCRIPTION, LANGUAGE, NUM_PAGES, CREATED_AT)
values ('PYT004', 'Python in a Nutshell', 51.10, 'PYT', 'Useful in many roles, from design and prototyping to testing, deployment, and maintenance, Python is consistently ranked among today’s most popular programming languages. The third edition of this practical book provides a quick reference to the language—including Python 3.5, 2.7, and highlights of 3.6—commonly used areas of its vast standard library, and some of the most useful third-party modules and packages.', 'English', 480, sysdate());

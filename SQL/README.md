### Data Modelling

###### Exercise 1

```SQL
CREATE TABLE cd.members (
    memid int PRIMARY KEY
    surname varchar(200),
    firstname varchar(200),
    address varchar(300),
    zipcode int,
    telephone varchar(20),
    recommendedBy int FOREIGN KEY REFERENCES cd.members(memid),
    joindate TIMESTAMP
);

CREATE TABLE cd.bookings (
    facid int PRIMARY KEY FOREIGN KEY REFERENCES cd.facilities(facid),
    memid int FOREIGN KEY REFERENCES cd.members(memid),
    starttime timestamp,
    slots int
);

CREATE TABLE cd.facilities (
    facid int PRIMARY KEY,
    name varchar(100),
    membercost float(2),
    guestcost float(2),
    initialoutlay float(2),
    monthlymaintenance float(2)
);
```

### Practice SQL Queries

###### insert

```SQL
INSERT INTO cd.facilities
VALUES (9, 'Spa', 20, 30, 100000, 800);
```

###### insert3

```SQL
INSERT INTO cd.facilities
    SELECT MAX(facid) + 1, 'Spa', 20, 30, 100000, 800
    FROM cd.facilities;
```

OR

```SQL
INSERT INTO cd.facilities (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
SELECT (SELECT MAX(facid) FROM cd.facilities) + 1, 'Spa', 20, 30, 100000, 800;
```

###### update

```SQL
UPDATE cd.facilities
SET initialoutlay = 10000
WHERE name = 'Tennis Court 2';
```

###### updatecalculated

```SQL
UPDATE cd.facilities
SET membercost = (SELECT membercost 
				 FROM cd.facilities 
				 WHERE name = 'Tennis Court 1') * 1.1,
	guestcost = (SELECT guestcost
				FROM cd.facilities
				WHERE name = 'Tennis Court 1') * 1.1
WHERE name = 'Tennis Court 2';
```

OR

```SQL
UPDATE cd.facilities facs
    SET
        membercost = (SELECT membercost * 1.1 FROM cd.facilities WHERE facid = 0),
        guestcost = (SELECT guestcost * 1.1 FROM cd.facilities WHERE facid = 0)
    WHERE facs.facid = 1;
```

###### delete

```SQL
DELETE FROM cd.bookings;
```

OR

```SQL
TRUNCATE cd.bookings;
```

###### deletewh

```SQL
DELETE FROM cd.members
WHERE memid = 37;
```

###### classify

```SQL
SELECT name,
CASE
	WHEN monthlymaintenance > 100 THEN 'expensive'
	ELSE 'cheap'
END AS cost
FROM cd.facilities;
```

###### union

```SQL
SELECT surname FROM cd.members
UNION
SELECT name FROM cd.facilities;
```

###### simplejoin

```SQL
SELECT starttime
FROM cd.bookings
INNER JOIN cd.members ON cd.bookings.memid=cd.members.memid
WHERE cd.members.firstname='David' AND cd.members.surname='Farrell';
```

###### simplejoin2

```SQL
SELECT starttime AS start, name
FROM cd.bookings
INNER JOIN cd.facilities ON cd.bookings.facid=cd.facilities.facid
WHERE starttime >= '2012-09-21' AND starttime < '2012-09-22' AND name LIKE 'Tennis Court%'
ORDER BY starttime;
```

###### self

```SQL
SELECT DISTINCT A.firstname, A.surname
FROM cd.members A
INNER JOIN cd.members B ON A.memid=B.recommendedby
ORDER BY surname, firstname;
```

###### self2

```SQL
SELECT mem.firstname AS memfname, mem.surname AS memsname, rec.firstname AS recfname, rec.surname AS recsname
FROM cd.members mem
LEFT OUTER JOIN cd.members rec ON rec.memid=mem.recommendedby
ORDER BY mem.surname, mem.firstname;
```

###### sub

```SQL
SELECT DISTINCT CONCAT(firstname, ' ', surname) AS member, (
	SELECT CONCAT(firstname, ' ', surname) AS recommender
	FROM cd.members AS recommenders
	WHERE recommenders.memid = members.recommendedBy)
FROM cd.members AS members
ORDER BY member;
```

###### count3

```SQL
SELECT recommendedby, COUNT(*)
FROM cd.members
WHERE recommendedby IS NOT NULL
GROUP BY recommendedby
ORDER BY recommendedby;
```

###### fachours

```SQL
SELECT facid, SUM(slots) AS "Total Slots"
FROM cd.bookings
GROUP BY facid
ORDER BY facid;
```

###### fachoursbymonth

```SQL
SELECT facid, SUM(slots) AS "Total Slots"
FROM cd.bookings
WHERE starttime >= '2012-09-01 00:00:01'
AND starttime <= '2012-09-30 23:59:59'
GROUP BY facid
ORDER BY "Total Slots";
```

###### fachoursbymonth2

```SQL
SELECT facid, EXTRACT(MONTH FROM starttime) AS month, SUM(slots) AS "Total Slots"
FROM cd.bookings
WHERE EXTRACT(YEAR FROM starttime) = 2012
GROUP BY facid, month
ORDER BY facid, month;
```

###### members1

```SQL
SELECT COUNT(DISTINCT memid)
FROM cd.bookings;
```

###### nbooking

```SQL
SELECT surname, firstname, cd.members.memid, MIN(starttime) AS starttime
FROM cd.bookings
LEFT JOIN cd.members ON cd.bookings.memid = cd.members.memid
WHERE starttime >= '2012-09-01 00:00:01' AND starttime <= '2012-09-30 23:59:59' 
GROUP BY cd.members.memid
ORDER BY cd.members.memid;
```

###### countmembers

```SQL
SELECT 
	COUNT(*) OVER(),
	firstname, 
	surname
FROM cd.members
ORDER BY joindate;
```

###### nummembers

```SQL
SELECT ROW_NUMBER() OVER(), firstname, surname
FROM cd.members
ORDER BY joindate;
```

###### fachours4

```SQL
SELECT facid, total
FROM (SELECT facid, SUM(slots) AS total, 
	  RANK() OVER (ORDER BY SUM(slots) DESC) AS rank
  	  FROM cd.bookings
      GROUP BY facid) AS table
WHERE rank = 1;
```

###### concat

```SQL
SELECT CONCAT(surname, ', ', firstname) AS name
FROM cd.members;
```
OR
```SQL
SELECT surname || ', ' || firstname AS name FROM cd.members 
```

###### case

```SQL
SELECT * 
FROM cd.facilities
WHERE name iLIKE 'tennis%';
```
OR
```SQL
SELECT * 
FROM cd.facilities 
WHERE UPPER(name) LIKE 'TENNIS%'; 
```

###### reg

```SQL
SELECT memid, telephone
FROM cd.members
WHERE telephone LIKE '(___) %';
```
OR
```SQL
SELECT memid, telephone
FROM cd.members 
WHERE telephone ~ '[()]';   
```

###### substr

```SQL
SELECT SUBSTRING(surname, 1, 1) AS letter, COUNT(*)
FROM cd.members
GROUP BY letter
ORDER BY letter;
```

### More Topics

###### Indexes

- How to create an index?
```SQL
CREATE INDEX [index name]
ON [table name] ([column1], [column2], ...);
```

- What are the advantages/disadvantages of an index?

Indexes allow queries to perform read operations more efficiently. However, writing operations will take longer 
because additional work needs to be done to maintain the indexes.

- What are the different kinds of indexes?

The B-tree index is the most commonly used index and is also the default index created if no specific index is 
specified. They are primarily used for comparison operations.

The Hash index can only be used for equality comparisons.

Generalized Search Tree indexes (GiST) can be used to index queries that are not indexable with B-trees.

Space-Partitioned GiST indexes (SP-GiST) supports non-balanced data structures such as quad-trees, k-d trees and
radix trees.

Generalized Inverted indexes (GIN) are used for multiple component values such as arrays. They are slower for INSERT
and UPDATE operations and so it is recommended to drop and recreate the GIN index after performing bulk operations.

The Block Range index (BRIN) was introduced with Postgres v9.5. It uses minimum and maximum values to help with
executing queries.

###### Execution Plan for query optimization

- How to show execution plan?

A query execution plan can be displayed by adding the `EXPLAIN` term at the beginning of the query before running it. 
This will show the list of operations expected to happen when the query is executed.

- What's a full scan?

A full scan is when a sequential scan is used to fetch data directly from the table. This is usually the most expensive
method to fetch data since every single row would need to be checked with the given condition.

- How do you optimize a query?

The `EXPLAIN` and `ANALYZE` terms will show the execution details of the given query including run time, loops made and
rows searched. This information give insight into which operation is taking the longest to complete. With this 
information, the query can be modified to perform queries that would be more efficient or the database could be 
modified to make certain operations perform more quickly.

### Python Apps

###### App 1: Dockerize Python Flask App

- What's Flask?

Flask is a web microframework implemented with Python. It is used in the implementation of many web applications 
including Pinterest and LinkedIn. It requires no tools or libraries and supports extensions that can add application
features.

- What's the requirements.txt?

The 'requirements.txt' file includes the version of Flask to be downloaded when running Flask from the docker image.

- What's a virtual environment in Python?

A virtual environment in Python is a self-contained directory that contains a Python installation for a specific 
version of Python along with a number of additional packages.

- How to create a docker container for a python app?

First, a docker file will need to be created using the python 3.8-slim-buster as the base image. If there are any 
tools or libraries that need to be imported, they will need to be included in the 'requirements.txt' file. Once the
docker file is written, use the `docker build` command to build the image and the `docker run` command to build the
container.
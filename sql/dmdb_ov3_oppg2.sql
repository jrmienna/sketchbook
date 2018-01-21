/*##########################################################################################*/

DROP TABLE IF EXISTS `Order`;
DROP TABLE IF EXISTS `Article`;
DROP TABLE IF EXISTS `Customer`;
DROP TABLE IF EXISTS `Postplace`;

--
-- Table structure for table Postplace
--

CREATE TABLE Postplace (
    postno      INT             NOT NULL    PRIMARY KEY,
    postplace   VARCHAR(30)     NOT NULL
);

--
-- Dumping data for table Postplace
--

LOCK TABLES Postplace WRITE;

INSERT INTO Postplace VALUES 
(9912, 'Hesseng'),
(9900, 'Kirkenes'),
(7030, 'Trondheim'),
(7034, 'Trondheim'),
(3034, 'Drammen'),
(2000, 'Lillestrom'),
(6060, 'Hareid'),
(7900, 'Rorvik');

UNLOCK TABLES;

/*##########################################################################################*/

--
-- Table structure for table Customer
--

CREATE TABLE Customer (
    cno         INT             NOT NULL    PRIMARY KEY,
    name        VARCHAR(30),
    credit      DOUBLE          NOT NULL,
    postno      INT,
 
    FOREIGN KEY (postno)
                REFERENCES Postplace(postno)
                ON DELETE SET NULL
                ON UPDATE CASCADE
);

--
-- Dumping data for table Customer
--

LOCK TABLES Customer WRITE;

INSERT INTO Customer VALUES
(1, 'Hans', 30000.0, 9912),
(2, 'John', 50000.0, 9912),
(3, 'Chris', 50000.0, 7900),
(4, 'Frank', 80000.0, 7034);

UNLOCK TABLES;

/*##########################################################################################*/

--
-- Table structure for table Article
--

CREATE TABLE Article (
    artno      INT              NOT NULL    PRIMARY KEY,
    name       VARCHAR(30)      NOT NULL,
    numb       INT,
    price      DOUBLE
);

--
-- dumping data for table article
--

LOCK TABLES Article WRITE;

INSERT INTO Article VALUES
(1, 'Shampoo', 100, 20),
(2, 'Toothpaste', 300, 25),
(3, 'Dildo', 1, 10000),
(4, 'Banana', 1, 1),
(5, 'Meat', 50, 50),
(6, 'Car', 20, 2000),
(7, 'Diamond', 1, 0),
(8, 'Gold', 2000, 1000),
(9, 'Baby', 666, 666),
(10, 'Mustard', 9, 9),
(11, 'Grease', 7, 21),
(12, 'Oil', 120, 490),
(13, 'Conditioner', 920, 5800),
(14, 'MacBook', 293, 2900),
(15, 'iPhone', 931, 29000000),
(16, 'XBox', 22, 200),
(17, 'Playstation', 592, 100),
(18, 'Robot', 2, 700),
(19, 'Grandma', 1919, 0),
(20, 'Lol', 21, 21),
(21, 'Whatevz', 92, 29);

UNLOCK TABLES;

/*##########################################################################################*/

--
-- Table structure for table Order
--

CREATE TABLE Order (
    artno       INT             NOT NULL,
    cno         INT             NOT NULL,
    quantity    INT,

    PRIMARY KEY (artno, cno),

    FOREIGN KEY (artno)
                REFERENCES Article(artno)
                ON DELETE CASCADE
                ON UPDATE CASCADE,

    FOREIGN KEY (cno)
                REFERENCES Customer(cno)
                ON DELETE CASCADE
                ON UPDATE CASCADE,

    CONSTRAINT creditConstraint
                CHECK (Article(artno).price * quantity <= Customer(cno).credit)
);

--
-- Dumping data for table Order
--

LOCK TABLES Order WRITE;

INSERT INTO Order VALUES
(1, 1, 1),
(2, 10, 3),
(3, 11, 1),
(4, 2, 2),
(5, 2, 2),
(6, 4, 1),
(7, 9, 5),
(8, 9, 1),
(9, 2, 10),
(10, 3, 2),
(11, 11, 1),
(12, 10, 1),
(13, 8, 1),
(14, 8, 1),
(15, 1, 2),
(16, 1, 2),
(17, 2, 1),
(18, 2, 1),
(19, 2, 1),
(20, 3, 1),
(21, 3, 2),
(22, 10, 2),
(23, 10, 1);

UNLOCK TABLES;

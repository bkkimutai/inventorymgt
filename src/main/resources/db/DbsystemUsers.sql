CREATE TABLE IF NOT EXISTS systemUserDetails(
    userId int PRIMARY KEY auto_increment,
    firstName VARCHAR,
    lastName VARCHAR,
    email VARCHAR,
    company VARCHAR,
    roles VARCHAR,
    phoneNumber INT,
);

CREATE TABLE IF NOT EXISTS loginCredentials(
    userId int PRIMARY KEY auto_increment,
    email VARCHAR,
    password VARCHAR,
);
BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, beer, breweries, brewery_blog, favorites, reviews;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_beer_id;
DROP SEQUENCE IF EXISTS seq_brewery_id;
DROP SEQUENCE IF EXISTS seq_brewery_blog_id;
DROP SEQUENCE IF EXISTS seq_favorite_id;
DROP SEQUENCE IF EXISTS seq_review_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_beer_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

 CREATE SEQUENCE seq_brewery_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

 CREATE SEQUENCE seq_brewery_blog_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

 CREATE SEQUENCE seq_favorite_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

 CREATE SEQUENCE seq_review_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;



  CREATE TABLE users (
	  user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	  username varchar(50) NOT NULL,
	  password_hash varchar(200) NOT NULL,
	  role varchar(50) NOT NULL,
	  email varchar(50) NOT NULL,
	  CONSTRAINT PK_user_id PRIMARY KEY (user_id),
	  CONSTRAINT email_unique UNIQUE (email)
  );

    CREATE TABLE breweries(
        brewery_id int DEFAULT nextval('seq_brewery_id'::regclass) NOT NULL,
        name varchar(255) NOT NULL,
        address varchar(500) NOT NULL,
        user_id int NOT NULL,
        city varchar(255) NOT NULL,
        state_abbr varchar(2) NOT NULL,
        zipcode varchar(5) NOT NULL,
        phone_number varchar(20),
        description varchar,
        logo_url varchar(500),
        hours varchar(500),
        website varchar(500),
        CONSTRAINT PK_breweries PRIMARY KEY (brewery_id),
        CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
    );

   CREATE TABLE beer (
       beer_id int DEFAULT nextval('seq_beer_id'::regclass) NOT NULL,
       brewery_id int NOT NULL,
       beer_name varchar(50) NOT NULL,
       description varchar,
       image_url varchar(255) NOT NULL,
       abv decimal,
       beer_type varchar(50),
       hops varchar(200),
       ibu int,
       CONSTRAINT PK_beer_id PRIMARY KEY (beer_id),
       CONSTRAINT FK_brewery_id FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id)
   );

    CREATE TABLE brewery_blog(
        blog_id int DEFAULT nextval('seq_brewery_blog_id'::regclass) NOT NULL,
        brewery_id int NOT NULL,
        title varchar NOT NULL,
        body varchar NOT NULL,
        create_date timestamp with time zone,
        CONSTRAINT PK_brewery_blog PRIMARY KEY (blog_id),
        CONSTRAINT FK_brewery_id FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id)
    );

    CREATE TABLE favorites(
        favorite_id int DEFAULT nextval('seq_favorite_id'::regclass) NOT NULL,
        user_id int NOT NULL,
        brewery_id int NOT NULL,
        beer_id int NOT NULL,
        CONSTRAINT PK_favorites PRIMARY KEY (favorite_id),
        CONSTRAINT FK_beer FOREIGN KEY (beer_id) REFERENCES beer(beer_id),
        CONSTRAINT FK_brewery FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id),
        CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)

    );

    CREATE TABLE reviews(
        review_id int DEFAULT nextval('seq_review_id'::regclass) NOT NULL,
        user_id int NOT NULL,
        username varchar(255) NOT NULL,
        rating int NOT NULL ,
        description varchar,
        create_date timestamp with time zone DEFAULT NOW(),
        beer_id int,
        brewery_id int,
        CONSTRAINT PK_reviews PRIMARY KEY (review_id),
        CONSTRAINT FK_beer FOREIGN KEY (beer_id) REFERENCES beer(beer_id),
        CONSTRAINT FK_brewery FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id),
        CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)
        CHECK(rating between 1 and 5)
    );

INSERT INTO users (username,password_hash,role,email) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'elsee388+user@gmail.com');
INSERT INTO users (username,password_hash,role,email) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'elsee388+admin@gmail.com');
INSERT INTO users (username,password_hash,role,email) VALUES ('LBrew','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWERY', 'elsee388+Lbrewer@gmail.com');
INSERT INTO users (username,password_hash,role,email) VALUES ('SBrew','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWERY', 'elsee388+Sbrewer@gmail.com');
INSERT INTO users (username,password_hash,role,email) VALUES ('JBrew','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWERY', 'elsee388+Jbrewer@gmail.com');
INSERT INTO users (username,password_hash,role,email) VALUES ('EBrew','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWERY', 'elsee388+Ebrewer@gmail.com');



COMMIT TRANSACTION;

CREATE TABLE BOOK
(
    book_ID           BIGINT PRIMARY KEY NOT NULL auto_increment,
    PRICE        DOUBLE             NOT NULL,
    TITLE_RU     VARCHAR(255)       NOT NULL,
    TITLE_EN     VARCHAR(255)       NOT NULL,
    FK_AUTHOR_ID BIGINT             NOT NULL,
    DESCRIPTION  VARCHAR(1024),
    FK_IMAGE_ID  BIGINT
);
CREATE TABLE CATEGORY
(
    category_Id       BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    category_Title_Ru VARCHAR(128)       NOT NULL,
    category_Title_En VARCHAR(128)       NOT NULL
);
CREATE TABLE AUTHOR
(
    author_Id          BIGINT PRIMARY KEY NOT NULL auto_increment,
    NAME        VARCHAR(128)       NOT NULL,
    SURNAME     VARCHAR(128)       NOT NULL,
    FK_IMAGE_ID BIGINT
);
CREATE TABLE AUTHOR_IMAGE
(
    Id    BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    IMAGE VARCHAR(255)
#     FK_AUTHOR_ID BIGINT
);
CREATE TABLE BOOK_IMAGE
(
    ID    BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    IMAGE VARCHAR(255)
#     FK_BOOK_ID BIGINT
);
CREATE TABLE BASKET
(
    ID         BIGINT PRIMARY KEY NOT NULL auto_increment,
    FK_USER_ID BIGINT             NOT NULL
);
CREATE TABLE ROLE
(
    ID    BIGINT PRIMARY KEY NOT NULL auto_increment,
    TITLE VARCHAR(128)       NOT NULL
);
CREATE TABLE USER
(
    ID           BIGINT PRIMARY KEY NOT NULL auto_increment,
    EMAIL        VARCHAR(128)       NOT NULL,
    ACTIVE       SMALLINT           NOT NULL,
    USERNAME     VARCHAR(128)       NOT NULL,
    PASSWORD     VARCHAR(60)        NOT NULL,
    FK_ROLE_ID   BIGINT             NOT NULL,
    FK_BASKET_ID BIGINT
);
CREATE TABLE book_with_category
(
    book_id     bigint not null,
    category_id bigint not null
);

ALTER TABLE book_with_category
    add FOREIGN KEY (book_id) references book (book_ID);
ALTER TABLE book_with_category
    add FOREIGN KEY (category_id) references CATEGORY (category_Id);
ALTER TABLE BOOK
    add foreign key (fk_image_id) references BOOK_IMAGE (ID);
ALTER TABLE BOOK
    ADD FOREIGN KEY (FK_AUTHOR_ID) REFERENCES AUTHOR (author_Id);
ALTER TABLE AUTHOR
    ADD FOREIGN KEY (FK_IMAGE_ID) REFERENCES AUTHOR_IMAGE (Id);
# ALTER TABLE AUTHOR_IMAGE
#     ADD FOREIGN KEY (FK_AUTHOR_ID) REFERENCES AUTHOR (ID);
# ALTER TABLE BOOK_IMAGE
#     ADD FOREIGN KEY (FK_BOOK_ID) REFERENCES BOOK (ID);
ALTER TABLE BASKET
    ADD FOREIGN KEY (FK_USER_ID) REFERENCES USER (ID);
ALTER TABLE USER
    ADD FOREIGN KEY (FK_ROLE_ID) REFERENCES ROLE (ID);
ALTER TABLE USER
    ADD FOREIGN KEY (FK_BASKET_ID) REFERENCES BASKET (ID);

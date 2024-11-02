INSERT INTO CATEGORY(ID, DESCRIPTION) VALUES (1000, 'Comic Books');
INSERT INTO CATEGORY(ID, DESCRIPTION) VALUES (2000, 'Movies');
INSERT INTO CATEGORY(ID, DESCRIPTION) VALUES (3000, 'Books');

INSERT INTO SUPPLIER(ID, NAME) VALUES (1000, 'Amazon');
INSERT INTO SUPPLIER(ID, NAME) VALUES (2000, 'Panini Comics');

INSERT INTO PRODUCT(ID, NAME, FK_SUPPLIER, FK_CATEGORY, QUANTITY_AVAILABLE) VALUES(1000, 'Avengers: Endgame', 1000,2000, 10);
INSERT INTO PRODUCT(ID, NAME, FK_SUPPLIER, FK_CATEGORY, QUANTITY_AVAILABLE) VALUES(2000, 'Harry Potter and the Prisioner of Azkaban', 1000,2000, 5);
INSERT INTO PRODUCT(ID, NAME, FK_SUPPLIER, FK_CATEGORY, QUANTITY_AVAILABLE) VALUES(3000, 'Dragon Ball vol. 1', 2000,2000, 5);
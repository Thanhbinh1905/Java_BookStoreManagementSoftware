CREATE TABLE Authors (
    author_id NUMBER PRIMARY KEY,
    author_name NVARCHAR2(100) UNIQUE
);
CREATE TABLE Publishers (
    publisher_id NUMBER PRIMARY KEY,
    publisher_name NVARCHAR2(100)UNIQUE
);
CREATE TABLE Customers (
    customer_id NUMBER PRIMARY KEY,
    first_name NVARCHAR2(50),
    last_name NVARCHAR2(50),
    email VARCHAR2(100),
    phone VARCHAR2(20)
);
CREATE TABLE Users (
    user_id NUMBER PRIMARY KEY,
    username VARCHAR2(50) UNIQUE,
    password VARCHAR2(100)
);
CREATE TABLE Discount_Coupons (
    coupon_id VARCHAR2(50) PRIMARY KEY,
    discount_percentage NUMBER
); 
CREATE TABLE Books (
    book_id NUMBER PRIMARY KEY,
    title NVARCHAR2(100) UNIQUE,
    author_id NUMBER REFERENCES Authors(author_id),
    publisher_id NUMBER REFERENCES Publishers(publisher_id),
    genre NVARCHAR2(50),
    purchase_price NUMBER,
    price NUMBER,
    quantity_in_stock NUMBER
);
CREATE TABLE Orders (
    order_id NUMBER PRIMARY KEY,
    customer_id NUMBER REFERENCES Customers(customer_id),
    coupon_id VARCHAR2(50) REFERENCES Discount_Coupons(coupon_id),
    order_date DATE
);
CREATE TABLE OrderItems (
   item_id NUMBER PRIMARY KEY,
   order_id NUMBER REFERENCES Orders(order_id),
   book_id NUMBER REFERENCES Books(book_id),
   quantity NUMBER,
   total_amount NUMBER
);

CREATE OR REPLACE TRIGGER calculate_total_amount
BEFORE INSERT ON OrderItems
FOR EACH ROW
BEGIN
    SELECT ( :NEW.quantity * (
            SELECT price
            FROM Books
            WHERE book_id = :NEW.book_id
        ))
    INTO :NEW.total_amount
    FROM dual;
END;

CREATE OR REPLACE TRIGGER delete_related_order_items
AFTER DELETE ON Orders
FOR EACH ROW
BEGIN
    DELETE FROM OrderItems
    WHERE order_id = :OLD.order_id;
END;

SELECT Orders.order_id, Orders.customer_id, Orders.coupon_id, Orders.order_date, Customers.first_name, Customers.last_name,
    SUM((OrderItems.total_amount * Discount_Coupons.discount_percentage / 100) + OrderItems.total_amount) AS total_amount
FROM Orders
INNER JOIN Customers ON Orders.customer_id = Customers.customer_id
INNER JOIN OrderItems ON Orders.order_id = OrderItems.order_id
INNER JOIN Discount_Coupons ON Orders.coupon_id = Discount_Coupons.coupon_id
GROUP BY Orders.order_id, Orders.customer_id, Orders.coupon_id, Orders.order_date, Customers.first_name, Customers.last_name;

INSERT INTO ORDERITEMS (ITEM_ID, ORDER_ID, BOOK_ID, QUANTITY) VALUES (1, 1, 1, 2);
INSERT INTO ORDERITEMS (ITEM_ID, ORDER_ID, BOOK_ID, QUANTITY) VALUES (2, 1, 2, 2);
INSERT INTO ORDERITEMS (ITEM_ID, ORDER_ID, BOOK_ID, QUANTITY) VALUES (3, 2, null, null);

SELECT b.book_id, b.title, b.author_id, b.publisher_id, b.genre,b.purchase_price, b.price, b.quantity_in_stock, a.author_name, p.publisher_name
FROM Books b
JOIN Authors a ON b.author_id = a.author_id 
JOIN Publishers p ON b.publisher_id = p.publisher_id;
-- T?o trigger ?? c?p nh?t unit_price và tính toán total_amount khi thêm m?i d? li?u

CREATE OR REPLACE TRIGGER create_order_items
AFTER INSERT ON Orders
FOR EACH ROW
BEGIN
    INSERT INTO OrderItems (item_id, order_id, book_id, quantity)
    VALUES (:NEW.order_id || '00', :NEW.order_id, null, null);
END;

UPDATE Orders po
SET po.total_amount = (
    SELECT SUM(poi.total_amount)
    FROM OrderItems poi
    WHERE poi.order_id = po.order_id
)
WHERE EXISTS (
    SELECT 1
    FROM OrderItems poi
    WHERE poi.order_id = po.order_id
);

-- Du lieu cho bang Users
INSERT INTO Users (user_id, username, password) VALUES (1, 'admin', 'admin');
-- Du lieu khach hang
INSERT ALL
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (1, N'Tr?n Thanh', N'Tr?n', 'thanhtran@gmail.com', '0987267123')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (2, N'V?n Khánh', N'Nguy?n', 'vankhanh@gmail.com', '0905123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (3, N'Th? H?ng', N'Lê', 'thihong@gmail.com', '0978123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (4, N'Minh Kh?i', N'Ph?m', 'minhkhai@gmail.com', '0915123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (5, N'Th? H??ng', N'V?', 'thihuong@gmail.com', '0988123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (6, N'Quang Huy', N'Nông', 'quanghuy@gmail.com', '0909123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (7, N'Th? My', N'Bùi', 'thimy@gmail.com', '0938123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (8, N'V?n Long', N'Hoàng', 'vanlong@gmail.com', '0977123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (9, N'Th? Nguy?t', N'Ngô´', 'thinguyet@gmail.com', '0947123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (10, N'Minh Tu?n', N'?inh', 'minhtuan@gmail.com', '0911123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (11, N'Vân Anh', N'Ph?m', 'vananh@gmail.com', '0987123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (12, N'Th? Hà ', N'Lê', 'thiha@gmail.com', '0918123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (13, N'Ng?c Thành', N'Nguy?n', 'ngocthanh@gmail.com', '0923123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (14, N'V?n H?i', N'Tr?n', 'vanhai@gmail.com', '0986123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (15, N'Minh Trang', N'Lê', 'minhtrang@gmail.com', '0968123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (16, N'Qu?c Khánh', N'Mai', 'quockhanh@gmail.com', '0906123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (17, N'Th? Th?o', N'Nguy?n', 'thithao@gmail.com', '0937123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (18, N'V?n Tu?n', N'Hoàng', 'vantuan2@gmail.com', '0979123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (19, N'Th? Thu', N'Tr?n', 'thithu@gmail.com', '0948123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (20, N'Minh Hi?p', N'Bùi', 'minhhiep@gmail.com', '0912123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (21, N'V?n ??c', N'Ph?m', 'vanduc@gmail.com', '0982123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (22, N'Th? Hi?n', N'Tr?n', 'thihien@gmail.com', '0901123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (23, N'Ng?c Khánh', N'Lê', 'ngockhanh@gmail.com', '0935123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (24, N'V?n Th?ng', N'Nguy?n', 'vanthang@gmail.com', '0926123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (25, N'Minh H?i', N'V?', 'minhhai@gmail.com', '0976123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (26, N'Qu?c B?o', N'Nguy?n', 'quocbao@gmail.com', '0946123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (27, N'Th? Nhung', N'Hoàng', 'thinhung@gmail.com', '0936123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (28, N'V?n Thanh', N'Bùi', 'vanthanh@gmail.com', '0913123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (29, N'Th? Ph??ng', N'Lê', 'thiphuong@gmail.com', '0981123456')
  INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (30, N'Minh Trí', N'Hoàng', 'minhtri@gmail.com', '0903123456')
SELECT * FROM dual;

-- Du lieu cho bang Authors
INSERT ALL 
    INTO Authors (author_id, author_name) VALUES (1, N'Harper Lee')
    INTO Authors (author_id, author_name) VALUES (2, N'J.K. Rowling')
    INTO Authors (author_id, author_name) VALUES (3, N'George Orwell')
    INTO Authors (author_id, author_name) VALUES (4, N'Jane Austen')
    INTO Authors (author_id, author_name) VALUES (5, N'Mark Twain')
    INTO Authors (author_id, author_name) VALUES (6, N'J.R.R. Tolkien')
    INTO Authors (author_id, author_name) VALUES (7, N'Agatha Christie')
    INTO Authors (author_id, author_name) VALUES (8, N'Gabriel García Márquez')
    INTO Authors (author_id, author_name) VALUES (9, N'Leo Tolstoy')
    INTO Authors (author_id, author_name) VALUES (10, N'F. Scott Fitzgerald')
    INTO Authors (author_id, author_name) VALUES (11, N'Herman Melville')
    INTO Authors (author_id, author_name) VALUES (12, N'Mary Shelley')
    INTO Authors (author_id, author_name) VALUES (13, N'C.S. Lewis')
    INTO Authors (author_id, author_name) VALUES (14, N'Aldous Huxley')
    INTO Authors (author_id, author_name) VALUES (15, N'Ray Bradbury')
    INTO Authors (author_id, author_name) VALUES (16, N'Oscar Wilde')
    INTO Authors (author_id, author_name) VALUES (17, N'Bram Stoker')
    INTO Authors (author_id, author_name) VALUES (18, N'Lewis Carroll')
    INTO Authors (author_id, author_name) VALUES (19, N'John Steinbeck')
    INTO Authors (author_id, author_name) VALUES (20, N'Emily Brontë')
SELECT * FROM dual;


-- Du lieu cho bang Publishers
INSERT ALL 
    INTO Publishers (publisher_id, publisher_name) VALUES (1, N'HarperCollins')
    INTO Publishers (publisher_id, publisher_name) VALUES (2, N'Bloomsbury')
    INTO Publishers (publisher_id, publisher_name) VALUES (3, N'Penguin Books')
    INTO Publishers (publisher_id, publisher_name) VALUES (4, N'Random House')
    INTO Publishers (publisher_id, publisher_name) VALUES (5, N'Scholastic Corporation')
    INTO Publishers (publisher_id, publisher_name) VALUES (6, N'Vintage Books')
    INTO Publishers (publisher_id, publisher_name) VALUES (7, N'Hachette Book Group')
    INTO Publishers (publisher_id, publisher_name) VALUES (8, N'Alfred A. Knopf')
    INTO Publishers (publisher_id, publisher_name) VALUES (9, N'Moscow University Press')
    INTO Publishers (publisher_id, publisher_name) VALUES (10, N'Scribner')
    INTO Publishers (publisher_id, publisher_name) VALUES (11, N'Simon & Schuster')
    INTO Publishers (publisher_id, publisher_name) VALUES (12, N'Macmillan Publishers')
    INTO Publishers (publisher_id, publisher_name) VALUES (13, N'Viking Press')
    INTO Publishers (publisher_id, publisher_name) VALUES (14, N'Penguin Random House')
    INTO Publishers (publisher_id, publisher_name) VALUES (15, N'Russian Messenger')
    INTO Publishers (publisher_id, publisher_name) VALUES (16, N'Editorial Sudamericana')
SELECT * FROM dual;
delete from Books;
INSERT ALL
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (1, N'To Kill a Mockingbird', 1, 1, N'Fiction', 10.99, 12.99, 50)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (2, N'Harry Potter and the Philosopher''s Stone', 2, 2, N'Fantasy', 10.99, 14.99, 100)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (3, N'Nineteen Eighty-Four', 3, 3, N'Dystopian', 5.99, 10.99, 75)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (4, N'Pride and Prejudice', 4, 4, N'Romance', 5.99, 9.99, 60)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (5, N'The Adventures of Huckleberry Finn', 5, 5, N'Adventure', 5.99, 11.99, 40)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (6, N'The Hobbit', 6, 6, N'Fantasy', 5.99, 13.99, 70)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (7, N'Murder on the Orient Express', 7, 7, N'Mystery', 5.99, 12.49, 55)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (8, N'One Hundred Years of Solitude', 8, 8, N'Magical Realism', 5.99, 15.99, 90)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (9, N'War and Peace', 9, 9, N'Historical Fiction', 13.99, 18.99, 65)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (10, N'The Great Gatsby', 10, 10, N'Classic', 5.99, 10.99, 80)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (11, N'Jane Eyre', 4, 4, N'Gothic Fiction', 5.99, 11.99, 45)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (12, N'Moby-Dick', 11, 11, N'Adventure', 10.99, 13.99, 50)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (13, N'Frankenstein', 12, 12, N'Horror', 5.99, 9.99, 40)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (14, N'The Lion, the Witch and the Wardrobe', 13, 13, N'Fantasy', 10.99, 12.99, 60)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (15, N'Brave New World', 14, 14, N'Science Fiction', 10.99, 11.99, 55)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (16, N'Fahrenheit 451', 15, 15, N'Dystopian', 5.99, 10.49, 50)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (17, N'The Picture of Dorian Gray', 16, 16, N'Gothic Fiction', 5.99, 9.99, 45)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (18, N'Dracula', 17, 11, N'Horror', 5.99, 10.99, 40)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (19, N'Alice''s Adventures in Wonderland', 18, 12, N'Fantasy', 5.99, 8.99, 60)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (20, N'Of Mice and Men', 19, 13, N'Novella', 5.99, 9.99, 55)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (21, N'Wuthering Heights', 20, 16, N'Gothic Fiction', 5.99, 10.49, 50)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (22, N'The Lord of the Rings', 6, 11, N'Fantasy', 5.99, 22.99, 65)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (23, N'Animal Farm', 19, 16, N'Satire', 5.99, 9.99, 60)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (24, N'Adventures of Tom Sawyer', 5, 5, N'Adventure', 5.99, 10.99, 70)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (25, N'The Catcher in the Rye', 18, 6, N'Coming-of-Age', 5.99, 11.99, 75)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (26, N'Anna Karenina', 9, 7, N'Novel', 5.99, 12.49, 80)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (27, N'Love in the Time of Cholera', 8, 8, N'Romance', 5.99, 10.99, 85)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (28, N'Crime and Punishment', 9, 8, N'Psychological Fiction', 5.99, 11.99, 90)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (29, N'The Sun Also Rises', 11, 8, N'Novel', 5.99, 9.99, 95)
  INTO Books (book_id, title, author_id, publisher_id, genre, purchase_price, price, quantity_in_stock)
  VALUES (30, N'The Brothers Karamazov', 9, 10, N'Novel', 5.99, 12.99, 100)
SELECT * FROM dual;

INSERT ALL 
    INTO Discount_Coupons (coupon_id, discount_percentage) VALUES ('SUMMER15', 15)
    INTO Discount_Coupons (coupon_id, discount_percentage) VALUES ('FALL20', 20)
    INTO Discount_Coupons (coupon_id, discount_percentage) VALUES ('HOLIDAY25', 25)
SELECT * FROM dual;
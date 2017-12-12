CREATE OR REPLACE VIEW sales_product AS
  SELECT product.id AS id_product, count(details.id) AS quantity
  FROM
    details, bill_details, product, bill
  WHERE
    month(bill.date) = month(now()) AND
    bill_details.bill_id = bill.id AND
    details.id = bill_details.details_id AND
    product.name = details.name
  GROUP BY product.id
  ORDER BY count(details.id) DESC LIMIT 5;

DROP PROCEDURE getUsers;
CREATE DEFINER='compucom'@'localhost' PROCEDURE getUserEmails()
  BEGIN
    SELECT email FROM user;
  END;

DROP PROCEDURE getUsers;
CREATE DEFINER='compucom'@'localhost' PROCEDURE getUsers()
  BEGIN
    SELECT * FROM user;
  END;

DROP PROCEDURE getUser;
CREATE DEFINER='compucom'@'localhost' PROCEDURE getUser(IN p_id INT)
  BEGIN
    SELECT * FROM user WHERE id = p_id;
  END;
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
CREATE DEFINER='compucom'@'localhost' PROCEDURE getUserEmails()
  BEGIN
    SELECT email FROM user;
  END;

CREATE DEFINER='compucom'@'localhost' PROCEDURE getUsers()
  BEGIN
    SELECT * FROM user;
  END;

CREATE DEFINER='compucom'@'localhost' PROCEDURE getUser(IN p_id INT)
  BEGIN
    SELECT * FROM user WHERE id = p_id;
  END;

CREATE DEFINER = 'compucom'@'localhost' PROCEDURE addUser(
  IN p_email VARCHAR(255),
  IN p_password VARCHAR(255),
  IN p_username VARCHAR(255),
  IN p_city VARCHAR(255),
  IN p_colony VARCHAR(255),
  IN p_country VARCHAR(255),
  IN p_number VARCHAR(255),
  IN p_referece VARCHAR(255),
  IN p_state VARCHAR(255),
  IN p_street VARCHAR(255),
  IN p_zip VARCHAR(255)
)
  BEGIN
    DECLARE id_user INT;
    INSERT INTO user (email, password, username) VALUES (p_email,p_password,p_username);
    SELECT user.id INTO id_user FROM user WHERE user.email = p_email;
    INSERT INTO user_address (id, city, colony, country, number, reference, state, street, zip) VALUES (id_user, p_city, p_colony, p_country, p_number, p_referece, p_state, p_street, p_zip);
    INSERT INTO user_role (user_id, role_id) VALUES (id_user, 2);
  END;
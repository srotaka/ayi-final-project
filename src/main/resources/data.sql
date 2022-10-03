INSERT INTO `ayi_final_project`.`clients` (`client_id`, `dni`, `document_type`, `email`, `first_name`, `last_name`) SELECT 1, '30123456', 'DNI', 'benja@mail.com', 'Benjamín','White'
WHERE NOT EXISTS (SELECT * FROM `clients` WHERE client_id = 1);
INSERT INTO `ayi_final_project`.`clients` (`client_id`, `dni`, `document_type`, `email`, `first_name`, `last_name`) SELECT 2, '31234567', 'DNI', 'flavia@mail.com', 'Flavia', 'Gray'
WHERE NOT EXISTS (SELECT * FROM `clients` WHERE client_id = 2);
INSERT INTO `ayi_final_project`.`clients` (`client_id`, `dni`, `document_type`, `email`, `first_name`,  `last_name`) SELECT 3, '32345678', 'DNI', 'alberto@mail.com', 'Alberto', 'Red'
WHERE NOT EXISTS (SELECT * FROM `clients` WHERE client_id = 3);

INSERT INTO `ayi_final_project`.`client_details` (`client_detail_id`, `client_type`,  `points`, `client_id`) SELECT 1, 'PRIME','1000', 1
WHERE NOT EXISTS (SELECT * FROM `client_details` WHERE client_detail_id = 1);
INSERT INTO `ayi_final_project`.`client_details` (`client_detail_id`, `client_type`,  `points`, `client_id`) SELECT 2, 'VIP', '1000', 2
WHERE NOT EXISTS (SELECT * FROM `client_details` WHERE client_detail_id = 2);
INSERT INTO `ayi_final_project`.`client_details` (`client_detail_id`, `client_type`,  `points`, `client_id`) SELECT 3, 'STANDARD', '1000', 3
WHERE NOT EXISTS (SELECT * FROM `client_details` WHERE client_detail_id = 3);

UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '1' WHERE (`client_id` = '1');
UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '2' WHERE (`client_id` = '2');
UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '3' WHERE (`client_id` = '3');

INSERT INTO `ayi_final_project`.`bills` (`bill_id`, `description`,  `total_amount`, `client_id`) SELECT '1', 'TV Smart Samsung','5000', '1'
WHERE NOT EXISTS (SELECT * FROM `bills` WHERE bill_id = 1);
INSERT INTO `ayi_final_project`.`bills` (`bill_id`, `description`,  `total_amount`, `client_id`) SELECT '2', 'Heladera Samsung','8000', '2'
WHERE NOT EXISTS (SELECT * FROM `bills` WHERE bill_id = 2);
INSERT INTO `ayi_final_project`.`bills` (`bill_id`, `description`,  `total_amount`, `client_id`) SELECT '3', 'Aspiradora Samsung', '3000', '3'
WHERE NOT EXISTS (SELECT * FROM `bills` WHERE bill_id = 3);
INSERT INTO `ayi_final_project`.`bills` (`bill_id`, `description`,  `total_amount`, `client_id`) SELECT '4', 'Escritorio','2000', '1'
WHERE NOT EXISTS (SELECT * FROM `bills` WHERE bill_id = 4);

INSERT INTO `ayi_final_project`.`clients_bill_list` (`client_client_id`, `bill_list_bill_id`) SELECT '1', '1'
WHERE NOT EXISTS (SELECT * FROM `clients_bill_list` WHERE bill_list_bill_id = 1);
INSERT INTO `ayi_final_project`.`clients_bill_list` (`client_client_id`, `bill_list_bill_id`) SELECT '2', '2'
WHERE NOT EXISTS (SELECT * FROM `clients_bill_list` WHERE bill_list_bill_id = 2);
INSERT INTO `ayi_final_project`.`clients_bill_list` (`client_client_id`, `bill_list_bill_id`) SELECT '3', '3'
WHERE NOT EXISTS (SELECT * FROM `clients_bill_list` WHERE bill_list_bill_id = 3);
INSERT INTO `ayi_final_project`.`clients_bill_list` (`client_client_id`, `bill_list_bill_id`) SELECT '1', '4'
WHERE NOT EXISTS (SELECT * FROM `clients_bill_list` WHERE bill_list_bill_id = 4);

INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
SELECT '1', 'B', 'Rosario', 'Argentina', '3', '5561', '2000', 'Santa Fe', 'Uquiza', '1'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 1);
INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
SELECT '2', 'A', 'Posadas', 'Argentina', '10', '3421', '3360', 'Misiones', 'Dorrego', '2'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 2);
INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
SELECT '3', 'D', 'Córdoba', 'Argentina', '6', '341', '5000', 'Córdoba', 'Moreno', '3'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 3);
INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
SELECT '4', 'C', 'Rosario', 'Argentina', '1', '256', '2000', 'Santa Fe', 'Belgrano', '1'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 4);

INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '1', '1'
WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 1);
INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '2', '2'
WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 2);
INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '3', '3'
WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 3);
INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '1', '4'
WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 4);
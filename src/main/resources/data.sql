INSERT INTO `ayi_final_project`.`clients` (`client_id`, `dni`, `document_type`, `email`, `first_name`, `last_name`) SELECT 1, '30123456', 'DNI', 'benja@mail.com', 'Benjamín','White'
WHERE NOT EXISTS (SELECT * FROM `clients` WHERE client_id = 1);
INSERT INTO `ayi_final_project`.`clients` (`client_id`, `dni`, `document_type`, `email`, `first_name`, `last_name`) SELECT 2, '31234567', 'DNI', 'flavia@mail.com', 'Flavia', 'Gray'
WHERE NOT EXISTS (SELECT * FROM `clients` WHERE client_id = 2);
INSERT INTO `ayi_final_project`.`clients` (`client_id`, `dni`, `document_type`, `email`, `first_name`,  `last_name`) SELECT 3, '32345678', 'DNI', 'alberto@mail.com', 'Alberto', 'Red'
WHERE NOT EXISTS (SELECT * FROM `clients` WHERE client_id = 3);
INSERT INTO `ayi_final_project`.`clients` (`client_id`, `dni`, `document_type`, `email`, `first_name`, `last_name`) SELECT 4, '33568941', 'DNI', 'claudia@mail.com', 'Claudia','Purpule'
WHERE NOT EXISTS (SELECT * FROM `clients` WHERE client_id = 4);
INSERT INTO `ayi_final_project`.`clients` (`client_id`, `dni`, `document_type`, `email`, `first_name`, `last_name`) SELECT 5, '34652840', 'DNI', 'hugo@mail.com', 'Hugo', 'Orange'
WHERE NOT EXISTS (SELECT * FROM `clients` WHERE client_id = 5);
INSERT INTO `ayi_final_project`.`clients` (`client_id`, `dni`, `document_type`, `email`, `first_name`,  `last_name`) SELECT 6, '35069054', 'DNI', 'fabian@mail.com', 'Fabian', 'Blue'
WHERE NOT EXISTS (SELECT * FROM `clients` WHERE client_id = 6);

INSERT INTO `ayi_final_project`.`client_details` (`client_detail_id`, `is_vip`,  `points`, `client_id`) SELECT 1, 1,'3000', 1
WHERE NOT EXISTS (SELECT * FROM `client_details` WHERE client_detail_id = 1);
INSERT INTO `ayi_final_project`.`client_details` (`client_detail_id`, `is_vip`,  `points`, `client_id`) SELECT 2, 0, '1000', 2
WHERE NOT EXISTS (SELECT * FROM `client_details` WHERE client_detail_id = 2);
INSERT INTO `ayi_final_project`.`client_details` (`client_detail_id`, `is_vip`,  `points`, `client_id`) SELECT 3, 1, '500', 3
WHERE NOT EXISTS (SELECT * FROM `client_details` WHERE client_detail_id = 3);
INSERT INTO `ayi_final_project`.`client_details` (`client_detail_id`, `is_vip`,  `points`, `client_id`) SELECT 4, 1, '300', 4
WHERE NOT EXISTS (SELECT * FROM `client_details` WHERE client_detail_id = 4);
INSERT INTO `ayi_final_project`.`client_details` (`client_detail_id`, `is_vip`,  `points`, `client_id`) SELECT 5, 0, '750', 5
WHERE NOT EXISTS (SELECT * FROM `client_details` WHERE client_detail_id = 5);
INSERT INTO `ayi_final_project`.`client_details` (`client_detail_id`, `is_vip`,  `points`, `client_id`) SELECT 6, 1, '10', 6
WHERE NOT EXISTS (SELECT * FROM `client_details` WHERE client_detail_id = 6);

UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '1' WHERE (`client_id` = '1');
UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '2' WHERE (`client_id` = '2');
UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '3' WHERE (`client_id` = '3');
UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '4' WHERE (`client_id` = '4');
UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '5' WHERE (`client_id` = '5');
UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '6' WHERE (`client_id` = '6');

INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '1', 'TV Smart Samsung','750', '1'
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 1);
INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '2', 'Heladera Samsung','690', '2'
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 2);
INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '3', 'Aspiradora Samsung', '120', '3'
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 3);
INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '4', 'Escritorio','230', '1'
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 4);
INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '5', 'Lavarropas Samsung','480', '4'
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 5);
INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '6', 'Licuadora','140', '5'
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 6);
INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '7', 'Perchero', '90', '6'
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 7);


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
INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
SELECT '5', 'E', 'Corrientes', 'Argentina', '7', '694', '3478', 'Corrientes', 'Güemes', '4'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 5);
INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
SELECT '6', 'R', 'Resistencia', 'Argentina', '9', '8451', '3501', 'Chaco', 'Saavedra', '5'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 6);
INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
SELECT '7', 'F', 'Mendoza', 'Argentina', '2', '1067', '5500', 'Mendoza', 'Cabral', '6'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 7);

INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '1', '1'
WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 1);
INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '2', '2'
WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 2);
INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '3', '3'
WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 3);
INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '1', '4'
WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 4);
INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '4', '5'
WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 5);
INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '5', '6'
WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 6);
INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '6', '7'
WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 7);

INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '1', '1'
WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 1);
INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '2', '2'
WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 2);
INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '3', '3'
WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 3);
INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '1', '4'
WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 4);
INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '4', '5'
WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 5);
INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '5', '6'
WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 6);
INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '6', '7'
WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 7);
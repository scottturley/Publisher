DROP PROCEDURE IF EXISTS get_unique_id;
DELIMITER $$
CREATE PROCEDURE get_unique_id(OUT id INT)
BEGIN
	DECLARE next_id INT;

	START TRANSACTION;

	SELECT `next_value` INTO id FROM `sequence`;
	SET next_id = id + 1;
	UPDATE sequence SET next_value = next_id;
	
	commit;

END
$$
DELIMITER ;

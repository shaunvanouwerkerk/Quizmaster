ALTER TABLE `group`
CHANGE COLUMN `idGroup` `idGroup` INT NOT NULL AUTO_INCREMENT;

INSERT INTO `group` Values(default, 5, 1), (default, 5, 2), (default, 22, 3), (default, 23, 4);
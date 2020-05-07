CREATE DATABASE file_search
  CHARACTER SET utf8
  COLLATE utf8_general_ci;
GRANT ALL PRIVILEGES ON file_search.* TO'trik'@'%';

use file_search;
CREATE TABLE `files` (
  `file_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `path` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`file_id`),
  UNIQUE KEY `path` (`path`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=82189;

INSERT INTO `file_search`.`files`
(`file_id`,
`name`,
`path`,
`content`,
`updated`)
VALUES
(1,'The Dark Tower: The Gunslinger','/home/trikonenko/books/dark_tower.doc','The Man in Black fled across the desert, and the gunslinger followed. So begins Book I of Stephen King’s iconic fantasy series, The Dark Tower. Part sci-fi novel, part futuristic dystopia, part spaghetti Western, and part high fantasy vision, The Gunslinger tells the story of Roland Deschain, Mid-World’s last gunslinger...','2019-08-20 14:10:17'),
(2,'Misery','/home/trikonenko/books/Misery.doc','Misery Chastain was dead. Paul Sheldon had just killed her - with relief, with joy. Misery had made him rich; she was the heroine of a string of bestsellers. And now he wanted to get on to some real writing...','2019-08-20 14:10:17'),
(3,'Mr Mercedes.doc','/home/trikonenko/books/Mr_Mercedes.doc','The stolen Mercedes emerges from the pre-dawn fog and plows through a crowd of men and women on line for a job fair in a distressed American city. Then the lone driver backs up, charges again, and speeds off, leaving eight dead and more wounded...','2019-08-20 14:10:17');
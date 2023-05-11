CREATE TABLE IF NOT EXISTS `register` (
  `account` varchar(20) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `reg_time` datetime NOT NULL DEFAULT NOW(),
  `active` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

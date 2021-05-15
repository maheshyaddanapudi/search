CREATE TABLE `record_data` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(150) NOT NULL,
  `category` varchar(150) DEFAULT 'All Sports',
  `event_type` varchar(150) DEFAULT NULL,
  `event_title` varchar(150) NOT NULL,
  `event_timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `tags` varchar(1500) DEFAULT NULL,
  `fav` varchar(1) DEFAULT 'N',
  `insert_timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `event_data_UNIQUE` (`category`, `event_type`, `event_title`, `event_timestamp`)
);
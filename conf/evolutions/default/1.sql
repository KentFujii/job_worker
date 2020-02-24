# --- !Ups
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  PRIMARY KEY (`id`)
);

# --- !Downs
DROP TABLE `messages`;

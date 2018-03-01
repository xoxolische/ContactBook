SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- База данных: `test_pl`
--

-- --------------------------------------------------------

--
-- Структура таблицы `contacts`
--

DROP TABLE IF EXISTS `contacts`;
CREATE TABLE IF NOT EXISTS `contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birth_date` date DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `phone` varchar(255) COLLATE utf8_bin NOT NULL,
  `surname` varchar(255) COLLATE utf8_bin NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_728mksvqr0n907kujew6p3jc0` (`email`),
  KEY `FKna8bddygr3l3kq1imghgcskt8` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `contacts`
--

INSERT INTO `contacts` (`id`, `birth_date`, `create_date`, `email`, `name`, `phone`, `surname`, `user_id`) VALUES
(27, '1988-03-08', '2018-03-02 00:50:53', 'hope@sky.com', 'Jhon', '09767586925', 'Jo32', 6),
(28, '1975-09-01', '2018-03-02 00:52:05', 'floy@skl.com', 'Tron', '093737589625', 'Troya', 6),
(29, '1995-04-12', '2018-03-02 00:53:31', 'stacy@gmail.com', 'Benson', '0735698625', 'Bims', 6),
(30, '2018-03-21', '2018-03-02 00:55:22', 'lollipop@gmail.com', 'Yura', '0968754254', 'LOL', 6),
(31, '1999-06-17', '2018-03-02 00:56:42', 'greyslon@gmail.com', 'Tom', '0963554246', 'slon', 6),
(32, '1999-06-17', '2018-03-02 00:57:54', 'americanboy@gmail.com', 'Shally', '0503558974', 'spot', 6),
(34, '1979-01-02', '2018-03-02 01:11:56', 'moderator@world.com', 'cool', '3658574675', 'guy', 7),
(35, '1968-07-08', '2018-03-02 01:14:14', 'semen@mail.ru', 'Semen', '83754599887', 'Petrov', 7),
(36, '1985-09-07', '2018-03-02 01:15:15', 'oleg.ivanov@ukr.net', 'Oleg', '745654964694865', 'Ivanov', 7),
(37, '1971-02-03', '2018-03-02 01:17:34', 'pony228@cc.com', 'Evgen', '8375745490', 'Salo', 7),
(38, '1986-08-05', '2018-03-02 01:20:42', 'elena.sizikova@yandex.ru', 'elena', '9868086956', 'sizikova', 7),
(39, '2000-02-02', '2018-03-02 01:34:45', 'kroch07@gmail.com', 'Santiago', '0961720138', 'Krocha', 6);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `nickName` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `registration_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `email`, `nickName`, `password`, `registration_date`) VALUES
(6, 'kate.schony@ukr.net', 'schony', '$2a$10$p1xe8ElNmwkczdInIohfoeSFSNp7G0ivC2hXtVN.Cid/gEgmK2yX6', '2018-03-02 00:47:43'),
(7, 'admin@mail.com', 'admin', '$2a$10$p1xe8ElNmwkczdInIohfoeSFSNp7G0ivC2hXtVN.Cid/gEgmK2yX6', '2018-03-02 01:10:24');

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `contacts`
--
ALTER TABLE `contacts`
  ADD CONSTRAINT `FKna8bddygr3l3kq1imghgcskt8` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


CREATE TABLE IF NOT EXISTS `dm_user_education` (
  `id` mediumint(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `open_id` varchar(30) NOT NULL COMMENT '用户open id',
  `school` varchar(255) DEFAULT '' COMMENT '毕业院校',
  `starttime` int(10) unsigned NOT NULL COMMENT '开学时间',
  `endtime` int(10) unsigned NOT NULL COMMENT '毕业时间',
  `major` varchar(100) NOT NULL COMMENT '专业',
  `education` int(10) unsigned NOT NULL COMMENT '学历',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- 转存表中的数据 `dm_user_education`
--

INSERT INTO `dm_user_education` (`id`, `open_id`, `school`, `starttime`, `endtime`, `major`, `education`, `status`) VALUES
(1, '111', '武汉大学', 1283270400, 1285862400, '生物工程', 4, 1),
(2, '98', '湖南xx学校', 1375286400, 1376409600, '人工智能', 6912, 1),
(3, '113', '清华大学', 1249056000, 1317398400, '专业高级', 5, 1),
(4, '112', '清华学院', 1028160000, 1220227200, '经济', 6913, 1),
(5, '111', '清华大学', 1249056000, 1283270400, '电子科技', 4, 1),
(6, '182', '青蛙大学', 1375286400, 1377705600, '计算机科学与技术', 6912, 1),
(7, '111', '哈尔冰工业大学', 1283270400, 1285862400, '能源', 4, 1);
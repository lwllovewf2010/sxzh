SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


CREATE TABLE IF NOT EXISTS `dm_user_education` (
  `id` mediumint(10) NOT NULL AUTO_INCREMENT COMMENT '���',
  `open_id` varchar(30) NOT NULL COMMENT '�û�open id',
  `school` varchar(255) DEFAULT '' COMMENT '��ҵԺУ',
  `starttime` int(10) unsigned NOT NULL COMMENT '��ѧʱ��',
  `endtime` int(10) unsigned NOT NULL COMMENT '��ҵʱ��',
  `major` varchar(100) NOT NULL COMMENT 'רҵ',
  `education` int(10) unsigned NOT NULL COMMENT 'ѧ��',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '״̬',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- ת����е����� `dm_user_education`
--

INSERT INTO `dm_user_education` (`id`, `open_id`, `school`, `starttime`, `endtime`, `major`, `education`, `status`) VALUES
(1, '111', '�人��ѧ', 1283270400, 1285862400, '���﹤��', 4, 1),
(2, '98', '����xxѧУ', 1375286400, 1376409600, '�˹�����', 6912, 1),
(3, '113', '�廪��ѧ', 1249056000, 1317398400, 'רҵ�߼�', 5, 1),
(4, '112', '�廪ѧԺ', 1028160000, 1220227200, '����', 6913, 1),
(5, '111', '�廪��ѧ', 1249056000, 1283270400, '���ӿƼ�', 4, 1),
(6, '182', '���ܴ�ѧ', 1375286400, 1377705600, '�������ѧ�뼼��', 6912, 1),
(7, '111', '��������ҵ��ѧ', 1283270400, 1285862400, '��Դ', 4, 1);
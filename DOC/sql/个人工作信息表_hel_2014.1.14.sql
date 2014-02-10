SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `sanxian`
--

-- --------------------------------------------------------

--
-- 表的结构 `dm_user_work`
--

CREATE TABLE IF NOT EXISTS `dm_user_work` (
  `id` mediumint(10) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `open_id` varchar(30) NOT NULL COMMENT 'open_id',
  `company` varchar(60) NOT NULL COMMENT '公司',
  `company_id` mediumint(10) DEFAULT NULL COMMENT '公司id(备用)',
  `position` varchar(30) NOT NULL COMMENT '职位',
  `starttime` int(10) DEFAULT NULL COMMENT '上班时间',
  `endtime` int(10) DEFAULT NULL COMMENT '离岗时间',
  `province` varchar(30) NOT NULL COMMENT '省',
  `province_id` int(4) DEFAULT NULL COMMENT '省id',
  `city` varchar(30) NOT NULL COMMENT '市',
  `city_id` int(4) DEFAULT NULL COMMENT '市id',
  `area` int(4) DEFAULT NULL COMMENT '区',
  `area_id` int(4) DEFAULT NULL COMMENT '区id',
  `address` varchar(300) DEFAULT NULL COMMENT '详细地址',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='工作信息' AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `dm_user_work`
--

INSERT INTO `dm_user_work` (`id`, `open_id`, `company`, `company_id`, `position`, `starttime`, `endtime`, `province`, `province_id`, `city`, `city_id`, `area`, `area_id`, `address`, `status`) VALUES
(1, 'test_open_id', '百度公司', NULL, '程序员', 123456, 1222, '广东', NULL, '深圳', NULL, NULL, NULL, NULL, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

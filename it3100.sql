-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 04, 2018 lúc 09:14 PM
-- Phiên bản máy phục vụ: 10.1.30-MariaDB
-- Phiên bản PHP: 7.0.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `it3100`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `question`
--

CREATE TABLE `question` (
  `questionid` int(11) NOT NULL,
  `question` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `topic` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `dapan` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `dapantv` varchar(35) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `question`
--

INSERT INTO `question` (`questionid`, `question`, `topic`, `dapan`, `dapantv`) VALUES
(1, 'CLB Châu Âu nào vô địch Champion Leaguea mùa giải 2016-2017', 'Bóng Đá', 'REALMADRID', 'Real Madrid'),
(2, 'CLB Châu Âu nào vô địch Champion Leaguea nhiều nhất ?', 'Bóng Đá', 'REALMADRID', 'Real Madrid'),
(3, 'Ai là người đọc bản tuyên ngôn độc lập khai sinh ra nước Việt Nam dân chủ cộng hòa vào ngày 2/9/1945 ?', 'Lịch Sử', 'HOCHIMINH', 'Hồ Chí Minh'),
(4, 'Thành phố nào là thủ đô của Việt Nam', 'Địa Lí', 'HANOI', 'Hà Nội'),
(5, 'Ai là tác giả bài thơ \"Bài ca nhà tranh bị gió thu phá \" ?', 'Văn Học', 'DOPHU', 'Đỗ Phủ'),
(6, 'Máy tính điện tử ra đời năm nào ?', 'Tin Học', '1946', '1946'),
(7, 'Bộ phận nào trên cơ thể thằn lằn có thể mọc được', 'Sinh Học', 'DUOI', 'Đuôi'),
(8, 'Nước nào nhỏ nhất thế giới ?', 'Địa Lí', 'VANTICAN', 'Vantican'),
(9, 'Vị vua nào rời đô từ Hoa Lư về Thăng Long ?', 'Lịch Sử', 'LICONGUAN', 'Lí Công Uẩn'),
(10, 'Đội tuyển nào vô địch vòng chung kết Euro 2016 ?', 'Bóng Đá', 'BODAONHA', 'Bồ Đào Nha'),
(11, 'Cầu thủ nào có biệt danh là El Nino ?', 'Bóng Đá', 'FERNALDOTORRES', 'Fernaldo Torres'),
(12, 'Nhà thơ nào có biệt hiệu là \" Ức Trai \" ?', 'Văn Học', 'NGUYENTRAI', 'Nguyễn Trãi'),
(13, 'Đơn vị của tầng mạng trong OSI ?', 'Tin Học', 'DATAGRAM', 'datagram'),
(14, 'Neymar Jr là cầu thủ người nước nào ?', 'Bóng Đá', 'BRAZIL', 'Brazil');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `questionspecial`
--

CREATE TABLE `questionspecial` (
  `questionspecialid` int(11) NOT NULL,
  `question` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dapan` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `dapantv` varchar(35) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `questionspecial`
--

INSERT INTO `questionspecial` (`questionspecialid`, `question`, `dapan`, `dapantv`) VALUES
(1, 'Đây là một lợi ích cơ bản tiết kiệm năng lượng', 'BAOTONTAINGUYEN', 'Bảo Tồn Tài Nguyên'),
(3, 'Đây là ước mơ của nhiều người trong dịp tết', 'VEQUEANTET', 'Về Quê Ăn Tết');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`questionid`);

--
-- Chỉ mục cho bảng `questionspecial`
--
ALTER TABLE `questionspecial`
  ADD PRIMARY KEY (`questionspecialid`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `questionspecial`
--
ALTER TABLE `questionspecial`
  MODIFY `questionspecialid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

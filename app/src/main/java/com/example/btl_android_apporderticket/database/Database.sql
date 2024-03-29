create database [order_ticket]
USE [order_ticket]
GO
/****** Object:  Table [dbo].[cast]    Script Date: 3/26/2023 11:02:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cast](
	[cast_id] [varchar](36) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[birth_day] [date] NOT NULL,
	[nationality] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cast_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cinemas]    Script Date: 3/26/2023 11:02:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cinemas](
	[Id_cinema] [varchar](36) NOT NULL,
	[name_cinema] [nvarchar](50) NOT NULL,
	[phone_cinema] [varchar](12) NOT NULL,
	[image_cinema] [varchar](200) NULL,
	[address_cinema] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id_cinema] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[genres]    Script Date: 3/26/2023 11:02:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[genres](
	[genre_id] [int] NOT NULL,
	[name] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[genre_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[invoices]    Script Date: 3/26/2023 11:02:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[invoices](
	[invoice_id] [varchar](36) NOT NULL,
	[create_at] [datetime] NOT NULL,
	[total_price] [int] NOT NULL,
	[payment_method] [varchar](100) NOT NULL,
	[payment_status] [varchar](20) NOT NULL,
	[user_id] [varchar](36) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[invoice_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[movie_cast]    Script Date: 3/26/2023 11:02:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[movie_cast](
	[movie_id] [varchar](36) NOT NULL,
	[cast_id] [varchar](36) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[movie_id] ASC,
	[cast_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[movies]    Script Date: 3/26/2023 11:02:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[movies](
	[movie_id] [varchar](36) NOT NULL,
	[title] [nvarchar](300) NOT NULL,
	[language] [nvarchar](100) NOT NULL,
	[country] [nvarchar](100) NOT NULL,
	[description] [nvarchar](1000) NOT NULL,
	[released_date] [date] NOT NULL,
	[Director] [nvarchar](50) NOT NULL,
	[duration] [int] NOT NULL,
	[poster] [varchar](200) NOT NULL,
	[age_rating] [int] NOT NULL,
	[genre_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[movie_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rooms]    Script Date: 3/26/2023 11:02:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rooms](
	[room_id] [varchar](36) NOT NULL,
	[number_seats] [int] NOT NULL,
	[name] [varchar](30) NOT NULL,
	[available_seats] [int] NOT NULL,
	[Id_cinema] [varchar](36) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[room_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[schedules]    Script Date: 3/26/2023 11:02:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[schedules](
	[schedule_id] [varchar](36) NOT NULL,
	[show_time] [datetime] NOT NULL,
	[end_time] [datetime] NOT NULL,
	[price] [int] NOT NULL,
	[movie_id] [varchar](36) NOT NULL,
	[room_id] [varchar](36) NULL,
PRIMARY KEY CLUSTERED 
(
	[schedule_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[seats]    Script Date: 3/26/2023 11:02:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[seats](
	[seat_id] [int] NOT NULL,
	[row_number] [varchar](10) NOT NULL,
	[status] [varchar](20) NOT NULL,
	[type_seat] [varchar](20) NOT NULL,
	[room_id] [varchar](36) NOT NULL,
	[schedule_id] [varchar](36) NULL,
PRIMARY KEY CLUSTERED 
(
	[seat_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[shows]    Script Date: 3/26/2023 11:02:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[shows](
	[Id_cinema] [varchar](36) NOT NULL,
	[movie_id] [varchar](36) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id_cinema] ASC,
	[movie_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tickets]    Script Date: 3/26/2023 11:02:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tickets](
	[ticket_id] [varchar](36) NOT NULL,
	[purchase_at] [date] NOT NULL,
	[price] [int] NOT NULL,
	[invoice_id] [varchar](36) NOT NULL,
	[schedule_id] [varchar](36) NOT NULL,
	[seat_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ticket_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 3/26/2023 11:02:14 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[user_id] [varchar](36) NOT NULL,
	[fullname] [varchar](50) NOT NULL,
	[phone] [varchar](12) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[gender] [varchar](10) NOT NULL,
	[address] [varchar](100) NOT NULL,
	[birth_day] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[cast] ([cast_id], [name], [birth_day], [nationality]) VALUES (N'01be34a5-5ab1-400e-8d15-3cc45ce74ceb', N'Phạm Nguyễn Lan Thy', CAST(N'2023-03-26' AS Date), N'Việt Nam')
INSERT [dbo].[cast] ([cast_id], [name], [birth_day], [nationality]) VALUES (N'0c41fcb9-fe4b-40a5-af32-cd345c4ebddc', N'Jung Yu-mi', CAST(N'2023-03-26' AS Date), N'Hàn Quốc')
INSERT [dbo].[cast] ([cast_id], [name], [birth_day], [nationality]) VALUES (N'143afbfe-3287-4a7e-b78c-7800368eadec', N'Alana Boden', CAST(N'2023-03-26' AS Date), N'China')
INSERT [dbo].[cast] ([cast_id], [name], [birth_day], [nationality]) VALUES (N'1645f6b9-8516-4810-a326-6a8952777c7e', N'Nakatani Akari', CAST(N'2023-03-26' AS Date), N'England')
INSERT [dbo].[cast] ([cast_id], [name], [birth_day], [nationality]) VALUES (N'39cf22f3-a766-4da2-b309-3d9e3e935c13', N'Ma Dong-seok', CAST(N'2023-03-26' AS Date), N'Hàn Quốc')
INSERT [dbo].[cast] ([cast_id], [name], [birth_day], [nationality]) VALUES (N'458fd1b5-62d1-4954-ba06-6183aca780d1', N'Gong Yoog', CAST(N'2023-03-26' AS Date), N'Hàn Quốc')
INSERT [dbo].[cast] ([cast_id], [name], [birth_day], [nationality]) VALUES (N'67248efb-2efa-4d39-9c7c-70f588959a32', N'Lương Anh Vũ', CAST(N'2023-03-26' AS Date), N'Việt Nam')
INSERT [dbo].[cast] ([cast_id], [name], [birth_day], [nationality]) VALUES (N'cbab4d2c-3780-4eba-8e64-a9e582f949a0', N'Choi Woo-shik', CAST(N'2023-03-26' AS Date), N'Hàn Quốc')
INSERT [dbo].[cast] ([cast_id], [name], [birth_day], [nationality]) VALUES (N'cec56ee9-75bc-4a0a-8099-9d24ac8130c2', N'Mason Gooding', CAST(N'2023-03-26' AS Date), N'Singapo')
INSERT [dbo].[cast] ([cast_id], [name], [birth_day], [nationality]) VALUES (N'd49903e3-b740-45ff-8d00-f07961cffd3f', N'Nathalie Emmanuel', CAST(N'2023-03-26' AS Date), N'Mexico')
INSERT [dbo].[cast] ([cast_id], [name], [birth_day], [nationality]) VALUES (N'df1e97a6-392b-4823-a4bc-3e289817978c', N'Samuel An', CAST(N'2023-03-26' AS Date), N'China')
GO
INSERT [dbo].[Cinemas] ([Id_cinema], [name_cinema], [phone_cinema], [image_cinema], [address_cinema]) VALUES (N'3640d005-b799-4ade-9897-1ecc8662c27f', N'CGV Vĩnh Trung Plaza', N'0966225173', N'https://i.pinimg.com/564x/c8/41/37/c84137110513d85ee7e48ad4ad7cd9a9.jpg', N'403 Tôn Đức Thắng - P. Hòa Minh - Q.Liên Chiểu - TP.DN')
INSERT [dbo].[Cinemas] ([Id_cinema], [name_cinema], [phone_cinema], [image_cinema], [address_cinema]) VALUES (N'40791b45-d4fa-4ce7-bd47-8c12d86a063d', N'Lotte Đà Nẵng', N'0966874172', N'https://i.pinimg.com/564x/a3/b9/74/a3b9741e801897a2e54e3fa32c347c6c.jpg', N'Tầng 5 Lotte Mart Đà Nẵng, P. Hòa Cường Bắc, Q. Hải Châu, Tp.DN')
INSERT [dbo].[Cinemas] ([Id_cinema], [name_cinema], [phone_cinema], [image_cinema], [address_cinema]) VALUES (N'537a5fc4-b919-4d7d-a531-27891a8e0aa3', N'CGV IMC Trần Quang Khải', N'0946927174', N'https://i.pinimg.com/564x/a5/d0/bf/a5d0bfc0fc3ebaa8f002b14681078f48.jpg', N'Tầng 2 & 3, TTVH Đa Năng, 62 Trần Quang Khải, P. Tân Định, Q. 1, TP.HCM')
INSERT [dbo].[Cinemas] ([Id_cinema], [name_cinema], [phone_cinema], [image_cinema], [address_cinema]) VALUES (N'7d4ac89d-b570-43f3-a331-44f979fee0bf', N'CGV Vincom Bà Triệu', N'0966225173', N'https://i.pinimg.com/564x/c8/41/37/c84137110513d85ee7e48ad4ad7cd9a9.jpg', N'Tầng 6, VinCom Center Hà Nội, 191 Bà Triệu, Q. Hai Bà Trưng, TP.HN')
INSERT [dbo].[Cinemas] ([Id_cinema], [name_cinema], [phone_cinema], [image_cinema], [address_cinema]) VALUES (N'dc025da5-502a-444a-8e75-1e1e12f9306b', N'CGV Liberty Citypoint', N'0966525163', N'https://i.pinimg.com/564x/a5/d0/bf/a5d0bfc0fc3ebaa8f002b14681078f48.jpg', N'Tầng M – 1, Liberty Center Saigon Citypoint, 59 – 61 Pasteur, Q.1, TP.HCM')
INSERT [dbo].[Cinemas] ([Id_cinema], [name_cinema], [phone_cinema], [image_cinema], [address_cinema]) VALUES (N'e8930b98-6084-46b8-9693-43dcb16ec5b0', N'CGV Hồ Gươm Plaza', N'0969825567', N'https://i.pinimg.com/564x/c8/41/37/c84137110513d85ee7e48ad4ad7cd9a9.jpg', N'Tầng 3, TTTM Hồ Gươm Plaza, 110 Trần Phú, P. Mỗ Lao, Q. Hà Đông, TP.HN')
GO
INSERT [dbo].[genres] ([genre_id], [name]) VALUES (1, N'Action')
INSERT [dbo].[genres] ([genre_id], [name]) VALUES (2, N'Drama')
INSERT [dbo].[genres] ([genre_id], [name]) VALUES (3, N'Horror')
INSERT [dbo].[genres] ([genre_id], [name]) VALUES (4, N'Feeling')
GO
INSERT [dbo].[movie_cast] ([movie_id], [cast_id]) VALUES (N'27d16e32-aade-472d-b8df-2b24cd4e02d4', N'01be34a5-5ab1-400e-8d15-3cc45ce74ceb')
INSERT [dbo].[movie_cast] ([movie_id], [cast_id]) VALUES (N'27d16e32-aade-472d-b8df-2b24cd4e02d4', N'0c41fcb9-fe4b-40a5-af32-cd345c4ebddc')
INSERT [dbo].[movie_cast] ([movie_id], [cast_id]) VALUES (N'27d16e32-aade-472d-b8df-2b24cd4e02d4', N'143afbfe-3287-4a7e-b78c-7800368eadec')
INSERT [dbo].[movie_cast] ([movie_id], [cast_id]) VALUES (N'8bdbc2a5-a9ea-4120-8cb1-37dcad14d3ba', N'143afbfe-3287-4a7e-b78c-7800368eadec')
INSERT [dbo].[movie_cast] ([movie_id], [cast_id]) VALUES (N'92af7976-a420-4817-b86b-3dc972a8f543', N'458fd1b5-62d1-4954-ba06-6183aca780d1')
INSERT [dbo].[movie_cast] ([movie_id], [cast_id]) VALUES (N'92af7976-a420-4817-b86b-3dc972a8f543', N'67248efb-2efa-4d39-9c7c-70f588959a32')
INSERT [dbo].[movie_cast] ([movie_id], [cast_id]) VALUES (N'92af7976-a420-4817-b86b-3dc972a8f543', N'cbab4d2c-3780-4eba-8e64-a9e582f949a0')
INSERT [dbo].[movie_cast] ([movie_id], [cast_id]) VALUES (N'f930b958-e7ff-4c3f-91d2-929500a43d3f', N'0c41fcb9-fe4b-40a5-af32-cd345c4ebddc')
INSERT [dbo].[movie_cast] ([movie_id], [cast_id]) VALUES (N'f930b958-e7ff-4c3f-91d2-929500a43d3f', N'143afbfe-3287-4a7e-b78c-7800368eadec')
INSERT [dbo].[movie_cast] ([movie_id], [cast_id]) VALUES (N'f930b958-e7ff-4c3f-91d2-929500a43d3f', N'39cf22f3-a766-4da2-b309-3d9e3e935c13')
INSERT [dbo].[movie_cast] ([movie_id], [cast_id]) VALUES (N'f930b958-e7ff-4c3f-91d2-929500a43d3f', N'67248efb-2efa-4d39-9c7c-70f588959a32')
INSERT [dbo].[movie_cast] ([movie_id], [cast_id]) VALUES (N'f930b958-e7ff-4c3f-91d2-929500a43d3f', N'd49903e3-b740-45ff-8d00-f07961cffd3f')
GO
INSERT [dbo].[movies] ([movie_id], [title], [language], [country], [description], [released_date], [Director], [duration], [poster], [age_rating], [genre_id]) VALUES (N'27d16e32-aade-472d-b8df-2b24cd4e02d4', N'Kisaragi: Nhà Ga Nuốt Chửng', N'Viet Sub', N'Hàn Quốc', N'Truyền thuyết đô thị nổi tiếng về nhà ga Kisaragi (きさらぎ駅) đã được chuyển thể thành phim kinh dị do AEON Entertainment sản xuất, với Yuri Tsunematsu (Naked Director) đóng vai', CAST(N'2023-03-29' AS Date), N'Choi Gwi-hwa', 105, N'https://i.pinimg.com/564x/ab/31/50/ab3150cd58a0683858ab08568b3ba795.jpg', 16, 3)
INSERT [dbo].[movies] ([movie_id], [title], [language], [country], [description], [released_date], [Director], [duration], [poster], [age_rating], [genre_id]) VALUES (N'8bdbc2a5-a9ea-4120-8cb1-37dcad14d3ba', N'Mình Yêu Nhau Đi', N'Viet Sub', N'Hàn Quốc', N'Mình Yêu Nhau Đi là bộ phim thuộc thể loại hài hước, tình cảm lãng mạn của Thái Lan được sản xuất vào năm 2022 do Prueksa Amaruji biên kịch và Pruk Emaruji Tui chịu trách nhiệm', CAST(N'2023-03-29' AS Date), N'Peach Pachara Chirathivat', 130, N'https://i.pinimg.com/564x/5e/cc/a4/5ecca4c3c70271af1d387b52813491f2.jpg', 18, 1)
INSERT [dbo].[movies] ([movie_id], [title], [language], [country], [description], [released_date], [Director], [duration], [poster], [age_rating], [genre_id]) VALUES (N'92af7976-a420-4817-b86b-3dc972a8f543', N'Cú Rơi Tử Thần – Fall', N'Viet Sub', N'Trung Quốc', N'Cú Rơi Tử Thần – Fall là một bộ phim kinh dị sinh tồn năm 2022 của Mỹ do Scott Mann đạo diễn và đồng biên kịch. Phim có sự tham gia của Grace Caroline Currey', CAST(N'2023-03-20' AS Date), N'Julia Pace Mitchell', 125, N'https://i.pinimg.com/564x/40/85/83/40858369f4603b8e47a22bc2fc6d3dd6.jpg', 14, 1)
INSERT [dbo].[movies] ([movie_id], [title], [language], [country], [description], [released_date], [Director], [duration], [poster], [age_rating], [genre_id]) VALUES (N'b965600e-123a-4a8f-a62a-3957a2095073', N'Em Và Trịnh', N'Tiếng Việt', N'Việt Nam', N'Em Và Trịnh là một bộ phim điện ảnh Việt Nam do Phan Gia Nhật Linh đạo diễn, kể về cuộc đời của cố nhạc sĩ Trịnh Công Sơn. Tác phẩm xoay quanh cuộc đời Trịnh Công Sơn', CAST(N'2023-03-29' AS Date), N'Trọng Trinh', 140, N'https://i.pinimg.com/564x/ec/99/9e/ec999ec8987fd517e900267a4c0a0b76.jpg', 12, 4)
INSERT [dbo].[movies] ([movie_id], [title], [language], [country], [description], [released_date], [Director], [duration], [poster], [age_rating], [genre_id]) VALUES (N'd3cbe1ef-83df-43d8-9d45-7f98e75bc687', N'Dragon Ball: Super Hero', N'English', N'England', N'Dragon Ball: Super Hero là một bộ phim viễn tưởng / phiêu lưu võ thuật hoạt hình Nhật Bản năm 2022', CAST(N'2023-03-20' AS Date), N'Mamoru Miyano', 120, N'https://i.pinimg.com/564x/76/c9/04/76c9042cb70d6bd1604a874a05fefb65.jpg', 14, 1)
INSERT [dbo].[movies] ([movie_id], [title], [language], [country], [description], [released_date], [Director], [duration], [poster], [age_rating], [genre_id]) VALUES (N'f930b958-e7ff-4c3f-91d2-929500a43d3f', N'Chuyến Tàu Sinh Tử – Train To Busan', N'Viet Sub', N'Hàn Quốc', N'Chuyến Tàu Sinh Tử – Train To Busan lấy bối cảnh đất nước Hàn bị tấn công bởi một loại virus bí ẩn, biến con người thành những xác sống hung hăng, khát máu. Có mặt trên chuyến tàu từ Seoul tới Busan', CAST(N'2023-03-20' AS Date), N'Choi Gwi-hwa', 110, N'https://i.pinimg.com/564x/b3/46/8e/b3468e0cbfb9fbff032e2d946f3d0466.jpg', 16, 2)
GO
INSERT [dbo].[rooms] ([room_id], [number_seats], [name], [available_seats], [Id_cinema]) VALUES (N'47671f19-dc17-4698-a497-73e14b48d0ed', 30, N'room', 30, N'dc025da5-502a-444a-8e75-1e1e12f9306b')
INSERT [dbo].[rooms] ([room_id], [number_seats], [name], [available_seats], [Id_cinema]) VALUES (N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', 30, N'room', 30, N'e8930b98-6084-46b8-9693-43dcb16ec5b0')
INSERT [dbo].[rooms] ([room_id], [number_seats], [name], [available_seats], [Id_cinema]) VALUES (N'ac0dbad1-27da-439c-a0a3-778b2086281f', 30, N'room', 30, N'3640d005-b799-4ade-9897-1ecc8662c27f')
INSERT [dbo].[rooms] ([room_id], [number_seats], [name], [available_seats], [Id_cinema]) VALUES (N'af746bfe-4295-4d87-8f50-8504f45e298c', 30, N'room', 30, N'537a5fc4-b919-4d7d-a531-27891a8e0aa3')
INSERT [dbo].[rooms] ([room_id], [number_seats], [name], [available_seats], [Id_cinema]) VALUES (N'c8925643-9ff2-4bb0-a6f6-89fdfc267628', 30, N'room', 30, N'7d4ac89d-b570-43f3-a331-44f979fee0bf')
INSERT [dbo].[rooms] ([room_id], [number_seats], [name], [available_seats], [Id_cinema]) VALUES (N'e1476813-2d2e-43f2-afb9-3cf7be0ddd24', 30, N'room', 30, N'40791b45-d4fa-4ce7-bd47-8c12d86a063d')
GO
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'0d766432-0afe-4bc1-a797-8c9a7461fb9d', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 20, N'27d16e32-aade-472d-b8df-2b24cd4e02d4', N'ac0dbad1-27da-439c-a0a3-778b2086281f')
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'1014e5d6-758b-4299-9cf1-27ecaa4e96c1', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 21, N'92af7976-a420-4817-b86b-3dc972a8f543', N'ac0dbad1-27da-439c-a0a3-778b2086281f')
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'29d83e6c-5bc0-456b-a9ec-49e61f2d60af', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 22, N'8bdbc2a5-a9ea-4120-8cb1-37dcad14d3ba', N'47671f19-dc17-4698-a497-73e14b48d0ed')
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'34921621-2ecd-40b2-ba0c-8f2082b2ede7', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 20, N'b965600e-123a-4a8f-a62a-3957a2095073', N'ac0dbad1-27da-439c-a0a3-778b2086281f')
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'3da19d5f-4a16-407d-b2ca-05393ccbc875', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 22, N'8bdbc2a5-a9ea-4120-8cb1-37dcad14d3ba', N'ac0dbad1-27da-439c-a0a3-778b2086281f')
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'4062d931-0b1b-45e6-a6de-56db2850ee3d', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 22, N'd3cbe1ef-83df-43d8-9d45-7f98e75bc687', N'47671f19-dc17-4698-a497-73e14b48d0ed')
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 22, N'8bdbc2a5-a9ea-4120-8cb1-37dcad14d3ba', N'47671f19-dc17-4698-a497-73e14b48d0ed')
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'6af54fd7-c8f4-4883-a8eb-1f1c9b2b8e34', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 21, N'92af7976-a420-4817-b86b-3dc972a8f543', N'ac0dbad1-27da-439c-a0a3-778b2086281f')
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'a1398591-54ca-485b-8aba-2023fb35c5e4', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 20, N'27d16e32-aade-472d-b8df-2b24cd4e02d4', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5')
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'a174dbc7-f6fc-44f7-bd5f-2b7a7314cf9b', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 21, N'd3cbe1ef-83df-43d8-9d45-7f98e75bc687', N'ac0dbad1-27da-439c-a0a3-778b2086281f')
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'aa609c25-e9ba-432f-aa74-c246f5b63ced', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 22, N'8bdbc2a5-a9ea-4120-8cb1-37dcad14d3ba', N'af746bfe-4295-4d87-8f50-8504f45e298c')
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'c3499e2c-5cf1-4c14-9a23-b5a43323196c', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 20, N'b965600e-123a-4a8f-a62a-3957a2095073', N'ac0dbad1-27da-439c-a0a3-778b2086281f')
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'df3ef62b-e05c-45a0-856b-2e01814a0573', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 20, N'8bdbc2a5-a9ea-4120-8cb1-37dcad14d3ba', N'ac0dbad1-27da-439c-a0a3-778b2086281f')
INSERT [dbo].[schedules] ([schedule_id], [show_time], [end_time], [price], [movie_id], [room_id]) VALUES (N'f26715a9-e7c6-458e-97a4-80df17546c37', CAST(N'2023-03-26T00:00:00.000' AS DateTime), CAST(N'2023-03-26T00:00:00.000' AS DateTime), 20, N'27d16e32-aade-472d-b8df-2b24cd4e02d4', N'47671f19-dc17-4698-a497-73e14b48d0ed')
GO
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (1, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (2, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (3, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (4, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (5, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (6, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (7, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (8, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (9, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (10, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (11, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (12, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (13, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (14, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (15, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (16, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (17, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (18, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (19, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (20, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (21, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (22, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (23, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (24, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (25, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (26, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (27, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (28, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (29, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (30, N'1', N'0', N'normal', N'47671f19-dc17-4698-a497-73e14b48d0ed', N'4062d931-0b1b-45e6-a6de-56db2850ee3d')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (31, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (32, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (33, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (34, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (35, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (36, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (37, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (38, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (39, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (40, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (41, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (42, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (43, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (44, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (45, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (46, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (47, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (48, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (49, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (50, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (51, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (52, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (53, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (54, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (55, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (56, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (57, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (58, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (59, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
INSERT [dbo].[seats] ([seat_id], [row_number], [status], [type_seat], [room_id], [schedule_id]) VALUES (60, N'1', N'0', N'normal', N'9ade6d3d-9e61-4590-b02d-b1a8ad8b18d5', N'5ca8d6da-245c-4ae9-b9fd-9ec8f7436550')
GO
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'3640d005-b799-4ade-9897-1ecc8662c27f', N'27d16e32-aade-472d-b8df-2b24cd4e02d4')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'3640d005-b799-4ade-9897-1ecc8662c27f', N'8bdbc2a5-a9ea-4120-8cb1-37dcad14d3ba')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'3640d005-b799-4ade-9897-1ecc8662c27f', N'92af7976-a420-4817-b86b-3dc972a8f543')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'3640d005-b799-4ade-9897-1ecc8662c27f', N'b965600e-123a-4a8f-a62a-3957a2095073')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'3640d005-b799-4ade-9897-1ecc8662c27f', N'd3cbe1ef-83df-43d8-9d45-7f98e75bc687')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'3640d005-b799-4ade-9897-1ecc8662c27f', N'f930b958-e7ff-4c3f-91d2-929500a43d3f')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'40791b45-d4fa-4ce7-bd47-8c12d86a063d', N'b965600e-123a-4a8f-a62a-3957a2095073')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'40791b45-d4fa-4ce7-bd47-8c12d86a063d', N'd3cbe1ef-83df-43d8-9d45-7f98e75bc687')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'40791b45-d4fa-4ce7-bd47-8c12d86a063d', N'f930b958-e7ff-4c3f-91d2-929500a43d3f')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'537a5fc4-b919-4d7d-a531-27891a8e0aa3', N'8bdbc2a5-a9ea-4120-8cb1-37dcad14d3ba')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'537a5fc4-b919-4d7d-a531-27891a8e0aa3', N'92af7976-a420-4817-b86b-3dc972a8f543')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'537a5fc4-b919-4d7d-a531-27891a8e0aa3', N'b965600e-123a-4a8f-a62a-3957a2095073')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'537a5fc4-b919-4d7d-a531-27891a8e0aa3', N'd3cbe1ef-83df-43d8-9d45-7f98e75bc687')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'e8930b98-6084-46b8-9693-43dcb16ec5b0', N'8bdbc2a5-a9ea-4120-8cb1-37dcad14d3ba')
INSERT [dbo].[shows] ([Id_cinema], [movie_id]) VALUES (N'e8930b98-6084-46b8-9693-43dcb16ec5b0', N'd3cbe1ef-83df-43d8-9d45-7f98e75bc687')
GO
INSERT [dbo].[users] ([user_id], [fullname], [phone], [email], [password], [gender], [address], [birth_day]) VALUES (N'ea53a15d-0eac-458a-8085-e810c5a583ac', N'string', N'admin', N'admin', N'admin', N'string', N'string', CAST(N'2023-03-26' AS Date))
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__users__AB6E61643E206180]    Script Date: 3/26/2023 11:02:14 AM ******/
ALTER TABLE [dbo].[users] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[invoices]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[movie_cast]  WITH CHECK ADD FOREIGN KEY([cast_id])
REFERENCES [dbo].[cast] ([cast_id])
GO
ALTER TABLE [dbo].[movie_cast]  WITH CHECK ADD FOREIGN KEY([movie_id])
REFERENCES [dbo].[movies] ([movie_id])
GO
ALTER TABLE [dbo].[movies]  WITH CHECK ADD FOREIGN KEY([genre_id])
REFERENCES [dbo].[genres] ([genre_id])
GO
ALTER TABLE [dbo].[rooms]  WITH CHECK ADD FOREIGN KEY([Id_cinema])
REFERENCES [dbo].[Cinemas] ([Id_cinema])
GO
ALTER TABLE [dbo].[schedules]  WITH CHECK ADD FOREIGN KEY([movie_id])
REFERENCES [dbo].[movies] ([movie_id])
GO
ALTER TABLE [dbo].[schedules]  WITH CHECK ADD  CONSTRAINT [room_id] FOREIGN KEY([room_id])
REFERENCES [dbo].[rooms] ([room_id])
GO
ALTER TABLE [dbo].[schedules] CHECK CONSTRAINT [room_id]
GO
ALTER TABLE [dbo].[seats]  WITH CHECK ADD FOREIGN KEY([room_id])
REFERENCES [dbo].[rooms] ([room_id])
GO
ALTER TABLE [dbo].[seats]  WITH CHECK ADD  CONSTRAINT [schedule_id] FOREIGN KEY([schedule_id])
REFERENCES [dbo].[schedules] ([schedule_id])
GO
ALTER TABLE [dbo].[seats] CHECK CONSTRAINT [schedule_id]
GO
ALTER TABLE [dbo].[shows]  WITH CHECK ADD FOREIGN KEY([Id_cinema])
REFERENCES [dbo].[Cinemas] ([Id_cinema])
GO
ALTER TABLE [dbo].[shows]  WITH CHECK ADD FOREIGN KEY([movie_id])
REFERENCES [dbo].[movies] ([movie_id])
GO
ALTER TABLE [dbo].[tickets]  WITH CHECK ADD FOREIGN KEY([invoice_id])
REFERENCES [dbo].[invoices] ([invoice_id])
GO
ALTER TABLE [dbo].[tickets]  WITH CHECK ADD FOREIGN KEY([schedule_id])
REFERENCES [dbo].[schedules] ([schedule_id])
GO
ALTER TABLE [dbo].[tickets]  WITH CHECK ADD FOREIGN KEY([seat_id])
REFERENCES [dbo].[seats] ([seat_id])
GO

Use master
Create Database PrayForFood
go
USE [PrayForFood]
GO
/****** Object:  Table [dbo].[tblBranch]    Script Date: 9/1/2021 8:31:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBranch](
	[Branch_ID] [char](4) NOT NULL,
	[Branch_Name] [nvarchar](100) NULL,
	[Branch_Address] [nvarchar](100) NULL,
	[Branch_image] [varchar](100) NULL,
	[Branch_Location_Longitude] [float] NULL,
	[Branch_Location_Latitude] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[Branch_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblMenu]    Script Date: 9/1/2021 8:31:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblMenu](
	[Menu_ID] [char](4) NOT NULL,
	[Menu_Type] [varchar](5) NULL,
	[Food_ID] [char](5) NOT NULL,
	[Food_Name] [nvarchar](50) NULL,
	[Price] [int] NULL,
	[Amount] [float] NULL,
	[Food_Image] [varchar](100) NULL,
 CONSTRAINT [PK__tblMenu__B2D2235F9D50AE5E] PRIMARY KEY CLUSTERED 
(
	[Menu_ID] ASC,
	[Food_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 9/1/2021 8:31:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[Order_ID] [char](100) NOT NULL,
	[Customer] [varchar](50) NULL,
	[Total] [int] NULL,
	[Order_status] [varchar](10) NULL,
	[Order_day] [date] NULL,
	[Restaurant_ID] [char](4) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Order_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrder_details]    Script Date: 9/1/2021 8:31:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder_details](
	[Order_ID] [char](100) NOT NULL,
	[Menu_ID] [char](4) NULL,
	[Food_ID] [char](5) NOT NULL,
	[Amount] [float] NULL,
	[Price] [int] NULL,
 CONSTRAINT [PK__tblOrder__2AD163DC22048C24] PRIMARY KEY CLUSTERED 
(
	[Order_ID] ASC,
	[Food_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblPayment]    Script Date: 9/1/2021 8:31:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblPayment](
	[Payment_ID] [char](4) NOT NULL,
	[Order_ID] [char](4) NOT NULL,
	[Pay_by] [varchar](50) NULL,
	[Payment_day] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[Payment_ID] ASC,
	[Order_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblReview]    Script Date: 9/1/2021 8:31:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblReview](
	[Menu_ID] [char](4) NOT NULL,
	[Food_ID] [char](5) NOT NULL,
	[Rating] [float] NULL,
	[Review] [nvarchar](150) NULL,
	[Purchase_Amount] [int] NULL,
 CONSTRAINT [PK__tblRevie__B2D2235F7B9C301C] PRIMARY KEY CLUSTERED 
(
	[Menu_ID] ASC,
	[Food_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 9/1/2021 8:31:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[Username] [varchar](50) NOT NULL,
	[U_pass] [varchar](50) NOT NULL,
	[Usertype] [char](1) NOT NULL,
	[Email] [varchar](50) NULL,
	[Phone] [varchar](11) NULL,
	[U_address] [nvarchar](100) NOT NULL,
	[U_image] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblBranch] ([Branch_ID], [Branch_Name], [Branch_Address], [Branch_image], [Branch_Location_Longitude], [Branch_Location_Latitude]) VALUES (N'0001', N'Nhà hàng Qua Môn', N'111 Lê Trọng Tấn, phường Tây Thạnh, quận Tân Phú, TPHCM', NULL, NULL, NULL)
INSERT [dbo].[tblBranch] ([Branch_ID], [Branch_Name], [Branch_Address], [Branch_image], [Branch_Location_Longitude], [Branch_Location_Latitude]) VALUES (N'0002', N'Nhà hàng Qua Môn 2', N'303 Cộng Hoà, phường 13, quận Tân Bình, TPHCM', NULL, NULL, NULL)
INSERT [dbo].[tblBranch] ([Branch_ID], [Branch_Name], [Branch_Address], [Branch_image], [Branch_Location_Longitude], [Branch_Location_Latitude]) VALUES (N'0003', N'Nhà hàng Qua Môn 3', N'304 Cộng Hoà, phường 13, quận Tân Bình, TPHCM', NULL, NULL, NULL)
INSERT [dbo].[tblBranch] ([Branch_ID], [Branch_Name], [Branch_Address], [Branch_image], [Branch_Location_Longitude], [Branch_Location_Latitude]) VALUES (N'0004', N'Nhà hàng Qua Môn 4', N'305 Cộng Hoà, phường 13, quận Tân Bình, TPHCM', NULL, NULL, NULL)
GO
INSERT [dbo].[tblMenu] ([Menu_ID], [Menu_Type], [Food_ID], [Food_Name], [Price], [Amount], [Food_Image]) VALUES (N'0001', N'Food', N'item1', N'Mocha', 35000, 1, NULL)
INSERT [dbo].[tblMenu] ([Menu_ID], [Menu_Type], [Food_ID], [Food_Name], [Price], [Amount], [Food_Image]) VALUES (N'0001', N'Food', N'item2', N'Dalgona', 55000, 1, NULL)
INSERT [dbo].[tblMenu] ([Menu_ID], [Menu_Type], [Food_ID], [Food_Name], [Price], [Amount], [Food_Image]) VALUES (N'0001', N'Food', N'item3', N'Americano', 35000, 1, NULL)
INSERT [dbo].[tblMenu] ([Menu_ID], [Menu_Type], [Food_ID], [Food_Name], [Price], [Amount], [Food_Image]) VALUES (N'0001', N'Food', N'item4', N'Chocolate latte', 48000, 1, NULL)
GO
INSERT [dbo].[tblOrder] ([Order_ID], [Customer], [Total], [Order_status], [Order_day], [Restaurant_ID]) VALUES (N'0001                                                                                                ', N'khachhang1', 100000, NULL, CAST(N'2021-02-02' AS Date), N'0001')
INSERT [dbo].[tblOrder] ([Order_ID], [Customer], [Total], [Order_status], [Order_day], [Restaurant_ID]) VALUES (N'khachhang12021-09-010001                                                                            ', N'khachhang1', 125000, NULL, CAST(N'2021-09-01' AS Date), N'0001')
INSERT [dbo].[tblOrder] ([Order_ID], [Customer], [Total], [Order_status], [Order_day], [Restaurant_ID]) VALUES (N'khachhang12021-09-01T20:10:07.9900001                                                               ', N'khachhang1', 125000, NULL, CAST(N'2021-09-01' AS Date), N'0001')
INSERT [dbo].[tblOrder] ([Order_ID], [Customer], [Total], [Order_status], [Order_day], [Restaurant_ID]) VALUES (N'khachhang12021-09-01T20:18:42.4980001                                                               ', N'khachhang1', 125000, NULL, CAST(N'2021-09-01' AS Date), N'0001')
INSERT [dbo].[tblOrder] ([Order_ID], [Customer], [Total], [Order_status], [Order_day], [Restaurant_ID]) VALUES (N'khachhang12021-09-01T20:20:29.1200001                                                               ', N'khachhang1', 180000, NULL, CAST(N'2021-09-01' AS Date), N'0001')
INSERT [dbo].[tblOrder] ([Order_ID], [Customer], [Total], [Order_status], [Order_day], [Restaurant_ID]) VALUES (N'khachhang12021-09-01T20:21:31.0770001                                                               ', N'khachhang1', 180000, NULL, CAST(N'2021-09-01' AS Date), N'0001')
INSERT [dbo].[tblOrder] ([Order_ID], [Customer], [Total], [Order_status], [Order_day], [Restaurant_ID]) VALUES (N'khachhang12021-09-01T20:25:19.3390001                                                               ', N'khachhang1', 180000, NULL, CAST(N'2021-09-01' AS Date), N'0001')
INSERT [dbo].[tblOrder] ([Order_ID], [Customer], [Total], [Order_status], [Order_day], [Restaurant_ID]) VALUES (N'khachhang12021-09-01T20:29:11.4960003                                                               ', N'khachhang1', 228000, NULL, CAST(N'2021-09-01' AS Date), N'0003')
GO
INSERT [dbo].[tblOrder_details] ([Order_ID], [Menu_ID], [Food_ID], [Amount], [Price]) VALUES (N'0001                                                                                                ', NULL, N'0001 ', 1, 2000)
INSERT [dbo].[tblOrder_details] ([Order_ID], [Menu_ID], [Food_ID], [Amount], [Price]) VALUES (N'0001                                                                                                ', NULL, N'0002 ', 1, 2000)
INSERT [dbo].[tblOrder_details] ([Order_ID], [Menu_ID], [Food_ID], [Amount], [Price]) VALUES (N'khachhang12021-09-010001                                                                            ', NULL, N'item2', 1, 55000)
INSERT [dbo].[tblOrder_details] ([Order_ID], [Menu_ID], [Food_ID], [Amount], [Price]) VALUES (N'khachhang12021-09-01T20:10:07.9900001                                                               ', NULL, N'item2', 1, 55000)
INSERT [dbo].[tblOrder_details] ([Order_ID], [Menu_ID], [Food_ID], [Amount], [Price]) VALUES (N'khachhang12021-09-01T20:25:19.3390001                                                               ', NULL, N'item1', 2, 35000)
INSERT [dbo].[tblOrder_details] ([Order_ID], [Menu_ID], [Food_ID], [Amount], [Price]) VALUES (N'khachhang12021-09-01T20:25:19.3390001                                                               ', NULL, N'item2', 2, 55000)
INSERT [dbo].[tblOrder_details] ([Order_ID], [Menu_ID], [Food_ID], [Amount], [Price]) VALUES (N'khachhang12021-09-01T20:29:11.4960003                                                               ', NULL, N'item1', 2, 35000)
INSERT [dbo].[tblOrder_details] ([Order_ID], [Menu_ID], [Food_ID], [Amount], [Price]) VALUES (N'khachhang12021-09-01T20:29:11.4960003                                                               ', NULL, N'item2', 2, 55000)
INSERT [dbo].[tblOrder_details] ([Order_ID], [Menu_ID], [Food_ID], [Amount], [Price]) VALUES (N'khachhang12021-09-01T20:29:11.4960003                                                               ', NULL, N'item4', 1, 48000)
GO
INSERT [dbo].[tblUser] ([Username], [U_pass], [Usertype], [Email], [Phone], [U_address], [U_image]) VALUES (N'admin', N'admin123', N'0', NULL, NULL, N'111 Nguyễn Thị Tú, phường Bình Hưng Hoà B, quận Bình Tân, TPHCM', N'avt.png')
INSERT [dbo].[tblUser] ([Username], [U_pass], [Usertype], [Email], [Phone], [U_address], [U_image]) VALUES (N'khachhang1', N'111111111', N'1', NULL, NULL, N'12 Nguyễn Hồng Đào, phường 14, quận Tân Bình, TPHCM', N'avt.png')
INSERT [dbo].[tblUser] ([Username], [U_pass], [Usertype], [Email], [Phone], [U_address], [U_image]) VALUES (N'ngocchinh', N'12344321', N'1', NULL, NULL, N'ABCCCCC', N'avt.png')
INSERT [dbo].[tblUser] ([Username], [U_pass], [Usertype], [Email], [Phone], [U_address], [U_image]) VALUES (N'user1', N'password', N'1', NULL, NULL, N'ABCCCCC', N'avt.png')
INSERT [dbo].[tblUser] ([Username], [U_pass], [Usertype], [Email], [Phone], [U_address], [U_image]) VALUES (N'user2', N'password', N'1', NULL, NULL, N'ABCCCCC', N'avt.png')
INSERT [dbo].[tblUser] ([Username], [U_pass], [Usertype], [Email], [Phone], [U_address], [U_image]) VALUES (N'user3', N'password', N'1', NULL, NULL, N'ABCCCCC', N'avt.png')
INSERT [dbo].[tblUser] ([Username], [U_pass], [Usertype], [Email], [Phone], [U_address], [U_image]) VALUES (N'user4', N'password', N'1', NULL, NULL, N'ABCCCCC', N'avt.png')
INSERT [dbo].[tblUser] ([Username], [U_pass], [Usertype], [Email], [Phone], [U_address], [U_image]) VALUES (N'user5', N'password', N'1', NULL, NULL, N'ABCCCCC', N'avt.png')
GO
ALTER TABLE [dbo].[tblUser] ADD  CONSTRAINT [df_image]  DEFAULT ('avt.png') FOR [U_image]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD FOREIGN KEY([Customer])
REFERENCES [dbo].[tblUser] ([Username])
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblBranch] FOREIGN KEY([Restaurant_ID])
REFERENCES [dbo].[tblBranch] ([Branch_ID])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblBranch]
GO
ALTER TABLE [dbo].[tblOrder_details]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_details_tblOrder1] FOREIGN KEY([Order_ID])
REFERENCES [dbo].[tblOrder] ([Order_ID])
GO
ALTER TABLE [dbo].[tblOrder_details] CHECK CONSTRAINT [FK_tblOrder_details_tblOrder1]
GO
ALTER TABLE [dbo].[tblPayment]  WITH CHECK ADD FOREIGN KEY([Pay_by])
REFERENCES [dbo].[tblUser] ([Username])
GO
ALTER TABLE [dbo].[tblReview]  WITH CHECK ADD  CONSTRAINT [FK_tblReview_tblMenu] FOREIGN KEY([Menu_ID], [Food_ID])
REFERENCES [dbo].[tblMenu] ([Menu_ID], [Food_ID])
GO
ALTER TABLE [dbo].[tblReview] CHECK CONSTRAINT [FK_tblReview_tblMenu]
GO
ALTER TABLE [dbo].[tblMenu]  WITH CHECK ADD  CONSTRAINT [CK_tblMenu] CHECK  (([Menu_Type]='food' OR [Menu_Type]='drink'))
GO
ALTER TABLE [dbo].[tblMenu] CHECK CONSTRAINT [CK_tblMenu]
GO
ALTER TABLE [dbo].[tblMenu]  WITH CHECK ADD  CONSTRAINT [CK_tblMenu_1] CHECK  (([Price]>=(0)))
GO
ALTER TABLE [dbo].[tblMenu] CHECK CONSTRAINT [CK_tblMenu_1]
GO
ALTER TABLE [dbo].[tblMenu]  WITH CHECK ADD  CONSTRAINT [CK_tblMenu_2] CHECK  (([Amount]>=(0)))
GO
ALTER TABLE [dbo].[tblMenu] CHECK CONSTRAINT [CK_tblMenu_2]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [CK_tblOrder] CHECK  (([Order_status]='paid' OR [Order_status]='not paid'))
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [CK_tblOrder]
GO
ALTER TABLE [dbo].[tblOrder_details]  WITH CHECK ADD  CONSTRAINT [CK_tblOrder_details] CHECK  (([Amount]>=(0)))
GO
ALTER TABLE [dbo].[tblOrder_details] CHECK CONSTRAINT [CK_tblOrder_details]
GO
ALTER TABLE [dbo].[tblOrder_details]  WITH CHECK ADD  CONSTRAINT [CK_tblOrder_details_1] CHECK  (([Price]>=(0)))
GO
ALTER TABLE [dbo].[tblOrder_details] CHECK CONSTRAINT [CK_tblOrder_details_1]
GO
ALTER TABLE [dbo].[tblPayment]  WITH CHECK ADD  CONSTRAINT [CK_tblPayment] CHECK  (([Pay_by] IS NOT NULL))
GO
ALTER TABLE [dbo].[tblPayment] CHECK CONSTRAINT [CK_tblPayment]
GO
ALTER TABLE [dbo].[tblReview]  WITH CHECK ADD  CONSTRAINT [CK_tblReview] CHECK  (([Purchase_Amount]>=(0)))
GO
ALTER TABLE [dbo].[tblReview] CHECK CONSTRAINT [CK_tblReview]
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD  CONSTRAINT [CK_tblUser] CHECK  (([Usertype]=(0) OR [Usertype]=(1)))
GO
ALTER TABLE [dbo].[tblUser] CHECK CONSTRAINT [CK_tblUser]
GO

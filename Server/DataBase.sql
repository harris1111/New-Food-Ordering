USE [master]
CREATE DATABASE [PrayForFood]
GO
USE [PrayForFood]
GO
/****** Object:  Table [dbo].[tblBranch]    Script Date: 22/06/2021 4:46:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBranch](
	[Branch_ID] [char](4) NOT NULL,
	[Branch_Name] [nvarchar](100) NULL,
	[Branch_Address] [nvarchar](100) NULL,
	[Branch_image] [image] NULL,
PRIMARY KEY CLUSTERED 
(
	[Branch_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblMenu]    Script Date: 22/06/2021 4:46:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblMenu](
	[Menu_ID] [char](4) NOT NULL,
	[Menu_Type] [varchar](5) NULL,
	[Food_ID] [char](4) NOT NULL,
	[Food_Name] [nvarchar](50) NULL,
	[Price] [int] NULL,
	[Amount] [float] NULL,
	[Food_Image] [image] NULL,
PRIMARY KEY CLUSTERED 
(
	[Menu_ID] ASC,
	[Food_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 22/06/2021 4:46:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[Order_ID] [char](4) NOT NULL,
	[Menu_ID] [char](4) NOT NULL,
	[Customer] [varchar](50) NULL,
	[Total] [int] NULL,
	[Order_status] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[Order_ID] ASC,
	[Menu_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrder_details]    Script Date: 22/06/2021 4:46:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder_details](
	[Order_ID] [char](4) NOT NULL,
	[Menu_ID] [char](4) NULL,
	[Food_ID] [char](4) NOT NULL,
	[Amount] [float] NULL,
	[Price] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Order_ID] ASC,
	[Food_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblPayment]    Script Date: 22/06/2021 4:46:18 CH ******/
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
/****** Object:  Table [dbo].[tblUser]    Script Date: 22/06/2021 4:46:18 CH ******/
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
	[U_image] [image] NULL,
PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD FOREIGN KEY([Customer])
REFERENCES [dbo].[tblUser] ([Username])
GO
ALTER TABLE [dbo].[tblOrder_details]  WITH CHECK ADD FOREIGN KEY([Order_ID], [Menu_ID])
REFERENCES [dbo].[tblOrder] ([Order_ID], [Menu_ID])
GO
ALTER TABLE [dbo].[tblOrder_details]  WITH CHECK ADD FOREIGN KEY([Menu_ID], [Food_ID])
REFERENCES [dbo].[tblMenu] ([Menu_ID], [Food_ID])
GO
ALTER TABLE [dbo].[tblPayment]  WITH CHECK ADD FOREIGN KEY([Pay_by])
REFERENCES [dbo].[tblUser] ([Username])
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
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD  CONSTRAINT [CK_tblUser] CHECK  (([Usertype]=(0) OR [Usertype]=(1)))
GO
ALTER TABLE [dbo].[tblUser] CHECK CONSTRAINT [CK_tblUser]
GO

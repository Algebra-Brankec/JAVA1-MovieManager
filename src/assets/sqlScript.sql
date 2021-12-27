CREATE DATABASE [Movies]
GO
USE [Movies]
GO
/****** Object:  Table [dbo].[Actor]    Script Date: 26/12/2021 16:18:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Actor](
	[IDActor] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](90) NULL,
	[MovieID] [int] NOT NULL,
 CONSTRAINT [PK_Actor] PRIMARY KEY CLUSTERED 
(
	[IDActor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Director]    Script Date: 26/12/2021 16:18:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Director](
	[IDDirector] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](90) NULL,
	[MovieID] [int] NOT NULL,
 CONSTRAINT [PK_Director] PRIMARY KEY CLUSTERED 
(
	[IDDirector] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Genre]    Script Date: 26/12/2021 16:18:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Genre](
	[IDGenre] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](90) NULL,
	[MovieID] [int] NOT NULL,
 CONSTRAINT [PK_Genre] PRIMARY KEY CLUSTERED 
(
	[IDGenre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Movie]    Script Date: 26/12/2021 16:18:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Movie](
	[IDMovie] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](300) NULL,
	[PublishedDate] [nvarchar](90) NULL,
	[Description] [nvarchar](900) NULL,
	[OrigName] [nvarchar](90) NULL,
	[Time] [int] NULL,
	[PicturePath] [nvarchar](90) NULL,
	[Link] [nvarchar](300) NULL,
	[GUID] [nvarchar](900) NULL,
	[StartDate] [nvarchar](90) NULL,
 CONSTRAINT [PK__MOVIE__BACCEC76810C8372] PRIMARY KEY CLUSTERED 
(
	[IDMovie] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 26/12/2021 16:18:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[IDRole] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[IDRole] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 26/12/2021 16:18:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[IDUser] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](100) NULL,
	[Password] [nvarchar](50) NULL,
	[RoleID] [int] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[IDUser] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Actor]  WITH CHECK ADD  CONSTRAINT [FK_Actor_Movie] FOREIGN KEY([MovieID])
REFERENCES [dbo].[Movie] ([IDMovie])
GO
ALTER TABLE [dbo].[Actor] CHECK CONSTRAINT [FK_Actor_Movie]
GO
ALTER TABLE [dbo].[Director]  WITH CHECK ADD  CONSTRAINT [FK_Director_Movie] FOREIGN KEY([MovieID])
REFERENCES [dbo].[Movie] ([IDMovie])
GO
ALTER TABLE [dbo].[Director] CHECK CONSTRAINT [FK_Director_Movie]
GO
ALTER TABLE [dbo].[Genre]  WITH CHECK ADD  CONSTRAINT [FK_Genre_Movie] FOREIGN KEY([MovieID])
REFERENCES [dbo].[Movie] ([IDMovie])
GO
ALTER TABLE [dbo].[Genre] CHECK CONSTRAINT [FK_Genre_Movie]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([IDRole])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Role]
GO
/****** Object:  StoredProcedure [dbo].[createMovie]    Script Date: 26/12/2021 16:18:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[createMovie]
	@Title NVARCHAR(300),
	@PublishedDate NVARCHAR(90),
	@Description NVARCHAR(900),
	@OrigName NVARCHAR(90),
	@Time INT,
	@PicturePath NVARCHAR(90),
	@Link NVARCHAR(300),
	@GUID NVARCHAR(900),
	@StartDate NVARCHAR(90),

	@Id INT OUTPUT
AS 
BEGIN 
	INSERT INTO Movie VALUES(@Title, @PublishedDate, @Description, @OrigName, @Time, @PicturePath, @Link, @GUID, @StartDate)
	SET @Id = SCOPE_IDENTITY()
END
GO
/****** Object:  StoredProcedure [dbo].[deleteMovie]    Script Date: 26/12/2021 16:18:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[deleteMovie]
	@IdMovie INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Movie
	WHERE 
		IDMovie = @IdMovie
END
GO
/****** Object:  StoredProcedure [dbo].[selectMovie]    Script Date: 26/12/2021 16:18:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[selectMovie]
	@IdMovie INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Movie
	WHERE 
		IDMovie = @IdMovie
END
GO
/****** Object:  StoredProcedure [dbo].[selectMovies]    Script Date: 26/12/2021 16:18:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[selectMovies]
AS 
BEGIN 
	SELECT * FROM Movie
END
GO
/****** Object:  StoredProcedure [dbo].[updateMovie]    Script Date: 26/12/2021 16:18:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[updateMovie]
	@Title NVARCHAR(300),
	@PublishedDate NVARCHAR(90),
	@Description NVARCHAR(900),
	@OrigName NVARCHAR(90),
	@Time INT,
	@PicturePath NVARCHAR(90),
	@Link NVARCHAR(300),
	@GUID NVARCHAR(900),
	@StartDate NVARCHAR(90),

	@IdMovie INT
	 
AS 
BEGIN 
	UPDATE Movie SET 
		Title = @Title,
		PublishedDate = @PublishedDate,
		Description = @Description,
		OrigName = @OrigName,
		Time = @Time,
		PicturePath = @PicturePath,
		Link = @Link,
		GUID = @GUID,
		StartDate = @StartDate
	
	WHERE 
		IDMovie = @IdMovie
END
GO

CREATE PROCEDURE [dbo].[createActor]
	@Name NVARCHAR(90),
	@MovieID int,

	@Id INT OUTPUT
AS 
BEGIN 
	INSERT INTO Actor VALUES(@Name, @MovieID)
	SET @Id = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE [dbo].[deleteActor]
	@IdActor INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Actor
	WHERE 
		IDActor = @IdActor
END
GO

CREATE PROCEDURE [dbo].[selectActor]
	@IdActor INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Actor
	WHERE 
		IDActor = @IdActor
END
GO

CREATE PROCEDURE [dbo].[selectActorsFromMovie]
        @MovieId INT
AS 
BEGIN 
	SELECT * FROM Actor WHERE MovieId = @MovieId
END
GO


CREATE PROCEDURE [dbo].[updateActor]
	@Name NVARCHAR(90),
	@MovieID int,

	@IdActor INT 
AS 
BEGIN 
	UPDATE Actor SET 
		Name = @Name,
		MovieID = @MovieID
	
	WHERE 
		IDActor = @IdActor
END
GO

CREATE PROCEDURE [dbo].[createDirector]
	@Name NVARCHAR(90),
	@MovieID int,

	@Id INT OUTPUT
AS 
BEGIN 
	INSERT INTO Director VALUES(@Name, @MovieID)
	SET @Id = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE [dbo].[deleteDirector]
	@IdDirector INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Director
	WHERE 
		IDDirector = @IdDirector
END
GO

CREATE PROCEDURE [dbo].[selectDirector]
	@IdDirector INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Director
	WHERE 
		IDDirector = @IdDirector
END
GO

CREATE PROCEDURE [dbo].[selectDirectorsFromMovie]
        @MovieId INT
AS 
BEGIN 
	SELECT * FROM Director WHERE MovieId = @MovieId
END
GO


CREATE PROCEDURE [dbo].[updateDirector]
	@Name NVARCHAR(90),
	@MovieID int,

	@IdDirector INT 
AS 
BEGIN 
	UPDATE Director SET 
		Name = @Name,
		MovieID = @MovieID
	
	WHERE 
		IDDirector = @IdDirector
END
GO

CREATE PROCEDURE [dbo].[createGenre]
	@Name NVARCHAR(90),
	@MovieID int,

	@Id INT OUTPUT
AS 
BEGIN 
	INSERT INTO Genre VALUES(@Name, @MovieID)
	SET @Id = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE [dbo].[deleteGenre]
	@IdGenre INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Genre
	WHERE 
		IDGenre = @IdGenre
END
GO

CREATE PROCEDURE [dbo].[selectGenre]
	@IdGenre INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Genre
	WHERE 
		IDGenre = @IdGenre
END
GO

CREATE PROCEDURE [dbo].[selectGenresFromMovie]
        @MovieId INT
AS 
BEGIN 
	SELECT * FROM Genre WHERE MovieId = @MovieId
END
GO


CREATE PROCEDURE [dbo].[updateGenre]
	@Name NVARCHAR(90),
	@MovieID int,

	@IdGenre INT 
AS 
BEGIN 
	UPDATE Genre SET 
		Name = @Name,
		MovieID = @MovieID
	
	WHERE 
		IDGenre = @IdGenre
END
GO

USE [master]
GO
ALTER DATABASE [Movies] SET  READ_WRITE 
GO

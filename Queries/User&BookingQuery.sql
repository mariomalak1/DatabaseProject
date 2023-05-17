create table [User](
	UserID int identity(1,1) primary key,
	FirstName varchar(15) not null,
	LastName varchar(15) not null,
	UserPassword varchar(100) not null,
	Email varchar(30),
	[Role] varchar(15) not null,
);


create table Booking(
	BookingID int identity(1,1),
	seatID int,
	userID int,
	foreign key(UserID) references [User](UserID), 
/*
	update the table with this when seat is created
	foreign key(SeatID) references Seat(SeatID),
*/
);


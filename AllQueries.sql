/*
create table [User](
	UserID int identity(1,1) primary key,
	FirstName varchar(15) not null,
	LastName varchar(15) not null,
	UserPassword varchar(100) not null,
	Email varchar(30),
	[Role] varchar(15) not null,
);

create table Trip(
 TripID int identity(1,1)  primary key ,
 Source varchar(20)not null,
 Dest varchar(20) not null,
 DateOftrip date not null,
 StartTime time not null,
 EndTime time not null,
);

create table Train (
	TrainID int identity(1,1) primary key,
	capacity int not null,
	tripID int not null,
	constraint tripID foreign key(tripID) References Trip(TripID)
);

CREATE TABLE Seat (
  SeatID INT identity(1,1) PRIMARY KEY,
  Booked BIT NOT NULL,
  Class VARCHAR(20) NOT NULL,
  Price AS (
    CASE Class
      WHEN 'Coach' THEN 100.00
      WHEN 'Business' THEN 200.00
      WHEN 'First' THEN 300.00
      WHEN 'Rooms' THEN 400.00
      ELSE 0.00
    END
  ),
  trainId INT,
  FOREIGN KEY (TrainId) REFERENCES Train(TrainID)
  --updated when the train created
);

create table Booking(
	BookingID int identity(1,1),
	seatID int,
	userID int,
	tripID int,
	foreign key(seatID) references [User](UserID), 
	foreign key(userID) references Seat(SeatID),
	foreign key(tripID) references Trip(TripID),
);

create table City(
	tripID int,
	Ariving_time date,
	city_name varchar(20) not null,
	foreign key(TripID) references Trip(TripID),
);
*/
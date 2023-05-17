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
	foreign key(SeatID) references Seat(SeatID),
);
create table Train (
	TrainId int identity(1,1) primary key,
	capacity int not null,
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
  /*TrainId INT,
  FOREIGN KEY (TrainId) REFERENCES Train(TrainID)
  */
  --updated when the train created
);
alter table Seat 
add TrainID int 
constraint seat_train_fk foreign key(TrainId)
references Train



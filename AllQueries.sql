/*create table [User](
	UserID int identity(1,1) primary key,
	FirstName varchar(15) not null,
	LastName varchar(15) not null,
	UserPassword varchar(100) not null,
	Email varchar(30),
	[Role] varchar(15) not null,
);

create table City(
	CityID int identity(1,1) primary key,
	city_name varchar(20) not null,
);

create table Trip(
	TripID int identity(1,1)  primary key ,
	SourceID int not null,
	DestenationID int not null,
	DateOftrip date,
	StartTime time,
	
	foreign key (SourceID) References City(CityID),
	foreign key (DestenationID) References City(CityID),
);

create table Visit(
	tripID int,
	cityID int,

	visit_Date date,
    visit_Time time,

	foreign key(tripID) references Trip(TripID),
	foreign key(cityID) references City(CityID),
);

create table Train (
	TrainID int identity(1,1) primary key,
	capacity int not null,
	tripID int not null,
	PricePerSeat float not null,
	constraint tripID foreign key(tripID) References Trip(TripID)
);

create table Booking(
	BookingID int identity(1,1),
	trainID int,
	userID int,
    NumberOfSeats int,
	foreign key(userID) references [User](UserID), 
	foreign key(trainID) references Train(TrainID),
);
*/
/*
alter table Visit
add visit_Time TIME not NULL
alter table Visit
add visit_Date DATE not NULL
*/
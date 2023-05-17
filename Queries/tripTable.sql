/*create trip table*/
create table trip(
 TripId int identity(1,1)  primary key ,
 Source varchar(15)not null,
 Dest varchar(15) not null,
 DateOftrip date not null,
 StartTime time not null,
 EndTime time not null,
 FOREIGN key (TrainId) REFERENCES train(TrainId) ,
);
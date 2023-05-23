/* to show how many for each booking done for each city where it will be the destination of the trip */ 
SELECT City.city_name As 'Destination City', COUNT(BookingID) AS BookingCount FROM City
join Trip on City.CityID = DestenationID  
join Train on Train.tripID = Trip.TripID
Join Booking on Train.TrainID = Booking.trainID
Group by(City.city_name)
Order By(BookingCount) DESC;

/* to show how many for each booking done for each city where it will be the source of the trip */ 
SELECT City.city_name As 'Source City', COUNT(BookingID) AS BookingCount FROM City
join Trip on City.CityID = SourceID  
join Train on Train.tripID = Trip.TripID
join Booking on Train.TrainID = Booking.trainID
Group by(City.city_name)
Order By(BookingCount) DESC;

/* Get All Revnue */
select Sum(Booking.NumberOfSeats * Train.PricePerSeat) As 'All Revenu' from Train, Booking, Trip, Visit
Where Booking.trainID = Train.TrainID
Order By('All Revenu') DESC;


/* Get All Revnue In Specific Start Date In Trip */
SELECT Trip.TripID AS 'Trip ID', SourceCity.city_name AS 'Source City',
    DestCity.city_name AS 'Destination City',
    SUM(Booking.NumberOfSeats * Train.PricePerSeat) AS 'Revenue' FROM Train
JOIN Booking ON Booking.trainID = Train.TrainID
JOIN Trip ON Trip.TripID = Train.tripID
JOIN City AS SourceCity ON SourceCity.CityID = Trip.SourceID
JOIN City AS DestCity ON DestCity.CityID = Trip.DestenationID
JOIN Visit ON Visit.tripID = Trip.TripID AND Visit.visit_date = '2023-05-27'
GROUP BY Trip.TripID, SourceCity.city_name, DestCity.city_name
ORDER BY Revenue DESC;


/* Get All Revnue For All Trains */
select Train.TrainID As 'Train ID', SUM(Booking.NumberOfSeats * Train.PricePerSeat) As 'Revenu' from Train
Join Booking on Booking.trainID = Train.TrainID
Group BY Train.TrainID
Order By(Revenu) DESC;


/* Get All Revnue For All Trains */
select Train.TrainID As 'Train ID', SoruceCity.city_name AS 'First Station', DestinationCity.city_name AS 'Last Station', SUM(Booking.NumberOfSeats * Train.PricePerSeat) As 'Revenu' from Train
Join Booking on Booking.trainID = Train.TrainID
join Trip on Trip.TripID = Train.tripID
Join City As SoruceCity on SoruceCity.CityID = Trip.SourceID
Join City As DestinationCity on SoruceCity.CityID = Trip.SourceID
Group BY Train.TrainID, SoruceCity.city_name ,DestinationCity.city_name
Order By(Revenu) DESC;

/* Show Booking For Every Train And It's Revenue */
select Train.TrainID As 'Train ID', COUNT(Booking.BookingID) As 'Booking Number', SUM(Booking.NumberOfSeats * Train.PricePerSeat) As 'Revenu' from Train
Join Booking on Booking.trainID = Train.TrainID
Group BY Train.TrainID
Order By('Booking Number') DESC;

/* Show Trains And It's Booking Revenue */
select Train.TrainID As 'Train ID', COUNT(Booking.BookingID) As 'Booking Number', SUM(Booking.NumberOfSeats * Train.PricePerSeat) As 'Revenu' from Train
Join Booking on Booking.trainID = Train.TrainID
Group BY Train.TrainID
Order By('Booking Number') DESC;

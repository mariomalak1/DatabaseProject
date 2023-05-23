/* to show how many for each booking done for each city where it will be the destination of the trip */ 
SELECT City.city_name As 'Destination City', COUNT(BookingID) AS BookingCount FROM City
join Trip on City.CityID = DestenationID  
join Train on Train.tripID = Trip.TripID
Join Booking on Train.TrainID = Booking.trainID
Group by(City.city_name);


/* to show how many for each booking done for each city where it will be the source of the trip */ 
SELECT City.city_name As 'Source City', COUNT(BookingID) AS BookingCount FROM City
join Trip on City.CityID = SourceID  
join Train on Train.tripID = Trip.TripID
join Booking on Train.TrainID = Booking.trainID
Group by(City.city_name);



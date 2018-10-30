# Project - Distance between cities

The objective of this project is to provide a webservice that returns a list of cities, combined in pairs and the given distance between them.

For instance:

Consider the cities: A, B e C

The return of the webservice will be:

```
A B 20 // Where 20 is the distance between the two cities
A C 30
B C 10
```

The distance can be returned in kilometers or miles and in the JSON or XML format, according to what was informed in the request.

And the returned list will not consider repetitions like "B A 20" or "A A 0".

## Endpoint

The webservice can be accessed through the URL: http://localhost:8080/{unity}/{mediaType}

The parameters of the URL "unity" and "mediaType" indicates respectively the options of distance unity and response format that the webservice can return.

The parameter "unity" expects to receive the values "km" to calculates the distances in kilometers, or "mi" to calculates the distances in miles.

The parameter "mediaType" expects to receive the values "json" or "xml" to answer in the respective format.

Possible requests:
* http://localhost:8080/km/json
* http://localhost:8080/km/xml
* http://localhost:8080/mi/json
* http://localhost:8080/mi/xml

## SQL

Use the following SQL commands to create the needed structure of the database. MySQL was the database used with this project, but it can be easily replaced.

```sql
create table project;

use project;

create table if not exists city (
	id int not null auto_increment primary key,
	name varchar(80) not null,
	latitude decimal(11, 8) not null,
	longitude decimal(11, 8) not null
);
```

The following cities was used as example, but is possible insert another according with the needs.

```sql
insert into city (name, latitude, longitude) values ('ANGRA DOS REIS', -23.00667, -44.31806);
insert into city (name, latitude, longitude) values ('ARACOIABA DA SERRA', -23.5086476, -47.608569699999975);
insert into city (name, latitude, longitude) values ('BARUERI', -23.5113691, -46.872941999999966);
insert into city (name, latitude, longitude) values ('CAMPINAS', -22.90993839999999, -47.06263319999999);
insert into city (name, latitude, longitude) values ('CAPELA DO ALTO', -23.4689594, -47.739289600000006);
insert into city (name, latitude, longitude) values ('ITAPEVA', -23.9793102, -48.87689969999997);
insert into city (name, latitude, longitude) values ('ITU', -23.2639728, -47.29970850000001);
insert into city (name, latitude, longitude) values ('JUNDIAI', -23.1857076, -46.89780569999999);
insert into city (name, latitude, longitude) values ('RIBEIRAO PRETO', -21.2457293, -47.803228399999966);
insert into city (name, latitude, longitude) values ('SALTO', -23.2000684, -47.29354860000001);
insert into city (name, latitude, longitude) values ('SALTO DE PIRAPORA', -23.6443472, -47.5720968);
insert into city (name, latitude, longitude) values ('SOROCABA', -23.5015299, -47.45256029999996);
insert into city (name, latitude, longitude) values ('VOTORANTIM', -23.540286, -47.4445978);
```

## Combination Algorithm

The combination algorithm written to this project to combine the pairs of cities is very simple and uses just two loops (for):

```java
for (indexA = 0; indexA < N; indexA++) {
	for (indexB = indexA + 1; indexB < N; indexB++) {
		cityA = cities[indexA];
		cityB = cities[indexB];
		
		println(cityA, cityB);
	}
}
```

The first loop walks through the list of cities and in each iteration retrieves the first city of the pair, from 0 (first item of the list) to N (total number of cities). The second loop walks through the list of cities to retrieve the second city of the pair, however, does begin at 0, but from the current position of the first loop plus one, that is, if the first loop is at position 3, the second loop will start from the position 4 (indexA + 1).

Therefore, this simple algorithm avoids repetitions of pairs, such as "A B" and "B A" or "A A", which results in a better performance in the webservice, given that will not be necessary to recalculate the repeated distances, such as, of the city A to the B and then of city B to the A or yet of the city A to itself.

The calculation to determinate the amount of cities combinations is:

```
C n,2 = n! / 2! * (n - 2)!
```

Whew "n" is the number of cities.

## Distance Calculation

The distance calculation used takes into consideration the Earth curvature, that makes the calculation more accurate, in comparison to an simpler calculation that only takes into consideration the coordinates.

```
// Converts the latitudes from degrees into radians
latitudeAInRad = latitudeA / 180.0 * PI
latitudeBInRad = latitudeB / 180.0 * PI

// Variation of longitudes
deltaLongitude = longitudeA - longitudeB
deltaLongitudeInRad = deltaLongitude / 180.0 * PI

// Calculates the sine and cosine
sinLatitudeA = sin(latitudeAInRad)
cosLatitudeA = cos(latitudeAInRad)
sinLatitudeB = sin(latitudeBInRad)
cosLatitudeB = cos(latitudeBInRad)
cosDeltaLongitude = cos(deltaLongitudeInRad)

// Earth radius in kilometers. For miles use 3959.
radius = 6371

// Calculates the distance between the points
arccos(cosLatitudeA * cosLatitudeB * cosDeltaLongitude + sinLatitudeA * sinLatitudeB) * radius
```

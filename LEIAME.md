# Projeto - Distância entre cidades

O objeto do projeto é disponibilizar um webservice que retorne uma lista de cidades, combinadas em pares e com suas respectivas distâncias entre elas.

Por exemplo:
 Considere as cidades: A, B e C
 O retorno do webservice deverá ser:
  A B 20 (onde 20 é a distância entre as duas cidades)
  A C 30
  B C 10

A distância pode ser retornada em quilômetros ou milhas e no formato JSON ou XML, de acordo com o que for informado na requisição.

E a lista de retorno não considera repetições como "B A 20" ou "A A 0".

## Endpoint

O webservice pode ser acessado através da URL: http://localhost:8080/{unity}/{mediaType}

Os parâmetros de URL "unity" e "mediaType" indicam respectivamente as opções de unidade de distância e formato de resposta que o webservice pode retornar.

O parâmetro "unity" espera receber os valores "km" para calcular as distâncias em quilômetros, ou "mi" para calcular as distâncias em milhas.

O parâmetro "mediaType" esperar receber os valores "json" ou "xml" para responder no respectivo formato.

Possíveis de requisições:
http://localhost:8080/km/json
http://localhost:8080/km/xml
http://localhost:8080/mi/json
http://localhost:8080/mi/xml

## SQL

Use os comandos SQL a seguir para criar a estrutura do banco de dados necessária. MySQL foi o banco de dados utilizado com esse projeto, mas pode ser facilmente substituído.

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

As seguintes cidades foram usadas como exemplo, mas é possível inserir outras de acordo com a necessidade.

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

## Algorítmo de combinação

O algoritmo de combinação escrito para esse projeto para combinar os pares de cidades é bem simples e utiliza apenas dois laços de repetição (for):

```java
for (indexA = 0; indexA < N; indexA++) {
	for (indexB = indexA + 1; indexB < N; indexB++) {
		cityA = cities[indexA];
		cityB = cities[indexB];
		
		println(cityA, cityB);
	}
}
```

O primeiro laço percorre a lista de cidades e a cada iteração recupera a primeira cidade do par, de 0 (primeiro item da lista) à N (número total de cidades). O segundo laço percorre a lista de cidades para recuperar a segunda cidade do par, porém, não inicia em 0, mas sim da posição atual do primeiro laço mais um, ou seja, se o primeiro laço está na posição 3, o segundo laço vai começar da posição 4 (indexA + 1).

Assim, esse simples algoritmo consegue evitar repetições de pares, tais quais "A B" e "B A" ou "A A", o que resulta em um desempenho melhor no serviço, tendo em vista que não será necessário recalcular distâncias repetidas, como por exemplo, da cidade A para a B e depois da cidade B para a A ou ainda dá cidade A para ela mesma.

O calculo para determinar a quantidade de combinações de cidades é:

```
C n,2 = n! / 2! * (n - 2)!
```

Onde "n" é o número de cidades.

## Cálculo de distância

O cálculo de distância utilizado leva em consideração a curvatura da Terra, isso faz com que o cálculo seja mais preciso, em comparação à um cálculo mais simples que leva em consideração apenas as coordenadas.

```
// Conversão de graus pra radianos das latitudes
latitudeAInRad = latitudeA / 180.0 * PI
latitudeBInRad = latitudeB / 180.0 * PI

// Diferença das longitudes
deltaLongitude = longitudeA - longitudeB
deltaLongitudeInRad = deltaLongitude / 180.0 * PI

// Calcula os senos e cossenos
sinLatitudeA = sen(latitudeAInRad)
cosLatitudeA = cos(latitudeAInRad)
sinLatitudeB = sen(latitudeBInRad)
cosLatitudeB = cos(latitudeBInRad)
cosDeltaLongitude = cos(deltaLongitudeInRad)

// Raio da terra em quilômetros. Para milhas use 3959.
radius = 6371

// Cálcula da distância entre os pontos
arccos(cosLatitudeA * cosLatitudeB * cosDeltaLongitude + sinLatitudeA * sinLatitudeB) * radius
```

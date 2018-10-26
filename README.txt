create table project;

use project;

create table if not exists city (
	id int not null auto_increment primary key,
	name varchar(80) not null,
	latitude decimal(11, 8) not null,
	longitude decimal(11, 8) not null
);

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

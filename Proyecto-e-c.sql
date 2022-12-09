drop table if exists person cascade;
drop table if exists product cascade;
drop table if exists reviews cascade;
drop table if exists shopingcar cascade;

create table Person(
	rut text primary key,
	namePerson text NOT NULL,
	lastName text not null,
	mail text not null unique,
	phone text unique,
	passwordPerson text not null,
	city text not null,
	commune text not null,
	direction text not null,
	image text,
	statePerson boolean not null
);



create table product(
	nameProduct text primary key,
	category text not null,
	description text not null,
	price int not null,
	stock int not null
);

create table reviews(
	nameProduct text,
	foreign key (nameProduct) references product(nameProduct),
	rutPerson text,
	foreign key (rutPerson) references person(rut),
	review text not null
);

create table shopingCar(
	nameProduct text,
	foreign key (nameProduct) references product(nameProduct),
	rutPerson text,
	foreign key (rutPerson) references person(rut),
	quantityPuchased int,
	finalPrice int
);



insert into person values('22564438-5','Baldomero','Castorena','BaldomeroCastorena@gmail.com','56961455733','5038','Monte Patria','Monte Patria','Calle387 Casa58','null','False');
insert into person values('16808732-7','Bartolomé','Guerrero','BartoloméGuerrero@gmail.com','56906753658','1368','Río Hurtado','Los Vilos','Calle262 Casa03','null','True');


insert into product values ('Tablet 9.8G','Tablet','Malo Malardo','4582','22');
insert into product values ('Refrigerador 360G','Refrigerador','Malardo Malardo','1304','11');
insert into product values ('Teclado ePad','Teclado','Malo','2606','18');
insert into product values ('Refrigerador T-800','Refrigerador','Bueno','3424','19');
insert into product values ('Mouse Smart','Mouse','Bueno','4807','21');
insert into product values ('Mouse 360G','Mouse','Buenardo','4361','34');
insert into product values ('Tablet Samsong','Tablet','Buenardo','3819','25');
insert into product values ('Computador T-800','Computador','Buenardo','2832','34');
insert into product values ('Celular ePhone','Celular','Buenardo Buenardo','2701','28');
create table Person(
	rut text primary key,
	namePerson text NOT NULL,
	lastName text not null,
	passwordPerson text not null,
	mail text not null unique,
	statePerson boolean not null,
	direction text not null,
	image text,
	phone int,
	ruthCompany text,
	passwordCompany text
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
	review text
);

create table shopingCar(
	nameProduct text,
	foreign key (nameProduct) references product(nameProduct),
	rutPerson text,
	foreign key (rutPerson) references person(rut),
	quantityPuchased int,
	finalPrice int
);


select * from person;
insert into person values('18602347-6','Bartolomé','Cardona','1558','BartoloméCardona@gmail.com','False','Calle215 Casa53');
insert into person values('24520300-8','Candelaria','Grande','2645','CandelariaGrande@gmail.com','False','Calle412 Casa56');
insert into person values('13514370-2','Caridad','Castiyo','6305','CaridadCastiyo@gmail.com','False','Calle442 Casa13');
insert into person values('26818880-4','Bartolomé','Grasia','3564','BartoloméGrasia@gmail.com','False','Calle148 Casa15');
insert into person values('26536826-3','Caritina','Guerrero','7135','CaritinaGuerrero@gmail.com','False','Calle470 Casa48');
insert into person values('24807317-6','Caridad','Cardiel','4616','CaridadCardiel@gmail.com','False','Calle284 Casa58');
insert into person values('11818382-4','Baldwin','Carillo','2551','BaldwinCarillo@gmail.com','False','Calle565 Casa37');
insert into person values('12727250-5','Baldomero','Grigalva','8518','BaldomeroGrigalva@gmail.com','False','Calle872 Casa34');
insert into person values('25758780-3','Andrea','Carillo','8627','AndreaCarillo@gmail.com','False','Calle272 Casa72');
insert into person values('22801200-7','Candelaria','Cardoso','8055','CandelariaCardoso@gmail.com','False','Calle862 Casa56');

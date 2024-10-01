create table zoneJpaEntity
(
    code         varchar(10) not null
        primary key,
    city         varchar(20),
    district     varchar(20),
    full_address varchar(100),
    sub_city     varchar(20),
    town         varchar(20),
    bound        geometry,
    center       geometry
);

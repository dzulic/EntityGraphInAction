##  running on localhost:8082

## endpoints:

/organizations

Showcases how entity graphs affect lazy loading

/locations

Showcases how entity graphs affect n+1 problem with performance

##  create tables and add data for testing:

```sh
create table IF NOT EXISTS organization
(
    id   uuid PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);
create table IF NOT EXISTS address
(
    id   uuid PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);
create table IF NOT EXISTS location
(
    id           uuid PRIMARY KEY,
    name         VARCHAR(50) UNIQUE NOT NULL,
    organization uuid               NOT NULL,
    address      uuid               NOT NULL,
    FOREIGN KEY (organization)
        REFERENCES organization (id),
    FOREIGN KEY (address)
        REFERENCES address (id)
);

insert into address
values ('123e4567-e89b-12d3-a456-425614174674', 'address1');

insert into address
values ('123e4567-e89b-12d3-a456-425614174620', 'address2');


insert into address
values ('123e4567-e89b-12d3-a456-426614174002', 'test2');

insert into organization
values ('123e4567-e89b-12d3-a456-426614174003', 'organization1');

insert into location
values ('123e4567-e89b-12d3-a456-426614174055', 'location1', '123e4567-e89b-12d3-a456-426614174003',
        '123e4567-e89b-12d3-a456-425614174674');

insert into location
values ('123e4567-e89b-12d3-a456-426614174058', 'location2', '123e4567-e89b-12d3-a456-426614174003',
        '123e4567-e89b-12d3-a456-425614174620');

insert into location
values ('123e4567-e89b-12d3-a434-426614174158', 'location3', '123e4567-e89b-12d3-a456-426614174003',
        '123e4567-e89b-12d3-a456-425614174620');
```

## Follow console logs for results
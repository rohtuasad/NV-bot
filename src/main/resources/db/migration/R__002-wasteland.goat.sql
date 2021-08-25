create table if not exists wasteland.goat
(
    goat_id uuid,
    name    varchar(50),
    constraint goat_id_pk primary key (goat_id)
);
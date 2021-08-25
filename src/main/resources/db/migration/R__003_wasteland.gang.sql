create table if not exists wasteland.gang
(
    gang_id uuid
        constraint gang_pk
            primary key,
    name    varchar(50),
    goat_id uuid
        constraint gang_goat_fk
            references wasteland.goat (goat_id)
);
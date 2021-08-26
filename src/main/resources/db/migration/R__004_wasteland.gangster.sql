create table if not exists wasteland.gangster
(
    gangster_tg_id VARCHAR(50)
        constraint gangster_tg_id_pk
            primary key,
    nick_name       varchar(50)
        constraint nick_name_uk
            unique,
    user_name       varchar(50)
        constraint user_name_uk
            unique,
    gang_id        uuid
        constraint gangster_gang_fk
            references wasteland.gang (gang_id)
);
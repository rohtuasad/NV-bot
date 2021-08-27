create table if not exists wasteland.chat
(
    tg_chat_id   VARCHAR(50)
        constraint tg_chat_pk
            primary key,
    ping_list    text,
    tg_chat_name varchar(255)
);
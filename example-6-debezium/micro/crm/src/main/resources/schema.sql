create sequence if not exists public.hibernate_sequence
    increment 1
    start 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;

create table if not exists public.customer
(
    id    integer not null,
    email character varying(255),
    name  character varying(255),
    constraint customer_pkey primary key (id)
);

create table if not exists public.outbox_event
(
    id            character varying(255) not null,
    aggregateid   integer                not null,
    aggregatetype character varying(255),
    payload       character varying(255),
    type          character varying(255),
    constraint outbox_event_pkey primary key (id)
);
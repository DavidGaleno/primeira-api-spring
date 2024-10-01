alter table consultas add column motivo_cancelamento varchar(100);
alter table consultas add column ativa boolean not null default true;
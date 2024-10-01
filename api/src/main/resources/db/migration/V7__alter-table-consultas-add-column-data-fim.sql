alter table consultas add column data_fim timestamp not null;
alter table consultas  RENAME COLUMN data TO data_inicio;
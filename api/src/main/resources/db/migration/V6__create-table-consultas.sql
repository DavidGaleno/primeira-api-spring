create table consultas(

    id serial not null primary key,
    medico_id bigint  not null,
    paciente_id bigint not null,
    data timestamp not null,
    constraint fk_consultas_medico_id foreign key(medico_id) references medicos(id),
    constraint fk_consultas_paciente_id foreign key(paciente_id) references pacientes(id)

);
create table consultas (

      id bigint not null auto_increment,
      paciente_id bigint not null,
      medico_id bigint not null,
      data date not null,
      hora time not null,

      primary key(id),
      constraint fk_consultas_paciente_id foreign key(paciente_id) references pacientes(id),
      constraint fk_consultas_medico_id foreign key(medico_id) references medicos(id)

);
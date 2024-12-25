package com.william.agendadortarefas.business.mapper;

import com.william.agendadortarefas.business.dtos.TarefaDTO;
import com.william.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefaEntity paraTarefaEntity(TarefaDTO dto);

    TarefaDTO paraTarefaDTO(TarefaEntity entity);
}

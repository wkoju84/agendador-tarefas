package com.william.agendadortarefas.business.mapper;

import com.william.agendadortarefas.business.dtos.TarefaDTO;
import com.william.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefas(TarefaDTO dto, @MappingTarget TarefaEntity entity);
}

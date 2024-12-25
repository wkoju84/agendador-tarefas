package com.william.agendadortarefas.business.mapper;

import com.william.agendadortarefas.business.dtos.TarefaDTO;
import com.william.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    //@Mapping(source = "id", target = "id")
    //@Mapping(source = "dataEvento", target = "dataEvento")
    //@Mapping(source = "dataCriacao", target = "dataCriacao")
    TarefaEntity paraTarefaEntity(TarefaDTO dto);

    TarefaDTO paraTarefaDTO(TarefaEntity entity);

    List<TarefaEntity> paraListaTarefasEntity(List<TarefaDTO> dtos);

    List<TarefaDTO> paraListaTarefasDTO(List<TarefaEntity> entities);
}

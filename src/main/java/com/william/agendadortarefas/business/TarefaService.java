package com.william.agendadortarefas.business;

import com.william.agendadortarefas.business.dtos.TarefaDTO;
import com.william.agendadortarefas.business.mapper.TarefaConverter;
import com.william.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.william.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.william.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.william.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.william.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.william.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefaDTO gravarTarefa(TarefaDTO dto, String token){

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);

        //Conversão feita com o mapper sendo uma alternativa ao builder
        TarefaEntity entity = tarefaConverter.paraTarefaEntity(dto);
        return tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));
    }

    public List<TarefaDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                               LocalDateTime dataFinal){

        return tarefaConverter.paraListaTarefasDTO(
                tarefaRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefaDTO> buscaTarefaPorEmail(String token){

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefaEntity> listaTarefas = tarefaRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDTO(listaTarefas);
    }

    public void deletaTarefaPorId(String id){

        try {

            tarefaRepository.deleteById(id);
        }
        catch (ResourceNotFoundException e){

            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente " + id,
                    e.getCause());
        }
    }

    /*Metodo de alteração de Status*/
    public TarefaDTO alteraStatus(StatusNotificacaoEnum status, String id){

        try {
            TarefaEntity entity = tarefaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                    "Tarefa não encontrada " + id));

            entity.setStatusNotificacaoEnum(status);

            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));
        }
        catch (ResourceNotFoundException e){

            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }

    public TarefaDTO updateTarefas(TarefaDTO dto, String id){

        try {
            TarefaEntity entity = tarefaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                    "Tarefa não encontrada " + id));

            tarefaUpdateConverter.updateTarefas(dto, entity);
            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));
        }
        catch (ResourceNotFoundException e){

            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }
}

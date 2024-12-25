package com.william.agendadortarefas.controller;

import com.william.agendadortarefas.business.TarefaService;
import com.william.agendadortarefas.business.dtos.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravarTarefas(@RequestBody TarefaDTO dto,
                                                   @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.gravarTarefa(dto, token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  dataFinal){

        return ResponseEntity.ok(tarefaService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscaTarefasPorEmail(@RequestHeader ("Authorization") String token){

        List<TarefaDTO> tarefas = tarefaService.buscaTarefaPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }
}

package br.com.fiap.concessionaria.resource;

import br.com.fiap.concessionaria.dto.request.LojaRequest;
import br.com.fiap.concessionaria.dto.request.VeiculoRequest;
import br.com.fiap.concessionaria.dto.response.LojaResponse;
import br.com.fiap.concessionaria.dto.response.VeiculoResponse;
import br.com.fiap.concessionaria.entity.Loja;
import br.com.fiap.concessionaria.entity.Veiculo;
import br.com.fiap.concessionaria.service.LojaService;
import br.com.fiap.concessionaria.service.VeiculoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(value = "/loja")
public class LojaResource {

    private final LojaService lojaService;
    private final VeiculoService veiculoService;

    @Autowired
    public LojaResource(LojaService lojaService, VeiculoService veiculoService) {
        this.lojaService = lojaService;
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<Collection<LojaResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome
    ) {
        Loja searchCriteria = new Loja(nome);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Loja> example = Example.of(searchCriteria, matcher);
        Collection<Loja> lojas = lojaService.findAll(example);

        Collection<LojaResponse> responses = lojas.stream()
                .map(lojaService::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<LojaResponse> save(@RequestBody @Valid LojaRequest request) {
        Loja entity = lojaService.toEntity(request);
        Loja saved = lojaService.save(entity);
        LojaResponse response = lojaService.toResponse(saved);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LojaResponse> findById(@PathVariable Long id) {
        Loja entity = lojaService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        LojaResponse response = lojaService.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}/veiculos")
    public ResponseEntity<Collection<VeiculoResponse>> findVeiculosByLoja(@PathVariable Long id) {
        Loja loja = lojaService.findById(id);
        if (loja == null) {
            return ResponseEntity.notFound().build();
        }
        Collection<Veiculo> veiculos = loja.getVeiculosComercializados();
        Collection<VeiculoResponse> responses = veiculos.stream()
                .map(veiculoService::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @Transactional
    @PostMapping(value = "/{id}/veiculos")
    public ResponseEntity<VeiculoResponse> saveVeiculo(@PathVariable Long id, @RequestBody @Valid VeiculoRequest veiculoRequest) {
        Loja loja = lojaService.findById(id);
        if (loja == null) {
            return ResponseEntity.notFound().build();
        }

        Veiculo entity = veiculoService.toEntity(veiculoRequest);
        loja.getVeiculosComercializados().add(entity);

        Veiculo saved = veiculoService.save(entity);
        VeiculoResponse response = veiculoService.toResponse(saved);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}

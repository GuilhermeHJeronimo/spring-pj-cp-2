package br.com.fiap.concessionaria.resource;

import br.com.fiap.concessionaria.dto.request.AcessorioRequest;
import br.com.fiap.concessionaria.dto.response.AcessorioResponse;
import br.com.fiap.concessionaria.entity.Acessorio;
import br.com.fiap.concessionaria.service.AcessorioService;
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


@RequestMapping(value = {"/acessorio", "/acessorio"})
@RestController
public class AcessorioResource {

    private final AcessorioService acessorioService;

    @Autowired
    public AcessorioResource(AcessorioService acessorioService) {
        this.acessorioService = acessorioService;
    }

    @GetMapping
    public ResponseEntity<Collection<AcessorioResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "preco", required = false) Double preco
    ) {
        Acessorio searchCriteria = new Acessorio(nome, preco);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Acessorio> example = Example.of(searchCriteria, matcher);
        Collection<Acessorio> acessorios = acessorioService.findAll(example);

        Collection<AcessorioResponse> responses = acessorios.stream()
                .map(acessorioService::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<AcessorioResponse> save(@RequestBody @Valid AcessorioRequest request) {
        Acessorio entity = acessorioService.toEntity(request);
        Acessorio saved = acessorioService.save(entity);
        AcessorioResponse response = acessorioService.toResponse(saved);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AcessorioResponse> findById(@PathVariable Long id) {
        Acessorio entity = acessorioService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        AcessorioResponse response = acessorioService.toResponse(entity);
        return ResponseEntity.ok(response);
    }
}

package br.com.fiap.concessionaria.resource;

import br.com.fiap.concessionaria.dto.request.FabricanteRequest;
import br.com.fiap.concessionaria.dto.response.FabricanteResponse;
import br.com.fiap.concessionaria.entity.Fabricante;
import br.com.fiap.concessionaria.service.FabricanteService;
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
@RequestMapping(value = "/fabricantes")
public class FabricanteResource {

    private final FabricanteService fabricanteService;

    @Autowired
    public FabricanteResource(FabricanteService fabricanteService) {
        this.fabricanteService = fabricanteService;
    }

    @GetMapping
    public ResponseEntity<Collection<FabricanteResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "nomeFantasia", required = false) String nomeFantasia
    ) {
        Fabricante searchCriteria = new Fabricante(nome, nomeFantasia);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Fabricante> example = Example.of(searchCriteria, matcher);
        Collection<Fabricante> fabricantes = fabricanteService.findAll(example);

        Collection<FabricanteResponse> responses = fabricantes.stream()
                .map(fabricanteService::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<FabricanteResponse> save(@RequestBody @Valid FabricanteRequest request) {
        Fabricante entity = fabricanteService.toEntity(request);
        Fabricante saved = fabricanteService.save(entity);
        FabricanteResponse response = fabricanteService.toResponse(saved);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FabricanteResponse> findById(@PathVariable Long id) {
        Fabricante entity = fabricanteService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        FabricanteResponse response = fabricanteService.toResponse(entity);
        return ResponseEntity.ok(response);
    }
}

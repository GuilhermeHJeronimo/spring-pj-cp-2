package br.com.fiap.concessionaria.resource;

import br.com.fiap.concessionaria.dto.request.TipoVeiculoRequest;
import br.com.fiap.concessionaria.entity.TipoVeiculo;
import br.com.fiap.concessionaria.service.TipoVeiculoService;
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
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/tipo-de-veiculo")
public class TipoVeiculoResource {

    private final TipoVeiculoService tipoVeiculoService;

    @Autowired
    public TipoVeiculoResource(TipoVeiculoService tipoVeiculoService) {
        this.tipoVeiculoService = tipoVeiculoService;
    }

    @GetMapping
    public ResponseEntity<List<Optional<String>>> findAll(
            @RequestParam(name = "nome", required = false) String nome
    ) {
        TipoVeiculo searchCriteria = new TipoVeiculo(nome);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<TipoVeiculo> example = Example.of(searchCriteria, matcher);
        Collection<TipoVeiculo> tiposVeiculo = tipoVeiculoService.findAll(example);

        List<Optional<String>> responses = tiposVeiculo.stream()
                .map(tipoVeiculoService::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid TipoVeiculoRequest request) {
        TipoVeiculo entity = tipoVeiculoService.toEntity(request);
        TipoVeiculo saved = tipoVeiculoService.save(entity);
        String response = String.valueOf(tipoVeiculoService.toResponse(saved));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> findById(@PathVariable Long id) {
        TipoVeiculo entity = tipoVeiculoService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        String response = String.valueOf(tipoVeiculoService.toResponse(entity));
        return ResponseEntity.ok(response);
    }
}

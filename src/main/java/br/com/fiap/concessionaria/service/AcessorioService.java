package br.com.fiap.concessionaria.service;

import br.com.fiap.concessionaria.dto.request.AcessorioRequest;
import br.com.fiap.concessionaria.dto.response.AcessorioResponse;
import br.com.fiap.concessionaria.entity.Acessorio;
import br.com.fiap.concessionaria.repository.AcessorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AcessorioService {

    private final AcessorioRepository acessorioRepository;

    @Autowired
    public AcessorioService(AcessorioRepository acessorioRepository) {
        this.acessorioRepository = acessorioRepository;
    }

    public Acessorio toEntity(AcessorioRequest request) {
        return new Acessorio(request.getNome(), request.getPreco());
    }

    public AcessorioResponse toResponse(Acessorio entity) {
        if (entity == null) return null;
        return new AcessorioResponse(entity.getNome(), entity.getPreco(), entity.getId());
    }

    public Collection<Acessorio> findAll() {
        return acessorioRepository.findAll();
    }

    public Acessorio findById(Long id) {
        return acessorioRepository.findById(id).orElse(null);
    }

    public Acessorio save(Acessorio entity) {
        return acessorioRepository.save(entity);
    }
}

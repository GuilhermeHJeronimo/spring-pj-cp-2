package br.com.fiap.concessionaria.service;

import br.com.fiap.concessionaria.dto.request.LojaRequest;
import br.com.fiap.concessionaria.dto.response.AcessorioResponse;
import br.com.fiap.concessionaria.dto.response.LojaResponse;
import br.com.fiap.concessionaria.dto.response.VeiculoResponse;
import br.com.fiap.concessionaria.entity.Acessorio;
import br.com.fiap.concessionaria.entity.Loja;
import br.com.fiap.concessionaria.entity.Veiculo;
import br.com.fiap.concessionaria.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Service
public class LojaService implements ServiceDTO<Loja, LojaRequest, LojaResponse>{
    @Autowired
    LojaRepository lojaRepository;
    @Autowired
    VeiculoService veiculoService;

    @Override
    public Loja toEntity(LojaRequest dto) {

        return Loja.builder()
                .nome(dto.nome())
                .build();
    }

    @Override
    public LojaResponse toResponse(Loja e) {
        if (Objects.isNull(e)) return null;

        Set<VeiculoResponse> veiculosComercializados = new HashSet<>();
        VeiculoService veiculoService1 = veiculoService;
        for (Veiculo veiculo : e.getVeiculosComercializados()) {
            Object response = veiculoService1.toResponse(veiculo);
            veiculosComercializados.add((VeiculoResponse) response);
        }

        return new LojaResponse(e.getNome(), veiculosComercializados, e.getId());
    }


    @Override
    public AcessorioResponse paraResposta(Acessorio e) {
        return null;
    }

    @Override
    public Collection<Loja> findAll(Example<Loja> example) {
        return lojaRepository.findAll();
    }

    @Override
    public Loja findById(Long id) {
        return lojaRepository.findById(id).orElse(null);
    }

    @Override
    public Loja save(Loja e) {
        return lojaRepository.save(e);
    }
}
package br.com.fiap.concessionaria.service;

import br.com.fiap.concessionaria.dto.request.CaracteristicaRequest;
import br.com.fiap.concessionaria.dto.response.AcessorioResponse;
import br.com.fiap.concessionaria.dto.response.CaracteristicaResponse;
import br.com.fiap.concessionaria.entity.Acessorio;
import br.com.fiap.concessionaria.entity.Caracteristica;
import br.com.fiap.concessionaria.entity.Veiculo;
import br.com.fiap.concessionaria.repository.CaracteristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class CaracteristicaService implements ServiceDTO<Caracteristica, CaracteristicaRequest, Boolean> {
    @Autowired
    CaracteristicaRepository caracteristicaRepository;
    @Autowired
    VeiculoService veiculoService;

    @Override
    public Caracteristica toEntity(CaracteristicaRequest dto) {

        var veiculo = veiculoService.toEntity(dto.veiculo());

        return Caracteristica.builder()
                .nome(dto.nome())
                .veiculo((Veiculo) veiculo)
                .descricao(dto.descricao())
                .build();
    }

    @Override
    public Boolean toResponse(Caracteristica e) {

        if (Objects.isNull(e)) return null;

        var veiculo = veiculoService.toResponse(e.getVeiculo());

        return CaracteristicaResponse.builder()
                .id()
                .equals(veiculo);
    }

    @Override
    public AcessorioResponse paraResposta(Acessorio e) {
        return null;
    }

    @Override
    public Collection<Caracteristica> findAll(Example<Caracteristica> example) {
        return caracteristicaRepository.findAll();
    }

    @Override
    public Caracteristica findById(Long id) {
        return caracteristicaRepository.findById(id).orElse(null);
    }

    @Override
    public Caracteristica save(Caracteristica e) {
        return caracteristicaRepository.save(e);
    }
}
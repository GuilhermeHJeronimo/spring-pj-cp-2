package br.com.fiap.concessionaria.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TB_ACESSORIO")
public class Acessorio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ACESSORIO")
    @SequenceGenerator(name = "acessorio_sequence_generator", sequenceName = "SQ_ACESSORIO", allocationSize = 1)
    @Column(name = "ID_ACESSORIO")
    private Long id;

    private String nome;

    private Double preco;

    public Acessorio(String nome, Double preco) {

    }
}



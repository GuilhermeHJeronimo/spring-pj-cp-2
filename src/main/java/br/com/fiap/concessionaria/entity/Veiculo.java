package br.com.fiap.concessionaria.entity;

import jakarta.persistence.*;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TB_VEICULO")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VEICULO")
    @SequenceGenerator(name = "SQ_VEICULO", sequenceName = "SQ_VEICULO", allocationSize = 1)
    @Column(name = "ID_VEICULO")
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Year anoDeFabricacao;

    private String cor;

    @Column(nullable = false)
    private Double preco;

    private Short cilindradas;

    @Column(length = 30)
    private String modelo;

    @Column(length = 15)
    private String palavraDeEfeito;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "FABRICANTE", referencedColumnName = "ID_FABRICANTE", foreignKey = @ForeignKey(name = "FK_VEICULO_FABRICANTE"))
    private Fabricante fabricante;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "TIPOVEICULO", referencedColumnName = "ID_TIPOVEICULO", foreignKey = @ForeignKey(name = "FK_VEICULO_TIPOVEICULO"))
    private TipoVeiculo tipo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_ACESSORIO_VEICULO",
            joinColumns = {
                    @JoinColumn(name = "VEICULO", referencedColumnName = "ID_VEICULO", foreignKey = @ForeignKey(name = "FK_VEICULO_ACESSORIO"))
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ACESSORIO", referencedColumnName = "ID_ACESSORIO", foreignKey = @ForeignKey(name = "FK_ACESSORIO_VEICULO"))
            }
    )
    private Set<Acessorio> acessorios = new HashSet<>();


}

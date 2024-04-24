package br.com.fiap.concessionaria.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_ACESSORIO")
public class Acessorio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ACESSORIO")
    @SequenceGenerator(name = "SQ_ACESSORIO", sequenceName = "SQ_ACESSORIO", allocationSize = 1)
    @Column(name = "ID_ACESSORIO")
    private Long id;
    private String nome;
    private Double preco;
}

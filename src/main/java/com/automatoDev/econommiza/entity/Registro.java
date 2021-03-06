package com.automatoDev.econommiza.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.automatoDev.econommiza.entity.validationGroups.ConverterGroup;
import com.automatoDev.econommiza.enumerator.TipoRegistroEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



/**
 * @author Marco Aurélio
 * @date 01/05/2021
 * Classe de mapemento para entidade tb_registro.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(exclude = {"nome","data","valor","tipo"})
@Entity(name = "tb_registro")
@Table(schema = "dbo")
public class Registro implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro")
    private Long idRegistro;

    @NotBlank(message = "Necessário informar um nome para o registro.")
    private String nome;

    @NotNull(message = "Campo data não pode ser nulo.")
    private Long data;

    @NotNull(message = "Campo valor não pode ser nulo.")
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private TipoRegistroEnum tipo;

    @ManyToOne()
    @JoinColumn(name = "id_perspectiva")
    @JsonIgnoreProperties({"registros","usuario"})
    @Valid
    @NotNull(message = "O registro precisa estar vinculado a uma perspectiva.")
    @ConvertGroup(from = Default.class,to = ConverterGroup.Perspectiva.class)
    private Perspectiva perspectiva;

    @ManyToMany()
    @JoinTable(name = "tb_categoria_registro", schema = "dbo"
    ,joinColumns = @JoinColumn(name = "id_registro")
    ,inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    @Valid
    @ConvertGroup(from = Default.class, to = ConverterGroup.Categoria.class)
    private List<Categoria> categorias = new ArrayList<>();


    
}

package br.com.sccon.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Person implements Serializable {

    private Long id;

    private String nome;

    private Date dataNascimento;

    private Date dataAdmissao;
}

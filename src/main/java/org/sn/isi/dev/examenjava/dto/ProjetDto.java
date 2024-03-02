package org.sn.isi.dev.examenjava.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProjetDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String descrption;
    //@NotEmpty
    private double budget;
}

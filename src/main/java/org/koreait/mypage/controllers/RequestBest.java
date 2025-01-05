package org.koreait.mypage.controllers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.koreait.file.entities.FileInfo;
import org.koreait.member.constants.Gender;
import org.koreait.pokemon.entities.Pokemon;

import java.util.List;

@Data
public class RequestBest {
    @NotBlank
    private String nickName;

    @NotNull
    private Gender gender; // 성별

    private FileInfo profileImage;

    private List<Pokemon> bestPokemon;
}

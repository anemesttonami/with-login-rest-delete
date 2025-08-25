package com.demoqa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BooksAddDto {
    private String userId;
    private List<IsbnDto> collectionOfIsbns;
}
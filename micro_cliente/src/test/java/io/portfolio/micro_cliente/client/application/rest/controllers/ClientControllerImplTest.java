package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.dtos.ClientDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import io.portfolio.micro_cliente.client.domain.filter.ClientFilterImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientControllerImplTest {

    private final String FIRST_NAME = "Robert";
    private final String LAST_NAME = "Martin";
    private final String CPF = "455.127.340-67";
    private final SexEnum SEXO = SexEnum.MASCULINO;
    private final GenreEnum GENRE = GenreEnum.NENHUM;
    private final LocalDate DATA_NASCIMENTO = LocalDate.now();
    private final MaritalStatusEnum MARITAL_STATUS = MaritalStatusEnum.CASADO;
    private final EducationEnum EDUCATION = EducationEnum.DOUTORADO_COMPLETO;

    private ClientDTORequestImpl dtoRequest;

    @Autowired
    private PolicyControllers<ClientDTORequestImpl, ClientFilterImpl, ClientDTOResponseImpl, Long> controller;

    @BeforeEach
    void setUp() {
        dtoRequest = ClientDTORequestImpl.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .sex(SEXO)
                .genre(GENRE)
                .birthDate(DATA_NASCIMENTO)
                .maritalStatus(MARITAL_STATUS)
                .education(EDUCATION)
                .build();
    }

    @AfterAll
    void tearDown() {
    }

    @Test
    void create_returnResponseEntityOfClientDTOResponseAndHttp201() {

        var response = this.controller.create(dtoRequest);
    }

}
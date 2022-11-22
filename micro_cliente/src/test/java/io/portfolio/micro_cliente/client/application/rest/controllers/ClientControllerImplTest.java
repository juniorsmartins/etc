package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.dtos.ClientDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import io.portfolio.micro_cliente.client.domain.filter.ClientFilterImpl;
import io.portfolio.micro_cliente.client.infrastructure.repositories.ClientRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@SpringBootTest
class ClientControllerImplTest {

    private final String FIRST_NAME = "Robert";
    private final String LAST_NAME = "Martin";
    private final String CPF = "455.127.340-67";
    private final SexEnum SEX = SexEnum.MASCULINO;
    private final GenreEnum GENRE = GenreEnum.NENHUM;
    private final LocalDate BIRTH_DATE = LocalDate.now();
    private final MaritalStatusEnum MARITAL_STATUS = MaritalStatusEnum.CASADO;
    private final EducationEnum EDUCATION = EducationEnum.DOUTORADO_COMPLETO;

    private ClientDTORequestImpl dtoRequest;

    @Autowired
    private PolicyControllers<ClientDTORequestImpl, ClientFilterImpl, ClientDTOResponseImpl, Long> controller;

    @Autowired
    private ClientRepository repository;

    @BeforeEach
    void setUp() {
        dtoRequest = ClientDTORequestImpl.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .sex(SEX)
                .genre(GENRE)
                .birthDate(BIRTH_DATE)
                .maritalStatus(MARITAL_STATUS)
                .education(EDUCATION)
                .build();
    }

    @AfterEach
    void tearDown() {
        this.repository.deleteByCPF(dtoRequest.getCpf());
    }

    @Test
    void create_returnResponseEntityOfClientDTOResponseAndHttp201() {
        var response = this.controller.create(dtoRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ClientDTOResponseImpl.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertEquals(FIRST_NAME, response.getBody().getFirstName());
        Assertions.assertEquals(LAST_NAME, response.getBody().getLastName());
        Assertions.assertEquals(CPF, response.getBody().getCpf());
        Assertions.assertEquals(SEX, response.getBody().getSex());
        Assertions.assertEquals(GENRE, response.getBody().getGenre());
        Assertions.assertEquals(BIRTH_DATE, response.getBody().getBirthDate());
        Assertions.assertEquals(MARITAL_STATUS, response.getBody().getMaritalStatus());
        Assertions.assertEquals(EDUCATION, response.getBody().getEducation());
    }

}
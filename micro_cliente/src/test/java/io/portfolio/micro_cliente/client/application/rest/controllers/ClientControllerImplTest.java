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
    private final String CPF_I = "857.046.090-23";
    private final String CPF_II = "455.127.340-67";
    private final SexEnum SEX = SexEnum.MASCULINE;
    private final GenreEnum GENRE = GenreEnum.NOOPTION;
    private final LocalDate BIRTH_DATE = LocalDate.now();
    private final MaritalStatusEnum MARITAL_STATUS = MaritalStatusEnum.MARRIED;
    private final EducationEnum EDUCATION = EducationEnum.FULL_DOCTORATE;

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
                .cpf(CPF_I)
                .sex(SEX)
                .genre(GENRE)
                .birthDate(BIRTH_DATE)
                .maritalStatus(MARITAL_STATUS)
                .education(EDUCATION)
                .build();
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
        Assertions.assertEquals(CPF_I, response.getBody().getCpf());
        Assertions.assertEquals(SEX, response.getBody().getSex());
        Assertions.assertEquals(GENRE, response.getBody().getGenre());
        Assertions.assertEquals(BIRTH_DATE, response.getBody().getBirthDate());
        Assertions.assertEquals(MARITAL_STATUS, response.getBody().getMaritalStatus());
        Assertions.assertEquals(EDUCATION, response.getBody().getEducation());
    }

    void tearDown() {
        this.repository.deleteByCpf(dtoRequest.getCpf());
    }
}
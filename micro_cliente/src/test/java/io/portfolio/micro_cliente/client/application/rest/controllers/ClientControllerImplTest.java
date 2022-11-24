package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.dtos.ClientDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import io.portfolio.micro_cliente.client.domain.filter.ClientFilterImpl;
import io.portfolio.micro_cliente.client.infrastructure.repositories.ClientRepository;
import io.portfolio.micro_cliente.shared.exceptions.BusinessRuleViolationCustomException;
import io.portfolio.micro_cliente.shared.exceptions.StandardExceptionHandledReturn;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
@TestPropertySource(properties = {"spring.profiles.active=tests"})
class ClientControllerImplTest {

    private final String FIRST_NAME = "Robert";
    private final String FIRST_NAME_II = "Loiane";
    private final String LAST_NAME = "Martin";
    private final String LAST_NAME_II = "Groner";
    private final String CPF_I = "857.046.090-23";
    private final String CPF_II = "455.127.340-67";
    private final SexEnum SEX = SexEnum.MASCULINE;
    private final SexEnum SEX_II = SexEnum.FEMININE;
    private final GenreEnum GENRE = GenreEnum.NOOPTION;
    private final GenreEnum GENRE_II = GenreEnum.BISEXUALS;
    private final LocalDate BIRTH_DATE = LocalDate.now();
    private final MaritalStatusEnum MARITAL_STATUS = MaritalStatusEnum.MARRIED;
    private final MaritalStatusEnum MARITAL_STATUS_II = MaritalStatusEnum.SINGLE;
    private final EducationEnum EDUCATION = EducationEnum.FULL_DOCTORATE;
    private final EducationEnum EDUCATION_II = EducationEnum.COMPLETE_MASTERS_DEGREE;

    private ClientDTORequestImpl dtoRequest;
    private ClientDTORequestImpl dtoRequest2;
    private ClientDTORequestImpl dtoRequest3;

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

        dtoRequest2 = ClientDTORequestImpl.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_II)
                .sex(SEX)
                .genre(GENRE)
                .birthDate(BIRTH_DATE)
                .maritalStatus(MARITAL_STATUS)
                .education(EDUCATION)
                .build();

        dtoRequest3 = ClientDTORequestImpl.builder()
                .firstName(FIRST_NAME_II)
                .lastName(LAST_NAME_II)
                .cpf(CPF_II)
                .sex(SEX_II)
                .genre(GENRE_II)
                .birthDate(BIRTH_DATE)
                .maritalStatus(MARITAL_STATUS_II)
                .education(EDUCATION_II)
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

    @Test
    void create_returnResponseEntityOfStandardExceptionHandlerReturnAndHttp409() {
        this.controller.create(dtoRequest2);

        Throwable response = catchThrowable(() -> {
            this.controller.create(dtoRequest3);
        });

        assertThat(response).isInstanceOf(BusinessRuleViolationCustomException.class);
    }

//    @AfterAll
//    void tearDown() {
//        this.repository.deleteByCpf(dtoRequest.getCpf());
//        this.repository.deleteByCpf(dtoRequest2.getCpf());
//        this.repository.deleteByCpf(dtoRequest3.getCpf());
//    }
}
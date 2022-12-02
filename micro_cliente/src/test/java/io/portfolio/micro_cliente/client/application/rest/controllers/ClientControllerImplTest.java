package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.dtos.ClientDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import io.portfolio.micro_cliente.client.domain.filter.ClientFilterImpl;
import io.portfolio.micro_cliente.client.infrastructure.repositories.ClientRepositoryJpa;
import io.portfolio.micro_cliente.shared.exceptions.BusinessRuleViolationCustomException;
import io.portfolio.micro_cliente.shared.exceptions.ResourceNotFoundCustomException;
import io.portfolio.micro_cliente.shared.messages.MessagesProperties;
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
    private ClientRepositoryJpa repository;

    @Autowired
    private MessagesProperties messages;

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
        Assertions.assertNotNull(response.getBody().id());
        Assertions.assertEquals(FIRST_NAME, response.getBody().firstName());
        Assertions.assertEquals(LAST_NAME, response.getBody().lastName());
        Assertions.assertEquals(CPF_I, response.getBody().cpf());
        Assertions.assertEquals(SEX, response.getBody().sex());
        Assertions.assertEquals(GENRE, response.getBody().genre());
        Assertions.assertEquals(BIRTH_DATE, response.getBody().birthDate());
        Assertions.assertEquals(MARITAL_STATUS, response.getBody().maritalStatus());
        Assertions.assertEquals(EDUCATION, response.getBody().education());

        this.controller.deleteById(response.getBody().id());
    }

    @Test
    void create_returnResponseEntityOfStandardExceptionHandlerReturnAndHttp409() {
        var dtoResponse = this.controller.create(dtoRequest2);

        Throwable response = catchThrowable(() -> {
            this.controller.create(dtoRequest3);
        });

        assertThat(response).isInstanceOf(BusinessRuleViolationCustomException.class)
                .hasMessageContaining(messages.getBusinessRuleViolated());
        this.controller.deleteById(dtoResponse.getBody().id());
    }



    @Test
    void searchById_returnResponseEntityOfClientDTOResponseAndHttp200() {
        var responseEntity = this.controller.create(dtoRequest3);

        var response = this.controller.searchById(responseEntity.getBody().id());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ClientDTOResponseImpl.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(responseEntity.getBody().id(), response.getBody().id());
        Assertions.assertEquals(responseEntity.getBody().cpf(), response.getBody().cpf());

        this.controller.deleteById(responseEntity.getBody().id());
    }

    @Test
    void searchById_returnResponseEntityOfStandardExceptionHandlerReturnAndHttp404() {
        Throwable response = catchThrowable(() -> {
           this.controller.searchById(10000L);
        });

        assertThat(response).isInstanceOf(ResourceNotFoundCustomException.class)
                .hasMessageContaining(messages.getResourceNotFound());
    }



    @Test
    void deleteById_returnResponseEntityOfStringAndHttp200() {
        var responseEntity = this.controller.create(dtoRequest3);

        var response = this.controller.deleteById(responseEntity.getBody().id());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(String.class, response.getBody().getClass());
        Assertions.assertEquals(messages.getResourceDeletedSuccessfully(), response.getBody());
    }

    @Test
    void deleteById_returnResponseEntityOfStandardExceptionHandlerReturnAndHttp404() {
        Throwable response = catchThrowable(() -> {
            this.controller.deleteById(10000L);
        });

        assertThat(response).isInstanceOf(ResourceNotFoundCustomException.class)
                .hasMessageContaining(messages.getResourceNotFound());
    }
}
package io.portfolio.micro_cliente.client.application.rest.controllers;

import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@TestPropertySource(properties = {"spring.profiles.active=tests"})
//class ClientControllerImplTest {
//
//    private final Long ID = 100100L;
//    private final String FIRST_NAME_I = "Robert";
//    private final String FIRST_NAME_II = "Loiane";
//    private final String LAST_NAME_I = "Martin";
//    private final String LAST_NAME_II = "Groner";
//    private final String CPF_I = "857.046.090-23";
//    private final String CPF_II = "455.127.340-67";
//    private final SexEnum SEX_I = SexEnum.MASCULINE;
//    private final SexEnum SEX_II = SexEnum.FEMININE;
//    private final GenreEnum GENRE_I = GenreEnum.NOOPTION;
//    private final GenreEnum GENRE_II = GenreEnum.BISEXUALS;
//    private final LocalDate BIRTH_DATE = LocalDate.now();
//    private final MaritalStatusEnum MARITAL_STATUS_I = MaritalStatusEnum.MARRIED;
//    private final MaritalStatusEnum MARITAL_STATUS_II = MaritalStatusEnum.SINGLE;
//    private final EducationEnum EDUCATION_I = EducationEnum.FULL_DOCTORATE;
//    private final EducationEnum EDUCATION_II = EducationEnum.COMPLETE_MASTERS_DEGREE;
//
//    private ClientPersonDTORequestImpl dtoRequest1;
//    private ClientPersonDTORequestImpl dtoRequest2;
//    private ClientPersonDTORequestImpl dtoRequest3;
//
//    @Autowired
//    private PolicyControllers<ClientPersonDTORequestImpl, ClientPersonFilterImpl, ClientPersonDTOResponseImpl, Long> controller;
//
//    @Autowired
//    private ClientPersonRepositoryJpa repository;
//
//    @Autowired
//    private MessagesProperties messages;
//
//    @BeforeEach
//    void setUp() {
//        dtoRequest1 = ClientPersonDTORequestImpl.builder()
//                .firstName(FIRST_NAME_I)
//                .lastName(LAST_NAME_I)
//                .cpf(CPF_I)
//                .sex(SEX_I)
//                .genre(GENRE_I)
//                .birthDate(BIRTH_DATE)
//                .maritalStatus(MARITAL_STATUS_I)
//                .education(EDUCATION_I)
//                .build();
//
//        dtoRequest2 = ClientPersonDTORequestImpl.builder()
//                .firstName(FIRST_NAME_II)
//                .lastName(LAST_NAME_II)
//                .cpf(CPF_II)
//                .sex(SEX_II)
//                .genre(GENRE_II)
//                .birthDate(BIRTH_DATE)
//                .maritalStatus(MARITAL_STATUS_II)
//                .education(EDUCATION_II)
//                .build();
//
//        dtoRequest3 = ClientPersonDTORequestImpl(
//                .firstName(FIRST_NAME_I)
//                .lastName(LAST_NAME_I)
//                .cpf(CPF_I)
//                .sex(SEX_I)
//                .genre(GENRE_I)
//                .birthDate(BIRTH_DATE)
//                .maritalStatus(MARITAL_STATUS_I)
//                .education(EDUCATION_I));
//    }
//
//    @Test
//    void create_returnResponseEntityOfClientDTOResponseAndHttp201() {
//        var response = this.controller.create(dtoRequest1);
//
//        Assertions.assertNotNull(response);
//        Assertions.assertEquals(ResponseEntity.class, response.getClass());
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(ClientPersonDTOResponseImpl.class, response.getBody().getClass());
//        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        Assertions.assertNotNull(response.getBody().id());
//        Assertions.assertEquals(FIRST_NAME_I, response.getBody().firstName());
//        Assertions.assertEquals(LAST_NAME_I, response.getBody().lastName());
//        Assertions.assertEquals(CPF_I, response.getBody().cpf());
//        Assertions.assertEquals(SEX_I, response.getBody().sex());
//        Assertions.assertEquals(GENRE_I, response.getBody().genre());
//        Assertions.assertEquals(BIRTH_DATE, response.getBody().birthDate());
//        Assertions.assertEquals(MARITAL_STATUS_I, response.getBody().maritalStatus());
//        Assertions.assertEquals(EDUCATION_I, response.getBody().education());
//
//        this.controller.deleteById(response.getBody().id());
//    }
//
//    @Test
//    void create_returnResponseEntityOfStandardExceptionHandlerReturnAndHttp409_quandoCpfJaExistir() {
//        var dtoResponse = this.controller.create(dtoRequest1);
//
//        Throwable response = catchThrowable(() -> {
//            this.controller.create(dtoRequest3);
//        });
//        assertThat(response).isInstanceOf(BusinessRuleViolationCustomException.class)
//                .hasMessageContaining(messages.getSingleCpfRuleViolation());
//
//        this.controller.deleteById(dtoResponse.getBody().id());
//    }
//
//    @Test
//    void searchById_returnResponseEntityOfClientDTOResponseAndHttp200() {
//        var dtoResponse = this.controller.create(dtoRequest1);
//
//        var response = this.controller.searchById(dtoResponse.getBody().id());
//
//        Assertions.assertNotNull(response);
//        Assertions.assertEquals(ResponseEntity.class, response.getClass());
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(ClientPersonDTOResponseImpl.class, response.getBody().getClass());
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertEquals(dtoResponse.getBody().id(), response.getBody().id());
//        Assertions.assertEquals(dtoResponse.getBody().cpf(), response.getBody().cpf());
//
//        this.controller.deleteById(dtoResponse.getBody().id());
//    }
//
//    @Test
//    void searchById_returnResponseEntityOfStandardExceptionHandlerReturnAndHttp404_quandoIdNaoExistir() {
//        Throwable response = catchThrowable(() -> {
//            this.controller.searchById(ID);
//        });
//
//        assertThat(response).isInstanceOf(ResourceNotFoundCustomException.class)
//                .hasMessageContaining(messages.getResourceNotFound());
//    }
//
//    @Test
//    void deleteById_returnResponseEntityOfStringAndHttp200() {
//        var dtoResponse = this.controller.create(dtoRequest1);
//
//        var response = this.controller.deleteById(dtoResponse.getBody().id());
//
//        Assertions.assertNotNull(response);
//        Assertions.assertEquals(ResponseEntity.class, response.getClass());
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(String.class, response.getBody().getClass());
//        Assertions.assertEquals(messages.getResourceDeletedSuccessfully(), response.getBody());
//    }
//
//    @Test
//    void deleteById_returnResponseEntityOfStandardExceptionHandlerReturnAndHttp404_quandoIdNaoExistir() {
//        Throwable response = catchThrowable(() -> {
//            this.controller.deleteById(ID);
//        });
//
//        assertThat(response).isInstanceOf(ResourceNotFoundCustomException.class)
//                .hasMessageContaining(messages.getResourceNotFound());
//    }
//
//
//
//
//    @Test
//    void update_returnResponseEntityOfClientDTOResponseAndHttp200() {
//        var dtoResponse = this.controller.create(dtoRequest1);
//        dtoRequest2.id(dtoResponse.getBody().id());
//
//        var response = this.controller.update(dtoRequest2);
//
//        Assertions.assertNotNull(response);
//        Assertions.assertEquals(ResponseEntity.class, response.getClass());
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(ClientPersonDTOResponseImpl.class, response.getBody().getClass());
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertNotNull(response.getBody().id());
//        Assertions.assertEquals(dtoResponse.getBody().id(), response.getBody().id());
//        Assertions.assertNotEquals(dtoResponse.getBody().firstName(), response.getBody().firstName());
//        Assertions.assertNotEquals(dtoResponse.getBody().lastName(), response.getBody().lastName());
//        Assertions.assertNotEquals(dtoResponse.getBody().cpf(), response.getBody().cpf());
//
//        this.controller.deleteById(dtoResponse.getBody().id());
//    }
//
//    @Test
//    void update_returnResponseEntityOfStandardExceptionHandlerReturnAndHttp404_quandoIdNaoExistir() {
//        dtoRequest1.id(ID);
//
//        Throwable response = catchThrowable(() -> {
//            this.controller.update(dtoRequest1);
//        });
//
//        assertThat(response).isInstanceOf(ResourceNotFoundCustomException.class)
//                .hasMessageContaining(messages.getResourceNotFound());
//    }
//
//    @Test
//    void update_returnResponseEntityOfStandardExceptionHandlerReturnAndHttp409_quandoCpfJaCadastradoEmOutroId() {
//        var dtoResponse1 = this.controller.create(dtoRequest1);
//        var dtoResponse2 = this.controller.create(dtoRequest2);
//        dtoRequest3.id = dtoResponse2.getBody().id();
//
//        Throwable response = catchThrowable(() -> {
//            this.controller.update(dtoRequest3);
//        });
//
//        assertThat(response).isInstanceOf(BusinessRuleViolationCustomException.class)
//                .hasMessageContaining(messages.getSingleCpfRuleViolation());
//
//        this.controller.deleteById(dtoResponse1.getBody().id());
//        this.controller.deleteById(dtoResponse2.getBody().id());
//    }
//}
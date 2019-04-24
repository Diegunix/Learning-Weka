package com.learning.learning.controller;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.learning.learning.dao.domain.Localidad;
import com.learning.learning.dao.domain.Provincia;
import com.learning.learning.service.LearningService;
import com.learning.learning.service.UtilService;

@RunWith(PowerMockRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureRestDocs
public class WeatherControllerRestDocsTest {

    private UtilService utilService;
    private LearningService learningService;

    WeatherController controller;

    @Autowired
    private MockMvc mockMvc;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    public static FieldDescriptor[] provinciasModel() {
        return new FieldDescriptor[] { fieldWithPath("id").description("The province id"),
                fieldWithPath("descripcion").description("The description of the province") };
    }

    public static FieldDescriptor[] localidadModel() {
        return new FieldDescriptor[] { fieldWithPath("id").description("The localidad id"),
                fieldWithPath("descripcion").description("The description of the localidad") };
    }

    @Before
    public void before() {
        utilService = mock(UtilService.class);
        learningService = mock(LearningService.class);
        controller = new WeatherController(utilService, learningService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .alwaysDo(MockMvcRestDocumentation.document("{class-name}/{method-name}", Preprocessors.preprocessRequest()))
                .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation).uris().withScheme("https")
                        .withHost("app-diego-learning.herokuapp.com").withPort(443).and().snippets()
                        .withDefaults(CliDocumentation.curlRequest(), HttpDocumentation.httpRequest(), HttpDocumentation.httpResponse()))
                .build();
    }

    @Test
    public void getProvincias() throws Exception {
        List<Provincia> listProvincias = new ArrayList<>();
        Provincia prov = new Provincia();
        prov.setId(2L);
        prov.setDescripcion("Albacete");
        listProvincias.add(prov);

        when(utilService.getProvincias()).thenReturn(listProvincias);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/weather/provincias").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}", preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(fieldWithPath("[]").description("Array of provinces")).andWithPrefix("[]", provinciasModel())));

    }

    @Test
    public void getLocalidades() throws Exception {
        List<Localidad> listLocalidades = new ArrayList<>();
        Localidad local = new Localidad();
        local.setId(1L);
        local.setDescripcion("Abengibre");
        listLocalidades.add(local);

        when(utilService.getLocalidades(anyLong())).thenReturn(listLocalidades);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/weather/provincia/{id}/localidades", 2).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}", preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(fieldWithPath("[]").description("Array of provinces")).andWithPrefix("[]", localidadModel()),
                        pathParameters(parameterWithName("id").description("The id of the input to search"))));
    }

}
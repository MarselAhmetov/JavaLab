package team404.hateoas;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import team404.hateoas.domain.model.Command;
import team404.hateoas.service.ProjectService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class CommandTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        when(projectService.closeCommand(1L)).thenReturn(closedCommand());
    }

    @Test
    public void commandCloseTest() throws Exception {
        mockMvc.perform(patch("/command/1/closeCommand")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(closedCommand().getStatus()))
                .andExpect(jsonPath("$.name").value(closedCommand().getName()))
                .andDo(document("close_command", responseFields(
                        fieldWithPath("status").description("Статус команды"),
                        subsectionWithPath("name").description("Название"),
                        subsectionWithPath("id").description("Идентификатор"),
                        subsectionWithPath("description").description("Описание"),
                        subsectionWithPath("_links.closeCommand").description("Закрыть команду")

                )));;

    }

    private Command closedCommand() {
        return Command.builder()
                .id(1L)
                .status("closed")
                .name("Name")
                .description("Description")
                .build();

    }

}

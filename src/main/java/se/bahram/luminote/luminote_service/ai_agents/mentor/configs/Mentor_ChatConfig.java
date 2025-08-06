package se.bahram.luminote.luminote_service.ai_agents.mentor.configs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class Mentor_ChatConfig {
    private final ResourceLoader resourceLoader;

    public Mentor_ChatConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean(name = "Mentor_ChatClient")
    ChatClient chatClient(ChatClient.Builder builder) {

        //ChatMemory chatMemory = MessageWindowChatMemory.builder().build();

        return builder
                .defaultSystem(getDefaultSystemContent())
                //.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    private String getDefaultSystemContent() {
        Resource chatbotPersonaResource =
                resourceLoader.getResource("classpath:ai_agents_persona/mentor-persona.txt");
        String defaultSystemContent;
        try {
            defaultSystemContent = new String(Files.readAllBytes(Paths.get(chatbotPersonaResource.getURI())));
            System.out.println("---------- Mentor Persona Content ----------");
            System.out.println(defaultSystemContent);
            System.out.println("--------------------------------------------");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return defaultSystemContent;
    }
}

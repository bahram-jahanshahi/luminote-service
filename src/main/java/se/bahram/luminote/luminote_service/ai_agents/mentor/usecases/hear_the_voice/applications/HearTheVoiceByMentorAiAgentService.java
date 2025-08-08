package se.bahram.luminote.luminote_service.ai_agents.mentor.usecases.hear_the_voice.applications;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.bahram.luminote.luminote_service.ai_agents.mentor.usecases.hear_the_voice.applications.ports.in.HearTheVoiceByMentor_AiAgentUseCase;
import se.bahram.luminote.luminote_service.ai_agents.mentor.usecases.hear_the_voice.domain.MentorFeedBackForVoiceOfImage;

@Service
@Slf4j
public class HearTheVoiceByMentorAiAgentService implements HearTheVoiceByMentor_AiAgentUseCase {

    private final ChatClient chatClient;

    public HearTheVoiceByMentorAiAgentService(@Qualifier("Mentor_ChatClient")ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public MentorFeedBackForVoiceOfImage execute(String actualDescriptionOfTheImage, String voiceOfImageWrittenByUser) {

        String prompt = """
                You are a mentor who provides feedback on the description of an image written by student.
                The actual description of the image is: %s
                The description of the image written by the student is: %s
                Please provide constructive feedback on the voice of the image, focusing on clarity, creativity, and emotional impact.
                Return the feedback as a JSON object with the following fields:
                - feedback (String)
                - suggestions (List of 3 concise suggestions)
                - metaphors (List of 3 metaphors)
                - examples (List of 3 examples)
                """.formatted(actualDescriptionOfTheImage, voiceOfImageWrittenByUser);

        log.info("Executing HearTheVoiceByMentorService with prompt: {}", prompt);

        MentorFeedBackForVoiceOfImage result = chatClient.prompt()
                .user(prompt)
                .call()
                .entity(MentorFeedBackForVoiceOfImage.class);

        log.info("MentorFeedBackForVoiceOfImage result: {}", result);

        return result;
    }
}

package se.bahram.luminote.luminote_service.application_usecases.give_feedback_to_voice.applications;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.bahram.luminote.luminote_service.ai_agents.mentor.usecases.hear_the_voice.applications.ports.in.HearTheVoiceByMentor_AiAgentUseCase;
import se.bahram.luminote.luminote_service.ai_agents.mentor.usecases.hear_the_voice.domain.MentorFeedBackForVoiceOfImage;
import se.bahram.luminote.luminote_service.application_usecases.give_feedback_to_voice.applications.ports.in.GiveFeedbackToVoice_UseCase;
import se.bahram.luminote.luminote_service.application_usecases.give_feedback_to_voice.domain.FeedBackToVoice;

@Service
@Slf4j
public class GiveFeedbackToVoiceService implements GiveFeedbackToVoice_UseCase {

    private final HearTheVoiceByMentor_AiAgentUseCase hearTheVoiceByMentorAiAgentUseCase;

    public GiveFeedbackToVoiceService(HearTheVoiceByMentor_AiAgentUseCase hearTheVoiceByMentorAiAgentUseCase) {
        this.hearTheVoiceByMentorAiAgentUseCase = hearTheVoiceByMentorAiAgentUseCase;
    }

    @Override
    public FeedBackToVoice execute(Long userId, Long imageId, String voiceOfImageWrittenByUser) throws Exception {

        log.info("Starting giveFeedbackToVoice for userId: {} imageId: {}, voiceOfImageWrittenByUser: {}", userId, imageId, voiceOfImageWrittenByUser);

        MentorFeedBackForVoiceOfImage mentorFeedBackForVoiceOfImage =
                this.hearTheVoiceByMentorAiAgentUseCase
                    .execute(
                            getImageDescription(imageId),
                            voiceOfImageWrittenByUser
                    );

        FeedBackToVoice feedBackToVoice = new FeedBackToVoice(
                mentorFeedBackForVoiceOfImage.feedback(),
                mentorFeedBackForVoiceOfImage.suggestions()
        );

        log.info("feedBackToVoice {}", feedBackToVoice);

        return feedBackToVoice;
    }

    private String getImageDescription(Long imageId) {
        return """
               The image is a beautiful landscape with mountains in the background, a clear blue sky, and a serene lake in the foreground. The colors are vibrant, with lush green trees and colorful wildflowers scattered throughout the scene. The sunlight casts a warm glow over the entire landscape, creating a peaceful and tranquil atmosphere.
               There is a small wooden cabin near the lake, adding a rustic charm to the picturesque setting. The mountains are majestic, with snow-capped peaks that contrast beautifully with the greenery below. The overall composition of the image evokes a sense of calm and appreciation for nature's beauty.
               There is man standing in the foreground, looking out over the lake, which adds a human element to the scene. He appears to be enjoying the tranquility of the moment, perhaps reflecting on life or simply taking in the beauty around him.
               There is an eagle soaring high above the mountains, its wings spread wide as it glides effortlessly through the clear sky. The eagle's presence adds a sense of freedom and majesty to the landscape, symbolizing strength and independence.
               """;
    }
}

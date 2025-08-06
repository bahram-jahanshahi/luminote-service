package se.bahram.luminote.luminote_service.ai_agents.mentor.usecases.hear_the_voice.applications.ports.in;

import se.bahram.luminote.luminote_service.ai_agents.mentor.usecases.hear_the_voice.domain.MentorFeedBackForVoiceOfImage;

public interface HearTheVoiceByMentor_AiAgentUseCase {

    MentorFeedBackForVoiceOfImage execute(String imageDescription, String voiceOfImageWrittenByUser);
}

package se.bahram.luminote.luminote_service.application_usecases.give_feedback_to_voice.applications.ports.in;

import se.bahram.luminote.luminote_service.application_usecases.give_feedback_to_voice.domain.FeedBackToVoice;

public interface GiveFeedbackToVoice_UseCase {

    FeedBackToVoice execute(Long imageId, String voiceOfImageWrittenByUser) throws Exception;
}

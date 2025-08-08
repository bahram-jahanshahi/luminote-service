package se.bahram.luminote.luminote_service.application_usecases.give_feedback_to_voice.domain;

public record GiveFeedbackToVoice_UseCaseCommand(Long userId, Long imageId, String voiceOfImageWrittenByUser) {
}

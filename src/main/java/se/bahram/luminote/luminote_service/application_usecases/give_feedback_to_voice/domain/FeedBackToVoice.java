package se.bahram.luminote.luminote_service.application_usecases.give_feedback_to_voice.domain;

import java.util.List;

public record FeedBackToVoice(String feedback, List<String> suggestions, List<String> metaphors, List<String> examples) {

    public FeedBackToVoice {
        if (feedback == null || feedback.isBlank()) {
            throw new IllegalArgumentException("Feedback cannot be null or blank");
        }
        if (suggestions == null || suggestions.isEmpty()) {
            throw new IllegalArgumentException("Suggestions cannot be null or empty");
        }
    }
}

package se.bahram.luminote.luminote_service.application_usecases.give_feedback_to_voice.adapters.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.bahram.luminote.luminote_service.application_usecases.give_feedback_to_voice.applications.ports.in.GiveFeedbackToVoice_UseCase;
import se.bahram.luminote.luminote_service.application_usecases.give_feedback_to_voice.domain.FeedBackToVoice;
import se.bahram.luminote.luminote_service.application_usecases.give_feedback_to_voice.domain.GiveFeedbackToVoice_UseCaseCommand;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@Slf4j
public class GiveFeedbackToVoice_RestController {

    private final GiveFeedbackToVoice_UseCase giveFeedbackToVoiceUseCase;

    public GiveFeedbackToVoice_RestController(GiveFeedbackToVoice_UseCase giveFeedbackToVoiceUseCase) {
        this.giveFeedbackToVoiceUseCase = giveFeedbackToVoiceUseCase;
    }

    @PostMapping("/give-feedback-to-voice")
    public ResponseEntity<FeedBackToVoice> giveFeedbackToVoice(@RequestBody GiveFeedbackToVoice_UseCaseCommand command) {

        log.info("Starting giveFeedbackToVoice for userId: {} imageId: {}, voiceOfImageWrittenByUser: {}", command.userId(), command.imageId(), command.voiceOfImageWrittenByUser());

        FeedBackToVoice feedBackToVoice = null;
        try {
            feedBackToVoice = this.giveFeedbackToVoiceUseCase
                    .execute(command.userId(), command.imageId(), command.voiceOfImageWrittenByUser());

            return  ResponseEntity.ok(feedBackToVoice);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

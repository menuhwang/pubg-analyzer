package com.menu.pubganalyzer.common.exception;

public class ParticipantNotFoundException extends AbstractApplicationException {
    public ParticipantNotFoundException() {
        super(ErrorCode.PARTICIPANT_NOT_FOUND);
    }
}

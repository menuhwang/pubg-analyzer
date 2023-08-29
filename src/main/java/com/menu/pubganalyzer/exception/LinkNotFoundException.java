package com.menu.pubganalyzer.exception;

import static com.menu.pubganalyzer.exception.ErrorCode.LINK_NOT_FOUND;

public class LinkNotFoundException extends AbstractApplicationException {
    public LinkNotFoundException() {
        super(LINK_NOT_FOUND);
    }
}

package com.menu.pubganalyzer.common.exception;

import static com.menu.pubganalyzer.common.exception.ErrorCode.LINK_NOT_FOUND;

public class LinkNotFoundException extends AbstractApplicationException {
    public LinkNotFoundException() {
        super(LINK_NOT_FOUND);
    }
}

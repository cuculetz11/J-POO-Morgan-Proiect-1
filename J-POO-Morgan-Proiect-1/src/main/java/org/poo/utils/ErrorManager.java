package org.poo.utils;

import org.poo.command.debug.dto.DebugActionsDTO;
import org.poo.command.debug.error.NotFoundError;

public final class ErrorManager {
    public static void notFound(String description, String command, int timestamp) {
        DebugActionsDTO<NotFoundError> notFound = new DebugActionsDTO<>(command, new NotFoundError(description,timestamp),timestamp);
        JsonOutManager.getInstance().addToOutput(notFound);

    }
}

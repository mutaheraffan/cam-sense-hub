package com.camsensehub.utils;

import com.camsensehub.model.response.GenericResponse;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UtilMethods {
    public static void logFailed(GenericResponse<?> response, Logger logger) {
        logger.error("FAILED -> ResponseCode: " + response.getResponseCode() + ", ResponseDesc:"
                + response.getResponseDescription());

    }

    public static void logException(String where, Exception e, Logger logger) {
        logger.error("EXCEPTION in " + where + " -> Message: " + e.getMessage() + "\n" + e.getStackTrace());

    }

    public static void logSuccess(GenericResponse<?> response, Logger logger) {
        logger.info("SUCCESS -> ResponseCode: " + response.getResponseCode() + ", ResponseDesc:"
                + response.getResponseDescription());

    }

    public static void generalLog(String text, Logger logger) {
        logger.info(text);
    }
}

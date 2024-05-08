package com.camsensehub.utils;

import java.util.HashMap;

public class Constants {

	public static class ResponseCodes {
		public static final String OK = "00";
		public static final String INVALID_PARAMS = "01";
		public static final String UNABLE_TO_PROCESS = "99";
		public static final String CAMERA_DETAILS_NOT_FOUND = "60";

		public static final String UNABLE_TO_PROCESS_MESSAGE = "Unable to process at this time. Please, try again later";

		public static final HashMap<String, String> RESPONSE_MAP = new HashMap<String, String>() {

			{
				put(OK, "Processed OK");
				put(CAMERA_DETAILS_NOT_FOUND, "Camera Details not found");
				put(INVALID_PARAMS, "Required Parameters Not Provided");
				put(UNABLE_TO_PROCESS, UNABLE_TO_PROCESS_MESSAGE);
			}
		};


	}

}

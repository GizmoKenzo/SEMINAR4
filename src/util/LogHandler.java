package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is responsible for the log.
 */
public class LogHandler {
    private static final String LOG_FILE = "POS_log.txt";
    private static final LogHandler LOGGER = new LogHandler();
    private PrintWriter logFile;

    public LogHandler()
    {
    	try {
    		this.logFile = new PrintWriter(new FileWriter(LOG_FILE), true);
            
    	} catch (IOException exc) {
    		System.out.println("Logger was not created.");
            exc.printStackTrace();
    	}
    }

    public static LogHandler getLogger() {
        return LOGGER;
    }

    /**
     * Writes a log entry describing a thrown exception.
     *
     * @param exception The exception that shall be logged.
     */
    public void logException(Exception exception)
    {
        StringBuilder logMsg = new StringBuilder();
        logMsg.append("\n");
        logMsg.append("ERROR:");
        logMsg.append(exception.getMessage());
        logMsg.append("\n");
        logFile.println(logMsg);
        exception.printStackTrace(logFile);
        
    }

    /**
     * Writes a logged general message.
     *
     * @param logMessage The message to be logged.
     */
    public void logMessage(String logMessage) {
        logFile.println(logMessage);
    }

    
}


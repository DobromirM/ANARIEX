package anariex;

import gui.AnariexEvaluationHandler;
import gui.AnariexGUI;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

/**
 * @author DMarinov
 * Created on: 06/Feb/2019
 */
class AnariexLogger
{
    private Logger logger;
    
    /**
     * Custom Logger initialization. The logger logs all parse information to a log file with the current timestamp.
     * It also redirects the parse log to the GUI parse area.
     *
     * @param name - Name of the logger.
     */
    AnariexLogger(String name)
    {
        logger = Logger.getLogger(name);
        logger.setUseParentHandlers(false);
        LogManager.getLogManager().reset();
        
        try
        {
            String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            FileHandler fileHandler = new FileHandler("src/main/resources/logs/log-" + fileSuffix + ".txt");
            fileHandler.setFormatter(new AnariexFormatter());
            logger.addHandler(fileHandler);
            logger.addHandler(new AnariexEvaluationHandler(AnariexGUI.evaluationArea));
        }
        catch (NoSuchFileException ignored)
        {
            logger.addHandler(new AnariexEvaluationHandler(AnariexGUI.evaluationArea));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Log message using the default logger.
     *
     * @param message - Message to be displayed.
     */
    void loggerWriteInfo(String message)
    {
        logger.info(message);
    }
    
    /**
     * Custom class for defining the Anariex log format.
     */
    class AnariexFormatter extends Formatter
    {
        @Override
        public String format(LogRecord record)
        {
            return String.valueOf(record.getMessage());
        }
    }
}
package ua.com.yatran.core.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.yatran.actions.BaseActions;
import ua.com.yatran.entities.RankingRecord;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

public class GameResultsHelper {

    private static Logger logger = LogManager.getLogger(BaseActions.class);
    private static final String FILE_PATH = System.getProperty("user.home") + FileSystems.getDefault().getSeparator() + "Yatran" + FileSystems.getDefault().getSeparator() + "Data.ytr";

    /**
     * Saves the provided game records by replacing the old ones
     *
     * @param records records to save
     */
    public static void saveRecords(List<RankingRecord> records) {
        logger.info("Saving game records: " + records + "...");
        File file = new File(FILE_PATH);
        createFile(file);
        writeToFile(file, records);
        logger.info("Game records have been successfully saved!");
    }

    /**
     * Returns the list of the records
     *
     * @return list of the records
     */
    public static List<RankingRecord> getRecords() {
        logger.info("Getting game records...");
        List<RankingRecord> resultList = new ArrayList<>();
        try (
                FileInputStream streamIn = new FileInputStream(FILE_PATH);
                ObjectInputStream objectinputstream = new ObjectInputStream(streamIn)
        ) {
            resultList = (List<RankingRecord>) objectinputstream.readObject();
        } catch (Exception e) {
            logger.error("Error during getting game records", e);
        }
        logger.info("The result = " + resultList);
        return resultList;
    }

    /**
     * Creates the file if it doesn't exist
     *
     * @param file file to create
     */
    private static void createFile(File file) {
        logger.info("Creating file: " + file.getAbsolutePath() + "...");
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException ex) {
            logger.error("Error during creating results file", ex);
        }
        logger.info("File has been successfully created!");
    }

    /**
     * Writes the provided results to the file
     *
     * @param file       file to write
     * @param resultList result list to write
     */
    private static void writeToFile(File file, List<RankingRecord> resultList) {
        logger.info("Writing data to file [" + file.getAbsolutePath() + "]: " + resultList + "...");
        try (
                FileOutputStream fout = new FileOutputStream(file, false);
                ObjectOutputStream oos = new ObjectOutputStream(fout)
        ) {
            oos.writeObject(resultList);
        } catch (Exception ex) {
            logger.error("Error during writing data to the file", ex);
        }
        logger.info("Data has been successfully written!");
    }
}

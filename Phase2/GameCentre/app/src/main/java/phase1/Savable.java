package phase1;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Savable  {

    /**
     * Save data to a .ser file.
     */
    public static void saveToFile(String fileName, Object dataToSave, String contextPath) {
        try {
            File file = new File(contextPath + fileName);
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(output);
            outputStream.writeObject(dataToSave);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Retrieve data from a .ser file.
     */
    @SuppressWarnings("unchecked")
    public static Object loadFromFile(String contextPath, String saveFileName) {
        try {
            File file = new File(contextPath + saveFileName);
            System.out.println("path:" + contextPath + saveFileName);
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(input);
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException error) {
            return error;
        }
    }

}

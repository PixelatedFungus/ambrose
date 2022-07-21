package lusaris;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AionUtils {


    /**
     * A function that takes a file and a HashMap. It converts the HashMap to
     * a JSON object then writes it to the given file.
     * @param file The file which we are writing to.
     * @param hashMap The HashMap which contains information we want to write.
     */
    public static void writeHashMaptoJSON(File file, HashMap<String, ?> hashMap) {
        JSONObject jsonObject = new JSONObject(hashMap);
        try (FileWriter writeObject = new FileWriter(file.getPath())) {
            writeObject.write(jsonObject.toJSONString());
            writeObject.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A function that takes a JSONArray and converts it to an ArrayList.
     * The ArrayList contains generic Objects.
     * @param jsonArray The JSONArray, usually from a JSON file.
     * @return An ArrayList containing Objects.
     */
    public static ArrayList<String> JSONArr_to_ArrayList(JSONArray jsonArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.toArray().length; i++) {
                arrayList.add(String.valueOf(jsonArray.get(i)));
            }
        }
        return arrayList;
    }

    /**
     * This method takes a class instance and serializes it, placing it in the
     * file described by the String fileName.
     * @param fileName The name of the file in which we want to store our
     *                 serialized object.
     * @param instance The object which we want to store in our file.
     */
    public static void serializeObject(String fileName, Object instance) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(instance);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method deserializes and reads and object from an intended file.
     * It requires the user to input the expected class and uses generics
     * to ensure the correct output.
     * @param fileName The name of the file from which we want to extract
     *                 our serialized object.
     * @param expectedClass The class that of the file that we want to extract.
     * @param <T> A generic standing in for the class of the object we want to
     *           extract.
     * @return The object we want from our file.
     */
    public static <T extends Serializable> T deserializeObject(
            String fileName, Class<T> expectedClass) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectOutputStream =
                    new ObjectInputStream(fileInputStream);

            T result =expectedClass.cast(objectOutputStream.readObject());

            objectOutputStream.close();
            fileInputStream.close();

            return result;
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Overload method for serializeObject with the first parameter as a String.
     * In this method, the file is simply passed on to the other
     * serializeObject method with the file's name passed in as the first
     * parameter.
     * @param file The file in which we want to store our serialized object.
     * @param instance The object which we want to store in our file.
     */
    public static void serializeObject(File file, Object instance) {
        serializeObject(file.getPath(), instance);
    }

    /**
     * Checks if the number of inputs for a command is the correct length.
     * @param n The length of the input we want.
     * @param inputs The inputs the user inputted.
     * @return Returns true if the number of inputs is correct, else false.
     */
    public static boolean checkNInputs(int n, String[] inputs) {
        if (inputs.length != n) {
            System.out.println("The input is the incorrect length.");
            System.out.printf("It requires %o input(s).%n", n);
            return false;
        } else {
            return true;
        }
    }
}

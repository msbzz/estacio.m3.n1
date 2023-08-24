package utils;

import java.io.*;

public class IDControle {

    private static final String FILE_ID = "id.bin";

    static {

        // Inicializar o arquivo com o ID 0, se ele ainda não existir.
        File file = new File(FILE_ID);
        if (!file.exists()) {
            try {
                saveID(0);
            } catch (IOException e) {
                System.err.println("Erro ao inicializar o arquivo de ID: " + e.getMessage());
            }
        }
    }

    public static  int getID()  {
        int proximoID=1;

        try{
            int atualID = readID();
            proximoID = atualID + 1;
            saveID(proximoID);
        }catch (IOException e){

        }finally {
            return proximoID;
        }

    }

    private static int readID() throws IOException {
        try (DataInputStream in = new DataInputStream(new FileInputStream(FILE_ID))) {
            return in.readInt();
        }
    }

    private static void saveID(int id) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(FILE_ID))) {
            out.writeInt(id);
        }
    }


}


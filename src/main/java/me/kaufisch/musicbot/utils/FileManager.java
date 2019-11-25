package me.kaufisch.musicbot.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

/**
 * @author Kaufisch
 */
public class FileManager {
    private HashMap<String, String> playlist = new HashMap<>();
    public ArrayList<String> currentSong = new ArrayList<>();

    public void randomSong() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream("MusicBot/playlist.txt"), StandardCharsets.UTF_8));
            String line = reader.readLine();
            while (line != null) {
                playlist.put(line.split(";")[1], line.split(";")[0]);
                line = reader.readLine();
            }
            Object[] crunchifyKeys = playlist.keySet().toArray();
            Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
            currentSong.add(playlist.get(key));
            currentSong.add(key.toString());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Config() throws FileNotFoundException, UnsupportedEncodingException {
        //Check If MusicBot Directory exists
        File dir = new File("MusicBot");
        //Create MusicBot Directory
        if (!(dir.exists() && dir.isDirectory())) {
            dir.mkdir();
        }

        //Check If playlist.txt exists
        File playlist = new File("MusicBot/playlist.txt");
        if (!(playlist.exists() && !playlist.isDirectory())) {
            PrintWriter writer = new PrintWriter("MusicBot/playlist.txt", "UTF8");
            writer.println("Kaufisch - Fill your playlist with songs;https://youtu.be/ctGOaiHP4dE");
            writer.close();
        }

        //Check If config.properties exists
        File config = new File("MusicBot/config.properties");
        if (!(config.exists() && !config.isDirectory())) {
            //Creates config.properties
            Properties prop = new Properties();
            OutputStream output = null;

            try {

                output = new FileOutputStream("MusicBot/config.properties");

                // set the properties value
                prop.setProperty("MusicChannel", "CHANNEL ID of your Voice Channel");
                prop.setProperty("VoteSkipChannel", "CHANNEL ID of your Text Channel");
                prop.setProperty("TOKEN", "Your Bot TOKEN");
                prop.setProperty("GUILD_ID", "Your GUILD ID");
                prop.setProperty("VoteSkipMessage", "Message sent when user already wanted to skip");

                // save properties to project root folder
                prop.store(output, null);

            } catch (IOException io) {
                io.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("You have to replace the Variables in MusicBot/config.properties");
            System.out.println("You have to fill your playlist in MusicBot/playlist.txt up");
            System.exit(0);
        } else {
            //config.properties Exists
            Properties prop = new Properties();
            InputStream input = null;
            try {
                input = new FileInputStream("MusicBot/config.properties");
                // load a properties file
                prop.load(input);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (prop.getProperty("MusicChannel").equals("CHANNEL ID of your Voice Channel") || prop.getProperty("VoteSkipChannel").equals("CHANNEL ID of your Text Channel") || prop.getProperty("TOKEN").equals("Your Bot TOKEN") || prop.getProperty("GUILD_ID").equals("Your GUILD ID") || prop.getProperty("VoteSkipMessage").equals("Message sent when user already wanted to skip")) {
                System.out.println("You have to replace the Variables in MusicBot/config.properties");
                System.exit(0);
            }

            Const.MusicChannel = prop.getProperty("MusicChannel");
            Const.VoteSkipChannel = prop.getProperty("VoteSkipChannel");
            Const.TOKEN = prop.getProperty("TOKEN");
            Const.GUILD_ID = prop.getProperty("GUILD_ID");
            Const.VoteSkipMessage = prop.getProperty("VoteSkipMessage");

        }
    }
}

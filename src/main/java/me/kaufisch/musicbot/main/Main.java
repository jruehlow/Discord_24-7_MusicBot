package me.kaufisch.musicbot.main;

import me.kaufisch.musicbot.events.ChannelListener;
import me.kaufisch.musicbot.events.MessageListener;
import me.kaufisch.musicbot.events.Resume;
import me.kaufisch.musicbot.utils.Const;
import me.kaufisch.musicbot.utils.FileManager;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;

import java.util.ArrayList;

/**
 * @author Kaufisch
 */
public class Main {

    public static JDA jda;
    public static ArrayList<Member> userAlreadyDid = new ArrayList<>();
    public static ArrayList<Member> userInChannel = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        FileManager fileManager = new FileManager();
        fileManager.Config();

        jda = new JDABuilder(AccountType.BOT)
                .setToken(Const.TOKEN)
                .setActivity(Activity.playing("Music"))
                .addEventListeners(new Resume())
                .addEventListeners(new MessageListener())
                .addEventListeners(new ChannelListener())
                .setAutoReconnect(true)
                .build();
    }
}

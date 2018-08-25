package me.kaufisch.musicbot.events;

import me.kaufisch.musicbot.audioCore.Music;
import me.kaufisch.musicbot.utils.Const;
import me.kaufisch.musicbot.main.Main;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
/**
 * @author Kaufisch
 */
public class MessageListener extends ListenerAdapter {

    private boolean started = false;
    private int a;
    private int b;
    private int c;
    private int temp;

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getMessage().getChannel().getId().equals(Const.VoteSkipChannel)) {
            if (event.getMessage().getContentRaw().contentEquals("!skip")) {
                if (Main.userInChannel.contains(event.getMember())) {
                    if (!Main.userAlreadyDid.contains(event.getMember())) {
                        c = Main.userInChannel.size();
                        a++;
                        Main.userAlreadyDid.add(event.getMember());
                        temp = a * 100;
                        b = temp / c;

                        if (c == 1) {
                            Music.skip();
                            event.getChannel().sendMessage("Voteskip: " + a + " / " + c).queue();
                            c = 0;
                            a = 0;
                            temp = 0;
                            b = 0;
                            Main.userAlreadyDid.clear();
                        } else if (c == 2) {
                            if (b == 100) {
                                Music.skip();
                                event.getChannel().sendMessage("Voteskip: " + a + " / " + c).queue();
                                c = 0;
                                a = 0;
                                temp = 0;
                                b = 0;
                                Main.userAlreadyDid.clear();
                            } else {
                                event.getChannel().sendMessage("Voteskip: " + a + " / " + c).queue();
                            }
                        } else if (c > 2) {
                            if (b > 60) {
                                Music.skip();
                                event.getChannel().sendMessage("Voteskip: " + a + " / " + c).queue();
                                c = 0;
                                a = 0;
                                temp = 0;
                                b = 0;
                                Main.userAlreadyDid.clear();
                            } else {
                                event.getChannel().sendMessage("Voteskip: " + a + " / " + c).queue();
                            }

                        } else {
                            event.getChannel().sendMessage("Voteskip: " + a + " / " + c).queue();
                        }
                    } else {
                        event.getChannel().sendMessage(event.getMember().getAsMention() + " " + Const.VoteSkipMessage).queue();
                    }
                } else {
                    event.getMessage().delete().queue();
                }
            } else {
                if (!event.getMember().getUser().isBot()) {
                    event.getMessage().delete().queue();
                }
            }
        }
    }
}

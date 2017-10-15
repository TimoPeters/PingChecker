package pingchecker.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PingChecker {

    private final String NA = "104.160.131.3";
    private final String EUW = "104.160.141.3";
    private final String EUNE = "104.160.142.3";
    private final String OCE = "104.160.156.1";
    private final String LAN = "104.160.136.3";


    public String getPing(String entryIpAddress) {
        String output = "";
        String regex = ".*Maximum = ([0-9]*)ms.*";
        String ping = "";
        String[] command = {"cmd.exe", "/C", "ping -n 1 " + entryIpAddress};
        try {
            Process commandProcess = Runtime.getRuntime().exec(command);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(commandProcess.getInputStream()));
            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                output = output += readLine;
            }
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(output);
            if (matcher.find()) {
                ping = matcher.group(1);
            }
        } catch (IOException e) {
            System.out.println("Command does not work");
            e.printStackTrace();
        }

        return ping;
    }

}
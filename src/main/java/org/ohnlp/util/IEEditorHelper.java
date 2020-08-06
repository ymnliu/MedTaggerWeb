package org.ohnlp.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class IEEditorHelper {

    public static JSONObject getIERulePackJSON(String rulesetPath) {
        return null;
    }

    public static Path saveIERulePack(String rulepack) throws Exception {
        // Unpack the rule package
        JSONParser jsonParser = new JSONParser();
        JSONObject jRulePack = (JSONObject) jsonParser.parse(rulepack);

        // create temp folder for the rule pack
        Path user_temp_path = Files.createTempDirectory("guest-");
        System.out.println("* created temp folder: " + user_temp_path.toAbsolutePath().toString());

        // create the used_resources.txt
        File file_used_resources_txt = new File(user_temp_path.toAbsolutePath().toString(), "used_resources.txt");
        Files.createFile(file_used_resources_txt.toPath());

        // create the content for the used_resources.txt
        String lines_used_resources_txt = "./used_resources.txt\n";

        // create 3 folders
        File dir_rules = new File(user_temp_path.toAbsolutePath().toString(), "rules");
        dir_rules.mkdir();
        File dir_regexp = new File(user_temp_path.toAbsolutePath().toString(), "regexp");
        dir_regexp.mkdir();
        File dir_context = new File(user_temp_path.toAbsolutePath().toString(), "context");
        dir_context.mkdir();

        // create rules/resources_rules_matchrules.txt
        File file_resources_rules_matchrules_txt = new File(
            dir_rules.getAbsolutePath(),
            "resources_rules_matchrules.txt"
        );
        Files.createFile(file_resources_rules_matchrules_txt.toPath());
        lines_used_resources_txt += "./rules/resources_rules_matchrules.txt\n";

        JSONArray matchrules = (JSONArray) jRulePack.get("matchrules");
        String lines_matchrules = "";
        for (int i = 0; i < matchrules.size(); i++) {
            JSONObject matchrule = (JSONObject) matchrules.get(i);
            String regexp = (String) matchrule.get("regexp");
            String rule_name = (String) matchrule.get("rule_name");
            String location = (String) matchrule.get("location");
            String norm = (String) matchrule.get("norm");

            // create the line for output
            String line = "RULENAME=\"" + rule_name + "\"," +
                          "REGEXP=\"" + regexp + "\"," +
                          "LOCATION=\"" + location + "\"," +
                          "NORM=\"" + norm + "\"\n";
            lines_matchrules += line;
        }
        Files.write(file_resources_rules_matchrules_txt.toPath(), lines_matchrules.getBytes());

        // create rules/resources_rules_matchrules.txt
        File file_contextRule_txt = new File(
            dir_context.getAbsolutePath(),
            "contextRule.txt"
        );
        Files.createFile(file_contextRule_txt.toPath());
        lines_used_resources_txt += "./context/contextRule.txt\n";

        // create regexp files
        JSONArray rsregexps = (JSONArray) jRulePack.get("rsregexps");
        for (int i = 0; i < rsregexps.size(); i++) {
            JSONObject rsregexp = (JSONObject) rsregexps.get(i);
            String name = (String) rsregexp.get("name");
            String text = (String) rsregexp.get("text");

            // create file for this regexp
            File file_rs_regexp_txt = new File(
                dir_regexp.getAbsolutePath(),
                name + ".txt"
            );
            Files.createFile(file_rs_regexp_txt.toPath());
            Files.write(file_rs_regexp_txt.toPath(), text.getBytes());
            lines_used_resources_txt += "./regexp/" + file_rs_regexp_txt.getName() + "\n";
        }
        Files.write(file_used_resources_txt.toPath(), lines_used_resources_txt.getBytes());

        // return the user_temp_path
        return user_temp_path;
    }
}
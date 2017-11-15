package it.infocert.eigor.api.utils;

import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class EigorVersion {

    private static final Logger log = LoggerFactory.getLogger(EigorVersion.class);

    private static Properties properties = null;


    public static Properties getAsProperties() {
        if (properties == null) {
            loadFromGitProperties();
        }
        return properties;
    }

    public static String getAsString() {
        if (properties == null) {
            loadFromGitProperties();
        }

        StringBuilder sb = new StringBuilder("Eigor ");

        sb.append(properties.getProperty("git.build.version")).append(" ");
        sb.append(properties.getProperty("git.branch"));

        boolean isDirty = Boolean.parseBoolean(properties.getProperty("git.dirty"));
        if (isDirty){
            sb.append("-dirty!");
        }
        sb.append(" ").append(properties.getProperty("git.commit.id"));
        sb.append(" ").append(properties.getProperty("git.commit.time"));

        return sb.toString();
    }

    private static void loadFromGitProperties() {
        properties = new Properties();
        URL resource = Resources.getResource("git.properties");
        try {
            properties.load(resource.openStream());
        } catch (IOException e) {
            log.error("Unable to load version information from git.properties", e);
        }
    }
}

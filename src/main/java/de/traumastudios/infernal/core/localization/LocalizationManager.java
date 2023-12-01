package de.traumastudios.infernal.core.localization;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.traumastudios.infernal.core.debug.InfernalLogger;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class LocalizationManager {
    private InfernalLogger logger = InfernalLogger.getInstance("localization.log");
    private static final LocalizationManager instance = new LocalizationManager();
    private final Map<String, Map<String, String>> translations = new HashMap<>();
    private String defaultLanguage = "en";
    private String currentLanguage = defaultLanguage;
    private final Gson gson;

    private LocalizationManager() {
        this.gson = new Gson();
    }

    /**
     * Add a new translation to the translations
     * @param languageCode
     * @param translation
     */
    public void addTranslation(String languageCode, Map<String, String> translation) {
        this.translations.put(languageCode, translation);
    }

    /**
     * Loads translations from a JSON translation file
     * @param filePath
     * @param languageCode
     */
    public void loadTranslationsFromFile(String filePath, String languageCode) {
        try {
            Path path = Path.of(filePath);
            String content = Files.readString(path);
            Type mapType = new TypeToken<Map<String, String>>() {}.getType();
            Map<String, String> translation = this.gson.fromJson(content, mapType);

            this.translations.put(languageCode, translation);
        } catch (IOException e) {
            this.logger.error("Translations couldn't be loaded from file: " + e);
        }
    }

    /**
     * Sets the default language
     * @param languageCode
     */
    public void setDefaultLanguage(String languageCode) {
        this.defaultLanguage = languageCode;
        this.currentLanguage = languageCode;
    }

    /**
     * Set the language
     * @param languageCode
     */
    public void setLanguage(String languageCode) {
        this.currentLanguage = languageCode;
    }

    /**
     * Translate a given key between current and default language
     * @param key
     * @return
     */
    public String translate(String key) {
        Map<String, String> languageTranslations = this.translations.get(this.currentLanguage);
        if (languageTranslations != null && languageTranslations.containsKey(key)) {
            return languageTranslations.get(key);
        }

        languageTranslations = this.translations.get(this.defaultLanguage);
        return languageTranslations != null ? languageTranslations.getOrDefault(key, key) : key;
    }
}

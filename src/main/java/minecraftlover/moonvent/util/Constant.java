package minecraftlover.moonvent.util;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.Map;

/**
 * Class {@code Constant} need for store all constants in project.
 * <p>
 * This class need to store all constants in project
 * </p>
 */
public final class Constant {
    public static String MOD_NAME = null;

    public static final String MOD_ID = "minecraftlover_moonvent_hphud";

    public static final String LOGGER_HUD_NAME = MOD_NAME + "| HUD";

    static {
        MOD_NAME = firstLoadModName();
    }

    public static final String firstLoadModName() {
        // load fabric.mod.json
        InputStreamReader reader = new InputStreamReader(Constant.class.getResourceAsStream("/fabric.mod.json"));
        Map<?, ?> jsonObject = new Gson().fromJson(reader, Map.class);

        // get mod title
        String modName = (String) jsonObject.get("name");
        return modName;
    }

}

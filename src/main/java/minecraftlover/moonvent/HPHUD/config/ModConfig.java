package minecraftlover.moonvent.HPHUD.config;

public final class ModConfig {

  // for singleton
  private static ModConfig instance;

  private String __comment = "Its a config for setup mod [HPHUD], json doesnt support quats, cause I use square brackets, but you need to use a double quotes instead it, I try to describe a field and it do with comment which start from double underscore, this comments doesnt config something, its just for understanding what doing field without double undersocre, for example [__searchDistance]: [range for how far (in block) you can see hp from entity], and this var themself [searchDistance]: 20";

  private String __outputGeneralAmountEnemyHp = "Output general amount of entity health, for example, if true, indicator will show [18/20], false - [18]";
  public boolean outputGeneralAmountEnemyHp = false;

  private String __searchDistance = "How far (in blocks) mod will handle entity for present amount of hp";
  public int searchDistance = 20;

  private String __indicatorColor = "Color of indicator. I try a dozen colors, but select exactly pink, because that color has the least chance of blending in with the background. Sets in int format, you can convert your color in some service, for example from that http://www.shodor.org/~efarrow/trunk/html/rgbint.html";
  public int indicatorColor = 0xFFAFFF;

  public ModConfig() {
    instance = this;
  }

  public static ModConfig getInstance() {
    return instance;
  }
}

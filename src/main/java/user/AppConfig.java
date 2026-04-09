package user;


import org.aeonbits.owner.Config;
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:config.${env}.properties"})

public interface AppConfig extends Config {

    @Key("browser")
    String browser();
    }


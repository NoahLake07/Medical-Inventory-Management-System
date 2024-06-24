package mims.data.app;

public class MIMSEnv {

    private boolean devMode;

    public MIMSEnv(){
        this.devMode = false;
    }

    public MIMSEnv(boolean devMode){
        this.devMode = devMode;
    }

    public boolean isDevMode() {
        return devMode;
    }

}

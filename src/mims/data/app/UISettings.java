package mims.data.app;

public class UISettings {

    private Theme theme;
    private FontSize fontSize;

    public UISettings(){
        this.theme = Theme.LIGHT;
        this.fontSize = FontSize.MEDIUM;
    }

    public UISettings(Theme theme, FontSize fontSize){
        this.theme = theme;
        this.fontSize = fontSize;
    }

    public Theme getTheme() {
        return theme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public enum Theme {
        LIGHT,
        DARK
    }

    public enum FontSize {
        SMALL,
        MEDIUM,
        LARGE
    }

}

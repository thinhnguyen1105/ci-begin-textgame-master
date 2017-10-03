package settings.Novels;

public class Choice {
    public String value;
    public String to;

    public Choice (String value,String to) {
        this.value = value;
        this.to = to;

    }

    public boolean match (String answer){
        return value.equalsIgnoreCase(answer) || value.equalsIgnoreCase("other");
    }
}

package settings.Novels;

import java.util.List;
import java.util.Arrays;

public class Story {
    public String id;
    public String text;
    // tạo ra thuộc tính kiểu Choice dạng List ( danh sách ) và biến đặt tên là choices
    public List<Choice> choices;
    // NHIỀU biến choices kiểu Choice ( trả về value và to )
    public Story (String id, String text, Choice... choices) {
        this.id = id;
        this.text = text;
        //biến choices đang kiểu List ép sang mảng như choices
        this.choices = Arrays.asList(choices);
    }
}

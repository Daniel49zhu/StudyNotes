enum Search {HITHER,YON}
public class UpcastEnum {
    public static void main(String[] args) {
        Search[] vals = Search.values();
        Enum e = Search.HITHER;//向上转型
        for(Enum en :e.getClass().getEnumConstants()) {
            System.out.println(en);
        }
    }
}
/* output
 HITHER
 YON
 */
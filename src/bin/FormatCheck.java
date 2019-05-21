package bin;

/**
 * 前端函数库
 * 用于管理检查输入格式的函数
 */
public class FormatCheck {
    public static int isID(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return 0;
            }
        }
        if (str.length() != 9)
            return 0;
        return 1;
    }

    private static int isCharacter(char c) {
        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')))
            return 0;
        else
            return 1;
    }

    public static int isName(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if ((isCharacter(str.charAt(i)) == 0) && (str.charAt(i) != ' ')) {
                return 0;
            } else if (str.charAt(0) == ' ' || str.charAt(str.length() - 1) == ' ') {
                return 0;
            }
        }
        return 1;
    }

    public static int isAddress(String str) {
        int count1 = 0, count2 = 0;
        int j = 0, k = 0, l = 0;
        for (int i = str.length(); --i >= 0; ) {
            if (str.charAt(i) == '@') {
                count1++;
                j = i;
            }
            if (str.charAt(i) == '.' && count2 == 0) {
                count2++;
                k = i;
            } else if (str.charAt(i) == '.' && count2 == 1) {
                count2++;
                l = i;
            }
        }
        //System.out.println(" "+j+k+l);
        if ((count1 != 1 || count2 != 2)) {
            return 0;
        } else if (!((j < l) && (l < k)))
            return 0;
        //������򣺱�����xxx@qmul.ac.uk;xxx���������֡���ĸ���»��ߣ���һλ��������ĸ
        for (int i = j; --i >= 0; ) {
            if ((isCharacter(str.charAt(i)) == 0) && (!Character.isDigit(str.charAt(i))) && (str.charAt(i) != '_')) {
                return 0;
            } else if (isCharacter(str.charAt(0)) == 0)
                return 0;
        }

        String subfix = str.substring(j + 1);
        if (!subfix.equals("qmul.ac.uk")) {
            return 0;
        }
        return 1;
    }
}

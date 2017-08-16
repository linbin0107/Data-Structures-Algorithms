import java.util.ArrayList;
import java.util.List;

/**
 * Created by blin on 8/16/17.
 */

/**
 * Given an array of words and a length L, format the text such that each line has exactly
 * L characters and is fully (left and right) justified. You should pack your words in a
 * greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' '
 * when necessary so that each line has exactly L characters. Extra spaces between words
 * should be distributed as evenly as possible. If the number of spaces on a line do not
 * divide evenly between words, the empty slots on the left will be assigned more spaces
 * than the slots on the right. For the last line of text, it should be left justified and
 * no extra space is inserted between words.
 *
 * A line other than the last line might contain only one word. What should you do in this case?
 * In this case, that line should be left-justified.
 */
public class Solution {
    public List<String> fullJustify(String []words, int maxWidth) {
        if (words == null || words.length == 0) {
            return  new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int j = i, len = 0;

            while (j < words.length && (len + words[j].length() + j - i <= maxWidth)) {
                len += words[j++].length();
            }

            StringBuilder sb = new StringBuilder();
            int extra = maxWidth - len;
            int num = j - i;

            // If the line contains only one word. In this case, the line should be left-justified
            if (num == 1) {
                sb.append(words[i]);

                while (extra > 0) {
                    sb.append(" ");
                    --extra;
                }

                res.add(sb.toString());
            // If the line is the last line, then the line also should be left justified
            } else if (j == words.length) {
                for (int k = i; k < j; ++k) {
                    sb.append(words[k]);

                    if (k == j - 1) {
                        break;
                    }

                    sb.append(" ");
                    --extra;
                }

                while (extra > 0) {
                    sb.append(" ");
                    --extra;
                }

                res.add(sb.toString());
            // All other cases, distribute the extra spaces as evenly as possible
            } else {
                int cnt = extra / (num - 1);
                String space = "";

                while (cnt > 0) {
                    space += " ";
                    --cnt;
                }

                int remainder = extra % (num - 1);

                for (int k = i; k < j; ++k) {
                    sb.append(words[k]);

                    if (k == j - 1) {
                        break;
                    }

                    sb.append(space);

                    if (remainder > 0) {
                        sb.append(" ");
                        --remainder;
                    }
                }

                res.add(sb.toString());
            }

            i = j;
        }

        return res;
    }

    public static void main(String []args) {
        Solution solution = new Solution();

        String []test1 = {"This", "is", "an", "example", "of", "text", "justification."};
        String []test2 = {"My","momma","always","said,","\"Life","was","like","a","box",
                "of","chocolates.","You","never","know","what","you're","gonna","get."};

        System.out.println(solution.fullJustify(test1, 16).toString());
        System.out.println(solution.fullJustify(test2, 20).toString());
    }
}

import java.util.*;

class Solution {
    // 샵포함 악보를 음으로 정규화
    private String normalize(String s) {
        StringBuilder out = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (i + 1 < s.length() && s.charAt(i + 1) == '#') {
                out.append(Character.toLowerCase(ch));
                i++; //#건너뛰기
            } else {
                out.append(ch); 
            }
        }
        return out.toString();
    }

    private int toMinutes(String hhmm) {
        String[] t = hhmm.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }

    private String expandToDuration(String code, int dur) {
        if (dur <= 0) return "";
        StringBuilder sb = new StringBuilder(dur);
        for (int i = 0; i < dur; i++) {
            sb.append(code.charAt(i % code.length()));
        }
        return sb.toString();
    }

    public String solution(String m, String[] musicinfos) {
        String needle = normalize(m);

        String answer = "(None)";
        int bestDur = -1;
        int bestIdx = Integer.MAX_VALUE; //앞선 곡 우선

        for (int i = 0; i < musicinfos.length; i++) {
            String[] sp = musicinfos[i].split(",");
            int dur = toMinutes(sp[1]) - toMinutes(sp[0]);
            String title = sp[2];
            String code = normalize(sp[3]);

            String played = expandToDuration(code, dur);

            if (played.contains(needle)) {
                if (dur > bestDur || (dur == bestDur && i < bestIdx)) {
                    bestDur = dur;
                    bestIdx = i;
                    answer = title;
                }
            }
        }
        return answer;
    }
}

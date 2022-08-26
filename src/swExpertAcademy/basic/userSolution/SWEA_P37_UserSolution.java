package swExpertAcademy.basic.userSolution;

import java.util.Arrays;

/**
 * [No. 37] 7701. 염라대왕의 이름 정렬
 *
 * 시간 : 50개 테스트케이스를 합쳐서 C의 경우 5초 / C++의 경우 5초 / Java의 경우 10초 / Python의 경우 10초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P37_UserSolution {

    public String[] solution(String[] names) {
        return Arrays.stream(names)
                .distinct()
                .sorted((o1, o2) -> {
                    int diff = o1.length() - o2.length();
                    if (diff != 0) {
                        return diff;
                    } else {
                        return o1.compareTo(o2);
                    }
                })
                .toArray(String[]::new);
    }

}
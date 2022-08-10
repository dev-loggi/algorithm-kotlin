package swExpertAcademy.codeBattle.userSolution;

import java.util.HashSet;
import java.util.Set;

/**
 *  [No. 35] 9416. Social Media
 *
 *  실행 시간 : 최대 50개의 테스트 케이스를 합쳐서 3 초 이내
 *  메모리 : Heap, Global, Stack 등을 모두 합해 최대 256MB 까지 사용 가능
 *  (단, 스택은 최대 1MB 까지 사용 가능)
 *
 * <br/>[제약사항]<br/>
 *
 * 1. 사용자 수 N 은 2 이상 1,000 이하의 정수이다. (2 ≤ N ≤ 1,000)<br/>
 * 2. timestamp 는 1 에서 시작하고 최대 100,000 까지 증가한다.<br/>
 * 3. 모든 함수들은 timestamp 오름차순으로 호출된다.<br/>
 * 4. 모든 게시물들의 timestamp 는 서로 다르다.<br/>
 * 5. follow(), makePost(), like() 함수의 호출 횟수는 각각 100,000 회 이하이다.<br/>
 * 6. getFeed() 함수의 호출 횟수는 5,000 회 이하이다.<br/>
 * 7. 특정 사용자가 한번 “follow” 한 사용자를 다시 “follow” 하는 호출은 발생하지 않는다.<br/>
 * */
public class SWEA_P36_UserSolution {

    private static final int PID_MAX = 100_001;

    private HashSet[] follow;       // follow[uId1].add(uId2]

    private int[] uidByPid;         // uidByPid[pId]
    private int[] pIds;             // pIds[idx]: timestamp 순으로 pID 를 저장
    private int pIdSize;            // pIds 의 size

    private HashSet[] pidSetByUser; // pidSetByUser[uId]
    private int[] likeByPid;             // like[pId]
    private int[] timestampByPid;        // timestamp[pId]

    private int[] temp;


    /**
     * @param N 사용자 수 (2 ≤ N ≤ 1,000)
     * */
    public void init(int N) {
        follow = new HashSet[N + 1];

        uidByPid = new int[PID_MAX];
        pIds = new int[PID_MAX];
        pIdSize = 0;

        pidSetByUser = new HashSet[N + 1];
        likeByPid = new int[PID_MAX];
        timestampByPid = new int[PID_MAX];

        for (int uId = 1; uId <= N; uId++) {
            follow[uId] = new HashSet<Integer>();
            pidSetByUser[uId] = new HashSet<Integer>();
        }

        temp = new int[PID_MAX];
    }

    /**
     * “uID1” 사용자가 “uID2” 사용자를 “follow” 한다.<br/>
     * “uID1” 사용자는 “uID2” 사용자의 모든 게시글을 볼 수 있다.
     *
     * @param uID1 사용자1 의 id (1 ≤ uID1 ≤ N)
     * @param uID2 사용자2 의 id (1 ≤ uID2 ≤ N)
     * @param timestamp 현재 시점의 “timestamp” (1 ≤ timestamp ≤ 100,000)
     * */
    public void follow(int uID1, int uID2, int timestamp) {
        follow[uID1].add(uID2);
    }

    /**
     * “uID” 사용자가 “pID” 게시글을 게시한다.
     *
     * @param uID 사용자의 ID (1 ≤ uID ≤ N)
     * @param pID 게시글의 ID (1부터 오름차순으로 주어진다.)
     * @param timestamp 현재 시점
     * */
    public void makePost(int uID, int pID, int timestamp) {
        pIds[pIdSize++] = pID;
        pidSetByUser[uID].add(pID);
        uidByPid[pID] = uID;
        timestampByPid[pID] = timestamp;
    }

    /**
     * “pID” 게시글에 “like”를 1번 추가 한다.<br/>
     * “pID” 는 makePost() 에서 전달되었던 값으로만 주어 진다.
     *
     * @param pID “like”를 추가할 게시글의 pID
     * @param timestamp 현재 시점
     * */
    public void like(int pID, int timestamp) {
        likeByPid[pID] += 1;
    }

    /**
     * 현재 “timestamp” 를 기준으로 “uID” 사용자에게 보여지는 최대 10 개의 게시글의 “pID” 들을 찾아
     * 우선순위의 내림차순으로 “pIDList[]” 배열에 저장하여 반환 한다.<br/>
     *
     * <br/>[우선순위]<br/>
     * timestamp 1000 이하(like -> time) -> timestamp 1000 초과
     *
     * @param uID 사용자의 id (1 ≤ uID ≤ N)
     * @param timestamp 현재 시점
     * @param pIDList 보여지는 게시글의 pID 들을 저장하는 배열
     * */
    public void getFeed(int uID, int timestamp, int[] pIDList) {
//        logln("◆ getFeed(): uID=%d, timestamp=%d", uID, timestamp);

        int size = 0;
        int recentIdx = -1;

        Set<Integer> ownPidSet = pidSetByUser[uID];
        Set<Integer> followingSet = follow[uID];

//        logln("        own=%s, following=%s", ownPidSet, followingSet);

        for (int i = pIdSize - 1; i >= 0; i--) {
            int pid = pIds[i];
            int uidByPost = uidByPid[pid];

//            logln("        uid=%d, pid=%d, like=%d, time=%d", uidByPost, pid, likeByPid[pid], timestampByPid[pid]);

            if (!ownPidSet.contains(pid) && !followingSet.contains(uidByPost))
                continue;

            int timeDiff = timestamp - timestampByPid[pid];

            if (timeDiff > 1000) {
                if (size >= 10)
                    break;
            } else {
                recentIdx++;
            }

            temp[size++] = pid;
        }

//        logln("        recentIdx=%d", recentIdx);

        sortByPriority(temp, size, recentIdx, pIDList);
    }

    private void sortByPriority(int[] temp, int size, int recentIdx, int[] pIdList) {
        for (int i = 1; i <= recentIdx; i++) {
            int pid = temp[i];
            int like = likeByPid[pid];

            for (int j = i - 1; j >= 0; j--) {
                if (likeByPid[temp[j]] >= like)
                    break;

                temp[j + 1] = temp[j];
                temp[j] = pid;
            }
        }

        for (int i = 0; i < 10 && i < size; i++) {
            pIdList[i] = temp[i];
        }
    }

    private void logln(String s, Object ... args) {
        log(s + "\n", args);
    }

    private void log(String s, Object ... args) {
        System.out.printf(s, args);
    }
}

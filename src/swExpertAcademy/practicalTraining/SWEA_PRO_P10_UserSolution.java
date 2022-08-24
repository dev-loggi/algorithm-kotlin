package swExpertAcademy.practicalTraining;

import utils.CS;

import java.util.*;

/**
 * [No. 10] 14604. [Pro] AI로봇 (8/24)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * <br/><br/>[제약사항]<br/><br/>
 *
 * 1. 각 테스트 케이스 시작 시 init() 함수가 한 번 호출된다.<br/>
 * 2. 로봇의 총 대수 N은 10 이상 50,000 이하의 정수이다.<br/>
 * 3. 시각 cTime 은 1 이상 1,000,000,000 이하의 정수이다.<br/>
 * 4. 각 테스트 케이스에서 init()을 제외한 함수가 호출될 때마다 cTime은 증가하는 값으로 주어진다.<br/>
 * 5. 각 테스트 케이스에서 callJob() 함수를 통해 투입되는 로봇 대수의 총합은 200,000 이하이다.<br/>
 * 6. 각 테스트 케이스에서 init() 을 제외한 함수의 호출 횟수의 총합은 50,000 이하이다.<br/>
 *
 * AVL Tree, 우선순위 큐
 * */
public class SWEA_PRO_P10_UserSolution implements SWEA_PRO_P10.UserSolution {

    static class TrainingCenter {

        static class Group implements Comparable<Group> {

            // offset = lastIQ[rID] - returnTime[rID]
            int offset;
            private final TreeSet<Integer> robots = new TreeSet<>();

            public Group(int offset) {
                this.offset = offset;
            }

            @Override
            public int compareTo(Group o) {
                return offset - o.offset;
            }
        }

        // TreeMap<Group.offset, Group>
        private final TreeMap<Integer, Group> groups = new TreeMap<>();

        public TrainingCenter(int N) {
            Group group = new Group(0);

            for (int rID = 1; rID <= N; rID++) {
                group.robots.add(rID);
            }

            groups.put(0, group);
        }
    }

    static class WorkSpace {

        static class Job {
            int id, optType;

            // LinkedHashMap<IQ, LinkedHashSet<rID>>
            final LinkedHashMap<Integer, LinkedHashSet<Integer>> robots = new LinkedHashMap<>();

            public Job(int id, int optType) {
                this.id = id;
                this.optType = optType;
            }
        }

        // HashMap<wID, Job>
        private final HashMap<Integer, Job> jobs = new HashMap<>();

        public void addJob(Job job) {
            jobs.put(job.id, job);
        }

        public Job returnJob(int wID) {
            return jobs.remove(wID);
        }

        public void removeRobot(int wID, int rID, int IQ) {
            jobs.get(wID).robots.get(IQ).remove(rID);
        }
    }

    private int[] lastIQ, jobID, returnTime;

    private TrainingCenter trainingCenter;
    private WorkSpace workSpace;
    private HashSet<Integer> repairing;

    @Override
    public void init(int N) {
        lastIQ = new int[N + 1];
        jobID = new int[N + 1];
        returnTime = new int[N + 1];

        trainingCenter = new TrainingCenter(N);
        repairing = new HashSet<>();
        workSpace = new WorkSpace();
    }

    @Override
    public int callJob(int cTime, int wID, int mNum, int mOpt) {
        int sum = 0;

        NavigableMap<Integer, TrainingCenter.Group> trainingGroups = (mOpt == 0)
                ? trainingCenter.groups.descendingMap() // 높은 지능 우선 방식
                : trainingCenter.groups; // 낮은 지능 우선 방식

        TreeSet<Integer> trainings = trainingGroups.firstEntry().getValue().robots;

        WorkSpace.Job job = new WorkSpace.Job(wID, mOpt);
        LinkedHashSet<Integer> workers = new LinkedHashSet<>();

        boolean isPut = false;

        for (int i = 1; i <= mNum; i++) {
            if (trainings.isEmpty()) {
                trainingGroups.pollFirstEntry();
                trainings = trainingGroups.firstEntry().getValue().robots;

                workers = new LinkedHashSet<>();
                isPut = false;
            }

            int id = trainings.pollFirst();

            jobID[id] = wID;
            lastIQ[id] += cTime - returnTime[id];

            if (!isPut) {
                isPut = true;
                job.robots.put(lastIQ[id], workers);
            }

            workers.add(id);

            sum += id;
        }

        if (trainings.isEmpty()) {
            trainingGroups.pollFirstEntry();
        }

        workSpace.addJob(job);

        return sum;
    }

    @Override
    public void returnJob(int cTime, int wID) {
        WorkSpace.Job job = workSpace.returnJob(wID);

        for (Map.Entry<Integer, LinkedHashSet<Integer>> entry : job.robots.entrySet()) {
            int IQ = entry.getKey();
            int offset = IQ - cTime;
            LinkedHashSet<Integer> workers = entry.getValue();

            if (workers.isEmpty())
                continue;

            TrainingCenter.Group trainingGroup = trainingCenter.groups
                    .computeIfAbsent(offset, key -> new TrainingCenter.Group(offset));

            for (int rID : workers) {
                jobID[rID] = 0;
                returnTime[rID] = cTime;

                trainingGroup.robots.add(rID);
            }
        }
    }

    @Override
    public void broken(int cTime, int rID) {
        if (jobID[rID] == 0) // 현재 작업중이 아님
            return;

        workSpace.removeRobot(jobID[rID], rID, lastIQ[rID]);
        repairing.add(rID);

        jobID[rID] = 0;
        lastIQ[rID] = 0;
    }

    @Override
    public void repair(int cTime, int rID) {
        if (!repairing.remove(rID))
            return;

        // IQ 0인 상태로 center 복귀
        returnTime[rID] = cTime;
        trainingCenter.groups
                .computeIfAbsent(-cTime, key -> new TrainingCenter.Group(-cTime))
                .robots.add(rID);
    }

    @Override
    public int check(int cTime, int rID) {
        if (repairing.contains(rID)) { // 수리중
            return 0;
        } else if (jobID[rID] != 0) { // 작업중
            return -jobID[rID];
        } else { // 센터에서 트레이닝중
            return lastIQ[rID] + (cTime - returnTime[rID]);
        }
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    private void printTrainingCenter(int cTime) {
        log("");
        log("    ---------- [Training Center] start ----------");
        for (Map.Entry<Integer, TrainingCenter.Group> entry: trainingCenter.groups.entrySet()) {
            TreeSet<Integer> robots = entry.getValue().robots;
            int size = robots.size();
            int iq = 0;
            if (size != 0) {
                int firstId = robots.first();
                iq = lastIQ[firstId] + (cTime - returnTime[firstId]);
            }
            log("        robots[size=%d, IQ=%d] → %s", size, iq, robots);
        }
        log("    ========== end ==========");
        log("");
    }

    private void printWorkSpace() {
        log("");
        log("    ---------- [Work Space] start ----------");
        for (Map.Entry<Integer, WorkSpace.Job> entry : workSpace.jobs.entrySet()) {
            WorkSpace.Job job = entry.getValue();

            log("        wID=%d, optType=%d", job.id, job.optType);
            for (Map.Entry<Integer, LinkedHashSet<Integer>> entry2 : job.robots.entrySet()) {
                int iq = entry2.getKey();
                int size = entry2.getValue().size();
                LinkedHashSet<Integer> robots = entry2.getValue();
                log("            IQ=%d, robots[size=%d] → %s", iq, size, robots);
            }
        }
        log("    ========== end ==========");
        log("");
    }
}
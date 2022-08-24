package swExpertAcademy.practicalTraining;


import java.util.*;

/**
 * [No. 3] 14612. [Pro] 메모리 시스템 (8/12)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * [제약사항]
 * 1. 각 테스트 케이스 시작 시 init() 함수가 호출된다.
 * 2. 각 테스트 케이스에서 모든 함수의 호출 횟수 총합은 30,000 이하이다.
 *
 * 트리
 *
 * TODO: 트립(Treap) 자료구조를 직접 구현해서 풀어보기
 * */
public class SWEA_PRO_P03_UserSolution implements SWEA_PRO_P03.UserSolution {

    /** Memory Size */
    private int memorySize;

    /** (Key, Value) = (Address, Size) */
    private TreeMap<Integer, Integer> allocTree;

    /** (Key, Value) = (Size, Address Ascending List) */
    private TreeMap<Integer, LinkedList<Integer>> emptyTree;

    @Override
    public void init(int N) {
        memorySize = N;
        allocTree = new TreeMap<>();
        emptyTree = new TreeMap<>();

        LinkedList<Integer> addresses = new LinkedList<>();
        addresses.add(0);
        emptyTree.put(N, addresses);
    }

    @Override
    public int allocate(int mSize) {
        if (emptyTree.isEmpty())
            return -1;

        Map.Entry<Integer, LinkedList<Integer>> entry = emptyTree.ceilingEntry(mSize);

        if (entry == null)
            return -1;

        int size = entry.getKey();
        int allocAddress = entry.getValue().removeFirst();

        allocTree.put(allocAddress, mSize);

        if (entry.getValue().isEmpty()) {
            emptyTree.remove(entry.getKey());
        }

        if (mSize < size) {
            addNewEmptyLocation(size - mSize, allocAddress + mSize);
        }

        return allocAddress;
    }

    @Override
    public int release(int mAddr) {
        Integer size = allocTree.remove(mAddr);

        if (size == null)
            return -1;

        Map.Entry<Integer, Integer> left = allocTree.floorEntry(mAddr);
        Map.Entry<Integer, Integer> right = allocTree.ceilingEntry(mAddr);

        int newEmptyAddr = mAddr;
        int newEmptySize = size;

        if (left != null) {
            if (left.getKey() + left.getValue() != mAddr) {
                newEmptyAddr = left.getKey() + left.getValue();
                newEmptySize += mAddr - newEmptyAddr;

                removeEmptyLocation(mAddr - newEmptyAddr, newEmptyAddr);
            }
        } else if (mAddr != 0) {
            newEmptyAddr = 0;
            newEmptySize += mAddr;

            removeEmptyLocation(mAddr, 0);
        }

        if (right != null) {
            if (mAddr + size != right.getKey()) {
                newEmptySize += right.getKey() - (mAddr + size);

                removeEmptyLocation(right.getKey() - (mAddr + size), mAddr + size);
            }
        } else if (mAddr + size < memorySize) {
            newEmptySize += memorySize - (mAddr + size);

            removeEmptyLocation(memorySize - (mAddr + size), mAddr + size);
        }

        addNewEmptyLocation(newEmptySize, newEmptyAddr);

        return size;
    }

    private void addNewEmptyLocation(int size, int address) {
        LinkedList<Integer> list = emptyTree.get(size);

        if (list == null) {
            list = new LinkedList<>();
            list.add(address);

            emptyTree.put(size, list);
        } else if (list.getLast() <= address) {
            list.addLast(address);
        } else {
            ListIterator<Integer> it = list.listIterator();

            while (it.hasNext()) {
                if (address < it.next()) {
                    it.previous();
                    it.add(address);
                    break;
                }
            }
        }
    }

    private void removeEmptyLocation(int size, int address) {
        LinkedList<Integer> addresses = emptyTree.get(size);
        addresses.remove(new Integer(address));

        if (addresses.isEmpty()) {
            emptyTree.remove(size);
        }
    }

    private void logln(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    public void printInfo() {
        logln("    allocTree ▷▷ %s", allocTree.toString());
        logln("    emptyTree ▷▷ %s", emptyTree.toString());
    }

}
package utils

import kotlin.math.log10
import java.util.ArrayDeque

/**
 * Insertion Sort
 * O(n), O(n^2), O(n^2)
 * https://gmlwjd9405.github.io/2018/05/06/algorithm-insertion-sort.html
 * */
fun IntArray.insertionSort() {
    for (i in 1..lastIndex) {
        val n = this[i]
        for (j in i - 1 downTo 0) {
            if (this[j] < n)
                break

            this[j + 1] = this[j]
            this[j] = n
        }
    }
}

/**
 * Insertion Sort, Descending
 * O(n), O(n^2), O(n^2)
 * https://gmlwjd9405.github.io/2018/05/06/algorithm-insertion-sort.html
 * */
fun IntArray.insertionSortDescending() {
    for (i in 1..lastIndex) {
        val n = this[i]
        for (j in i - 1 downTo 0) {
            if (this[j] > n)
                break

            this[j + 1] = this[j]
            this[j] = n
        }
    }
}

/**
 * Selection Sort
 * O(n^2), O(n^2), O(n^2)
 * https://gmlwjd9405.github.io/2018/05/06/algorithm-selection-sort.html
 * */
fun IntArray.selectionSort() {
    for (i in 0 until lastIndex) {
        var k = i
        for (j in i + 1..lastIndex) {
            if (this[j] < this[k]) k = j
        }
        if (i != k) {
            val tmp = this[i]
            this[i] = this[k]
            this[k] = tmp
        }
    }
}

/**
 * Selection Sort, Descending
 * O(n^2), O(n^2), O(n^2)
 * https://gmlwjd9405.github.io/2018/05/06/algorithm-selection-sort.html
 * */
fun IntArray.selectionSortDescending() {
    for (i in 0 until lastIndex) {
        var k = i
        for (j in i + 1..lastIndex) {
            if (this[j] > this[k]) k = j
        }
        if (i != k) {
            val tmp = this[i]
            this[i] = this[k]
            this[k] = tmp
        }
    }
}

/**
 * Bubble Sort
 * O(n^2), O(n^2), O(n^2)
 * https://gmlwjd9405.github.io/2018/05/06/algorithm-bubble-sort.html
 * */
fun IntArray.bubbleSort() {
    for (i in lastIndex downTo 1) {
        for (j in 0 until i) {
            if (this[j] > this[j + 1]) {
                val tmp = this[j + 1]
                this[j + 1] = this[j]
                this[j] = tmp
            }
        }
    }
}

/**
 * Bubble Sort, Descending
 * O(n^2), O(n^2), O(n^2)
 * https://gmlwjd9405.github.io/2018/05/06/algorithm-bubble-sort.html
 * */
fun IntArray.bubbleSortDescending() {
    for (i in lastIndex downTo 1) {
        for (j in 0 until i) {
            if (this[j] < this[j + 1]) {
                val tmp = this[j + 1]
                this[j + 1] = this[j]
                this[j] = tmp
            }
        }
    }
}

/**
 * Shell Sort
 * O(n), O(n^1.5), O(n^2)
 * https://gmlwjd9405.github.io/2018/05/08/algorithm-shell-sort.html
 * */
fun IntArray.shellSort() {
    fun Int.halfOdd(): Int {
        return if (this >= 4) if (this % 4 == 0) this / 2 else this / 2 + 1
        else if (this == 3) 1 else if (this == 2) 1 else 0
    }

    var gap = size.halfOdd()
    while (gap >= 1) {
        // gap 간격의 부분 리스트
        for (start in 0 until gap) {

            // 부분리스트들의 삽입 정렬
            for (i in start + gap..this.lastIndex step gap) {
                val key = this[i]

                for (j in i - gap downTo 0 step gap) {
                    if (this[j] < key)
                        break

                    this[j + gap] = this[j]
                    this[j] = key
                }
            }
        }

        gap = gap.halfOdd()
    }
}

/**
 * Shell Sort, Descending
 * O(n), O(n^1.5), O(n^2)
 * https://gmlwjd9405.github.io/2018/05/08/algorithm-shell-sort.html
 * */
fun IntArray.shellSortDescending() {
    fun Int.halfOdd(): Int {
        return if (this >= 4) if (this % 4 == 0) this / 2 else this / 2 + 1
        else if (this == 3) 1 else if (this == 2) 1 else 0
    }

    var gap = size.halfOdd()
    while (gap >= 1) {
        // gap 간격의 부분 리스트
        for (start in 0 until gap) {

            // 부분리스트들의 삽입 정렬
            for (i in start + gap..this.lastIndex step gap) {
                val key = this[i]

                for (j in i - gap downTo 0 step gap) {
                    if (this[j] > key)
                        break

                    this[j + gap] = this[j]
                    this[j] = key
                }
            }
        }

        gap = gap.halfOdd()
    }
}

/**
 * Quick Sort
 * O(n log n), O(n log n), O(n^2)
 * 피벗을 중간으로 잡기
 * https://gmlwjd9405.github.io/2018/05/10/algorithm-quick-sort.html
 * */
fun IntArray.quickSort() {
    val arr = this

    fun swap(i: Int, j: Int) { // 스왑
        val tmp = arr[i]
        arr[i] = arr[j]
        arr[j] = tmp
    }

    fun partition(left: Int, right: Int): Int { // 분할
        val pivot = arr[(left + right) / 2]
        var i = left
        var j = right

        while (i <= j) {
            while (arr[i] < pivot) i++
            while (arr[j] > pivot) j--

            if (i <= j) {
                swap(i++, j--)
            }
        }

        return i // 피벗의 바뀐 위치
    }

    fun conquer(left: Int, right: Int) { // 정복
        val p = partition(left, right)

        if (left < p - 1)
            conquer(left, p - 1) // 피벗의 왼쪽 배열 정복
        if (p < right)
            conquer(p, right) // 피벗의 오른쪽 배열 정복
    }

    conquer(0, lastIndex)
}

/**
 * Quick Sort, Descending
 * O(n log n), O(n log n), O(n^2)
 * 피벗을 중간으로 잡기
 * https://gmlwjd9405.github.io/2018/05/10/algorithm-quick-sort.html
 * */
fun IntArray.quickSortDescending() {
    val arr = this

    fun swap(i: Int, j: Int) { // 스왑
        val tmp = arr[i]
        arr[i] = arr[j]
        arr[j] = tmp
    }

    fun partition(left: Int, right: Int): Int { // 분할
        val pivot = arr[(left + right) / 2]
        var i = left
        var j = right

        while (i <= j) {
            while (arr[i] > pivot) i++
            while (arr[j] < pivot) j--

            if (i <= j) {
                swap(i++, j--)
            }
        }

        return i // 피벗의 바뀐 위치
    }

    fun conquer(left: Int, right: Int) { // 정복
        val p = partition(left, right)

        if (left < p - 1)
            conquer(left, p - 1) // 피벗의 왼쪽 배열 정복
        if (p < right)
            conquer(p, right) // 피벗의 오른쪽 배열 정복
    }

    conquer(0, lastIndex)
}


/**
 * Quick Sort
 * 피벗을 오른쪽 끝으로 잡기
 * https://gmlwjd9405.github.io/2018/05/10/algorithm-quick-sort.html
 * */
fun IntArray.quickSort2() {
    val arr = this

    fun swap(i: Int, j: Int) { // 스왑
        val tmp = arr[i]
        arr[i] = arr[j]
        arr[j] = tmp
    }

    fun partition(left: Int, right: Int): Int { // 분할
        val pivot = arr[(left + right) / 2]
        var i = left - 1

        for (j in left until right) {
            if (arr[j] >= pivot)
                continue

            swap(++i, j)
        }

        swap(i + 1, right)

        return i + 1 // 확정된 피벗의 위치
    }

    fun conquer(left: Int, right: Int) { // 정복
        if (left >= right)
            return

        val pivot = partition(left, right)

        conquer(left, pivot - 1) // 피벗의 왼쪽 배열 정복
        conquer(pivot + 1, right) // 피벗의 오른쪽 배열 정복
    }

    conquer(0, lastIndex)
}

/**
 * Heap Sort, Ascending
 * O(n log n), O(n log n), O(n log n)
 * https://gmlwjd9405.github.io/2018/05/10/algorithm-heap-sort.html
 * */
fun IntArray.heapSort() {
    fun IntArray.swap(i: Int, j: Int) {
        val tmp = this[i]
        this[i] = this[j]
        this[j] = tmp
    }

    tailrec fun IntArray.recur1(child: Int) {
        val parent = child / 2

        if (parent > 0 && this[parent] > this[child]) {
            swap(parent, child)
            recur1(parent)
        }
    }

    tailrec fun IntArray.recur2(parent: Int) {
        val child1 = parent * 2
        val child2 = parent * 2 + 1

        if (this.getOrElse(child1) { Int.MAX_VALUE } < this.getOrElse(child2) { Int.MAX_VALUE } ) {
            if (this.getOrElse(child1) { Int.MAX_VALUE } < this[parent]) {
                swap(parent, child1)
                recur2(child1)
            }
        } else {
            if (this.getOrElse(child2) { Int.MAX_VALUE } < this[parent]) {
                swap(parent, child2)
                recur2(child2)
            }
        }
    }

    val arr = this
    val heap = IntArray(this.size + 1) // 최소힙, root=heap[1]

    for (i in arr.indices) { // 힙 추가
        heap[i + 1] = arr[i]

        heap.recur1(i + 1)
    }

    for (i in arr.indices) { // 힙 삭제
        arr[i] = heap[1]
        heap[1] = heap[size - i]
        heap[size - i] = Int.MAX_VALUE

        heap.recur2(1)
    }
}

/**
 * Heap Sort, Descending
 * O(n log n), O(n log n), O(n log n)
 * https://gmlwjd9405.github.io/2018/05/10/algorithm-heap-sort.html
 * */
fun IntArray.heapSortDescending() {
    fun IntArray.swap(i: Int, j: Int) {
        val tmp = this[i]
        this[i] = this[j]
        this[j] = tmp
    }

    tailrec fun IntArray.recur1(child: Int) {
        val parent = child / 2

        if (parent > 0 && this[parent] < this[child]) {
            swap(parent, child)
            recur1(parent)
        }
    }

    tailrec fun IntArray.recur2(parent: Int) {
        val child1 = parent * 2
        val child2 = parent * 2 + 1

        if (this.getOrElse(child1) { Int.MIN_VALUE } > this.getOrElse(child2) { Int.MIN_VALUE } ) {
            if (this.getOrElse(child1) { Int.MIN_VALUE } > this[parent]) {
                swap(parent, child1)
                recur2(child1)
            }
        } else {
            if (this.getOrElse(child2) { Int.MIN_VALUE } > this[parent]) {
                swap(parent, child2)
                recur2(child2)
            }
        }
    }

    val arr = this
    val heap = IntArray(this.size + 1) // 최대힙, root=heap[1]

    for (i in arr.indices) { // 힙 추가
        heap[i + 1] = arr[i]

        heap.recur1(i + 1)
    }

    for (i in arr.indices) { // 힙 삭제
        arr[i] = heap[1]
        heap[1] = heap[size - i]
        heap[size - i] = Int.MIN_VALUE

        heap.recur2(1)
    }
}

/**
 * Merge Sort
 * O(n log n), O(n log n), O(n log n)
 * https://gmlwjd9405.github.io/2018/05/08/algorithm-merge-sort.html
 * */
fun IntArray.mergeSort() {
    val arr = this
    val tmp = IntArray(arr.size)

    fun merge(left: Int, mid: Int, right: Int) {
        for (i in left..right) tmp[i] = arr[i] // copy

        var i = left
        var j = mid + 1
        var k = left

        while (i <= mid && j <= right) {
            if (tmp[i] < tmp[j]) arr[k++] = tmp[i++]
            else arr[k++] = tmp[j++]
        }

        while (i <= mid) arr[k++] = tmp[i++]
        while (j <= right) arr[k++] = tmp[j++]
    }

    fun partition(left: Int, right: Int) {
        if (left >= right)
            return

        val mid = (left + right) / 2

        partition(left, mid)
        partition(mid + 1, right)

        merge(left, mid, right)
    }

    partition(0, lastIndex)
}

/**
 * Merge Sort, Descending
 * O(n log n), O(n log n), O(n log n)
 * https://gmlwjd9405.github.io/2018/05/08/algorithm-merge-sort.html
 * */
fun IntArray.mergeSortDescending() {
    val arr = this
    val tmp = IntArray(arr.size)

    fun merge(left: Int, mid: Int, right: Int) {
        for (i in left..right) tmp[i] = arr[i] // copy

        var i = left
        var j = mid + 1
        var k = left

        while (i <= mid && j <= right) {
            if (tmp[i] < tmp[j]) arr[k++] = tmp[j++]
            else arr[k++] = tmp[i++]
        }

        while (i <= mid) arr[k++] = tmp[i++]
        while (j <= right) arr[k++] = tmp[j++]
    }

    fun partition(left: Int, right: Int) {
        if (left >= right)
            return

        val mid = (left + right) / 2

        partition(left, mid)
        partition(mid + 1, right)

        merge(left, mid, right)
    }

    partition(0, lastIndex)
}

/**
 * Counting Sort
 * O(n)
 * */
fun IntArray.countingSort() {
    var max = Int.MIN_VALUE
    for (n in this) if (max < n) max = n

    val counting = IntArray(max + 1)

    for (n in this) counting[n]++

    var i = 0
    var k = -1
    do {
        while (counting[++k] == 0);

        do {
            this[i++] = k
        } while (--counting[k] > 0)
    } while (i < size)
}

/**
 * Counting Sort, Descending
 * O(n)
 * */
fun IntArray.countingSortDescending() {
    var max = Int.MIN_VALUE
    for (n in this) if (max < n) max = n

    val counting = IntArray(max + 1)

    for (n in this) counting[n]++

    var i = 0
    var k = counting.size
    do {
        while (counting[--k] == 0);

        do {
            this[i++] = k
        } while (--counting[k] > 0)
    } while (i < size)
}

/**
 * Radix Sort
 * O(d * (n + b))
 * */
fun IntArray.radixSort() {
    fun Int.pow(e: Int): Int {
        var res = 1
        for (i in 1..e) res *= this
        return res
    }

    val arr = this
    val radixQueue = Array(10) { ArrayDeque<Int>() }
    val maxLength = (log10(arr.maxOf { it }.toDouble()) + 1).toInt()

    for (length in 1..maxLength) {
        val e = 10.pow(length)

        for (n in arr) {
            radixQueue[(n % e) / (e / 10)].offer(n)
        }

        var i = 0
        for (queue in radixQueue) {
            while (queue.isNotEmpty()) {
                arr[i++] = queue.poll()
            }
        }
    }
}

/**
 * Radix Sort, Descending
 * O(d * (n + b))
 * */
fun IntArray.radixSortDescending() {
    fun Int.pow(e: Int): Int {
        var res = 1
        for (i in 1..e) res *= this
        return res
    }

    val arr = this
    val radixQueue = Array(10) { ArrayDeque<Int>() }
    val maxLength = (log10(arr.maxOf { it }.toDouble()) + 1).toInt()

    for (length in 1..maxLength) {
        val e = 10.pow(length)

        for (n in arr) {
            radixQueue[(n % e) / (e / 10)].offer(n)
        }

        var i = 0
        for (j in radixQueue.lastIndex downTo 0) {
            val queue = radixQueue[j]

            while (queue.isNotEmpty()) {
                arr[i++] = queue.poll()
            }
        }
    }
}
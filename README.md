# Algorithm - Kotlin
<br/>

알고리즘 문제 및 풀이 정리<br/>


1. [BOJ](#1-boj) <br/>
2. [Programmers](#2-programmers) <br/>
3. [SW Expert Academy(삼성전자 S/W 알고리즘 특강)](#3-sw-expert-academy) <br/>


<br/>

## 1. BOJ

<details>
<summary>&nbsp;BOJ: 1000 ~ 1999 (8)</summary>
<div markdown="1">
<br/>

| 번호                                           | 제목                                                       | 유형       | 풀이                                    |
|----------------------------------------------|----------------------------------------------------------|----------|---------------------------------------|
| [1012](https://www.acmicpc.net/problem/1012) | [유기농 배추](src/boj/dfsBfs/BOJ_1012_OrganicCabbage.kt)      | BFS      |
| [1175](https://www.acmicpc.net/problem/1175) | [배달](src/boj/dfsBfs/BOJ_1175_Delivery.kt)                | BFS      | [blog](https://jsl663.tistory.com/33) |
| [1182](https://www.acmicpc.net/problem/1182) | [부분수열의 합](src/boj/bitmask/BOJ_1182_SumOfSubsequences.kt) | 비트마스크    |
| [1260](https://www.acmicpc.net/problem/1260) | [DFS 와 BFS](src/boj/dfsBfs/BOJ_1260_DFSandBFS.kt)        | DFS, BFS |
| [1385](https://www.acmicpc.net/problem/1385) | [벌집](src/boj/dfsBfs/BOJ_1385_HoneyComb.kt)               | BFS      | [blog](https://jsl663.tistory.com/38) |
| [1463](https://www.acmicpc.net/problem/1463) | [1로 만들기](src/boj/dp/BOJ_1463_MakeItToOne.kt)             | DP       |
| [1525](https://www.acmicpc.net/problem/1525) | [퍼즐](src/boj/dfsBfs/BOJ_1525_Puzzle.kt)                  | BFS      | [blog](https://jsl663.tistory.com/31) |
| [1913](https://www.acmicpc.net/problem/1913) | [달팽이1](src/boj/unsolved/BOJ_1913_Snail.kt)               | 구현       |

</div>
</details>


<details>
<summary>&nbsp;BOJ: 2000 ~ 2999 (11)</summary>
<div markdown="1">
<br/>

| 번호                                           | 제목                                                                        | 유형    | 풀이  |
|----------------------------------------------|---------------------------------------------------------------------------|-------|-----|
| [2178](https://www.acmicpc.net/problem/2178) | [미로 탐색](src/boj/dfsBfs/BOJ_2178_MazeExploration.kt)                       | BFS   |
| [2210](https://www.acmicpc.net/submit/2210)  | [숫자판 점프](src/boj/bruteForce/BOJ_2210_NumPadJump.kt)                       | 완전 탐색 |
| [2422](https://www.acmicpc.net/problem/2422) | [한윤정이 이탈리아에 가서 아이스크림을 사먹는데](src/boj/bruteForce/BOJ_2422_ItalyIcecream.kt) | 완전 탐색 |
| [2470](https://www.acmicpc.net/problem/2470) | [두 용액](src/boj/etc/BOJ_2470_TwoSolutions.kt)                              | 투 포인터 |
| [2529](https://www.acmicpc.net/problem/2529) | [부등호](src/boj/bruteForce/BOJ_2529_InequalitySign.kt)                      | 완전 탐색 |
| [2606](https://www.acmicpc.net/problem/2606) | [바이러스](src/boj/dfsBfs/BOJ_2606_Virus.kt)                                  | BFS   |
| [2667](https://www.acmicpc.net/problem/2667) | [단지 번호 붙이기](src/boj/dfsBfs/BOJ_2667_VillageNumbering.kt)                  | BFS   |
| [2675](https://www.acmicpc.net/problem/2675) | [문자열 반복](src/boj/unsolved/BOJ_2675_StringRepetition.kt)                   | 구현    |
| [2745](https://www.acmicpc.net/problem/2745) | [진법 변환](src/boj/unsolved/BOJ_2745_BaseConversion.kt)                      | 구현    |
| [2839](https://www.acmicpc.net/problem/2839) | [설탕 배달](src/boj/dp/BOJ_2839_SugarDelivery.kt)                             | DP    |
| [2902](https://www.acmicpc.net/problem/2902) | [KMP는 왜 KMP일까?](src/boj/etc/BOJ_2902_WhyIsKMPaKMP.kt)                     | 구현    |

</div>
</details>


<details>
<summary>&nbsp;BOJ: 8000 ~ 8999 (1)</summary>
<div markdown="1">
<br/>

| 번호                                           | 제목                                             | 유형  | 풀이  |
|----------------------------------------------|------------------------------------------------|-----|-----|
| [8111](https://www.acmicpc.net/problem/8111) | [0 과 1](src/boj/dfsBfs/BOJ_8111_ZeroAndOne.kt) | BFS |

</div>
</details>


<details>
<summary>&nbsp;BOJ: 9000 ~ 9999 (3)</summary>
<div markdown="1">
<br/>

| 번호                                           | 제목                                          | 유형  | 풀이  |
|----------------------------------------------|---------------------------------------------|-----|-----|
| [9019](https://www.acmicpc.net/problem/9019) | [DSLR](src/boj/unsolved/BOJ_9019_DSLR.kt)   | BFS |
| [9095](https://www.acmicpc.net/problem/9095) | [1,2,3 더하기](src/boj/dp/BOJ_9095_Plus123.kt) | DP  |
| [9328](https://www.acmicpc.net/problem/9328) | [열쇠](src/boj/dfsBfs/BOJ_9328_Key.kt)        | BFS |

</div>
</details>


<details>
<summary>&nbsp;BOJ: 10000 ~ 10999 (1)</summary>
<div markdown="1">
<br/>

| 번호                                             | 제목                                          | 유형  | 풀이  |
|------------------------------------------------|---------------------------------------------|-----|-----|
| [10942](https://www.acmicpc.net/problem/10942) | [팰린드롬?](src/boj/dp/BOJ_10942_Palindrome.kt) | DP  |

</div>
</details>


<details>
<summary>&nbsp;BOJ: 11000 ~ 11999 (7)</summary>
<div markdown="1">
<br/>

| 번호                                             | 제목                                                 | 유형    | 풀이  |
|------------------------------------------------|----------------------------------------------------|-------|-----|
| [11005](https://www.acmicpc.net/problem/11005) | [진법 변환2](src/boj/etc/BOJ_11005_BaseConversion2.kt) | 구현    |
| [11048](https://www.acmicpc.net/problem/11048) | [이동하기](src/boj/dp/BOJ_11048_Move.kt)               | DP    |
| [11052](https://www.acmicpc.net/problem/11052) | [카드 구매하기](src/boj/dp/BOJ_11052_BuyCards.kt)        | DP    |
| [11060](https://www.acmicpc.net/problem/11060) | [점프 점프](src/boj/dp/BOJ_11060_JumpJump.kt)          | DP    |
| [11723](https://www.acmicpc.net/problem/11723) | [집합](src/boj/bitmask/BOJ_11723_Set.kt)             | 비트마스크 |
| [11726](https://www.acmicpc.net/problem/11726) | [2xN 타일링](src/boj/dp/BOJ_11726_2xN_Tiling.kt)      | DP    |
| [11727](https://www.acmicpc.net/problem/11727) | [2xN 타일링2](src/boj/dp/BOJ_11727_2xN_Tiling2.kt)    | DP    |

</div>
</details>


<details>
<summary>&nbsp;BOJ: 12000 ~ 12999 (1)</summary>
<div markdown="1">
<br/>

| 번호                                             | 제목                                                 | 유형  | 풀이  |
|------------------------------------------------|----------------------------------------------------|-----|-----|
| [12851](https://www.acmicpc.net/problem/12851) | [숨바꼭질 2](src/boj/dfsBfs/BOJ_12851_HideAndSeek2.kt) | BFS |

</div>
</details>


<details>
<summary>&nbsp;BOJ: 14000 ~ 14999 (2)</summary>
<div markdown="1">
<br/>

| 번호                                             | 제목                                                    | 유형    | 풀이  |
|------------------------------------------------|-------------------------------------------------------|-------|-----|
| [14391](https://www.acmicpc.net/problem/14391) | [종이 조각](src/boj/bitmask/BOJ_14391_PaperPiece.kt)      | 비트마스크 |
| [14889](https://www.acmicpc.net/problem/14889) | [스타트와 링크](src/boj/bitmask/BOJ_14889_StartAndLink.kt)  | 비트마스크 |


</div>
</details>


<details>
<summary>&nbsp;BOJ: 15000 ~ 15999 (4)</summary>
<div markdown="1">
<br/>

| 번호                                             | 제목                                                       | 유형    | 풀이                                    |
|------------------------------------------------|----------------------------------------------------------|-------|---------------------------------------|
| [15558](https://www.acmicpc.net/problem/15558) | [점프 게임](src/boj/dfsBfs/BOJ_15558_JumpGame.kt)            | BFS   |
| [15653](https://www.acmicpc.net/problem/15653) | [구슬 탈출 4](src/boj/dfsBfs/BOJ_15653_MarbleEscape4.kt)     | BFS   | [blog](https://jsl663.tistory.com/33) |
| [15683](https://www.acmicpc.net/problem/15683) | [감시](src/boj/bruteForce/BOJ_15683_Serveillance.kt)       | 완전 탐색 |
| [15686](https://www.acmicpc.net/problem/15686) | [치킨 배달](src/boj/bruteForce/BOJ_15686_ChickenDelivery.kt) | 완전 탐색 |

</div>
</details>


<details>
<summary>&nbsp;BOJ: 16000 ~ 16999 (12)</summary>
<div markdown="1">
<br/>

| 번호                                             | 제목                                                                   | 유형    | 풀이                                    |
|------------------------------------------------|----------------------------------------------------------------------|-------|---------------------------------------|
| [16637](https://www.acmicpc.net/problem/16637) | [괄호 추가하기](src/boj/bruteForce/BOJ_16637_AddParentheses.kt)            | 완전 탐색 |
| [16920](https://www.acmicpc.net/problem/16920) | [확장 게임](src/boj/dfsBfs/BOJ_16920_ExpansionGame.kt)                   | BFS   | [blog](https://jsl663.tistory.com/35) |
| [16922](https://www.acmicpc.net/problem/16922) | [로마 숫자 만들기](src/boj/bruteForce/BOJ_16922_MakingRomanNumerals.kt)     | 완전 탐색 |
| [16924](https://www.acmicpc.net/problem/16924) | [십자가 찾기](src/boj/bruteForce/BOJ_16924_FindCross.kt)                  | 완전 탐색 |
| [16936](https://www.acmicpc.net/problem/16936) | [나3곱2](src/boj/bruteForce/BOJ_16936_Division3Multiplication2.kt)     | 완전 탐색 |
| [16937](https://www.acmicpc.net/problem/16937) | [두 스티커](src/boj/bruteForce/BOJ_16937_TwoStickers.kt)                 | 완전 탐색 |
| [16938](https://www.acmicpc.net/problem/16938) | [캠프 준비](src/boj/bruteForce/BOJ_16938_CampReady.kt)                   | 완전 탐색 |
| [16943](https://www.acmicpc.net/problem/16943) | [숫자 재배치](src/boj/bruteForce/BOJ_16943_NumberReplacement.kt)          | 완전 탐색 |
| [16959](https://www.acmicpc.net/problem/16959) | [체스판 여행 1](src/boj/dfsBfs/BOJ_16959_ChessboardTravel1.kt)            | BFS   | [blog](https://jsl663.tistory.com/40) |
| [16968](https://www.acmicpc.net/problem/16968) | [차량 번호판1](src/boj/bruteForce/BOJ_16968_LicensePlate1.kt)             | 완전 탐색 |
| [16971](https://www.acmicpc.net/problem/16917) | [양념 반 후라이드 반](src/boj/bruteForce/BOJ_16971_HalfSeasonedHalfFried.kt) | 완전 탐색 |
| [16973](https://www.acmicpc.net/problem/16973) | [직사각형 탈출](src/boj/dfsBfs/BOJ_16973_RectangleEscape.kt)               | BFS   |

</div>
</details>


<details>
<summary>&nbsp;BOJ: 17000 ~ 17999 (6)</summary>
<div markdown="1">
<br/>

| 번호                                             | 제목                                                                     | 유형    | 풀이                                    |
|------------------------------------------------|------------------------------------------------------------------------|-------|---------------------------------------|
| [17088](https://www.acmicpc.net/problem/17088) | [등차수열 변환](src/boj/bruteForce/BOJ_17088_ArithmeticSequenceTransform.kt) | 완전 탐색 |
| [17089](https://www.acmicpc.net/problem/17089) | [세 친구](src/boj/bruteForce/BOJ_17089_ThreeFriends.kt)                   | 완전 탐색 |
| [17071](https://www.acmicpc.net/problem/17071) | [숨바꼭질 5](src/boj/dfsBfs/BOJ_17071_HideAndSeek5.kt)                     | BFS   | [blog](https://jsl663.tistory.com/32) |
| [17135](https://www.acmicpc.net/problem/17135) | [캐슬 디펜스](src/boj/bruteForce/BOJ_17135_CastleDefense.kt)                | 완전 탐색 |
| [17281](https://www.acmicpc.net/problem/17281) | [야구](src/boj/bruteForce/BOJ_17281_Baseball.kt)                         | 완전 탐색 |
| [17406](https://www.acmicpc.net/problem/17406) | [배열 돌리기4](src/boj/bruteForce/BOJ_17406_ArrayRotation4.kt)              | 완전 탐색 |

</div>
</details>


<details>
<summary>&nbsp;BOJ: 21000 ~ 21999 (1)</summary>
<div markdown="1">
<br/>

| 번호                                             | 제목                                   | 유형       | 풀이  |
|------------------------------------------------|--------------------------------------|----------|-----|
| [21921](https://www.acmicpc.net/problem/21921) | [블로그](src/boj/etc/BOJ_21921_Blog.kt) | 슬라이딩 윈도우 |

</div>
</details>


<br/>

## 2. Programmers

<details>
<summary>&nbsp;Programmers: Level 1 (21)</summary>
<div markdown="1">
<br/>

| 레벨  | 제목                                                                                | 유형  |
|:---:|-----------------------------------------------------------------------------------|-----|
|  1  | [없는 숫자 더하기](src/programmers/practice/level1/AddMissingNumbers.kt)                 |
|  1  | [음양 더하기](src/programmers/practice/level1/AddNegativePositiveNumbers.kt)           |
|  1  | [부족한 금액 계산하기](src/programmers/practice/level1/CalculateShortfall.kt)              |
|  1  | [크레인 인형뽑기 게임](src/programmers/practice/level1/CranePuppetGame.kt)                 |
|  1  | [나누어 떨어지는 숫자 배열](src/programmers/practice/level1/DivisibleIntArray.kt)            |
|  1  | [내적(Dot product)](src/programmers/practice/level1/DotProduct.kt)                  |
|  1  | [나머지가 1이 되는 수 찾기](src/programmers/practice/level1/FindRemainderOne.kt)            |
|  1  | [가운데 글자 가져오기](src/programmers/practice/level1/GetMiddleLetter.kt)                 |
|  1  | [신고 결과 받기](src/programmers/practice/level1/GetReportResults.kt)                   |
|  1  | [체육복](src/programmers/practice/level1/GymSuit.kt)                                 |
|  1  | [K번째 수](src/programmers/practice/level1/KthNumber.kt)                             |
|  1  | [로또](src/programmers/practice/level1/Lotto.kt)                                    |
|  1  | [최소 직사각형](src/programmers/practice/level1/MinRectangle.kt)                        |
|  1  | [모의고사](src/programmers/practice/level1/MockExam.kt)                               |
|  1  | [약수의 갯수와 덧셈](src/programmers/practice/level1/NumberOfDivisorsAndAddition.kt)      |
|  1  | [문자열 내 마음대로 정렬하기](src/programmers/practice/level1/SortingStringsMyOwnWay.kt)      |
|  1  | [문자열 내림차순으로 배치하기](src/programmers/practice/level1/SortStringInDescendingOrder.kt) |
|  1  | [두 정수 사이의 합](src/programmers/practice/level1/SumBetweenTwoIntegers.kt)            |
|  1  | [두 개 뽑아서 더하기](src/programmers/practice/level1/TakeTwoNumbersAndSums.kt)           |
|  1  | [3진법 뒤집기](src/programmers/practice/level1/TernaryReversed.kt)                     |
|  1  | [2016년](src/programmers/practice/level1/Year2016.kt)                              |

</div>
</details>


<details>
<summary>&nbsp;Programmers: Level 2 (33)</summary>
<div markdown="1">
<br/>

| 레벨  | 제목                                                                            | 유형  |
|:---:|-------------------------------------------------------------------------------|-----|
|  2  | [양궁 대회](src/programmers/practice/level2/ArcheryCompetition.kt)                |
|  2  | [카펫](src/programmers/practice/level2/Carpet.kt)                               |
|  2  | [거리두기 확인하](src/programmers/practice/level2/CheckSocialDistancing.kt)          |
|  2  | [배달](src/programmers/practice/level2/Delivery.kt)                             |
|  2  | [할인 행사](src/programmers/practice/level2/DiscountEvent.kt)                     |
|  2  | [위장](src/programmers/practice/level2/DisGuise.kt)                             |
|  2  | [예상 대진표](src/programmers/practice/level2/ExpectedScorecard.kt)                |
|  2  | [피로도](src/programmers/practice/level2/FatigueLevel.kt)                        |
|  2  | [소수 찾기](src/programmers/practice/level2/FindPrimeNumber.kt)                   |
|  2  | [기능 개발](src/programmers/practice/level2/FunctionDevelopment.kt)               |
|  2  | [H-Index](src/programmers/practice/level2/H_Index.kt)                         |
|  2  | [조이스틱](src/programmers/practice/level2/JoyStick.kt)                           |
|  2  | [K진수에서 소수 갯수 구하기](src/programmers/practice/level2/KdecimalPrimeNumber.kt)     |
|  2  | [가장 큰 수](src/programmers/practice/level2/LargestNumber.kt)                    |
|  2  | [빛의 경로 싸이클](src/programmers/practice/level2/LightPathCycle.kt)                |
|  2  | [멀리뛰기](src/programmers/practice/level2/LongJump.kt)                           |
|  2  | [큰 수 만들기](src/programmers/practice/level2/MakingBigNumbers.kt)                |
|  2  | [행렬 테두리 회전하기](src/programmers/practice/level2/MatrixEdgeRotation.kt)          |
|  2  | [수식 최대화](src/programmers/practice/level2/MaximizeFormulas.kt)                 |
|  2  | [괄호 변환](src/programmers/practice/level2/ParenthesisConversion.kt)             |
|  2  | [주차 요금 계산](src/programmers/practice/level2/ParkingFeeCalculation.kt)          |
|  2  | [멀쩡한 사각형](src/programmers/practice/level2/PlainSquare.kt)                     |
|  2  | [프린터](src/programmers/practice/level2/Printer.kt)                             |
|  2  | [쿼드압축 후 개수 세기](src/programmers/practice/level2/QuadExpressionCounting.kt)     |
|  2  | [N개의 최소공배수](src/programmers/practice/level2/Nlcm.kt)                          |
|  2  | [2개 이하로 다른 비트](src/programmers/practice/level2/NoMoreThanTwoDifferentBits.kt) |
|  2  | [괄호 회전하기](src/programmers/practice/level2/RotateParentheses.kt)               |
|  2  | [문자열 압축](src/programmers/practice/level2/StringZip.kt)                        |
|  2  | [타겟 넘버](src/programmers/practice/level2/TargetNumber.kt)                      |
|  2  | [삼각 달팽이](src/programmers/practice/level2/TriangleSnail.kt)                    |
|  2  | [다리를 지나는 트럭](src/programmers/practice/level2/TrucksCrossingBridge.kt)         |
|  2  | [튜플](src/programmers/practice/level2/Tuple.kt)                                |
|  2  | [모음 사전](src/programmers/practice/level2/VowelDictionary.kt)                   |

</div>
</details>


<details>
<summary>&nbsp;Programmers: Level 3 (10)</summary>
<div markdown="1">
<br/>

|  레벨  | 제목                                                                | 유형  |
|:----:|-------------------------------------------------------------------|-----|
|  3   | [베스트 앨범](src/programmers/practice/level3/BestAlbum.kt)            |
|  3   | [섬 연결하기](src/programmers/practice/level3/ConnectingIslands.kt)    |
|  3   | [디스크 컨트롤러](src/programmers/practice/level3/DiskController.kt)     |
|  3   | [이중 우선순위 큐](src/programmers/practice/level3/DualPriorityQueue.kt) |
|  3   | [가장 먼 노드](src/programmers/practice/level3/FarthestNode.kt)        |
|  3   | [입국 심사](src/programmers/practice/level3/Immigration.kt)           |
|  3   | [네트워크](src/programmers/practice/level3/Network.kt)                |
|  3   | [양과 늑대](src/programmers/practice/level3/SheepAndWolf.kt)          |
|  3   | [여행 경로](src/programmers/practice/level3/TravelRoute.kt)           |
|  3   | [단어 변환](src/programmers/practice/level3/WordConversion.kt)        |

</div>
</details>


<details>
<summary>&nbsp;2019 KAKAO BLIND RECRUITMENT (7)</summary>
<div markdown="1">
<br/>

| 번호  | 제목                                                               |
|:---:|------------------------------------------------------------------|
|  1  | [실패율](src/programmers/kakao/y2019/KAKAO_BLIND_2019_P1.kt)        |
|  2  | [오픈채팅방](src/programmers/kakao/y2019/KAKAO_BLIND_2019_P2.kt)      |
|  3  | [후보키](src/programmers/kakao/y2019/KAKAO_BLIND_2019_P3.kt)        |
|  4  | [길 찾기 게임](src/programmers/kakao/y2019/KAKAO_BLIND_2019_P4.kt)    |
|  5  | [무지의 먹방 라이브](src/programmers/kakao/y2019/KAKAO_BLIND_2019_P5.kt) |
|  6  | [블록 게임](src/programmers/kakao/y2019/KAKAO_BLIND_2019_P6.kt)      |
|  7  | [매칭 점수](src/programmers/kakao/y2019/KAKAO_BLIND_2019_P7.kt)      |

</div>
</details>

<details>
<summary>&nbsp;2020 KAKAO BLIND RECRUITMENT (7)</summary>
<div markdown="1">
<br/>

| 번호  | 제목                                                             |
|:---:|----------------------------------------------------------------|
|  1  | [괄호 변환](src/programmers/kakao/y2020/KAKAO_BLIND_2020_P1.kt)    |
|  2  | [문자열 압축](src/programmers/kakao/y2020/KAKAO_BLIND_2020_P2.kt)   |
|  3  | [자물쇠와 열쇠](src/programmers/kakao/y2020/KAKAO_BLIND_2020_P3.kt)  |
|  4  | [기둥과 보 설치](src/programmers/kakao/y2020/KAKAO_BLIND_2020_P4.kt) |
|  5  | [외벽 점검](src/programmers/kakao/y2020/KAKAO_BLIND_2020_P5.kt)    |
|  6  | [가사 검색](src/programmers/kakao/y2020/KAKAO_BLIND_2020_P6.kt)    |
|  7  | [블록 이동하기](src/programmers/kakao/y2020/KAKAO_BLIND_2020_P7.kt)  |

</div>
</details>

<details>
<summary>&nbsp;2021 KAKAO BLIND RECRUITMENT (7)</summary>
<div markdown="1">
<br/>

| 번호  | 제목                                                              |
|:---:|-----------------------------------------------------------------|
|  1  | [신규 아이디 추천](src/programmers/kakao/y2021/KAKAO_BLIND_2021_P1.kt) |
|  2  | [메뉴 리뉴얼](src/programmers/kakao/y2021/KAKAO_BLIND_2021_P2.kt)    |
|  3  | [합승 택시 요금](src/programmers/kakao/y2021/KAKAO_BLIND_2021_P3.kt)  |
|  4  | [순위 검색](src/programmers/kakao/y2021/KAKAO_BLIND_2021_P4.kt)     |
|  5  | [광고 삽입](src/programmers/kakao/y2021/KAKAO_BLIND_2021_P5.kt)     |
|  6  | [카드 짝 맞추기](src/programmers/kakao/y2021/KAKAO_BLIND_2021_P6.kt)  |
|  7  | [매출 하락 최소화](src/programmers/kakao/y2021/KAKAO_BLIND_2021_P7.kt) |

</div>
</details>


<br/>

## 3. SW Expert Academy


<details>
<summary>&nbsp;Basic (50)</summary>
<div markdown="1">
<br/>

| 순서  | 제목                                                                                            | 유형           |
|:---:|-----------------------------------------------------------------------------------------------|--------------|
|  1  | [새로운 불면증 치료법](src/swExpertAcademy/basic/SWEA_P01.java)                                        | 비트 연산        |
|  2  | [이진수 표현](src/swExpertAcademy/basic/SWEA_P02.java)                                             | 비트 연산        |
|  3  | [동아리실 관리하기](src/swExpertAcademy/basic/SWEA_P03.java)                                          | 비트 연산        |
|  4  | [기초 Single Linked List 연습](src/swExpertAcademy/basic/SWEA_P04.java)                           | 연결 리스트       |
|  5  | [기초 Double Linked List 연습](src/swExpertAcademy/basic/SWEA_P05.java)                           | 연결 리스트       |
|  6  | [암호문3](src/swExpertAcademy/basic/SWEA_P05.java)                                               | 연결 리스트       |
|  7  | -                                                                                             |              |
|  8  | [수열 편집](src/swExpertAcademy/basic/SWEA_P08.java)                                              | 연결 리스트       |
|  9  | [중위 순회](src/swExpertAcademy/basic/SWEA_P09.java)                                              | 트리           |
| 10  | [사칙연산 유효성 검사](src/swExpertAcademy/basic/SWEA_P10.java)                                        | 트리           |
| 11  | [사칙연산](src/swExpertAcademy/basic/SWEA_P11.java)                                               | 트리           |
| 12  | [공통조상](src/swExpertAcademy/basic/SWEA_P12.java)                                               | 트리           |
| 13  | [Directory](src/swExpertAcademy/basic/SWEA_P13.java)                                          | 트리           |
| 14  | [기초 DFS 연습](src/swExpertAcademy/basic/SWEA_P14.java)                                          | 그래프          |
| 15  | [기초 BFS 연습](src/swExpertAcademy/basic/SWEA_P15.java)                                          | 그래프          |
| 16  | [프로세서 연결하기](src/swExpertAcademy/basic/SWEA_P16.java)                                          | 그래프          |
| 17  | [파핑파핑 지뢰찾기](src/swExpertAcademy/basic/SWEA_P17.java)                                          | 그래프          |
| 18  | [영준이의 진짜 BFS](src/swExpertAcademy/basic/SWEA_P18.java)                                        | 그래프          |
| 19  | [최장 공통 부분 수열](src/swExpertAcademy/basic/SWEA_P19.java)                                        | DP           |
| 20  | [0/1 Knapsack](src/swExpertAcademy/basic/SWEA_P20.java)                                       | DP           |
| 21  | [스팟마트](src/swExpertAcademy/basic/SWEA_P21.java)                                               | DP           |
| 22  | [쉬운 거스름돈](src/swExpertAcademy/basic/SWEA_P22.java)                                            | 그리디          |
| 23  | [자기 방으로 돌아가기](src/swExpertAcademy/basic/SWEA_P23.java)                                        | 그리디          |
| 24  | [최대 상금](src/swExpertAcademy/basic/SWEA_P24.java)                                              | 그리디          |
| 25  | [문자열 교집합](src/swExpertAcademy/basic/SWEA_P25.java)                                            | 해시           |
| 26  | [[Pro] 단어가 등장하는 횟수](src/swExpertAcademy/basic/SWEA_P26.java)                                  | 해시           |
| 27  | [은기의 아주 큰 그림](src/swExpertAcademy/basic/SWEA_P27.java)                                        | 해시           |
| 28  | [연락처 Database](src/swExpertAcademy/basic/SWEA_P28.java)                                       | 해시           |
| 29  | [메일 서버](src/swExpertAcademy/basic/SWEA_P29.java)                                              | 해시           |
| 30  | [문자열 암호화](src/swExpertAcademy/basic/SWEA_P30.java)                                            | 해시           |
| 31  | [기초 Partial Sort 연습](src/swExpertAcademy/basic/userSolution/SWEA_P31_UserSolution.java)       | 힙            |
| 32  | [힙](src/swExpertAcademy/basic/SWEA_P32.java)                                                  | 힙            |
| 33  | [보급로](src/swExpertAcademy/basic/SWEA_P33.java)                                                | 힙            |
| 34  | [중간값 구하기](src/swExpertAcademy/basic/userSolution/SWEA_P34_UserSolution.java)                  | 힙            |
| 35  | [수 만들기](src/swExpertAcademy/basic/userSolution/SWEA_P35_UserSolution.java)                    | 힙            |
| 36  | [Social Media](src/swExpertAcademy/basic/userSolution/SWEA_P36_UserSolution.java)             | 힙            |
| 37  | [염라대왕의 이름 정렬](src/swExpertAcademy/basic/userSolution/SWEA_P37_UserSolution.java)              | 분할정복         |
| 38  | [사탕 분배](src/swExpertAcademy/basic/userSolution/SWEA_P38_UserSolution.java)                    | 분할정복         |
| 39  | [[Pro] Inversion Counting](src/swExpertAcademy/basic/userSolution/SWEA_P39_UserSolution.java) | 분할정복         |
| 40  | [영어 공부](src/swExpertAcademy/basic/userSolution/SWEA_P40_UserSolution.java)                    | 이분 탐색        |
| 41  | [촛불 이벤트](src/swExpertAcademy/basic/userSolution/SWEA_P41_UserSolution.java)                   | 이분 탐색        |
| 42  | [사탕 가방](src/swExpertAcademy/basic/userSolution/SWEA_P42_UserSolution.java)                    | 이분 탐색        |
| 43  | [광고 시간 정하기](src/swExpertAcademy/basic/userSolution/SWEA_P43_UserSolution.java)                | 이분 탐색        |
| 44  | [3차원 농부](src/swExpertAcademy/basic/SWEA_P44.java)                                             | 이분 탐색        |
| 45  | [[Pro] 그래도 수명이 절반이 되어서는...](src/swExpertAcademy/basic/SWEA_P45.java)                          | 이분 탐색        |
| 46  | [K번째 접미어](src/swExpertAcademy/basic/SWEA_P46.java)                                            | 트라이          |
| 47  | [K번째 문자열](src/swExpertAcademy/basic/SWEA_P47.java)                                            | 트라이          |
| 48  | [단어 검색](src/swExpertAcademy/basic/userSolution/SWEA_P48_UserSolution.java)                    | 트라이          |
| 49  | [Segment Tree 연습 - 1](src/swExpertAcademy/basic/SWEA_P49.java)                                | Segment Tree |
| 50  | [Segment Tree 연습 - 2](src/swExpertAcademy/basic/SWEA_P50.java)                                | Segment Tree |

</div>
</details>


<details>
<summary>&nbsp;Professional (12)</summary>
<div markdown="1">
<br/>

| 순서  | 제목                                                                                | 키워드                                      |
|:---:|-----------------------------------------------------------------------------------|------------------------------------------|
|  1  | [[Pro] 병사 관리](src/swExpertAcademy/professional/SWEA_PRO_P01_UserSolution.java)    | 원형 더블 연결 리스트                             |
|  2  | [[Pro] 긴 사다리 게임](src/swExpertAcademy/professional/SWEA_PRO_P02_UserSolution.java) | 트리                                       |
|  3  | [[Pro] 메모리 시스템](src/swExpertAcademy/professional/SWEA_PRO_P03_UserSolution.java)  | 트리                                       |
|  4  | [[Pro] 계산 게임](src/swExpertAcademy/professional/SWEA_PRO_P04_UserSolution.java)    | Hash, Sliding Window                     |
|  5  | [[Pro] 섬 지키기](src/swExpertAcademy/professional/SWEA_PRO_P05_UserSolution.java)    | 배열, Hash, BFS, DP                        |
|  6  | [[Pro] 가게 관리](src/swExpertAcademy/professional/SWEA_PRO_P06_UserSolution.java)    | HashMap, AVL Tree                        |
|  7  | [[Pro] 우주자원개발](src/swExpertAcademy/professional/SWEA_PRO_P07_UserSolution.java)   | 트라이, 정렬                                  |
|  8  | [[Pro] 리스트 복사](src/swExpertAcademy/professional/SWEA_PRO_P08_UserSolution.java)   | Trie, HashMap, List, Reference, DeepCopy |
|  9  | [[Pro] 끝말잇기2](src/swExpertAcademy/professional/SWEA_PRO_P09_UserSolution.java)    | 해시 함수, AVL Tree, HashSet                 |
| 10  | [[Pro] AI 로봇](src/swExpertAcademy/professional/SWEA_PRO_P10_UserSolution.java)    | AVL Tree, 우선순위 큐                         |
| 11  | [[Pro] P2P 네트워크](src/swExpertAcademy/professional/SWEA_PRO_P11_UserSolution.java) | Trie, BFS, Graph                         |
| 12  | [[Pro] 순위표](src/swExpertAcademy/professional/SWEA_PRO_P12_UserSolution.java)      | 세그먼트 트리, 이분 탐색, AVL Tree                 |

</div>
</details>
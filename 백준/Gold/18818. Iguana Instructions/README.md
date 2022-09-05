# [Gold V] Iguana Instructions - 18818 

[문제 링크](https://www.acmicpc.net/problem/18818) 

### 성능 요약

메모리: 16236 KB, 시간: 148 ms

### 분류

너비 우선 탐색(bfs), 그래프 이론(graphs), 그래프 탐색(graph_traversal)

### 문제 설명

<p>Iggy the Iguana has found himself trapped in a corn maze! The corn maze can be modelled as a square grid where some of the cells are blocked off with impassable corn plants and others are cleared out. Iggy can only travel in and through cells that are cleared out. Iggy can move to a cell in any of the four cardinal directions (north, south, east, and west).</p>

<p>Iggy is not good at mazes and needs your help. He has asked you to write down a list of instructions to show him how to reach the end of the maze. Each instruction has the form <code><direction> <amount></code> where <code><direction></code> is either North, South, East, or West and <code><amount></code> is how many cells Iggy should travel in that direction. Because Iggy has a bad memory, he wants this list of instructions to be as short as possible even if that means he has to walk further.</p>

<p>Iggy starts in the top-left cell of the maze and needs to get to the bottom-right cell. It is guaranteed that there exists a path Iggy can take to reach the end.</p>

<p>What is the minimum number of instructions you can give Iggy so that he can reach the end of the maze?</p>

### 입력 

 <p>The first line contains n (2 ≤ n ≤ 100), which is the length of one side of the square grid representing the maze.</p>

<p>Following this is an n × n grid of characters. If a cell is cleared out, its corresponding character is a dot (<code>.</code>). If a cell is blocked off with corn plants, its corresponding character is a hash (<code>#</code>).</p>

### 출력 

 <p>Display the minimum number of instructions you can give Iggy such that he can reach the end of the maze.</p>


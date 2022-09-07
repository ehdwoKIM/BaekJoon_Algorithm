#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <string>
#include <random>
#include <map>
#include <set>

#pragma warning(disable: 4996)

using namespace std;

int indegree[1003];
map<int, int> resultCost;
map<int, int> cost;

int main() {
	int N, a, b, M, c, d, T, K, W;
	int x, y, node_cost;

	cin >> T;

	for (int i = 0; i < T; i++) {
		int answer = 0;

		cin >> N >> K;
		vector<vector<int>> v(N + 1);
		queue<int> q;

		for (int j = 1; j <= N; j++) {
			scanf("%d", &node_cost);
			cost[j] = node_cost;
			resultCost[j] = node_cost;
		}

		for (int j = 0; j < K; j++) {
			scanf("%d %d", &x, &y);
			v[x].emplace_back(y);
			indegree[y]++;
		}

		cin >> W;

		for (int j = 1; j <= N; j++) {
			if (j == W)
				continue;
			else if (indegree[j] == 0) {
				q.emplace(j);
			}
		}

		while (!q.empty()) {

			int idx = q.front();
			q.pop();

			for (int j = 0; j < v[idx].size(); j++) {
				int n = v[idx][j];

				resultCost[n] = max(resultCost[n], resultCost[idx] + cost[n]);
				if (--indegree[n] == 0)
					q.emplace(n);
			}
		}

		cout << resultCost[W] << endl;

		for (int j = 1; j <= N; j++) {
			indegree[j] = 0;
			resultCost[j] = 0;
			cost[j] = 0;
		}
	}

	return 0;
}
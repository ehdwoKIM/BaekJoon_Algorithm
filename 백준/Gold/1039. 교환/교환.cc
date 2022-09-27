#include <iostream>
#include <set>
#include <queue>
#include <algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	string N("");
	int K(0);
	set<string>visit;
	queue<pair<string, int>>q;

	cin >> N >> K;	

	q.push({ N,0 });
	visit.insert(N);

	int idx(0), idx2(0);
	string answer("");

	while (!q.empty()) {
		string num = q.front().first;
		int cnt = q.front().second;
		q.pop();
		for (idx = 0; idx < N.size(); ++idx) {
			for (idx2 = idx + 1; idx2 < N.size(); ++idx2) {
				string n_num = num;
				swap(n_num[idx], n_num[idx2]);
				auto is_visit = visit.insert(n_num);
				if (n_num[0] != '0') {
					if (cnt + 1 <= K) {
						if (is_visit.second)
							q.push({ n_num, cnt + 1 });
						if ((K - (cnt + 1)) % 2 == 0)
							answer = max(answer, n_num);
						else if (cnt + 1 == K)
							answer = max(answer, n_num);
						else if (cnt % 2 == 0)
							answer = max(answer, num);
					}
				}
			}
		}
	}
	if (answer != "") cout << answer;
	else cout << -1;
}
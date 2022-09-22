#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <math.h>
using namespace std;
int n, m, answer = -1;//n ==r m//c
vector<string> input;


//제곱수면 제곱수를 반환
int toSquare(int num) {
	int squareRoot = sqrt(num);
	if(squareRoot*squareRoot==num)
		return num;
	else 
		return - 1;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	cin >> n >> m; 

	for (int i = 0; i < n; i++) {
		string str;
		cin >> str;
		input.push_back(str);
	}
	//input[r][c]가 등차수열의 시작점
	for (int r = 0; r < n; r++) {
		for (int c = 0; c < m; c++) {
			//행의 공차와, 열의 공차를 구하는데, -인 경우도 구해야 한다.
			for (int dr = -n + 1; dr < n; dr++) {
				for (int dc = -m + 1; dc < m; dc++) {
					//행과 열의 공차가 모두 0이면 무한루프
					if (dr == 0 && dc == 0)
						continue;
					int a = r, b = c;
					string str="";
					//해당 행과 열의 공차에서 나오는 수열의 모든 값을 검사한다.
					while (a >= 0 && a < n && b >= 0 && b < m) {
						str += input[a][b];
						answer = max(answer,toSquare(stoi(str)));
						a += dr;
						b += dc;
					}
				}
			}
		}
	}
	//n과m이 1이면 반복문을 돌지 않기 때문에 따로 검사해 준다.
	if (n == 1 && m == 1) {
		cout <<toSquare(input[0][0]-'0');
	}
	else
	cout << answer;

}

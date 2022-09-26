#include <iostream>
#include <vector>
#include <set>
#include <cmath>
#include <climits>
#include <algorithm>

void solve(void);

int main(void)
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(NULL);

	int test_case;
	std::cin >> test_case;
	for (int t = 0; t < test_case; t++)
		solve();
}

double get_minimum(const int idx, const int count, std::pair<int, int> current, std::vector<std::pair<int, int>>& points)
{
	if (points.size() == idx)
	{
		if (count == points.size() / 2)
			return sqrt(pow(current.first, 2) + pow(current.second, 2));
		return LLONG_MAX;
	}

	std::pair<int, int>& temp = points[idx];
	
	if (points.size() / 2 == count)
		return get_minimum(idx + 1, count, std::make_pair(current.first - temp.first, current.second - temp.second), points);
	
	double return_a = get_minimum(idx + 1, count, std::make_pair(current.first - temp.first, current.second - temp.second), points);
	double return_b = get_minimum(idx + 1, count + 1, std::make_pair(current.first + temp.first, current.second + temp.second), points);

	return return_a < return_b ? return_a : return_b;
}

void solve(void)
{
	int n;
	std::cin >> n;

	std::vector<std::pair<int, int>> point;
	for (int i = 0; i < n; i++)
	{
		int x, y;
		std::cin >> x >> y;
		point.push_back(std::make_pair(x, y));
	}

	double minimum = LLONG_MAX;
	double m = get_minimum(0, 0, std::make_pair(0, 0), point);
	minimum = m < minimum ? m : minimum;

	std::cout << std::fixed;
	std::cout.precision(12);
	std::cout << minimum << '\n';
}

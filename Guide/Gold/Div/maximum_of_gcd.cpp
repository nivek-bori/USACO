/// TECH: Looping over a map, use this:
///     const auto& [key, val] : map
/// NOTE: When using const auto& [key, val] : iteraterable, the const locks the key and val so that they can't be changed. 
/// CONT: This means for maps, you have to use .at()

/// NOTE: The buffer stream in files are an object of the file class, meaning when the file class is deleted the streams are deleted.
/// CONT: If you want to keep the files within their redirect scope, make the file static inside its scope
///         if (TESTING) {
///             static ifstream in_file("System.in");
///             static ofstream out_file("System.out");

/// NOTE: For problems that are "for every subarray, eveny rectangle, every etc." find ways to describe the whole with parts. For this problem, size and gcd are all that are required for the problem - The rest are only used during computation of the gcd

/// NOTE: It is best to store the gcd of each subarray and have length be a characteristic that is maximized.
/// CONT: At each i (end point of all subarrays that have been started), for subarrays w/ gcd g only the largest one should be saved

/// TODO: Implementing gcd to length is left as an exercise for the reading

#include <fstream>
#include <iostream>
#include <vector>
#include <unordered_map>
#include <numeric>

bool TESTING = false;
using namespace std;

int main() {
	// Testing constructor
	ifstream in_file("System.in");
	ofstream out_file("System.out");

	if (TESTING) {
		if (in_file.is_open()){
			cin.rdbuf(in_file.rdbuf());
		} else {
			cerr << "IN FILE NOT FOUND" << endl;
		}

		if (out_file.is_open()) {
			cout.rdbuf(out_file.rdbuf());
		} else {
			cerr << "OUT FILE NOT FOUND" << endl;
		}
	}

	int T;
	cin >> T;

	for (int t = 0; t < T; t++ ) {
		int n;
		cin >> n;

		int largest_gcd[(n + 1)];
		fill(largest_gcd, largest_gcd + n + 1, 0);

		unordered_map<int, int> subarrays; // len to gcd

		for (int i = 0; i < n; i++) {
			int val;
			cin >> val;

			unordered_map<int, int> new_subarrays;
			for (const auto& [len, gcd_val] : subarrays) {
				new_subarrays[len + 1] = gcd(gcd_val, val);
				largest_gcd[len + 1] = max(new_subarrays[len + 1], largest_gcd[len + 1]);
			}

			new_subarrays[1] = val;
			largest_gcd[1] = max(val, largest_gcd[1]);

			subarrays = new_subarrays;
		}

		for (int i = 1; i < n + 1; i++) {
			cout << largest_gcd[i] << " ";
		}
		cout << endl;
	}

	return 0;
}

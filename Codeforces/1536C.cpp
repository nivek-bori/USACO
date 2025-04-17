/// Notes: For the gcd function, always use the Euclidian algorithm - std::__gcd() is a function that exists
/// for maps of pair to value, use map<keyA, map<keyB, value>>


#include <iostream>
#include <fstream>
#include <map>
#include <unordered_map>
#include <string>

using namespace std;
bool TESTING = false;


unordered_map<int, unordered_map<int, int>> ratio_gcd;

int gcd_rec(int a, int b) {
    if (a % b == 0) {
        return b;
    }

    return gcd_rec(b, a % b);
}

int gcd(int a, int b) {
    if (ratio_gcd[a][b] != 0) {
        return ratio_gcd[a][b];
    }

    if (a == 0 || b == 0) {
        return 1;
    }

    int gcd = gcd_rec(max(a, b), min(a, b));
    ratio_gcd[a][b] = ratio_gcd[b][a] = gcd;
    return gcd;
}

int main() {
    ifstream in_file("System.in");
    ofstream out_file("System.out");

    if (TESTING) {
        if (in_file.is_open()) {
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

    for (int t = 0; t < T; t++) {
        // if (TESTING) {cerr << "TEST CASE: " << t << endl;} // REMOVE

        unordered_map<int, unordered_map<int, int>> ratio_freq;
        int a_count = 0;
        int b_count = 0;

        int n;
        cin >> n;

        string line;
        cin >> line;

        for (int i = 0; i < n; i++) {
            // if (TESTING) {cerr << "INDEX: " << i << endl;} // REMOVE

            if (line[i] == 'D') {a_count++;}
            else {b_count++;}

            if (a_count == 0 || b_count == 0) {
                ratio_freq[0][0]++;

                cout << ratio_freq[0][0] << " ";
            } else {
                int gcd_val = gcd(a_count, b_count);
                ratio_freq[a_count / gcd_val][b_count / gcd_val]++;

                cout << ratio_freq[a_count / gcd_val][b_count / gcd_val] << " ";
            }
        }
        cout << endl;

        ratio_freq.clear();
    }

    ratio_gcd.clear();

    return 0;
}

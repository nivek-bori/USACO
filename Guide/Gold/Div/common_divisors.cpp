/// NOTE: Do not modify a vector or unordered_set while iterating over it
/// TECH: For vectors and unordered_set, a.insert(b.begin(), b.end()) adds on to the other
/// NOTE: Pruning is very important - try and avoid any computation that isn't required or does not contribute to the final answer

#include <vector>
#include <unordered_set>
#include <iostream>
#include <fstream>

bool TESTING = false;
using namespace std;

int MAX_VAL = 1e6 + 1;
vector<int> max_prime(MAX_VAL, 0);

int main() {
    // Prime factorization constructor
    for (int i = 2; i < MAX_VAL; i++) {
        if (max_prime[i] == 0) {
            for (int j = i; j < MAX_VAL; j += i) {
                max_prime[j] = i;
            }
        }
    }

    // Testing constructor
    ifstream in_file("System.in");
    ofstream out_file("System.out");

    if (TESTING) {
        if (in_file.is_open()) {cin.rdbuf(in_file.rdbuf());}
        else {cerr << "IN FILE NOT FOUND" << endl;}

        if (out_file.is_open()) {cout.rdbuf(out_file.rdbuf());}
        else {cerr << "OUT FILE NOT FOUND" << endl;}
    }

    // PROBLEM SOLUTION
    int n;
    cin >> n;

    // Divisors over all numbers
    unordered_set<int> all_divs;
    int largest_div = 0;
    
    for (int i = 0; i < n; i++) {
        // Divisors for each number
        unordered_set<int> divisors;
        divisors.insert(1);
        
        int num;
        cin >> num;

        // Prime factorize and apply prime to each previous divisor to build divisors set
        while (num != 1) {
            int p = max_prime[num];
            
            while (num % p == 0) {
                unordered_set<int> new_divisors;

                for (int d : divisors) {
                    if (num / p * d > largest_div) {new_divisors.insert(d);}
                    if (num * d > largest_div) {new_divisors.insert(p * d);}
                }
                divisors = new_divisors;
                
                num /= p;
            }
        }

        // Add divisors into all_divisors and update largest divisors
        for (int div : divisors) {
            if (all_divs.contains(div)) {
                largest_div = max(div, largest_div);
            } else if (div > largest_div) {
                all_divs.insert(div);
            }
        }
    }

    cout << largest_div << endl;
    
    return 0;
}

#include <vector>
#include <unordered_set>
#include <iostream>

using namespace std;

int MAX_VAL = 1e6 + 1;
vector<int> max_prime(MAX_VAL, 0);

int main() {
    for (int i = 2; i < MAX_VAL; i++) {
        if (max_prime[i] == 0) {
            for (int j = i; j < MAX_VAL; j += i) {
                max_prime[j] = i;
            }
        }
    }

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
                for (int d : divisors) {
                    divisors.insert(p * d);
                }
                num /= p;
            }
        }

        // Add divisors into all_divisors and update largest divisors
        // TODO: Optimize by sorting?
        for (int div : divisors) {
            if (all_div.contains(div) {
                largest_div = max(div, largest_div);
            }
        }
    }
    
    return 0;
}

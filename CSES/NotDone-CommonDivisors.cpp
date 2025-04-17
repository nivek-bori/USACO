#include <vector>
#include <unordered_set>
#include <iostream>
#include <cstdlib>

using namespace std;

int MAX_VAL = 1e7;
vector<int> max_prime(MAX_VAL, 0);

int main() {
    for (int i = 2; i < MAX_VAL; i++) {
        if (max_prime[i] == 0) {
            for (int j = i; j < MAX_VAL; j += i) {
                max_prime[j] = i;
            }
        }
    }
    
    for (int t = 0; t < static_cast<int>(numbers.size()); t++) {
        unordered_set<int> divisors;
        divisors.insert(1);
        
        int num = numbers[t];
        while (num != 1) {
            int p = max_prime[num];
            
            while (num % p == 0) {
                for (int d : divisors) {
                    divisors.insert(p * d);
                }
                num /= p;
            }
        }
        
        cout << endl;
    }
    
    return 0;
}

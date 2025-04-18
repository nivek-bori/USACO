/// Problem is Technocup2020EliminationRound2PowerProducts D. Power Productsm, 1225

/// TECH: the map compares items using the "<" which vectors support - allowing maps to get keys by value
/// NOTE: unordered_map compares items using hash ids and direct comparisons. Only primitive types (int, string, etc.) have built in hashing and compare functions
/// TECH: To allow unordered_map to get keys by value, define a hashing and direct comparision function and pass like this: unordered_map<type, n, hashing_function, comparison_function>

#include <vector>
#include <iostream>
#include <map>

using namespace std;
using pr = pair<int, int>;

int MAX_VAL = 1e5 + 1;
vector<int> max_prime(MAX_VAL);

int exponent;

vector<pr> pFactors(int num) {
   vector<pr> ret;
   
   while (num != 1) {
       int p = max_prime[num];
       int cnt = 0;

       while (num % p == 0) {
           num /= p;
           cnt++;
       }

       if (cnt % exponent != 0) {
           ret.push_back(pr(p, cnt % exponent));
       }
   }

   return ret;
}

vector<pr> inversePFactors(vector<pr> p_factors) {
   size_t size = p_factors.size();
   vector<pr> ret(size);

   for (int i = 0; i < size; i++) {
       ret[i].first = p_factors[i].first;
       ret[i].second = exponent - p_factors[i].second;
   }

   return ret;
}

int main() {
   for (int i = 2; i < MAX_VAL; i++) {
       if (max_prime[i] == 0) {
           for (int j = i; j < MAX_VAL; j += i) {max_prime[j] = i;}
       }
   }

   int n;
   cin >> n >> exponent;

   vector<vector<pr>> p_factors(n);
   map<vector<pr>, int> num_prime_factors;

   for (int i = 0; i < n; i++) {
       int num;
       cin >> num;

       p_factors[i] = pFactors(num);
       num_prime_factors[p_factors[i]]++;
   }

   long long num_pairs = 0;
   for (int i = 0; i < n; i++) {
       vector<pr> p_factor = p_factors[i];
       vector<pr> inverse_p_factor = inversePFactors(p_factor);

       if (p_factor == inverse_p_factor){
           num_pairs += num_prime_factors[inverse_p_factor] - 1;
       } else {
           num_pairs += num_prime_factors[inverse_p_factor];
       }
   }

   cout << num_pairs / 2 << endl;

   return 0;
}

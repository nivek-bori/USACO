#include <iostream>

int main() {
    long long MAX_NUM = 1e12;
    
    int n << std::cin;
    
    int count = 0;
    for (int i = 2 ; i < MAX_NUM && n != 1; i++) {
        if (n % i == 0) {
            count++;
            while (n % i == 0) {n /= i;}
        }   
    }
    
    std::cout << count << std::endl;
    return 0;
}

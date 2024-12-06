binary search cost range is: 0 - (timeC + timeM)
given a cost, binary search money on cookies
  save state with 1000 * c + m

int timeC = Input;
int timeM = Input;

int lo = 0;
int hi = timeC + timeM;

while (lo < hi) {
  int mid = (int) ((hi + lo + 1) / 2.0);

  if (try(mid)) {
    lo = mid + 1;
  } else {
    hi = mid;
  }
}

return (lo + hi + 1) / 2;

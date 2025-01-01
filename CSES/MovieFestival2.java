LinkedList<Movie> movies
Collections.sort(movies)

int t = 0
int cnt = 0
for movie : movies
if movie.start < t {continue}
t = movie.end
cnt++

out.println(cnt)
in.close()
out.close()

class Movie extends Comparable
int start
int end

function compareTo(Movie other)
return Integer.compare(end, other.end)

import java.util.*;

/*
Users can rate movies they have seen from 1 to 5. We want to use these ratings to make movie recommendations.

Ratings will be provided in the following format:
  [Member Name, Movie Name, Rating]

We consider two users to have similar taste in movies if they have both rated the same movie as 4 or 5.

A movie should be recommended to a user if:
- They haven't rated the movie
- A user with similar taste has rated the movie as 4 or 5

Example:
ratings = [
    ["Alice", "Frozen", "5"],
    ["Bob", "Mad Max", "5"],
    ["Charlie", "Lost In Translation", "4"],
    ["Charlie", "Inception", "4"],
    ["Bob", "All About Eve", "3"],
    ["Bob", "Lost In Translation", "5"],
    ["Dennis", "All About Eve", "5"],
    ["Dennis", "Mad Max", "4"],
    ["Charlie", "Topsy-Turvy", "2"],
    ["Dennis", "Topsy-Turvy", "4"],
    ["Alice", "Lost In Translation", "1"]
]

If we want to recommend a movie to Charlie, we would recommend "Mad Max" because:
- Charlie has not rated "Mad Max"
- Charlie and Bob have similar taste as they both rated "Lost in Translation" 4 or 5
- Bob rated "Mad Max" a 5

Write a function that takes the name of a user and a collection of ratings, and returns a collection of all movie recommendations that can be made for the given user.

All test cases:
recommendations("Charlie", ratings) => ["Mad Max"]
recommendations("Bob", ratings) => ["Inception", "Topsy-Turvy"]
recommendations("Dennis", ratings) => ["Lost In Translation"]
recommendations("Alice", ratings) => []

Complexity Variable:
R = number of ratings
M = number of movies
U = number of users

*/

public class MovieRecommendations {
    public static String[][] ratings = new String[][]{
            {"Alice", "Frozen", "5"},
            {"Bob", "Mad Max", "5"},
            {"Charlie", "Lost In Translation", "4"},
            {"Charlie", "Inception", "4"},
            {"Bob", "All About Eve", "3"},
            {"Bob", "Lost In Translation", "5"},
            {"Dennis", "All About Eve", "5"},
            {"Dennis", "Mad Max", "4"},
            {"Charlie", "Topsy-Turvy", "2"},
            {"Dennis", "Topsy-Turvy", "4"},
            {"Alice", "Lost In Translation", "1"}
    };

    public static void main(String[] args) {
        MovieRecommendations recommendations = new MovieRecommendations();
        List<String> result = recommendations.recommend("Charlie", ratings);
        for (String item : result) {
            System.out.println(item);
        }
    }

    public List<String> recommend(String name, String[][] ratings) {
        Map<String, Map<String, List<String>>> ratingIdx = new HashMap<>();
        Map<String, Map<String, List<String>>> invertIdx = new HashMap<>();

        for (int i = 0; i < ratings.length; i++) {
            String id = ratings[i][0];
            String movie = ratings[i][1];
            String rate = ratings[i][2];

            ratingIdx.computeIfAbsent(id, k -> new HashMap<>()).computeIfAbsent(rate, p -> new ArrayList<>()).add(movie);
            invertIdx.computeIfAbsent(movie, k -> new HashMap<>()).computeIfAbsent(rate, p -> new ArrayList<>()).add(id);
        }
        Set<String> watched = new HashSet<>();
        Set<String> popular = new HashSet<>();
        for (String key : ratingIdx.get(name).keySet()) {
            watched.addAll(ratingIdx.get(name).get(key));
            if (key.equals("5") || key.equals("4")) popular.addAll(ratingIdx.get(name).get(key));
        }
        Set<String> ids = new HashSet<>();
        for (String movie : popular) {
            if (invertIdx.get(movie).containsKey("5")) ids.addAll(invertIdx.get(movie).get("5"));
            if (invertIdx.get(movie).containsKey("4")) ids.addAll(invertIdx.get(movie).get("4"));

        }

        List<String> result = new ArrayList<>();
        for (String id : ids) {
            Set<String> movies = new HashSet<>();
            if (ratingIdx.get(id).containsKey("4")) movies.addAll(ratingIdx.get(id).get("4"));
            if (ratingIdx.get(id).containsKey("5")) movies.addAll(ratingIdx.get(id).get("5"));
            for (String movie : movies) {
                if (!watched.contains(movie)) result.add(movie);
            }
        }
        return result;

    }
}

package notex.android.blackcoder.com.jokeproviderjava;

import java.util.Random;

public class JokeProvider {
    private String[] jokesRepo = {
            "Q: What do computers eat for a snack? \n" +
                    "A: Microchips!",
            "The energizer bunny was arrested on a charge of battery",
            "Q: What computer sings the best? \n" +
                    "A: A Dell.",
            "There are 10 types of people in the world: those who understand binary, and those who don't.",
            "Why do they call it hyper text? \n" +
                    "Too much JAVA."
    };
    public String jokeResponse(){
//        Picks a Joke at Random
        int jokesInRepo = jokesRepo.length;
        Random r = new Random();
        int index = r.nextInt(jokesInRepo);
        return jokesRepo[index];
    }
}

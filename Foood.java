/**
 * A program to carry on conversations with a human user.
 * This version: 
 * <ul><li>
 *    Uses advanced search for keywords 
 * </li></ul> 
 *    
 * @author Laurie White
 * @version April 2012
 */
public class Foood
{
    /**
     * Get a default greeting
     * 
     * @return a greeting
     */
    public String getGreeting()
    {

        findKeyword("I'd like to eat", "eat", 0);
        findKeyword("I want food", "food", 0);
        findKeyword("I'm hungry.", "hungry", 0);
        findKeyword("Hello" ,"Hello", 0);

        return "Hello, what would you like to eat?";
    }

    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (statement.length() == 0)
        {
            response = "Say something, please.";
        }
        else if (findKeyword(statement, "no") >= 0)
        {
            response = "What else would you like to eat?";
        }
        else if (findKeyword(statement, "lunch") >= 0
        || findKeyword(statement, "dinner") >= 0
        || findKeyword(statement, "breakfast") >= 0
        || findKeyword(statement, "food") >= 0)
        {
            response = "What type of food would you like to eat?";
        }
        else if (findKeyword(statement, "burrito") >= 0)
        {
            response = "How about Chipotle?";
        }
        else if (findKeyword(statement, "taco") >= 0)
        {
            response = "How about Taco Bell?";
        }else if (findKeyword(statement, "waffle") >= 0
        || findKeyword(statement, "pancake") >= 0)
        {
            response = "How about IHOP?";
        }else if (findKeyword(statement, "chinese") >= 0)
        {
            response = "How about Panda Express?";
        }else if (findKeyword(statement, "burger") >= 0)
        {
            response = "How about The Habit?";
        }else if (findKeyword(statement, "sandwich") >= 0)
        {
            response = "How about Panera?";
        }
        
        else
        {
            response = getRandomResponse();
        }
        return response;
    }

    /**
     * Search for one word in phrase. The search is not case
     * sensitive. This method will check that the given goal
     * is not a substring of a longer string (so, for
     * example, "I know" does not contain "no").
     * 
     * @param statement
     *            the string to search
     * @param goal
     *            the string to search for
     * @param startPos
     *            the character of the string to begin the
     *            search at
     * @return the index of the first occurrence of goal in
     *         statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal,
    int startPos)
    {
        String phrase = statement.trim();
        // The only change to incorporate the startPos is in
        // the line below
        int psn = phrase.toLowerCase().indexOf(
                goal.toLowerCase(), startPos);

        // Refinement--make sure the goal isn't part of a
        // word
        while (psn >= 0)
        {
            // Find the string of length 1 before and after
            // the word
            String before = " ", after = " ";
            if (psn > 0)
            {
                before = phrase.substring(psn - 1, psn)
                .toLowerCase();
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(
                    psn + goal.length(),
                    psn + goal.length() + 1)
                .toLowerCase();
            }

            // If before and after aren't letters, we've
            // found the word
            if (((before.compareTo("a") < 0) || (before
                    .compareTo("z") > 0)) // before is not a
                // letter
            && ((after.compareTo("a") < 0) || (after
                    .compareTo("z") > 0)))
            {
                return psn;
            }

            // The last position didn't work, so let's find
            // the next, if there is one.
            psn = phrase.indexOf(goal.toLowerCase(),
                psn + 1);

        }

        return -1;
    }

    /**
     * Search for one word in phrase. The search is not case
     * sensitive. This method will check that the given goal
     * is not a substring of a longer string (so, for
     * example, "I know" does not contain "no"). The search
     * begins at the beginning of the string.
     * 
     * @param statement
     *            the string to search
     * @param goal
     *            the string to search for
     * @return the index of the first occurrence of goal in
     *         statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal)
    {
        return findKeyword(statement, goal, 0);
    }

    /**
     * Pick a default response to use if nothing else fits.
     * 
     * @return a non-committal string
     */
    private String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 4;
        double r = Math.random();
        int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
        String response = "";

        if (whichResponse == 0)
        {
            response = "I'm sorry, I don't recognize that, but how about Vitality Bowl?";
        }
        else if (whichResponse == 1)
        {
            response = "I'm sorry, I don't recognize that, but how about Wendy's?";
        }
        else if (whichResponse == 2)
        {
            response = "I'm sorry, I don't recognize that, but how about McDonald's?";
        }
        else if (whichResponse == 3)
        {
            response = "I'm sorry, I don't recognize that, but how about Urban Plates?";
        }

        return response;
    }

}

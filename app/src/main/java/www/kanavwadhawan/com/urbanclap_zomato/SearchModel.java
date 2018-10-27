package www.kanavwadhawan.com.urbanclap_zomato;


public class SearchModel
{
    private String results_start;

    private String results_found;

    private Restaurants[] restaurants;

    private String results_shown;

    public String getResults_start ()
    {
        return results_start;
    }

    public void setResults_start (String results_start)
    {
        this.results_start = results_start;
    }

    public String getResults_found ()
    {
        return results_found;
    }

    public void setResults_found (String results_found)
    {
        this.results_found = results_found;
    }

    public Restaurants[] getRestaurants ()
    {
        return restaurants;
    }

    public void setRestaurants (Restaurants[] restaurants)
    {
        this.restaurants = restaurants;
    }

    public String getResults_shown ()
    {
        return results_shown;
    }

    public void setResults_shown (String results_shown)
    {
        this.results_shown = results_shown;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results_start = "+results_start+", results_found = "+results_found+", restaurants = "+restaurants+", results_shown = "+results_shown+"]";
    }
}


